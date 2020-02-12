package com.icookBackstage.managementLogin.dao;

import java.util.List;

import com.icookBackstage.model.helpQuestion;
import com.icookBackstage.model.orderBean;
import com.icookBackstage.model.orderDetail;

public interface AllOrdDao {
	public List<orderBean> getAllOrders(); 
	public boolean checkOrders(int userId);
	public List<orderDetail> getAllOrderDetails(int orderId);
	public List<orderBean> Reverse(List<orderBean> list);
	public List<helpQuestion> Reverse2(List<helpQuestion> list);
	public void DeleteOrders(int orderId);
	public List<helpQuestion> getAllHelpQuestion();
	public helpQuestion getHelpQuestion(int helpQAId);
	public void UpdateQuestionStatus(int helpQAId);
}
