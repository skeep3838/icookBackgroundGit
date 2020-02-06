package com.icookBackstage.product.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icookBackstage.model.ProductBean;
import com.icookBackstage.product.dao.IProductDAO;

/*
 *ProductDAOImpl方法: 
 *	1.新建一項商品 
 *	2.讀取全部商品 
 *	3.取頁數的商品資料 (上架or下架)
 *	4.取商品總數 (上架or下架)
 *	5.刪除商品型別
 *	6.更新商品(測試中)
 */
@Repository
public class ProductDAOImpl implements IProductDAO {
	//公用的SessionFactory
	SessionFactory factory;
	
	//注入factory
	@Override
	@Autowired
	public void getFactory(SessionFactory factory) {
		this.factory = factory;
	}

	//保留無輸入參數的建構式
	public ProductDAOImpl() {
	}
	
	
	// 1.新建一項商品
	@Override
	public void insertProduct(ProductBean bean) {

		Session session = factory.getCurrentSession();
			session.persist(bean);
	}

	// 2.讀取全部商品
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductBean> getAllProduct() {

		Session session = factory.getCurrentSession();
		List<ProductBean> products = null;
			String hql = "FROM ProductBean";
			products = session.createQuery(hql).getResultList();
			System.out.println(products);
		return products;
	}
	
	// 3.取頁數的商品資料(上架or下架)
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductBean> getProductOfPage(Integer first, Integer onePageNunber, Boolean status){
		//設定必要參數
		Session session = factory.getCurrentSession();
		List<ProductBean> productOfPage = null;
		String hql = "FROM ProductBean p WHERE p.itemStatus = :status";
		
		//hql取得範圍內資料
		productOfPage = session.createQuery(hql).setParameter("status", status).setFirstResult(first)
											.setMaxResults(onePageNunber).getResultList();
		//測試用
//		productOfPage = session.createQuery(hql).setParameter("status", true).setFirstResult(first)
//				.setMaxResults(onePageNunber).getResultList();
		return productOfPage;
	}
	
	//4.取商品總數 (上架or下架)
	@Override
	public Integer getAllProductNumber(Boolean status) {
		//設定必要參數
		Session session = factory.getCurrentSession();
		String hql = "SELECT COUNT(*) FROM ProductBean p WHERE p.itemStatus = :status";
		Integer number = null;
		
		//取得產品總數(count(*)預設取回Long型態物件, 使用intValue轉成int型別)
		number = ((Long)session.createQuery(hql).setParameter("status", status).getSingleResult()).intValue();
		return number;
	}
	
	//test
	@Override
	public ProductBean queryProduct() {

		Session session = factory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		ProductBean proBean = null;
		try {
			proBean = session.get(ProductBean.class ,1);
			System.out.println(proBean);
			tx.commit();
			System.out.println("=========Commit done!!=========");
		} catch (Exception e) {
			if (tx != null) {
				System.out.println("=========Rollback done!!=========");
				tx.rollback();
			}
			throw new RuntimeException(e);
		}

		return proBean;
	}
	//test2
	public ProductBean getProduct(int BeanPk) {
		Session session = factory.getCurrentSession();
		System.out.println("=====do getProduct=====");
		ProductBean bean = session.get(ProductBean.class,BeanPk);
		System.out.println("=====done getProduct=====");
		return bean;
	}
	
	//5.刪除商品型別
	@Override
	public void deleteProductType(Integer productId) {
		Session session = factory.getCurrentSession();
		String hqlStr = "DELETE FROM ProductTypeBean WHERE productID = :productId";
		int result = session.createQuery(hqlStr).setParameter("productId", productId).executeUpdate();
		
	}
	
	
	//6.更新商品(測試中)
	@Override
	public Boolean updateProduct(ProductBean prodocut) {
		Session session = factory.getCurrentSession();
		System.out.println("=====do updateProduct=====");
		session.update(prodocut);
		System.out.println("=====done updateProduct=====");
		
		return null;
		
	}

}
