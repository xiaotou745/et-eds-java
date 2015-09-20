package com.edaisong.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.edaisong.api.dao.inter.IMarkDao;
import com.edaisong.entity.Mark;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.MarkReq;
@Repository
public class MarkDao implements IMarkDao {

	@Override
	public PagedResponse<Mark> getMarkList(MarkReq par) {
		// TODO Auto-generated method stub
		return null;
	}

}
