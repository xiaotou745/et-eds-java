package com.edaisong.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IMarkDao;
import com.edaisong.entity.Mark;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.MarkReq;
@Repository
public class MarkDao extends DaoBase implements IMarkDao {

	@Override
	public PagedResponse<Mark> getMarkList(MarkReq par) {
		PagedResponse<Mark> resp=new PagedResponse<Mark>();
		resp = getReadOnlySqlSessionUtil().selectPageList(
				"com.edaisong.api.dao.inter.IMarkDao.getMarkList", par);
		return resp;
	}

}
