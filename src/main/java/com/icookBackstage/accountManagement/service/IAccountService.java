package com.icookBackstage.accountManagement.service;

import java.util.List;

import com.icookBackstage.accountManagement.dao.IAccountDAO;
import com.icookBackstage.model.Manageral;
import com.icookBackstage.model.MemberBean;

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
	// 5.新建一個使用者帳號
	Boolean insertOneUserAcc(MemberBean bean);
	// 6.取頁數的管理員資料
	List<Manageral> getManagerialUserAccountOfPage(Integer page);
	// 7.取管理員總數
	Integer getAllManagerialNumber();
	// 8.更新管理員資訊
	Boolean updateUserManagerial(Manageral member);
	// 9.取出單筆管理員資訊
	Manageral getOneManagerial(int BeanPk);
	// 10.新建一個管理員
	Boolean insertOneManagerial(Manageral bean);
}