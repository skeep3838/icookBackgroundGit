package com.icookBackstage.order.service;

import java.util.List;

import com.icookBackstage.model.orderBean;
import com.icookBackstage.model.orderDetail;

public interface SearchOrdersServiceDao {
	public List<orderBean> searchAllDetails(int userId);
	public List<orderDetail> searchAllOrdDetails(int orderId);
	public void Delete(int orderId);
}
