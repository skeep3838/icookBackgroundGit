package com.icookBackstage.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.icookBackstage.model.ProductBean;
import com.icookBackstage.product.service.IProductService;

//@ResponseBody跟@Controller的複合Annotation
@RestController
public class ProductRESTfulController {
	IProductService service;
	
	//注入Service
	@Autowired
	public void setService(IProductService service) {
		this.service = service;
	}
	
	//用RESTful回傳上下架{status}和頁數{page}對應的Json資料(Map型態)
	@GetMapping(value="/products/{status}/{page}",produces="application/json")
	public Map<String , Object> getProductsForPage(	@PathVariable String status,
													@PathVariable Integer page){
		System.out.println("==== getProductsForPage start====");
		//建立必要變數:
		Boolean productStatus = null;
		Map<String, Object> json = new HashMap<>();
		String productPageJson = null;
		Integer allProductNumber;
		
		//將status轉乘Boolean值(status = "true"時 productStatus = true, 否則為false)
		productStatus = Boolean.valueOf(status);
		
		//將該頁的資料撈出來
		List<ProductBean> productPage = service.getProductOfPage(page, productStatus);
		System.out.println("==== productPage= " + productPage + " ====");
		
		//該頁諾有資料, 將資料轉利用Gson套件換成Json格式
		if(productPage != null) {
			//改寫Gson對@Expose的判斷, 並加入時間的格式:
			//@Override功能
			Gson gson = new GsonBuilder()
			        .addSerializationExclusionStrategy(new ExclusionStrategy() {
			            @Override
			            public boolean shouldSkipField(FieldAttributes fieldAttributes) {
			                final Expose expose = fieldAttributes.getAnnotation(Expose.class);
			                return expose != null && !expose.serialize();
			            }
			            @Override
			            public boolean shouldSkipClass(Class<?> aClass) {
			                return false;
			            }
			        })
			        .addDeserializationExclusionStrategy(new ExclusionStrategy() {
			            @Override
			            public boolean shouldSkipField(FieldAttributes fieldAttributes) {
			                final Expose expose = fieldAttributes.getAnnotation(Expose.class);
			                return expose != null && !expose.deserialize();
			            }
	
			            @Override
			            public boolean shouldSkipClass(Class<?> aClass) {
			                return false;
			            }
			        })
			        .setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			
			//將productPage轉成Json格式的String字串
			//@Expose 轉gson-----------------------------
			productPageJson = gson.toJson(productPage);
			System.out.println("gson.toJson(productPage)= "+ productPageJson);
		}
		
		//取得商品總數
		System.out.println("==== Start getAllProductNumber ====");
		allProductNumber = service.getAllProductNumber(productStatus);
		System.out.println("===== allProductNumber= " + allProductNumber + " =====");
		
		//建立Json內容(Map型態)
		json.put("productPageJson", productPageJson);
		json.put("page", page);
		json.put("status", status);
		json.put("allProductNumber", allProductNumber);

		return json;
		
	}

}
