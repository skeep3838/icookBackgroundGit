package com.icookBackstage.managementLogin.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.icookBackstage.managementLogin.service.IBackEndLoginService;
import com.icookBackstage.model.Manageral;

@Controller
//設定Session, 名稱為currentManager, 當控制器任何方法在Model內加入同名屬性, 會複製一樣的屬性到session
@SessionAttributes(value = { "currentManager" })
public class BackstageController {
	IBackEndLoginService service;

	// 注入Service
	@Autowired
	public void getService(IBackEndLoginService service) {
		this.service = service;
	}
	
	// 開啟網頁時轉跳到登入畫面
	@RequestMapping("/")		
	public String welcome() {	
		
		return "forward:/managermentLogin.page"; 
	}
	
	// 轉跳後台畫面
	@RequestMapping("/backstage.page")
	public String changeToBackstage() {
		return "backstage";
	}

	// 轉跳管理者登入,並檢查Session
	@RequestMapping("/managermentLogin.page")
	public String changeToManagermentLogin(
			@SessionAttribute(value = "currentManager", required = false) Manageral currentSesMan) {
		// 如果session含有登入者資訊 就直接以該使用者登入
		if (currentSesMan != null) {
			return "backstage";
		}
		return "managementLogin";
	}

	// 登出並清除session的currentManager
	@RequestMapping("/managementLogout.do")
	public String managermentLogout(SessionStatus status) {
		// 登出時將從@SessionAttributes寫入的資料全部從Session內清除
		status.setComplete();
		return "managementLogin";
	}

	// 進行帳號判斷 並取回登入者ID存放到session
	@RequestMapping("/managementLogin.do")
	public String chickPassword(@RequestParam("inputAccount") String inputAccount,
			@RequestParam("inputPassword") String inputPassword, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		
		//建立Remember Cookie的預設值
		Cookie acCookie = new Cookie("remAccrount",null);
		Cookie pasCookie = new Cookie("remPassword",null);
		//檢查Remember有沒有被打勾
		if(request.getParameter("rememberBox") != null) {
			acCookie = new Cookie("remAccrount",inputAccount);
			pasCookie = new Cookie("remPassword",inputPassword);
		}else {
			acCookie.setMaxAge(0);
			pasCookie.setMaxAge(0);
		}
		response.addCookie(acCookie);
		response.addCookie(pasCookie);
		
		//建立帳密檢查的參數
		String goBackPath = null;
		Manageral inputBean = new Manageral(inputAccount, inputPassword);
		Manageral loginBean = service.chickPasswordAndGetManageral(inputBean);

		//判斷帳密是否正確
		if (loginBean != null) {
			//密碼正確, 將登入者資訊寫進session並轉跳進後台畫面
			model.addAttribute("currentManager", loginBean);
			System.out.println("loginMaId= " + loginBean.getMaId());
			System.out.println("====== goBackstage ======");
			goBackPath = "backstage";

		} else {
			//密碼錯誤, 回傳錯誤訊息到登入畫面
			model.addAttribute("errMessage", "The Password you enter is not match, <br>Please re-enter your password");
			System.out.println("====== returnManagementLogin ======");
			goBackPath = "managementLogin";
		}
		return goBackPath;
	}

}
