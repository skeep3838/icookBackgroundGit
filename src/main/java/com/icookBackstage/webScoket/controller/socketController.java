package com.icookBackstage.webScoket.controller;


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
public class socketController {
	@Autowired
	sendmailService service;

	@Autowired
	public void setService(sendmailService service) {
		this.service = service;
	}

	
	@RequestMapping(value = "/WebSocket", method = RequestMethod.GET)
	public String WebSocket(@RequestParam(value = "page", required = false) String page, HttpServletRequest request,
			HttpServletResponse response, Model model) throws IOException {
		return "webSocket/webSocket";
	}
}
