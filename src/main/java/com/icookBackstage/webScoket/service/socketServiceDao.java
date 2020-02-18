package com.icookBackstage.webScoket.service;

import java.util.List;

import com.icookBackstage.model.chatMember;
import com.icookBackstage.model.socketBean;

public interface socketServiceDao {
	public boolean saveMessage(socketBean temp);
	public boolean checkMessageExist(int userId);
	public List<chatMember> getAllChatMember();
}
