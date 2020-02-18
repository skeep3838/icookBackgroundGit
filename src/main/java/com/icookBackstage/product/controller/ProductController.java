package com.icookBackstage.product.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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

@Controller
public class ProductController {
	IProductService service;

	// retrofit建立request基底實例
	static final ImgurAPI imgurApi = createImgurAPI();

	// 注入Service
	@Autowired
	public void setService(IProductService service) {
		this.service = service;
	}

	// 轉跳商品管理頁面
	@RequestMapping("/demoMyProduct.page")
	public String changeToMyProduct() {
		return "demoMyProduct";
	}

	// 跳轉新增商品頁面
	@RequestMapping("/demoNewProduct.page")
	public String changeTonProduct() {
		return "demoNewProduct";
	}

	// 新增產品的資訊
	@RequestMapping("/createNewProduct")
	public String createNewProduct(Model model, HttpServletRequest req,
			@RequestParam("image1") MultipartFile[] image1) {

		// 設定Bean參數
		String productName = req.getParameter("productName");
		String[] typeTitles = req.getParameterValues("typeTitle");
		String[] typeUnitprices = req.getParameterValues("unitPrice");
		String[] typeUnitStocks = req.getParameterValues("unitStock");
		Set<ProductTypeBean> typesLink = new LinkedHashSet<>();
		ProductTypeBean tempPTB;

		// 設定寫入圖片參數
		int count = 0;
		String imgLink = "";
		String allImg = "";

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
					}
					++count;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// ProductBean填入設定值
		ProductBean prodBean = new ProductBean(null, productName, true, req.getParameter("category"),
				req.getParameter("productInfo"), allImg, (new Date()), typesLink);

		// ProductTypeBean填入設定值
		int typeNum = typeTitles.length;
		for (int i = 0; i < typeNum; ++i) {
			tempPTB = new ProductTypeBean(null, (i + 1), typeTitles[i], Integer.parseInt(typeUnitprices[i]),
					Integer.parseInt(typeUnitStocks[i]), 0, 1.0f);
			tempPTB.setProducts(prodBean);
			typesLink.add(tempPTB);
		}
		// 呼叫DAO方法將資料寫入DB
		System.out.println("--------InsertStart--------");
		service.insertProduct(prodBean);
		System.out.println("--------InsertEnd--------");

		// 轉跳到販賣網站
		return "redirect://demoMyProduct.page";
	}

	// 建立對ImgurAPI的request方法
	static ImgurAPI createImgurAPI() {
		Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
				.baseUrl(ImgurAPI.SERVER).build();
		return retrofit.create(ImgurAPI.class);
	}

	// 測試用: 抓固定單筆資料
	@RequestMapping("productGet")
	public String getProduct(Model model) {
		int beanPk = 1;
		ProductBean pbn = service.getProduct(beanPk);
		System.out.println(pbn);
		return "getProductPage";
	}

	// 測試用: 更新多對一資料
	@RequestMapping("updateProductTest")
	public void updateProductTest(Model model) {
		System.out.println("===== Start updateProductTest =====");

		// 取出ProductId=10的資料(測試3)
		ProductBean product = service.getProduct(10);
		System.out.println("product(id=10)= " + product);

		ProductTypeBean addOne = new ProductTypeBean(null, 2, "測試123", 999, 999, 999, 1.0f);
		// 加入ProductBean
		addOne.setProducts(product);

		Set<ProductTypeBean> temp = product.getType();
		temp.add(addOne);
		System.out.println("temp = " + temp);
		product.setType(temp);
		System.out.println("after: product(id=10)= " + product);
		service.updateProduct(product);

		ProductBean productAfter = service.getProduct(10);
		System.out.println("productAfter(id=10)= " + productAfter);

		System.out.println("===== End updateProductTest =====");

	}

	// 測試用: 更新多對一資料
	@RequestMapping("updateProductTest2")
	public void updateProductTest2(Model model) {
		System.out.println("===== Start updateProductTest2 =====");

		// 取出ProductId=10的資料(測試3)
		ProductBean product = service.getProduct(10);
		System.out.println("product(id=10)= " + product);

		ProductTypeBean newProductType = new ProductTypeBean(null, 22, "新的資料", 222, 222, 222, 2.0f);

		// 指向主Bean:ProductBean
		newProductType.setProducts(product);

		Set<ProductTypeBean> temp = new LinkedHashSet();
		temp.add(newProductType);
		System.out.println("temp = " + temp);
		product.setType(temp);
		System.out.println("after: product(id=10)= " + product);
		service.updateProduct(product);

		ProductBean productAfter = service.getProduct(10);
		System.out.println("productAfter(id=10)= " + productAfter);

		System.out.println("===== End updateProductTest =====");

	}

}
