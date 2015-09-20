package com.edaisong.api.service.impl;

import org.springframework.stereotype.Service;

import com.edaisong.api.service.inter.IMarkService;
import com.edaisong.entity.Mark;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.MarkReq;
@Service
public class MarkService implements IMarkService {

	@Override
	public PagedResponse<Mark> getTagList(MarkReq par) {
		// TODO Auto-generated method stub
		return null;
	}

}
