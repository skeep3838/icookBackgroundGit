package com.icookBackstage.news.service;

import java.util.List;

import com.icookBackstage.model.NewsBean;
import com.icookBackstage.news.dao.INewsDAO;

public interface INewsService {
	
	void getIAccountDaoImpl(INewsDAO dao);
	// 1.讀取頁面公告, page不對或是抓到空資料, 都以null回應
	List<NewsBean> getNewsOfPage(Integer page);
	// 2.取公告總數
	Integer getNewsNumber();
	// 3.更新公告資訊	
	Boolean updateNews(NewsBean member) ;
	// 4.取出單筆公告資訊
	NewsBean getOneNews(int BeanPk);
	// 5.新建一個公告
	Boolean insertOneNews(NewsBean bean);
	// 6.刪除一個公告
	Boolean deleteOneNews(Integer id);	
}
