package com.icookBackstage.webScoket.service;

import com.icookBackstage.model.socketBean;

public interface socketServiceDao {
	public boolean saveMessage(socketBean temp);
	public boolean checkMessageExist(int userId);
}
