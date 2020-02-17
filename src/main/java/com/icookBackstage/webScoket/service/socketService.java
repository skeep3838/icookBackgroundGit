package com.icookBackstage.webScoket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icookBackstage.model.socketBean;
import com.icookBackstage.webScoket.dao.socketDao;

@Transactional
@Service
public class socketService implements socketServiceDao {
	socketDao dao;
	@Autowired
	public void setDao(socketDao dao) {
		this.dao = dao;
	}
	@Override
	public boolean saveMessage(socketBean temp) {
		boolean result = dao.saveWebSocketMessage(temp);
		if(result == true) return true;
		else return false;
	}
	@Override
	public boolean checkMessageExist(int userId) {
		boolean result = dao.checkWebSocketMessage(userId);
		if(result == true) return true;
		else return false;
	}
	
 }
