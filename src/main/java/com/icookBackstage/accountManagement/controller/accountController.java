package com.icookBackstage.accountManagement.controller;

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
import com.icookBackstage.accountManagement.service.IAccountService;
import com.icookBackstage.model.Manageral;
import com.icookBackstage.model.MemberBean;

@Controller
public class accountController {
	IAccountService service;

	// 注入Service
	@Autowired
	public void setService(IAccountService service) {
		this.service = service;
	}

	// 轉跳使用者帳號管理
	@RequestMapping("/userAccountManag.page")
	public String changeToUserAccountManag() {
		return "userAccountManag";
	}

	// 跳轉管理員管理
	@RequestMapping("/managerialManag.page")
	public String changeToManagerialManag() {
		return "managerialManag";
	}

	// 用RESTful回傳頁數{page}對應的使用者帳戶資料(Json資料)
	@ResponseBody
	@GetMapping(value = "/userAccount/{page}", produces = "application/json")
	public Map<String, Object> getUserAccountForPage(@PathVariable Integer page) {

		System.out.println("==== getProductsForPage start====");
		// 建立必要變數:
		Map<String, Object> json = new HashMap<>();
		String userAccountPageJson = null;
		Integer allAccountNumber;

		// 將該頁的資料撈出來
		List<MemberBean> accountPage = service.getUserAccountOfPage(page);
		System.out.println("==== accountPage= " + accountPage + " ====");

		// 該頁諾有資料, 將資料轉利用Gson套件換成Json格式
		if (accountPage != null) {
			// 建立Gson
			Gson gson = new Gson();

			// 將productPage轉成Json格式的String字串
			userAccountPageJson = gson.toJson(accountPage);
			System.out.println("gson.toJson(productPage)= " + userAccountPageJson);
		}

		// 取得商品總數
		System.out.println("==== Start getAllProductNumber ====");
		allAccountNumber = service.getAllAccountNumber();
		System.out.println("===== allProductNumber= " + allAccountNumber + " =====");

		// 建立Json內容(Map型態)
		json.put("userAccountPageJson", userAccountPageJson);
		json.put("page", page);
		json.put("allAccountNumber", allAccountNumber);

		return json;
	}

	// 更新使用者帳戶資訊
	@ResponseBody
	@PostMapping(value = "/updateUserAccount/{id}", produces = "application/json")
	public Map<String, Object> updateUserAccount(@PathVariable Integer id, @ModelAttribute MemberBean member,
			Model model, HttpServletRequest req) {
		System.out.println("=== member:" + member + " ===");
		// 建立必要參數
		Boolean result = null;
		Map<String, Object> json = new HashMap<>();

		// 設定userId回member
		member.setUserId(id);

		// 確認是否有需要更換密碼, 密碼欄位為空的就塞回舊的密碼
		if (member.getPassword().isEmpty()) {
			member.setPassword(service.getOneUserAccount(id).getPassword());
		}

		// 更新user account
		result = service.updateUserAccount(member);

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
	@PostMapping(value = "/insertUserAccount", produces = "application/json")
	public Map<String, Object> insertUserAccount(@ModelAttribute MemberBean member, Model model,
			HttpServletRequest req) {

		System.out.println("=== insertMem:" + member + " ===");
		// 建立必要參數
		Boolean result = null;
		Map<String, Object> json = new HashMap<>();

		result = service.insertOneUserAcc(member);

		// 確認更新結果
		if (result) {
			json.put("status", "OK");
		} else {
			json.put("status", "false");
		}
		return json;
	}

	// 用RESTful回傳頁數{page}對應的管理員帳戶資料(Json資料)
	@ResponseBody
	@GetMapping(value = "/managerial/{page}", produces = "application/json")
	public Map<String, Object> getManagerialForPage(@PathVariable Integer page) {

		System.out.println("==== getProductsForPage start====");
		// 建立必要變數:
		Map<String, Object> json = new HashMap<>();
		String managerialPageJson = null;
		Integer allAccountNumber;

		// 將該頁的資料撈出來
		List<Manageral> accountPage = service.getManagerialUserAccountOfPage(page);
		System.out.println("==== accountPage= " + accountPage + " ====");

		// 該頁諾有資料, 將資料轉利用Gson套件換成Json格式
		if (accountPage != null) {
			// 建立Gson
			Gson gson = new Gson();

			// 將productPage轉成Json格式的String字串
			managerialPageJson = gson.toJson(accountPage);
			System.out.println("gson.toJson(productPage)= " + managerialPageJson);
		}

		// 取得商品總數
		System.out.println("==== Start getAllProductNumber ====");
		allAccountNumber = service.getAllManagerialNumber();
		System.out.println("===== allProductNumber= " + allAccountNumber + " =====");

		// 建立Json內容(Map型態)
		json.put("managerialPageJson", managerialPageJson);
		json.put("page", page);
		json.put("allAccountNumber", allAccountNumber);

		return json;
	}

	// 更新管理員資訊
	@ResponseBody
	@PostMapping(value = "/updateUserManagerial", produces = "application/json")
	public Map<String, Object> updateUserManagerial(@ModelAttribute Manageral member,
			Model model, HttpServletRequest req) {
		System.out.println("=== member:" + member + " ===");
		// 建立必要參數
		Boolean result = null;
		Map<String, Object> json = new HashMap<>();

		// 取maId
		Integer id = member.getMaId();

		// 確認是否有需要更換密碼, 密碼欄位為空的就塞回舊的密碼
		if (member.getPassword().isEmpty()) {
			member.setPassword(service.getOneManagerial(id).getPassword());
		}

		// 更新user account
		result = service.updateUserManagerial(member);

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
		@PostMapping(value = "/insertManagerial", produces = "application/json")
		public Map<String, Object> insertManagerial(@ModelAttribute Manageral member, Model model,
				HttpServletRequest req) {

			System.out.println("=== insertMem:" + member + " ===");
			// 建立必要參數
			Boolean result = null;
			Map<String, Object> json = new HashMap<>();

			result = service.insertOneManagerial(member);

			// 確認更新結果
			if (result) {
				json.put("status", "OK");
			} else {
				json.put("status", "false");
			}
			return json;
		}

}
