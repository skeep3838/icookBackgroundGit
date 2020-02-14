package com.icookBackstage.managementLogin.controller;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.icookBackstage.managementLogin.service.SearchAllOrdServiceDao;
import com.icookBackstage.model.CustomerInfo;
import com.icookBackstage.model.MemberBean;
import com.icookBackstage.model.ProductOrder;
import com.icookBackstage.model.helpQuestion;
import com.icookBackstage.model.orderBean;
import com.icookBackstage.model.orderDetail;
import com.icookBackstage.sendmail.service.sendmailService;

@Controller
public class managerController {

	@Autowired
	SearchAllOrdServiceDao service;

	@Autowired
	public void setService(SearchAllOrdServiceDao service) {
		this.service = service;
	}
	
	@Autowired
	sendmailService mailService;

	@Autowired
	public void setService(sendmailService service) {
		this.mailService = service;
	}
	
	@RequestMapping(value = "/managerJson", method = RequestMethod.POST, produces="text/html;charset=UTF-8")
	@ResponseBody
	public String changeJson(@RequestParam("page") String page,Model model, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		HttpSession session = request.getSession(false);
		Gson gson = new Gson();
		MemberBean mem = (MemberBean) session.getAttribute("LoginOK");
		int pag = Integer.parseInt(page);
		session.setAttribute("page", pag);
		List<orderBean> orderData = service.searchAllOrders();
		System.out.println("======= List<orderBean> orderData= " + orderData + " =======");
		String json = gson.toJson(orderData);
		return json;
	}
	
