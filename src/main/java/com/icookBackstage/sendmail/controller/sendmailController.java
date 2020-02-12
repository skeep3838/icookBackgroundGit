package com.icookBackstage.sendmail.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.icookBackstage.model.CustomerInfo;
import com.icookBackstage.model.MemberBean;
import com.icookBackstage.model.ProductOrder;
import com.icookBackstage.sendmail.service.sendmailService;

@Controller
public class sendmailController {
	@Autowired
	sendmailService service;

	@Autowired
	public void setService(sendmailService service) {
		this.service = service;
	}


	// 導向驗證已過的頁面
	@RequestMapping(value = "/memberCheck")
	public String checkSuccess(@RequestParam(value = "userId", required = false) String userId) {
		service.modifyVerificationStatus(userId);
		return "redirect:/login/loginView";
	}

	@RequestMapping(value = "/sendmail", method = RequestMethod.GET)
	public String sendEmail(Model model, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String account = (String) session.getAttribute("account");
		MemberBean temp = service.searchMemberBean(account);
		service.sendOrderConfirmation(getDummyOrder(temp,"forgetPassword"));
		return "index";
	}

	public static ProductOrder getDummyOrder(MemberBean memberBean,String purpose) {
		ProductOrder order = new ProductOrder();
		order.setOrderId(memberBean.getPassword());
		order.setProductName(purpose);
		System.out.println(String.valueOf(memberBean.getUserId()));
		order.setStatus(String.valueOf(memberBean.getUserId()));
		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.setName(memberBean.getNickname());
		customerInfo.setAddress("WallStreet");
		customerInfo.setEmail(memberBean.getAccount());
		order.setCustomerInfo(customerInfo);
		return order;
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(@RequestParam(value = "page", required = false) String page, HttpServletRequest request,
			HttpServletResponse response, Model model) throws IOException {
		return "test";
	}
}
