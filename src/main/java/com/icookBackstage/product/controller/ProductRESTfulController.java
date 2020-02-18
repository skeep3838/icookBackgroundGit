package com.icookBackstage.product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.icookBackstage._00_init.imgur.ImageResponse;
import com.icookBackstage._00_init.imgur.ImgurAPI;
import com.icookBackstage.model.ProductBean;
import com.icookBackstage.model.ProductTypeBean;
import com.icookBackstage.product.service.IProductService;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//@ResponseBody跟@Controller的複合Annotation
@RestController
public class ProductRESTfulController {
	IProductService service;

	// retrofit建立request基底實例
	static final ImgurAPI imgurApi = createImgurAPI();

	// 注入Service
	@Autowired
	public void setService(IProductService service) {
		this.service = service;
	}

	// 用RESTful回傳上下架{status}和頁數{page}對應的Json資料(Map型態)
	@GetMapping(value = "/products/{status}/{page}", produces = "application/json")
	public Map<String, Object> getProductsForPage(@PathVariable String status, @PathVariable Integer page) {
		System.out.println("==== getProductsForPage start====");
		// 建立必要變數:
		Boolean productStatus = null;
		Map<String, Object> json = new HashMap<>();
		String productPageJson = null;
		Integer allProductNumber;

		// 將status轉乘Boolean值(status = "true"時 productStatus = true, 否則為false)
		productStatus = Boolean.valueOf(status);

		// 將該頁的資料撈出來
		List<ProductBean> productPage = service.getProductOfPage(page, productStatus);
		System.out.println("==== productPage= " + productPage + " ====");

		// 該頁諾有資料, 將資料轉利用Gson套件換成Json格式
		if (productPage != null) {
			// 改寫Gson對@Expose的判斷, 並加入時間的格式:
			Gson gson = new GsonBuilder().addSerializationExclusionStrategy(new ExclusionStrategy() {
				@Override
				public boolean shouldSkipField(FieldAttributes fieldAttributes) {
					final Expose expose = fieldAttributes.getAnnotation(Expose.class);
					return expose != null && !expose.serialize();
				}

				@Override
				public boolean shouldSkipClass(Class<?> aClass) {
					return false;
				}
			}).addDeserializationExclusionStrategy(new ExclusionStrategy() {
				@Override
				public boolean shouldSkipField(FieldAttributes fieldAttributes) {
					final Expose expose = fieldAttributes.getAnnotation(Expose.class);
					return expose != null && !expose.deserialize();
				}

				@Override
				public boolean shouldSkipClass(Class<?> aClass) {
					return false;
				}
			}).setDateFormat("yyyy-MM-dd").create();

			// 將productPage轉成Json格式的String字串
			productPageJson = gson.toJson(productPage);
			System.out.println("gson.toJson(productPage)= " + productPageJson);
		}

		// 取得商品總數
		System.out.println("==== Start getAllProductNumber ====");
		allProductNumber = service.getAllProductNumber(productStatus);
		System.out.println("===== allProductNumber= " + allProductNumber + " =====");

		// 建立Json內容(Map型態)
		json.put("productPageJson", productPageJson);
		json.put("page", page);
		json.put("status", status);
		json.put("allProductNumber", allProductNumber);

		return json;

	}