	@RequestMapping(value = "/ManagerOrders", method = RequestMethod.GET)
	public String SearchOrders(@RequestParam(value="page", required=false) String page, HttpServletRequest request,
			HttpServletResponse response,Model model) throws IOException {
		HttpSession session = request.getSession(false);
		if(page==null) {
			page = "1";
		}
		session.setAttribute("LogYesNo", "Yes");
		int pag = Integer.parseInt(page);
		session.setAttribute("page", pag);
		List<orderBean> orderData = service.searchAllOrders();
		for(int i=0;i<orderData.size();i++) {
			switch (orderData.get(i).getStatus()) {
			case "N" :
				orderData.get(i).setStatus("未出貨");
				break;
			case "S" :
				orderData.get(i).setStatus("出貨中");
				break;
			case "F" :
				orderData.get(i).setStatus("已到貨");
				break;
			case "C" :
				orderData.get(i).setStatus("已取消");
				break;
			}
		}
		if (orderData.isEmpty()) {
			session.setAttribute("stat", false);
		} else {
			session.setAttribute("stat", true);
		}
		session.setAttribute("orderData", orderData);
		model.addAttribute("orderSize",orderData.size());
		
		int onePageQuantity = 8;
		model.addAttribute("onePageQuantity",onePageQuantity);
		int pageNo = (pag - 1) * onePageQuantity;
		model.addAttribute("pageNo",pageNo);
		int temp = (orderData.size() - pageNo);
		model.addAttribute("onePageTotalQuantity",temp);
		int endPage = 0;
		if(temp >= onePageQuantity) {
			endPage = pageNo + onePageQuantity;
			model.addAttribute("endPage",endPage);
		}
		else {
			endPage = orderData.size();
			model.addAttribute("endPage",endPage);
		}
		
		//range 帶表總共的頁數
		int range = 0;
		if ((orderData.size() % onePageQuantity) == 0) {
			range = orderData.size() / onePageQuantity;
			model.addAttribute("range",range);
		} else {
			range = (orderData.size() / onePageQuantity) + 1;
			model.addAttribute("range",range);
		}
		
		return "orderManagement";
	}
	
	
	
	
	@RequestMapping(value = "/SearchOrderDetails", method = RequestMethod.GET)
	public String SearchOrdDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false);
		Serializable name = request.getQueryString(); // 回傳id=2
		// 取得從Orders.jsp傳來的URL後面的id=2
		String temp = (String) name;
		// 把name從object型態轉換成String
		int orderId = Integer.parseInt(temp.substring(3, temp.length()));
		// 把id=多少用substring切出來之後再轉換成integer
		List<orderDetail> detail = service.searchAllOrdDetails(orderId);
		session.setAttribute("orderDetailsData", detail);
		return "orderDetailsManagerment";
	}

	@RequestMapping(value = "/DeleteOrders", method = RequestMethod.GET)
	public String DeleteOrders(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false);
		if (session.getAttribute("page") == null) {
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath()));
			return "index";
		}
		// 取得當前的頁數
		else {
			int page = (int) session.getAttribute("page");
			Serializable name = request.getQueryString();
			String temp = (String) name;
			int orderId = Integer.parseInt(temp.substring(6, temp.length()));
			service.Delete(orderId);
			return "redirect:/ManagerOrders?page=" + page;
			// 刪除後重新導向刪除資料的頁數並更新資料
		}
	}
	
	
	//----------------------------------------------------
	
	@RequestMapping(value = "/Managerhelp", method = RequestMethod.GET)
	public String Searchhelp(@RequestParam(value="page", required=false) String page, HttpServletRequest request,
			HttpServletResponse response,Model model) throws IOException {
		HttpSession session = request.getSession(false);
		if(page==null) {
			page = "1";
		}
		session.setAttribute("LogYesNo", "Yes");
		int pag = Integer.parseInt(page);
		session.setAttribute("page", pag);
		List<helpQuestion> HelpQuestionData = service.searchHelpQuestion();
		if (HelpQuestionData.isEmpty()) {
			session.setAttribute("stat", false);
		} else {
			session.setAttribute("stat", true);
		}
		
		session.setAttribute("HelpQuestionData", HelpQuestionData);
		model.addAttribute("orderSize",HelpQuestionData.size());
		
		int onePageQuantity = 8;
		model.addAttribute("onePageQuantity",onePageQuantity);
		int pageNo = (pag - 1) * onePageQuantity;
		model.addAttribute("pageNo",pageNo);
		int temp = (HelpQuestionData.size() - pageNo);
		model.addAttribute("onePageTotalQuantity",temp);
		int endPage = 0;
		if(temp >= onePageQuantity) {
			endPage = pageNo + onePageQuantity;
			model.addAttribute("endPage",endPage);
		}
		else {
			endPage = HelpQuestionData.size();
			model.addAttribute("endPage",endPage);
		}
		
		//range 帶表總共的頁數
		int range = 0;
		if ((HelpQuestionData.size() % onePageQuantity) == 0) {
			range = HelpQuestionData.size() / onePageQuantity;
			model.addAttribute("range",range);
		} else {
			range = (HelpQuestionData.size() / onePageQuantity) + 1;
			model.addAttribute("range",range);
		}
		
		return "helpQuestion/managerHelpQuestion";
	}
	
	@RequestMapping(value = "/helpQuestionJson", method = RequestMethod.POST, produces="text/html;charset=UTF-8")
	@ResponseBody
	public String helpJson(@RequestParam("page") String page,Model model, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		HttpSession session = request.getSession(false);
		Gson gson = new Gson();
		int pag = Integer.parseInt(page);
		session.setAttribute("page", pag);
		List<helpQuestion> HelpQuestionData = service.searchHelpQuestion();
		String json = gson.toJson(HelpQuestionData);
		return json;
	}
	@RequestMapping(value = "/helpQuestionContent", method = RequestMethod.POST, produces="text/html;charset=UTF-8")
	@ResponseBody
	public String helpContent(@RequestParam("helpQAId")int helpQAId,Model model, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		helpQuestion temp = service.searchSingleHelpQuestion(helpQAId);
		Gson gson = new Gson();
		String json = gson.toJson(temp);
		return json;
	}
	
	@RequestMapping(value = "/sendResponsemail", method = RequestMethod.POST)
	@ResponseBody
	public String sendEmail(@RequestParam("helpQAId")int helpQAId,@RequestParam("textarea")String textarea,Model model, HttpServletRequest request, HttpServletResponse response) {
		helpQuestion temp = service.searchSingleHelpQuestion(helpQAId);
		MemberBean member = service.searchSingleMember(temp.getUserID());
		boolean result = mailService.sendResponseQuestion(responseQuestiob(textarea,member,"responseQuestion"));
//		boolean result = true;
		if(result == true) {
			service.finishUpdateResponseStatus(helpQAId);
			return "Y";
		}
		else {
			return "F";			
		}
	}
	
	public static ProductOrder responseQuestiob(String temp,MemberBean member,String purpose) {
		ProductOrder order = new ProductOrder();
		order.setProductName(purpose);
		order.setOrderId("");
		order.setStatus("");
		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.setName(member.getNickname());
		customerInfo.setAddress(temp);
		customerInfo.setEmail(member.getAccount());
		order.setCustomerInfo(customerInfo);
		return order;
	}
}
