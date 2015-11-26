package com.edaisong.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IClienterForzenDao;
import com.edaisong.entity.ClienterForzen;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedClienterForzenReq;
@Repository
public class ClienterForzenDao  extends DaoBase implements IClienterForzenDao{

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 */
	@Override
	public int insert(ClienterForzen record) {
		return getMasterSqlSessionUtil().insert("IClienterForzenDao.insert", record);
	}

	@Override
	public int insertSelective(ClienterForzen record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ClienterForzen selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(ClienterForzen record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(ClienterForzen record) {
		return getMasterSqlSessionUtil().update("IClienterForzenDao.updateByPrimaryKey", record); 
	}
    /*
     * 获取冻结单列表
     * 茹化肖
     * 2015年9月9日11:08:27
     * */
	@Override
	public PagedResponse<ClienterForzen> getForzenList(
			PagedClienterForzenReq par) {
		PagedResponse<ClienterForzen> resp = new PagedResponse<ClienterForzen>();
		resp = getReadOnlySqlSessionUtil().selectPageList(
				"IClienterForzenDao.getFrozenList", par);
		return resp;
	}

}
