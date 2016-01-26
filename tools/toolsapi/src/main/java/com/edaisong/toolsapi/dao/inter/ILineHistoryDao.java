package com.edaisong.toolsapi.dao.inter;

import com.edaisong.toolsentity.LineHistory;
import com.edaisong.toolsentity.common.PagedResponse;
import com.edaisong.toolsentity.req.LineHistoryReq;
import com.edaisong.toolsentity.req.PagedLineHistoryReq;

public interface ILineHistoryDao {
	PagedResponse<LineHistory> getList(PagedLineHistoryReq req);

	int addLineHistory(LineHistoryReq lineHistory);

	int deleteLineHistory(LineHistoryReq req);

	int modifyLineHistory(LineHistoryReq req);
}
