package com.icookBackstage.accountManagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icookBackstage.accountManagement.dao.IAccountDAO;
import com.icookBackstage.accountManagement.service.IAccountService;
import com.icookBackstage.model.Manageral;
import com.icookBackstage.model.MemberBean;

/*
 *AccountServiceImpl方法: 
 *	1.取頁數的使用者資料
 *	2.取使用者帳號總數
 *  3.更新使用者帳戶資訊
 *  4.取出單筆使用者帳戶資訊
 *  5.新建一個使用者帳號
 *  6.取頁數的管理員資料
 *	7.取管理員總數
 *	8.更新管理員資訊
 *	9.取出單筆管理員資訊
 *	10.新建一個管理員
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

	// 5.新建一個使用者帳號
	@Override
	@Transactional
	public Boolean insertOneUserAcc(MemberBean bean) {
		return dao.insertOneUserAcc(bean);
	}

	// 6.取頁數的管理員資料
	@Override
	@Transactional
	public List<Manageral> getManagerialUserAccountOfPage(Integer page) {
		// 設定初始參數
		List<Manageral> userAccountOfPage;
		int first = 0;
		int onePageNunber = 10;

		// 判斷page是否合法, 不合法直接回傳null離開
		if (page == null || page < 0) {
			return null;
		}

		// 計算起始資料為第 (page - 1)*10筆
		first = (page - 1) * onePageNunber;

		// 取得該頁的資料
		userAccountOfPage = dao.getManagerialUserAccountOfPage(first, onePageNunber);

		// 抓到空集合就回應null
		if (userAccountOfPage.isEmpty()) {
			userAccountOfPage = null;
		}
		return userAccountOfPage;
	}

	// 7.取管理員總數
	@Override
	@Transactional
	public Integer getAllManagerialNumber() {
		return dao.getAllManagerialNumber();
	}

	// 8.更新管理員資訊
	@Override
	@Transactional
	public Boolean updateUserManagerial(Manageral member) {
		return dao.updateUserManagerial(member);
	}

	// 9.取出單筆管理員資訊
	@Override
	@Transactional
	public Manageral getOneManagerial(int BeanPk) {
		return dao.getOneManagerial(BeanPk);
	}

	// 10.新建一個管理員
	@Override
	@Transactional
	public Boolean insertOneManagerial(Manageral bean) {
		return dao.insertOneManagerial(bean);
	}
}
