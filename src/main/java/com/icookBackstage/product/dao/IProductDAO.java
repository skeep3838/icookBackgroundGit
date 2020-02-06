package com.icookBackstage.product.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.icookBackstage.model.ProductBean;

/*
 *ProductDAOImpl方法: 
 *	1.新建一項商品 
 *	2.讀取全部商品 
 *	3.取頁數的商品資料 (上架or下架)
 *	4.取商品總數 (上架or下架)
 *	5.刪除商品...(後作)
 */
public interface IProductDAO {

	//注入factory
	void getFactory(SessionFactory factory);
	// 1.新建一項商品
	void insertProduct(ProductBean bean);
	// 2.讀取商品
	List<ProductBean> getAllProduct();
	//3.取頁數的商品資料(上架or下架)
	List<ProductBean> getProductOfPage(Integer first, Integer onePageNunber, Boolean status);
	//4.取商品總數 (上架or下架)
	Integer getAllProductNumber(Boolean status);
	//test
	ProductBean queryProduct();
	
	//test2
	ProductBean getProduct(int BeanPk);

}