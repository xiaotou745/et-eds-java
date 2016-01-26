package com.edaisong.toolsapi.dao.impl;

import org.springframework.stereotype.Repository;

import com.edaisong.toolsapi.common.DaoBase;
import com.edaisong.toolsapi.dao.inter.ILineHistoryDao;
import com.edaisong.toolsentity.LineHistory;
import com.edaisong.toolsentity.common.PagedResponse;
import com.edaisong.toolsentity.req.LineHistoryReq;
import com.edaisong.toolsentity.req.PagedLineHistoryReq;

@Repository
public class LineHistoryDao extends DaoBase implements ILineHistoryDao { 
	
	@Override
	public PagedResponse<LineHistory> getList(PagedLineHistoryReq req) {
		return getMasterSqlSessionUtil().selectPageList("ILineHistoryDao.getPageList", req);
	}

	@Override
	public int addLineHistory(LineHistoryReq lineHistory) { 
		return getMasterSqlSessionUtil().insert("ILineHistoryDao.addLineHistory",lineHistory);
	}

	@Override
	public int deleteLineHistory(LineHistoryReq req) {
		return getMasterSqlSessionUtil().update("ILineHistoryDao.deleteLineHistory",req);
	}

	@Override
	public int modifyLineHistory(LineHistoryReq req) {
		return getMasterSqlSessionUtil().update("ILineHistoryDao.modifyLineHistory",req);
	}

}
