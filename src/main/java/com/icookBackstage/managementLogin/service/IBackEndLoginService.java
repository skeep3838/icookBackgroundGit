package com.icookBackstage.managementLogin.service;

import com.icookBackstage.managementLogin.dao.impl.BackEndLoginDAOImpl;
import com.icookBackstage.model.Manageral;

public interface IBackEndLoginService {

	void getBackEndLoginDAOImpl(BackEndLoginDAOImpl dao);
	int chickPassword(Manageral managerl);
	Manageral chickPasswordAndGetManageral(Manageral managerl);
	Integer getUnchickOrder();
	
	Integer getProductStock();
	
	Integer getUnchickMeg();

}