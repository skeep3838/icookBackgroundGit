package com.icookBackstage.webScoket.dao;

import java.util.List;

import com.icookBackstage.model.chatMember;
import com.icookBackstage.model.socketBean;

public interface socketDao {
	public boolean saveWebSocketMessage(socketBean temp);
	public boolean checkWebSocketMessage(int userId);
	public List<chatMember> getChatMember();
}
