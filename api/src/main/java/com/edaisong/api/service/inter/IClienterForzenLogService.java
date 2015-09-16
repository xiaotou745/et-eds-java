package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.ClienterForzenLog;

public interface IClienterForzenLogService {
	List<ClienterForzenLog> getList(int forzenId);
}