	// 更新單筆商品資料
	@PostMapping(value = "/updateProduct/{id}", produces = "application/json")
	public Map<String, Object> updateOneProduct(@PathVariable Integer id,
			@RequestParam("image1") MultipartFile[] image1,
//			@RequestParam("imageStatrs") Integer[] imageStatrs, 
			Model model, HttpServletRequest req) {
		System.out.println("===== Start updateProduct/{id} =====");

//		System.out.println("====== imageStatrs= " + imageStatrs + " ======");
		// 回復OK訊息
		Map<String, Object> json = new HashMap<>();
		json.put("statuse", "OK");

		// 設定Bean參數
		String productName = req.getParameter("productName");
		String[] typeTitles = req.getParameterValues("typeTitle");
		String[] typeUnitprices = req.getParameterValues("unitPrice");
		String[] typeUnitStocks = req.getParameterValues("unitStock");
		String[] typeDiscount = req.getParameterValues("discount");
		String[] typeUnitOrder = req.getParameterValues("unitOrder");
		Set<ProductTypeBean> typesLink = new LinkedHashSet<>();
		ProductTypeBean tempPTB;

		// 設定寫入圖片參數
		int count = 0;
		String imgLink = "";
		String allImg = "";

		// 取出舊的image1
		ProductBean oldProduct = service.getProduct(id);
		String[] oldProductImage1 = oldProduct.getImage1().split(",");

		// 將圖片寫入雲端(本機)

		// 測試存雲端
		if (image1 != null) {
			try {
				for (MultipartFile image : image1) {
					System.out.println("=== image.isEmpty()" + count + "= " + image.isEmpty() + " ===");
					if (image.isEmpty() == false) {
						RequestBody request = RequestBody.create(MediaType.parse("image/*"), image.getBytes());
						Call<ImageResponse> call = imgurApi.postImage(request);
						Response<ImageResponse> res = call.execute();

						System.out.println("imgur上傳是否成功: " + res.isSuccessful());

						// 判斷與imgur的連線是否有異常
						if (res.isSuccessful()) {
							imgLink = res.body().data.link;
						} else {
							// 塞找不到圖片的的圖檔
							imgLink = "https://imgur.com/fUNhU08.jpg";
						}

						if (count == 0) {
							allImg += imgLink;
						} else {
							allImg += "," + imgLink;
						}
					} else {
						if (count == 0 && oldProductImage1.length > count) {
							allImg += oldProductImage1[count];
						} else if (oldProductImage1.length > count) {
							allImg += "," + oldProductImage1[count];
						}
					}

					++count;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// ProductBean填入設定值
		ProductBean prodBean = new ProductBean(id, productName, true, req.getParameter("category"),
				req.getParameter("productInfo"), allImg, (new Date()), typesLink);

		// ProductTypeBean填入設定值
		int typeNum = typeTitles.length;
		for (int i = 0; i < typeNum; ++i) {
			tempPTB = new ProductTypeBean(null, (i + 1), typeTitles[i], Integer.parseInt(typeUnitprices[i]),
					Integer.parseInt(typeUnitStocks[i]), Integer.parseInt(typeUnitOrder[i]),
					Float.parseFloat(typeDiscount[i]));
			tempPTB.setProducts(prodBean);
			typesLink.add(tempPTB);
		}

		// 呼叫DAO 刪除產品型別
//		service.deleteProductType(id);

		// 呼叫DAO更新DB的資料
		System.out.println("===== prodBean: " + prodBean + " =====");
		service.updateProduct(prodBean);

		return json;
	}

	// 上下架商品
	@GetMapping(value = "/changeStatus/{status}/{id}", produces = "application/json")
	public Map<String, Object> changeStatusaty(@PathVariable String status, @PathVariable Integer id) {
		System.out.println("==== changeStatus/{id} start====");

		// 建立必要參數
		Map<String, Object> json = new HashMap<>();
		Integer changeStatus = 1;
		Boolean result;

		if (status.equals("true")) {
			changeStatus = 0;
		}

		result = service.changeProductStr(id, changeStatus);

		// 確認更新結果
		if (result) {
			json.put("status", "OK");
		} else {
			json.put("status", "false");
		}
		return json;
	}

	// 用RESTful回傳上下架{status}和搜尋id{searchInt}和頁數{page}對應的Json資料(Map型態)
	@GetMapping(value = "/produSearch/{status}/{searchInt}/{page}", produces = "application/json")
	public Map<String, Object> getProduSearchForPage(@PathVariable String status, @PathVariable Integer searchInt,
			@PathVariable Integer page) {
		System.out.println("==== getProduSearchForPage start====");
		
		// 建立必要變數:
		Boolean productStatus = null;
		Map<String, Object> json = new HashMap<>();
		List<ProductBean> productPage = null;
		String productPageJson = null;
		Integer allProductNumber = 0;

		// 將status轉乘Boolean值(status = "true"時 productStatus = true, 否則為false)
		productStatus = Boolean.valueOf(status);

		// 搜尋的資料撈出來
		ProductBean searchProduct = service.getOneProduct(searchInt);
		
		//判斷是否有搜尋到該id, 沒搜尋到就給null
		if(searchProduct != null) {
			//判斷上下架狀態
			System.out.println("=== 判斷上下架狀態:"+productStatus == searchProduct.getItemStatus()+" ===");
			if(productStatus == searchProduct.getItemStatus()) {
				productPage = new ArrayList<>();
				productPage.add(searchProduct);
			}
			// 商品總數
			allProductNumber = 1;
		}

		System.out.println("==== productPage= " + productPage + " ====");

		// 該頁諾有資料, 將資料轉利用Gson套件換成Json格式
		if (productPage != null) {
			// 改寫Gson對@Expose的判斷, 並加入時間的格式:
			Gson gson = new GsonBuilder().addSerializationExclusionStrategy(new ExclusionStrategy() {
				@Override
				public boolean shouldSkipField(FieldAttributes fieldAttributes) {
					final Expose expose = fieldAttributes.getAnnotation(Expose.class);
					return expose != null && !expose.serialize();
				}

				@Override
				public boolean shouldSkipClass(Class<?> aClass) {
					return false;
				}
			}).addDeserializationExclusionStrategy(new ExclusionStrategy() {
				@Override
				public boolean shouldSkipField(FieldAttributes fieldAttributes) {
					final Expose expose = fieldAttributes.getAnnotation(Expose.class);
					return expose != null && !expose.deserialize();
				}

				@Override
				public boolean shouldSkipClass(Class<?> aClass) {
					return false;
				}
			}).setDateFormat("yyyy-MM-dd").create();

			// 將productPage轉成Json格式的String字串
			productPageJson = gson.toJson(productPage);
			System.out.println("gson.toJson(productPage)= " + productPageJson);
		}

		// 建立Json內容(Map型態)
		json.put("productPageJson", productPageJson);
		json.put("page", page);
		json.put("status", status);
		json.put("allProductNumber", allProductNumber);

		return json;

	}

	// 建立對ImgurAPI的request方法
	static ImgurAPI createImgurAPI() {
		Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
				.baseUrl(ImgurAPI.SERVER).build();
		return retrofit.create(ImgurAPI.class);
	}

}
