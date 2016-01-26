package com.edaisong.toolsapi.service.inter;

import com.edaisong.toolsentity.LineHistory;
import com.edaisong.toolsentity.common.PagedResponse;


public interface ILineHistoryService {
	
	PagedResponse<LineHistory> getList();
}
