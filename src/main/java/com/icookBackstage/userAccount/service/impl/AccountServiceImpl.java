package com.icookBackstage.userAccount.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icookBackstage.model.MemberBean;
import com.icookBackstage.userAccount.dao.IAccountDAO;
import com.icookBackstage.userAccount.service.IAccountService;

/*
 *AccountServiceImpl方法: 
 *	1.取頁數的使用者資料
 *	2.取使用者帳號總數
 *  3.更新使用者帳戶資訊
 *  4.取出單筆使用者帳戶資訊
 */
@Service
public class AccountServiceImpl implements IAccountService {

	IAccountDAO dao;

	// 注入IAccountDAO
	@Override
	@Autowired
	public void getIAccountDaoImpl(IAccountDAO dao) {
		this.dao = dao;
	}

	// 1.讀取頁面使用者帳戶, page不對或是抓到空資料, 都以null回應
	@Override
	@Transactional
	public List<MemberBean> getUserAccountOfPage(Integer page) {
		// 設定初始參數
		List<MemberBean> userAccountOfPage;
		int first = 0;
		int onePageNunber = 10;

		// 判斷page是否合法, 不合法直接回傳null離開
		if (page == null || page < 0) {
			return null;
		}

		// 計算起始資料為第 (page - 1)*10筆
		first = (page - 1) * onePageNunber;

		// 取得該頁的資料
		userAccountOfPage = dao.getUserAccountOfPage(first, onePageNunber);

		// 抓到空集合就回應null
		if (userAccountOfPage.isEmpty()) {
			userAccountOfPage = null;
		}
		return userAccountOfPage;
	}

	// 2.取使用者帳號總數
	@Override
	@Transactional
	public Integer getAllAccountNumber() {
		return dao.getAllAccountNumber();
	}

	// 3.更新使用者帳戶資訊
	@Override
	@Transactional
	public Boolean updateUserAccount(MemberBean member) {
		return dao.updateUserAccount(member);
	}

	// 4.取出單筆使用者帳戶資訊
	@Override
	@Transactional
	public MemberBean getOneUserAccount(int BeanPk) {
		return dao.getOneUserAccount(BeanPk);
	}
}
