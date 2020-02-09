package com.icookBackstage.userAccount.controller;

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
import com.icookBackstage.model.MemberBean;
import com.icookBackstage.userAccount.service.IAccountService;

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

	// 用RESTful回傳頁數{page}對應的Json資料(Map型態)
	@ResponseBody
	@GetMapping(value = "/userAccount/{page}", produces = "application/json")
	public Map<String, Object> getProductsForPage(@PathVariable Integer page) {

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
	
	//更新使用者帳戶資訊
	@ResponseBody
	@PostMapping(value = "/updateUserAccount/{id}", produces = "application/json")
	public Map<String, Object> getProductsForPage1(	@PathVariable Integer id,
													@ModelAttribute MemberBean member,
													Model model, 
													HttpServletRequest req) {
		System.out.println("=== member:" + member + " ===");
		//建立必要參數
		Boolean result = null;
		Map<String, Object> json = new HashMap<>();
		
		//設定userId回member
		member.setUserId(id);
				
		//確認是否有需要更換密碼, 密碼欄位為空的就塞回舊的密碼
		if(member.getPassword().isEmpty()) {
			member.setPassword(service.getOneUserAccount(id).getPassword());
		}
		
		//更新user account
		result = service.updateUserAccount(member);
		
		//確認更新結果
		if(result == false) {
			json.put("status", "false");
		}else {
			json.put("status", "OK");
		}
		return json;
	}
}
