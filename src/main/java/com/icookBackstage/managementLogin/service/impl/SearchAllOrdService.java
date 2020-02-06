package com.icookBackstage.managementLogin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icookBackstage.managementLogin.dao.AllOrdDao;
import com.icookBackstage.managementLogin.service.SearchAllOrdServiceDao;
import com.icookBackstage.model.orderBean;
import com.icookBackstage.model.orderDetail;


@Transactional
@Service
public class SearchAllOrdService implements SearchAllOrdServiceDao {
	AllOrdDao dao;
	@Autowired
	public void setDao(AllOrdDao dao) {
		this.dao = dao;
	}
	@Override
	public List<orderBean> searchAllOrders() {
		List<orderBean> list = dao.getAllOrders();
		return list;
	}
	public List<orderDetail> searchAllOrdDetails(int orderId) {
		List<orderDetail> list = dao.getAllOrderDetails(orderId);
		return list;
	}
	@Override
	public void Delete(int orderId) {
		dao.DeleteOrders(orderId);
	}
	
 }
