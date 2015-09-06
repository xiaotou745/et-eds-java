package com.edaisong.api.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IClienterBalanceRecordDao;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.core.util.StringUtils;
import com.edaisong.entity.ClienterBalanceRecord;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusinessModel;
import com.edaisong.entity.domain.ClienterModel;
import com.edaisong.entity.domain.GroupModel;
import com.edaisong.entity.req.PagedClienterBalanceRecordReq;
import com.edaisong.entity.req.PagedClienterReq;
import com.fasterxml.jackson.databind.deser.Deserializers.Base;

@Repository
public class ClienterBalanceRecordDao extends DaoBase implements IClienterBalanceRecordDao {

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 
	 */
	@Override
	public int insert(ClienterBalanceRecord record) {
		int result = getMasterSqlSessionUtil().insert("com.edaisong.api.dao.inter.IClienterBalanceRecordDao.insert",
				record);
		return result;
	}

	@Override
	public int insertSelective(ClienterBalanceRecord record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ClienterBalanceRecord selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(ClienterBalanceRecord record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(ClienterBalanceRecord record) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public PagedResponse<ClienterBalanceRecord> query(PagedClienterBalanceRecordReq req) {

		PagedResponse<ClienterBalanceRecord> model = getReadOnlySqlSessionUtil()
				.selectPageList(
						"com.edaisong.api.dao.inter.IClienterBalanceRecordDao.query",
						req);
		return model;
	}

	  /**
	   * 根据订单获取对象
	   * 引处使用了 nolock
	   * @author CaoHeYang
	   * @param id
	   * @date 20150831
	   * @return
	   */
	@Override
	public ClienterBalanceRecord getByOrderId(long id) {
		Map< String, Object> map=new HashedMap();
		map.put("id", id);
		return getMasterSqlSessionUtil().selectOne("com.edaisong.api.dao.inter.IClienterBalanceRecordDao.getByOrderId", map);
	}

}
