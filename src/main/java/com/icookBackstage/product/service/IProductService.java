package com.icookBackstage.product.service;

import java.util.List;

import com.icookBackstage.model.ProductBean;
import com.icookBackstage.product.dao.IProductDAO;

/*
 * ProductServiceImpl方法: 
 * 	1.新建一項商品  
 * 	2.讀取全部商品
 * 	3.讀取頁面商品(上架or下架)
 *  4.取商品總數 (上架or下架)
 */
public interface IProductService {

	void getProductDAOImpl(IProductDAO dao);
	// 1.新建一項商品
	void insertProduct(ProductBean bean);
	//test2
	ProductBean getProduct(int BeanPk);
	// 2.讀取全部商品
	List<ProductBean> getAllProduct();
	// 3.讀取頁面商品(上架or下架)
	List<ProductBean> getProductOfPage(Integer page, Boolean status);
	// 4.取商品總數 (上架or下架)
	Integer getAllProductNumber(Boolean status);
	// 5.刪除商品型別
	void deleteProductType(Integer productId);
	// 6.更新商品(測試中)
	Boolean updateProduct(ProductBean prodocut);
	// 7.改變商品狀態
	Boolean changeProductStr(Integer id, Integer status);
	// 8.取出單一商品資訊
	ProductBean getOneProduct(int BeanPk);

}