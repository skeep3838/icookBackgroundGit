package com.icookBackstage.managementLogin.dao.impl;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icookBackstage.managementLogin.dao.AllOrdDao;
import com.icookBackstage.model.orderBean;
import com.icookBackstage.model.orderDetail;

@Repository
public class AllOrdDaoImpl implements AllOrdDao{
	SessionFactory factory;
	@Autowired
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<orderBean> getAllOrders() {
		Session session = factory.getCurrentSession();
		List<orderBean> list = null;
		try {
			String hql = "from orderBean";
			list = session.createQuery(hql).getResultList();
			list = Reverse(list);
			System.out.println("--------------CORRECT---------------");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("--------------ERROR---------------");
		}
		return list;
	}
	
	@Override
	public List<orderBean> Reverse(List<orderBean> list){
		List<orderBean> list2 = new LinkedList<orderBean>();
		for(int i=list.size()-1;i>=0;i--) {
			list2.add(list.get(i));
		}
		return list2;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean checkOrders(int userId) {
		boolean status = true;
		Session session = factory.getCurrentSession();
		List<orderBean> list = null;
		try {
			String hql = "from orderBean m where m.userId = :mid";
			list = session.createQuery(hql).setParameter("mid",userId).getResultList();
			System.out.println(list);
			System.out.println("--------------CORRECT---------------");
		} catch (Exception e) {
			status = false;
			System.out.println("--------------ERROR---------------");
		}
		return status;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<orderDetail> getAllOrderDetails(int orderId) {
		Session session = factory.getCurrentSession();
		List<orderDetail> list = null;
		try {
			String hql = "from orderDetail m where m.orderId = :mid";
			list = session.createQuery(hql).setParameter("mid",orderId).getResultList();
			System.out.println("--------------CORRECT---------------");
		} catch (Exception e) {
			System.out.println("--------------ERROR---------------");
		}
		return list;
	}

	@Override
	public void DeleteOrders(int orderId) {
		Session session = factory.getCurrentSession();
		try {
			String hql2 = "update orderBean m set m.status = :status where m.orderId = :mid";
			session.createQuery(hql2).setParameter("status", "C")
			 .setParameter("mid", orderId)
			 .executeUpdate();
			System.out.println("--------------CORRECT---------------");
		} catch (Exception e) {			
			System.out.println("--------------ERROR---------------");
		}
		
	}
}
