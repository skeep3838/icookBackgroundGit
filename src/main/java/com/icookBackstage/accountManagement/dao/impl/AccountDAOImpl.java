package com.icookBackstage.accountManagement.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icookBackstage.accountManagement.dao.IAccountDAO;
import com.icookBackstage.model.Manageral;
import com.icookBackstage.model.MemberBean;
import com.icookBackstage.model.ProductBean;

/*
 *AccountDAOImpl方法: 
 *	1.取頁數的使用者資料
 *	2.取使用者帳號總數
 *	3.更新使用者帳戶資訊
 *	4.取出單筆使用者帳戶資訊
 *	5.新建一個使用者帳號
 *	6.取頁數的管理員資料
 *	7.取管理員總數
 *	8.更新管理員資訊
 *	9.取出單筆管理員資訊
 *	10.新建一個管理員
 */
@Repository
public class AccountDAOImpl implements IAccountDAO {
	// 公用的SessionFactory
	SessionFactory factory;

	// 注入factory
	@Override
	@Autowired
	public void getFactory(SessionFactory factory) {
		this.factory = factory;
	}

	// 保留無輸入參數的建構式
	public AccountDAOImpl() {
	}

	// 1.取頁數的使用者資料
	@SuppressWarnings("unchecked")
	@Override
	public List<MemberBean> getUserAccountOfPage(Integer first, Integer onePageNunber) {
		// 設定必要參數
		Session session = factory.getCurrentSession();
		List<MemberBean> userAccountOfPage = null;
		String hql = "FROM MemberBean m";

		// hql取得範圍內資料
		userAccountOfPage = session.createQuery(hql).setFirstResult(first).setMaxResults(onePageNunber).getResultList();
		return userAccountOfPage;
	}

	// 2.取使用者帳號總數
	@Override
	public Integer getAllAccountNumber() {
		// 設定必要參數
		Session session = factory.getCurrentSession();
		String hql = "SELECT COUNT(*) FROM MemberBean m";
		Integer number = null;

		// 取得產品總數(count(*)預設取回Long型態物件, 使用intValue轉成int型別)
		number = ((Long) session.createQuery(hql).getSingleResult()).intValue();
		return number;
	}

	// 3.更新使用者帳戶資訊
	@Override
	public Boolean updateUserAccount(MemberBean member) {
		Session session = factory.getCurrentSession();

		try {
			session.update(member);
		} catch (Exception e) {
			e.getStackTrace();
			return false;
		}
		return true;
	}

	// 4.取出單筆使用者帳戶資訊
	@Override
	public MemberBean getOneUserAccount(int BeanPk) {
		Session session = factory.getCurrentSession();
		MemberBean bean = session.get(MemberBean.class, BeanPk);

		System.out.println("===== bean= " + bean + " =====");
		System.out.println("===== bean.getClass()= " + bean.getClass() + " =====");
		return bean;
	}

	// 5.新建一個使用者帳號
	@Override
	public Boolean insertOneUserAcc(MemberBean bean) {
		Session session = factory.getCurrentSession();

		try {
			session.persist(bean);
		} catch (Exception e) {
			e.getStackTrace();
			return false;
		}
		return true;
	}

	// 6.取頁數的管理員資料
	@SuppressWarnings("unchecked")
	@Override
	public List<Manageral> getManagerialUserAccountOfPage(Integer first, Integer onePageNunber) {
		// 設定必要參數
		Session session = factory.getCurrentSession();
		List<Manageral> userAccountOfPage = null;
		String hql = "FROM Manageral m";

		// hql取得範圍內資料
		userAccountOfPage = session.createQuery(hql).setFirstResult(first).setMaxResults(onePageNunber).getResultList();
		return userAccountOfPage;
	}

	// 7.取管理員總數
	@Override
	public Integer getAllManagerialNumber() {
		// 設定必要參數
		Session session = factory.getCurrentSession();
		String hql = "SELECT COUNT(*) FROM Manageral m";
		Integer number = null;

		// 取得產品總數(count(*)預設取回Long型態物件, 使用intValue轉成int型別)
		number = ((Long) session.createQuery(hql).getSingleResult()).intValue();
		return number;
	}

	// 8.更新管理員資訊
	@Override
	public Boolean updateUserManagerial(Manageral member) {
		Session session = factory.getCurrentSession();

		try {
			session.update(member);
		} catch (Exception e) {
			e.getStackTrace();
			return false;
		}
		return true;
	}

	// 9.取出單筆管理員資訊
	@Override
	public Manageral getOneManagerial(int BeanPk) {
		Session session = factory.getCurrentSession();
		Manageral bean = session.get(Manageral.class, BeanPk);

		System.out.println("===== bean= " + bean + " =====");
		System.out.println("===== bean.getClass()= " + bean.getClass() + " =====");
		return bean;
	}

	// 10.新建一個管理員
	@Override
	public Boolean insertOneManagerial(Manageral bean) {
		Session session = factory.getCurrentSession();
		try {
			session.persist(bean);
		} catch (Exception e) {
			e.getStackTrace();
			return false;
		}
		return true;
	}

}
