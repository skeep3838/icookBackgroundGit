package com.icookBackstage.sendmail.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icookBackstage.model.MemberBean;
import com.icookBackstage.model.ProductOrder;
import com.icookBackstage.sendmail.dao.sendmailDao;
import com.icookBackstage.sendmail.service.sendmailService;
import com.icookBackstage.sendmail.service.mailService;

@Transactional
@Service
public class sendmailServiceImpl implements sendmailService {
	sendmailDao dao;
	@Autowired
	public void setDao(sendmailDao dao) {
		this.dao = dao;
	}
    
    
    @Autowired
    mailService mailService;
    
	@Override
	public void sendOrderConfirmation(ProductOrder productOrder) {
		mailService.sendEmail(productOrder);
	}
	
	
	@Transactional
	@Override
	public boolean searchAccount(String account) {
		return dao.searchAccount(account);
		
	}
	
	@Transactional
	@Override
	public MemberBean searchMemberBean(String account) {
		return dao.searchMemberBean(account);
	}
	
	@Transactional
	@Override
	public void modifyVerificationStatus(String userId) {
		dao.verificationLetter(userId);
	}
	
}