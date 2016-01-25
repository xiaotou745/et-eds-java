package com.edaisong.toolsapi.service.impl;

import org.springframework.stereotype.Service;

import com.edaisong.toolsapi.service.inter.ILineHistoryService;
import com.edaisong.toolsentity.LineHistory;
import com.edaisong.toolsentity.common.PagedResponse;

@Service
public class LineHistoryService implements ILineHistoryService{

	@Override
	public PagedResponse<LineHistory> getList() {
		// TODO Auto-generated method stub
		return null;
	}

}
