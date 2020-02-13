package com.icookBackstage.news.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icookBackstage.model.NewsBean;
import com.icookBackstage.news.dao.INewsDAO;

/*
 *AccountDAOImpl方法: 
 *	1.取頁數的公告資料
 *	2.取公告資料的總數
 *	3.更新公告資訊
 *	4.取出單筆公告資訊
 *	5.新建一個公告
 */

@Repository
public class NewsDAOImpl implements INewsDAO{
	// 公用的SessionFactory
		SessionFactory factory;

		// 注入factory
		@Override
		@Autowired
		public void getFactory(SessionFactory factory) {
			this.factory = factory;
		}

		// 保留無輸入參數的建構式
		public NewsDAOImpl() {
		}

		// 1.取頁數的公告資料
		@SuppressWarnings("unchecked")
		@Override
		public List<NewsBean> getNewsOfPage(Integer first, Integer onePageNunber) {
			// 設定必要參數
			Session session = factory.getCurrentSession();
			List<NewsBean> userAccountOfPage = null;
			String hql = "FROM NewsBean m";

			// hql取得範圍內資料
			userAccountOfPage = session.createQuery(hql).setFirstResult(first).setMaxResults(onePageNunber).getResultList();
			return userAccountOfPage;
		}

		// 2.取公告資料的總數
		@Override
		public Integer getNewsNumber() {
			// 設定必要參數
			Session session = factory.getCurrentSession();
			String hql = "SELECT COUNT(*) FROM NewsBean m";
			Integer number = null;

			// 取得產品總數(count(*)預設取回Long型態物件, 使用intValue轉成int型別)
			number = ((Long) session.createQuery(hql).getSingleResult()).intValue();
			return number;
		}

		// 3.更新公告資訊
		@Override
		public Boolean updateNews(NewsBean member) {
			Session session = factory.getCurrentSession();

			try {
				session.update(member);
			} catch (Exception e) {
				e.getStackTrace();
				return false;
			}
			return true;
		}

		// 4.取出單筆公告資訊
		@Override
		public NewsBean getOneNews(int BeanPk) {
			Session session = factory.getCurrentSession();
			NewsBean bean = session.get(NewsBean.class, BeanPk);

			System.out.println("===== bean= " + bean + " =====");
			System.out.println("===== bean.getClass()= " + bean.getClass() + " =====");
			return bean;
		}

		// 5.新建一個公告
		@Override
		public Boolean insertOneNews(NewsBean bean) {
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
