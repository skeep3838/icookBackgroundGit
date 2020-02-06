package com.icookBackstage.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icookBackstage.model.ProductBean;
import com.icookBackstage.product.dao.IProductDAO;
import com.icookBackstage.product.service.IProductService;

/*
 * ProductServiceImpl方法: 
 * 	1.新建一項商品  
 * 	2.讀取全部商品
 * 	3.讀取頁面商品(上架or下架)
 * 	4.取商品總數 (上架or下架)
 */
@Service
public class ProductServiceImpl implements IProductService {

	IProductDAO dao ;
	
	//注入ProductDAO
	@Override
	@Autowired
	public void getProductDAOImpl(IProductDAO dao) {
		this.dao = dao;
	}
	
	// 1.新建一項商品
	@Override
	@Transactional
	public void insertProduct(ProductBean bean) {
		dao.insertProduct(bean);
	}
	
	//test2
	@Override
	@Transactional
	public ProductBean getProduct(int BeanPk) {
		return dao.getProduct(BeanPk);
	}
	
	// 2.讀取全部商品
	@Override
	@Transactional
	public List<ProductBean> getAllProduct() {
		return dao.getAllProduct();
	}
	
	//3.讀取頁面商品(上架or下架), page不對或是抓到空資料, 都已null回應
	@Override
	@Transactional
	public List<ProductBean> getProductOfPage(Integer page, Boolean status){
		//設定初始參數
		List<ProductBean> productOfPage;
		int first = 0;
		int onePageNunber = 10;
		
		//判斷page是否合法, 不合法直接回傳null離開
		if(page == null || page < 0 ) {
			return null;
		}
		
		//計算起始資料為第 (page - 1)*10筆
		first = (page - 1)*onePageNunber;
		
		//取得該頁的資料
		productOfPage = dao.getProductOfPage(first, onePageNunber, status);
		//測試用
//		productOfPage = dao.getProductOfPage(3, 1, status);
		
		//抓到空集合就回應null
		if(productOfPage.isEmpty()) {
			productOfPage = null;
		}
		return productOfPage;
	}
	
	//4.取商品總數(上架or下架)
	@Override
	@Transactional
	public Integer getAllProductNumber(Boolean status) {
		return dao.getAllProductNumber(status);
	}
	
	//5.刪除商品型別
	@Override
	@Transactional
	public void deleteProductType(Integer productId) {
		dao.deleteProductType(productId);
	}
	
	//6.更新商品(測試中)
	@Override
	@Transactional
	public Boolean updateProduct(ProductBean prodocut) {
		return dao.updateProduct(prodocut);
	}
	
	
}

