package com.icookBackstage.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icookBackstage.model.MemberBean;
import com.icookBackstage.model.orderBean;
import com.icookBackstage.model.orderDetail;
import com.icookBackstage.order.dao.OrdersDao;

@Transactional
@Service
public class SearchOrdersService implements SearchOrdersServiceDao {
	OrdersDao dao;
	@Autowired
	public void setDao(OrdersDao dao) {
		this.dao = dao;
	}
	@Override
	public List<orderBean> searchAllDetails(int userId) {
		List<orderBean> list = dao.getAllOrders(userId);
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
	
	@Override
	public MemberBean searchMember(int userId) {
		MemberBean temp = dao.searchMem(userId);
		return temp;
	}
	@Override
	public boolean changeOrderStatus(int orderId,String status) {
		boolean stat = dao.changeOrderStatus(orderId, status);
		return stat;
	}
	
 }
