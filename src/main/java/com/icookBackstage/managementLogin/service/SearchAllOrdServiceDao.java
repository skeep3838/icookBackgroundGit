package com.icookBackstage.managementLogin.service;

import java.util.List;

import com.icookBackstage.model.orderBean;
import com.icookBackstage.model.orderDetail;

public interface SearchAllOrdServiceDao {
	public List<orderBean> searchAllOrders();
	public List<orderDetail> searchAllOrdDetails(int orderId);
	public void Delete(int orderId);
}
