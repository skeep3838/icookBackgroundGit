package com.icookBackstage.order.dao;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icookBackstage.model.MemberBean;
import com.icookBackstage.model.orderBean;
import com.icookBackstage.model.orderDetail;

@Repository
public class OrdersDaoImpl implements OrdersDao{
	SessionFactory factory;
	@Autowired
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<orderBean> getAllOrders(int userId) {
		Session session = factory.getCurrentSession();
		List<orderBean> list = null;
		try {
			String hql = "from orderBean m where m.userId = :mid";
			list = session.createQuery(hql).setParameter("mid",userId).getResultList();
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
	
	

	@Override
	public MemberBean searchMem(int userId) {
		Session session = factory.getCurrentSession();
		MemberBean memberBean = null;
		try {
			String hql = "from MemberBean m where m.userId = :mid";
			memberBean = (MemberBean) session.createQuery(hql).setParameter("mid", userId).getSingleResult();
			System.out.println("--------------CORRECT---------------");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("--------------ERROR---------------");
		}
		return memberBean;
	}

	@Override
	public boolean changeOrderStatus(int orderId,String status) {
		Session session = factory.getCurrentSession();
		String temp = "";
		try {
			switch(status) {
				case "未出貨":
					temp = "N";
					break;
				case "出貨中":
					temp = "S";
					break;
				case "已到貨":
					temp = "F";
					break;
				case "已取消":
					temp = "C";
					break;
			}
			String hql = "update orderBean m set m.status = :status where m.orderId = :mid";
			session.createQuery(hql).setParameter("status", temp)
			 .setParameter("mid", orderId)
			 .executeUpdate();
			System.out.println("--------------CORRECT---------------");
		} catch (Exception e) {			
			System.out.println("--------------ERROR---------------");
			return false;
		}
		return true;
	}
	
	
}
