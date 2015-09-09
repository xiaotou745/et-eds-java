package com.edaisong.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;


import com.edaisong.api.dao.inter.IClienterForzenDao;
import com.edaisong.api.service.inter.IClienterForzenService;
import com.edaisong.entity.ClienterForzen;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedClienterForzenReq;



public class ClienterForzenService implements IClienterForzenService{
	@Autowired
	private IClienterForzenDao clienterForzenDao;	
	/*
	 * 获取骑士冻结单信息
	 * 茹化肖
	 * 2015年9月9日11:30:19
	 * */
	@Override
	public PagedResponse<ClienterForzen> getForzenList(
			PagedClienterForzenReq par) {
		//if()
		return clienterForzenDao.getForzenList(par);
	}

}
