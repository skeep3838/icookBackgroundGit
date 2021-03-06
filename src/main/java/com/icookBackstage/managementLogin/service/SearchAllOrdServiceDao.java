package com.icookBackstage.managementLogin.service;

import java.util.List;

import com.icookBackstage.model.MemberBean;
import com.icookBackstage.model.helpQuestion;
import com.icookBackstage.model.orderBean;
import com.icookBackstage.model.orderDetail;

public interface SearchAllOrdServiceDao {
	public List<orderBean> searchAllOrders();
	public List<orderDetail> searchAllOrdDetails(int orderId);
	public void Delete(int orderId);
	public List<helpQuestion> searchHelpQuestion();
	public helpQuestion searchSingleHelpQuestion(int helpQAId);
	public MemberBean searchSingleMember(int userId);
	public void finishUpdateResponseStatus(int helpQAId);
}
