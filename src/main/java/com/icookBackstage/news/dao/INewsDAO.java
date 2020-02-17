package com.icookBackstage.news.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.icookBackstage.model.NewsBean;

public interface INewsDAO {
	
	void getFactory(SessionFactory factory);
	// 1.取頁數的公告資料
	List<NewsBean> getNewsOfPage(Integer first, Integer onePageNunber);
	// 2.取公告資料的總數
	Integer getNewsNumber();
	// 3.更新公告資訊
	Boolean updateNews(NewsBean member);
	// 4.取出單筆公告資訊
	NewsBean getOneNews(int BeanPk);
	// 5.新建一個公告
	Boolean insertOneNews(NewsBean bean);
	// 6.刪除一個公告
	Boolean deleteOneNews(Integer id);

}
