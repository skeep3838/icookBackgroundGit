package com.icookBackstage.userAccount.service;

import java.util.List;

import com.icookBackstage.model.MemberBean;
import com.icookBackstage.userAccount.dao.IAccountDAO;

/*
 * ProductServiceImpl方法: 
 * 	1.新建一項商品  
 * 	2.取使用者帳號總數
 */
public interface IAccountService {

	void getIAccountDaoImpl(IAccountDAO dao);
	//1.新建一項商品  
	List<MemberBean> getUserAccountOfPage(Integer page);
	//2.取使用者帳號總數
	Integer getAllAccountNumber();
	//3.更新使用者帳戶資訊
	Boolean updateUserAccount(MemberBean member);
	// 4.取出單筆使用者帳戶資訊
	MemberBean getOneUserAccount(int BeanPk);
}