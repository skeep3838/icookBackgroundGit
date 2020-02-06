package com.icookBackstage.managementLogin.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icookBackstage.managementLogin.dao.IBackEndLoginDAO;
import com.icookBackstage.model.Manageral;

@Repository
public class BackEndLoginDAOImpl implements IBackEndLoginDAO {
	// 公用SessionFactory
	SessionFactory factory;

	// 注入factory
	@Override
	@Autowired
	public void getSessionFactory(SessionFactory factory) {
		this.factory = factory;
	}

	/**************************************
	 * BackEndLoginDAOImpl方法: 1.輸入manageral檢查帳號密碼 2.輸入account取得maId
	 * 3.輸入account取得manageral
	 **************************************/
	// 1.檢查帳號密碼
	@Override
	@SuppressWarnings("unchecked")
	public boolean chickPassword(Manageral manageral) {
		Session session = factory.getCurrentSession();
		String hqlStr = "FROM Manageral m WHERE m.accrount = :inputAccount and " + "m.password = :inputPassword";

		System.out.println("===== doing chickPassword =====");
		List<Manageral> managerls = session.createQuery(hqlStr).setParameter("inputAccount", manageral.getAccrount())
				.setParameter("inputPassword", manageral.getPassword()).getResultList();
		System.out.println("===== done chickPassword =====");
		return ((managerls.size() == 1) ? true : false);
	}

	// 2.取得maId
	@Override
	@SuppressWarnings("unchecked")
	public int getAccrountId(String inputAccrount) {
		Session session = factory.getCurrentSession();
		String hqlStr = "SELECT m.maId FROM Manageral m WHERE m.accrount = :inputAccrount";
		System.out.println("===== doing getAccoruntId =====");
		List<Object> managerals = session.createQuery(hqlStr).setParameter("inputAccrount", inputAccrount)
				.getResultList();
		System.out.println("===== done getAccoruntId =====");
		return ((managerals.size() == 1) ? (int) managerals.get(0) : 0);
	}

	// 2.取得manageral
	@Override
	@SuppressWarnings("unchecked")
	public Manageral getManageral(String inputAccrount) {
		Session session = factory.getCurrentSession();
		String hqlStr = "FROM Manageral m WHERE m.accrount = :inputAccrount";
		System.out.println("===== doing getManageral =====");
		List<Manageral> managerals = session.createQuery(hqlStr).setParameter("inputAccrount", inputAccrount)
				.getResultList();
		System.out.println("===== done getManageral =====");
		
		try {
			if (managerals.get(0) == null) {
				throw new Exception("this Accrount is not exist!!");
			}
		} catch (Exception e) {
			return null;
		}
		return managerals.get(0);
	}

}
