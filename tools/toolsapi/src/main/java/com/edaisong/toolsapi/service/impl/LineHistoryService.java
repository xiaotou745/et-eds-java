package com.edaisong.toolsapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.toolsapi.dao.inter.ILineHistoryDao;
import com.edaisong.toolsapi.service.inter.ILineHistoryService;
import com.edaisong.toolsentity.LineHistory;
import com.edaisong.toolsentity.common.PagedResponse;
import com.edaisong.toolsentity.req.LineHistoryReq;
import com.edaisong.toolsentity.req.PagedLineHistoryReq;

@Service
public class LineHistoryService implements ILineHistoryService{

	@Autowired
	private ILineHistoryDao lineHistoryDao;
	@Override
	public PagedResponse<LineHistory> getList(PagedLineHistoryReq req) {
		return lineHistoryDao.getList(req);
	}
	@Override
	public int addLineHistory(LineHistoryReq lineHistory) {
		return lineHistoryDao.addLineHistory(lineHistory);
	}
	@Override
	public int deleteLineHistory(LineHistoryReq req) {
		return lineHistoryDao.deleteLineHistory(req);
	}
	@Override
	public int modifyLineHistory(LineHistoryReq req) {
		return lineHistoryDao.modifyLineHistory(req);
	}

}
