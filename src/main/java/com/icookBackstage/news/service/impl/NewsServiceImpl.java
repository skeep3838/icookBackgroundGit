package com.icookBackstage.news.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icookBackstage.model.MemberBean;
import com.icookBackstage.model.NewsBean;
import com.icookBackstage.news.dao.INewsDAO;
import com.icookBackstage.news.service.INewsService;

@Service
public class NewsServiceImpl implements INewsService{
	INewsDAO dao;

	// 注入INewsDAO
	@Override
	@Autowired
	public void getIAccountDaoImpl(INewsDAO dao) {
		this.dao = dao;
	}

	// 1.讀取頁面公告, page不對或是抓到空資料, 都以null回應
	@Override
	@Transactional
	public List<NewsBean> getNewsOfPage(Integer page) {
		// 設定初始參數
		List<NewsBean> userAccountOfPage;
		int first = 0;
		int onePageNunber = 10;

		// 判斷page是否合法, 不合法直接回傳null離開
		if (page == null || page < 0) {
			return null;
		}

		// 計算起始資料為第 (page - 1)*10筆
		first = (page - 1) * onePageNunber;

		// 取得該頁的資料
		userAccountOfPage = dao.getNewsOfPage(first, onePageNunber);

		// 抓到空集合就回應null
		if (userAccountOfPage.isEmpty()) {
			userAccountOfPage = null;
		}
		return userAccountOfPage;
	}

	// 2.取公告總數
	@Override
	@Transactional
	public Integer getNewsNumber() {
		return dao.getNewsNumber();
	}

	// 3.更新公告資訊
	@Override
	@Transactional
	public Boolean updateNews(NewsBean member) {
		return dao.updateNews(member);
	}

	// 4.取出單筆公告資訊
	@Override
	@Transactional
	public NewsBean getOneNews(int BeanPk) {
		return dao.getOneNews(BeanPk);
	}

	// 5.新建一個公告
	@Override
	@Transactional
	public Boolean insertOneNews(NewsBean bean) {
		return dao.insertOneNews(bean);
	}
}
