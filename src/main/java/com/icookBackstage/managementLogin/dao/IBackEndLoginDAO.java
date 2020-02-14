package com.icookBackstage.managementLogin.dao;

import org.hibernate.SessionFactory;

import com.icookBackstage.model.Manageral;

public interface IBackEndLoginDAO {

	//注入factory
	void getSessionFactory(SessionFactory factory);

	/**************************************
	 *BackEndLoginDAOImpl方法: 
	 *	1.輸入manageral檢查帳號密碼 
	 *	2.輸入account取得maId 
	 *	3.輸入account取得manageral
	 **************************************/
	//1.
	boolean chickPassword(Manageral managerl);
	//2.
	int getAccrountId(String inputAccrount);
	//3.
	Manageral getManageral(String inputAccrount);
	
	Integer getUnchickOrder();
	
	Integer getProductStock();
	
	Integer getUnchickMeg();

}