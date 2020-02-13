package com.icookBackstage.news.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icookBackstage.model.NewsBean;
import com.icookBackstage.news.service.INewsService;

@Controller
public class NewsController {
	INewsService service;

	// 注入Service
	@Autowired
	public void setService(INewsService service) {
		this.service = service;
	}

	// 轉跳公告管理
	@RequestMapping("/news.page")
	public String changeToUserAccountManag() {
		return "news";
	}
	
	// 用RESTful回傳頁數{page}對應的公告資料(Json資料)
		@ResponseBody
		@GetMapping(value = "/news/{page}", produces = "application/json")
		public Map<String, Object> getNewsForPage(@PathVariable Integer page) {
			
			// 建立必要變數:
			Map<String, Object> json = new HashMap<>();
			String newsPageJson = null;
			Integer allNewsNumber;
			
			// 將該頁的資料撈出來
			List<NewsBean> newsPage = service.getNewsOfPage(page);
			System.out.println("==== accountPage= " + newsPage + " ====");

			// 該頁諾有資料, 將資料轉利用Gson套件換成Json格式
			if (newsPage != null) {
				// 建立Gson
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

				// 將productPage轉成Json格式的String字串
				newsPageJson = gson.toJson(newsPage);
				System.out.println("gson.toJson(productPage)= " + newsPageJson);
			}

			// 取得商品總數
			System.out.println("==== Start getAllProductNumber ====");
			allNewsNumber = service.getNewsNumber();
			System.out.println("===== allProductNumber= " + allNewsNumber + " =====");

			// 建立Json內容(Map型態)
			json.put("newsPageJson", newsPageJson);
			json.put("page", page);
			json.put("allNewsNumber", allNewsNumber);

			
			
			return json;
		}


}
