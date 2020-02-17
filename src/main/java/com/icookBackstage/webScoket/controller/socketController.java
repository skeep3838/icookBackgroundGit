package com.icookBackstage.webScoket.controller;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icookBackstage.model.socketBean;
import com.icookBackstage.webScoket.service.socketServiceDao;

@Controller
public class socketController {
	@Autowired
	socketServiceDao service;

	@Autowired
	public void setService(socketServiceDao service) {
		this.service = service;
	}
	@RequestMapping(value = "/GoWebSocket")
	public String GoWebSocket(HttpServletRequest request,HttpServletResponse response, Model model) throws IOException {
		return "webSocket/webSocket";
	}
	@RequestMapping(value = "/WebSocket")
	@ResponseBody
	public String WebSocket(@RequestParam("Message") String message,@RequestParam("maid") int maId,HttpServletRequest request,HttpServletResponse response, Model model) throws IOException {
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
		String date = sdFormat.format(new Date());
		socketBean temp = new socketBean(null, 1, maId, message, date);
		boolean saveResult;
//		if(checkResult == true) {
//			saveResult = service.saveMessage(temp);
//		}
//		else {
//			saveResult = service.saveMessage(temp);
//		}
		saveResult = true;
		if(saveResult == true) {
			date = date.substring(0,16);
			return date;
		}
		else {
			return "error";
		}
	}
}
