package com.icookBackstage.webScoket.dao;

import com.icookBackstage.model.socketBean;

public interface socketDao {
	public boolean saveWebSocketMessage(socketBean temp);
	public boolean checkWebSocketMessage(int userId);
	
}
