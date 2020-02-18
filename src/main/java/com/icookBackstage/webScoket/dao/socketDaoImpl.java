package com.icookBackstage.webScoket.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icookBackstage.model.chatMember;
import com.icookBackstage.model.socketBean;

@Repository
public class socketDaoImpl implements socketDao{
	SessionFactory factory;
	@Autowired
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	@Override
	public boolean saveWebSocketMessage(socketBean temp) {
		try {
			Session session = factory.getCurrentSession();
			session.save(temp);
			System.out.println("---------------correct----------------");
		} catch (Exception e) {
			System.out.println("---------------error----------------");
			return false;
		}
		return true;
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean checkWebSocketMessage(int userId) {
		Session session = factory.getCurrentSession();
		List<socketBean> list = null;
		try {
			String hql = "from socketBean m where m.id = :mid";
			list = session.createQuery(hql).setParameter("mid",userId).getResultList();
			System.out.println(list);
			System.out.println("--------------CORRECT---------------");
		} catch (Exception e) {
			System.out.println("--------------ERROR---------------");
		}
		if(list == null) {
			return false;
		}
		else {
			return true;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<chatMember> getChatMember() {
		Session session = factory.getCurrentSession();
		List<chatMember> list = null;
		try {
			String hql = "from chatMember";
			list = session.createQuery(hql).getResultList();
			System.out.println("--------------CORRECT---------------");
		} catch (Exception e) {
			System.out.println("--------------ERROR---------------");
		}
		return list;
	}
	
	
}
