package com.icookBackstage.sendmail.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icookBackstage.model.MemberBean;
import com.icookBackstage.sendmail.dao.sendmailDao;

@Repository
public class sendmailDaoImpl implements sendmailDao {
	SessionFactory factory;

	@Autowired
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean searchAccount(String account) {// 搜尋會員資料
		Session session = factory.getCurrentSession();
		String hql = "from MemberBean m where m.account = :mid";
		List<MemberBean> temp = (List<MemberBean>) session.createQuery(hql).setParameter("mid", account)
				.getResultList();
		if (temp != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public MemberBean searchMemberBean(String account) {// 搜尋會員資料
		Session session = factory.getCurrentSession();
		String hql = "from MemberBean m where m.account = :mid";
		MemberBean memberBean = (MemberBean) session.createQuery(hql).setParameter("mid", account).getSingleResult();
		return memberBean;

	}

	@Override
	public void verificationLetter(String userId) {
		Session session = factory.getCurrentSession();
		try {
			System.out.println(userId);
			String hql = "update MemberBean m set m.checkstatus = :checksta where m.userId = :mid";
			session.createQuery(hql).setParameter("checksta","Y").setParameter("mid", Integer.parseInt(userId)).executeUpdate();
			System.out.println("--------------CORRECT---------------");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("--------------ERROR---------------");
		}
	}
}