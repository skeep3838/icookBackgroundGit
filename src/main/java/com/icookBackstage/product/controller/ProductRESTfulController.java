package com.icookBackstage.product.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import com.icookBackstage.model.ProductBean;
import com.icookBackstage.model.ProductTypeBean;
import com.icookBackstage.product.service.IProductService;

//@ResponseBody跟@Controller的複合Annotation
@RestController
public class ProductRESTfulController {
	IProductService service;

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
			}).setDateFormat("yyyy-MM-dd HH:mm:ss").create();

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
		InputStream inStream;
		OutputStream outStream;
		String fileName = "";
		String allImg = "";
		//路徑暫時無法用技術克服, 要自行更改
		String imgAddress = "E:/GitWorkspace/icookBackgroundGit/src/main/webapp/WEB-INF/views/images/";
		File imgAddressMacker = new File(imgAddress);
		byte[] buf = new byte[1024];
		int data;
		
		//取出舊的image1
		ProductBean oldProduct = service.getProduct(id);
		String[] oldProductImage1 = oldProduct.getImage1().split(",");
		
//		// 將圖片寫入雲端(本機)
		if (imgAddressMacker.exists() == false) {
			imgAddressMacker.mkdirs();
		}
		
		System.out.println("image1= " +image1);
		if (image1 != null) {
			try {
				//固定更新三張圖片
				for(int i = 0 ; i<=2 ; ++i) {
					//判斷該張圖片是否有更新
					if(image1[i].isEmpty() == false) {
						inStream = image1[i].getInputStream();
						System.out.println("==== inStream["+i+"]" + inStream + " ====");
						fileName = productName + (i+1) + ".jpg";
						outStream = new FileOutputStream(imgAddress + fileName);
						while ((data = inStream.read(buf)) != -1) {
							outStream.write(buf, 0, data);
						}
						inStream.close();
						outStream.close();
						if (i == 0) {
							allImg += "images/" + fileName;
						} else {
							allImg += "," + "images/" + fileName;
						}
					}else {
						if (i == 0) {
							allImg += oldProductImage1[i];
						} else {
							allImg += "," + oldProductImage1[i];
						}
						
					}
				}
				
//				for (MultipartFile image : image1) {
//					System.out.println("Part name=" + image.getName());
//					++count;
//					inStream = image.getInputStream();
//					fileName = productName + count + ".jpg";
//					outStream = new FileOutputStream(imgAddress + fileName);
//
//					while ((data = inStream.read(buf)) != -1) {
//						outStream.write(buf, 0, data);
//					}
//					inStream.close();
//					outStream.close();
//					if (count == 1) {
//						allImg += imgAddress + fileName;
//					} else {
//						allImg += "," + imgAddress + fileName;
//					}

//				}
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
		
		System.out.println(prodBean);
		
		//呼叫DAO 刪除產品型別
		System.out.println("--------Delete Start--------");
		service.deleteProductType(id);
		System.out.println("--------Delete End--------");		
		
		//呼叫DAO更新DB的資料
		System.out.println("--------InsertStart--------");
		service.updateProduct(prodBean);
		System.out.println("--------InsertEnd--------");

		ProductBean productAfter = service.getProduct(id);
		System.out.println("productAfter(id=" + id + ")= " + productAfter);
		
		System.out.println("===== End updateProduct/{id} =====");

		return json;
	}


}
