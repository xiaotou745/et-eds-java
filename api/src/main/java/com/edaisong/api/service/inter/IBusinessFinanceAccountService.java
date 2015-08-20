package com.edaisong.api.service.inter;

import com.edaisong.entity.BusinessFinanceAccount;

public interface IBusinessFinanceAccountService {
	 BusinessFinanceAccount getDetailByBusinesID(Integer businessID);
}
