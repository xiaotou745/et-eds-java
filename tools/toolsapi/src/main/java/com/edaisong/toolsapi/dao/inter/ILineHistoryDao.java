package com.edaisong.toolsapi.dao.inter;

import com.edaisong.toolsentity.LineHistory;
import com.edaisong.toolsentity.common.PagedResponse;

public interface ILineHistoryDao {
	PagedResponse<LineHistory> getList();
}
