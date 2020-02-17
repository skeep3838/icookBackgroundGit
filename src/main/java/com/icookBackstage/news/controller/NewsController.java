package com.icookBackstage.news.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.icookBackstage.model.Manageral;
import com.icookBackstage.model.MemberBean;
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

	// 更新公告資訊
	@ResponseBody
	@PostMapping(value = "/updateNews/{id}", produces = "application/json")
	public Map<String, Object> updateNews(@PathVariable Integer id, @ModelAttribute NewsBean news, Model model,
			HttpServletRequest req) {
		System.out.println("=== updateNews start ===");
		// 建立必要參數
		Boolean result = null;
		Map<String, Object> json = new HashMap<>();

		// 設定newsId回news
		news.setNewsId(id);

		// 設定更新時間
		news.setCreateTime(service.getOneNews(id).getCreateTime());
		news.setUpdateTime(new Date());

		System.out.println("=== news:" + news + " ===");

		// 更新user account
		result = service.updateNews(news);

		// 確認更新結果
		if (result == false) {
			json.put("status", "false");
		} else {
			json.put("status", "OK");
		}
		return json;
	}

	// 新增使用者帳戶資訊
	@ResponseBody
	@PostMapping(value = "/insertNews", produces = "application/json")
	public Map<String, Object> insertNews(@ModelAttribute NewsBean news, Model model, HttpServletRequest req) {

		System.out.println("=== insertNews start ===");
		// 建立必要參數
		Boolean result = null;
		Map<String, Object> json = new HashMap<>();

		// 設定時間
		news.setCreateTime(new Date());
		news.setUpdateTime(new Date());

		result = service.insertOneNews(news);

		// 確認更新結果
		if (result) {
			json.put("status", "OK");
		} else {
			json.put("status", "false");
		}
		return json;
	}

	// 新增使用者帳戶資訊
	@ResponseBody
	@GetMapping(value = "/deleteNews/{id}", produces = "application/json")
	public Map<String, Object> deleteNews(@PathVariable Integer id, Model model, HttpServletRequest req) {
		System.out.println("=== deleteNews start ===");
		// 建立必要參數
		Boolean result = null;
		Map<String, Object> json = new HashMap<>();

		result = service.deleteOneNews(id);

		// 確認更新結果
		if (result) {
			json.put("status", "OK");
		} else {
			json.put("status", "false");
		}
		return json;
	}

	// 用RESTful回傳id{searchInt}對應的公告資料(Json資料)
	@ResponseBody
	@GetMapping(value = "/newsSearch/{searchInt}", produces = "application/json")
	public Map<String, Object> getNewsSearchForPage(@PathVariable Integer searchInt) {
		// 建立必要變數:
		Map<String, Object> json = new HashMap<>();
		String newsPageJson = null;
		Integer allNewsNumber = 0;
		List<NewsBean> newsPage = null;

		// 將該頁的資料撈出來
		NewsBean searchNews = service.getOneNews(searchInt);

		// 判斷是否有搜尋到該id, 沒搜尋到就給null
		if (searchNews != null) {
			newsPage = new ArrayList<>();
			newsPage.add(searchNews);
			// 取得商品總數
			allNewsNumber = 1;
		}

		// 該頁諾有資料, 將資料轉利用Gson套件換成Json格式
		if (newsPage != null) {
			// 建立Gson
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

			// 將productPage轉成Json格式的String字串
			newsPageJson = gson.toJson(newsPage);
			System.out.println("gson.toJson(productPage)= " + newsPageJson);
		}

		// 建立Json內容(Map型態)
		json.put("newsPageJson", newsPageJson);
		json.put("page", 1);
		json.put("allNewsNumber", allNewsNumber);

		return json;
	}

}
