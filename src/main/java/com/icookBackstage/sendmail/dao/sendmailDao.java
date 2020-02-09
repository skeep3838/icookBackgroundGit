package com.icookBackstage.sendmail.dao;

import com.icookBackstage.model.MemberBean;


public interface sendmailDao {
	
	boolean searchAccount(String account);

	MemberBean searchMemberBean(String account);

	public void verificationLetter(String userId);
	
}
