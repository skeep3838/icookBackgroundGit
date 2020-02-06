package com.icookBackstage.managementLogin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icookBackstage.managementLogin.dao.impl.BackEndLoginDAOImpl;
import com.icookBackstage.managementLogin.service.IBackEndLoginService;
import com.icookBackstage.model.Manageral;

@Service
public class BackEndLoginServiceImpl implements IBackEndLoginService {
	
	BackEndLoginDAOImpl dao;
	
	@Override
	@Autowired
	public void getBackEndLoginDAOImpl(BackEndLoginDAOImpl dao) {
		this.dao = dao;
	}
	
	//確認帳號並回傳ID
	@Override
	@Transactional
	public int chickPassword(Manageral managerl){
		boolean chick = dao.chickPassword(managerl);
		int MaId = (chick == true) ? dao.getAccrountId(managerl.getAccrount()) : 0 ;
		return MaId;
	}
	
	//確認帳號並回傳ID
	@Override
	@Transactional
	public Manageral chickPasswordAndGetManageral(Manageral managerl){
		boolean chick = dao.chickPassword(managerl);
		Manageral CurrentManager = (chick == true) ? 
									dao.getManageral(managerl.getAccrount()) : null;
		return CurrentManager;
	}
	
}
