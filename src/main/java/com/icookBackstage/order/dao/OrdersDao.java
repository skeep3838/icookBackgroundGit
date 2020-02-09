package com.icookBackstage.order.dao;

import java.util.List;

import com.icookBackstage.model.MemberBean;
import com.icookBackstage.model.orderBean;
import com.icookBackstage.model.orderDetail;

public interface OrdersDao {
	public List<orderBean> getAllOrders(int userId); 
	public boolean checkOrders(int userId);
	public List<orderDetail> getAllOrderDetails(int orderId);
	public List<orderBean> Reverse(List<orderBean> list);
	public void DeleteOrders(int orderId);
	public boolean changeOrderStatus(int orderId,String status);
	public MemberBean searchMem(int userId);
}
