package com.edaisong.toolsapi.dao.impl;

import org.springframework.stereotype.Repository;
import com.edaisong.toolsapi.dao.inter.ILineHistoryDao;
import com.edaisong.toolsentity.LineHistory;
import com.edaisong.toolsentity.common.PagedResponse;

@Repository
public class LineHistoryDao implements ILineHistoryDao {

	@Override
	public PagedResponse<LineHistory> getList() {
		// TODO Auto-generated method stub
		return null;
	}

}
