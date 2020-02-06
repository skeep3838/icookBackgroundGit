package com.icookBackstage.managementLogin.dao;

import java.util.List;

import com.icookBackstage.model.orderBean;
import com.icookBackstage.model.orderDetail;

public interface AllOrdDao {
	public List<orderBean> getAllOrders(); 
	public boolean checkOrders(int userId);
	public List<orderDetail> getAllOrderDetails(int orderId);
	public List<orderBean> Reverse(List<orderBean> list);
	public void DeleteOrders(int orderId);
}
