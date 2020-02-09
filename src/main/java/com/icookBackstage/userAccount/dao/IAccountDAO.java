package com.icookBackstage.userAccount.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.icookBackstage.model.MemberBean;

public interface IAccountDAO {
	void getFactory(SessionFactory factory);
	// 1.取頁數的使用者資料
	List<MemberBean> getUserAccountOfPage(Integer first, Integer onePageNunber);
	// 2.取使用者帳號總數
	Integer getAllAccountNumber();
	// 3.更新使用者帳戶資訊
	Boolean updateUserAccount(MemberBean member);
	// 4.取出單筆使用者帳戶資訊
	MemberBean getOneUserAccount(int BeanPk);
}