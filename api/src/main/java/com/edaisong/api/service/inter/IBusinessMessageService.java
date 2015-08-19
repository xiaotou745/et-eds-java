package com.edaisong.api.service.inter;

import com.edaisong.entity.BusinessMessage;

public interface IBusinessMessageService {
	BusinessMessage getLatestMessage(int businessId);
}
