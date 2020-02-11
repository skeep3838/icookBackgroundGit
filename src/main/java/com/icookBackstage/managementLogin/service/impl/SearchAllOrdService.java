package com.icookBackstage.managementLogin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icookBackstage.accountManagement.dao.IAccountDAO;
import com.icookBackstage.managementLogin.dao.AllOrdDao;
import com.icookBackstage.managementLogin.service.SearchAllOrdServiceDao;
import com.icookBackstage.model.MemberBean;
import com.icookBackstage.model.helpQuestion;
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
	IAccountDAO Dao;
	@Autowired
	public void setDao(IAccountDAO dao) {
		this.Dao = dao;
	}
	@Override
	public List<orderBean> searchAllOrders() {
		List<orderBean> list = dao.getAllOrders();
		return list;
	}
	@Override
	public List<orderDetail> searchAllOrdDetails(int orderId) {
		List<orderDetail> list = dao.getAllOrderDetails(orderId);
		return list;
	}
	@Override
	public void Delete(int orderId) {
		dao.DeleteOrders(orderId);
	}
	@Override
	public List<helpQuestion> searchHelpQuestion() {
		List<helpQuestion> list = dao.getAllHelpQuestion();
		return list;
	}
	@Override
	public helpQuestion searchSingleHelpQuestion(int helpQAId) {
		helpQuestion temp = dao.getHelpQuestion(helpQAId);
		return temp;
	}
	@Override
	public MemberBean searchSingleMember(int userId) {
		MemberBean temp = Dao.getOneUserAccount(userId);
		return temp;
	}
	@Override
	public void finishUpdateResponseStatus(int helpQAId) {
		dao.UpdateQuestionStatus(helpQAId);
	}
	
 }
