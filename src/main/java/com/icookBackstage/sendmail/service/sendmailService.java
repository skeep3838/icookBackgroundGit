package com.icookBackstage.sendmail.service;

import com.icookBackstage.model.MemberBean;
import com.icookBackstage.model.ProductOrder;

public interface sendmailService {
	
	public void sendOrderConfirmation(ProductOrder productOrder);
	
	public boolean sendResponseQuestion(ProductOrder productOrder);

	boolean searchAccount(String account);

	MemberBean searchMemberBean(String account);
	
	public void modifyVerificationStatus(String userId); 
}
