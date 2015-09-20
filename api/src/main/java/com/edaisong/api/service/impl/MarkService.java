package com.edaisong.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IMarkDao;
import com.edaisong.api.service.inter.IMarkService;
import com.edaisong.entity.Mark;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.MarkReq;
@Service
public class MarkService implements IMarkService {
	@Autowired
    private IMarkDao markDao;
	@Override
	public PagedResponse<Mark> getMarkList(MarkReq par) {
		return markDao.getMarkList(par);
	}

}
