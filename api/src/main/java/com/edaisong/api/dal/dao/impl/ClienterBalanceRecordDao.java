package com.edaisong.api.dal.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.edaisong.api.dal.dao.inter.IClienterBalanceRecordDao;
import com.edaisong.core.common.ParseHelper;
import com.edaisong.core.util.StringUtils;
import com.edaisong.entity.ClienterBalanceRecord;
import com.edaisong.entity.common.ResponsePageList;
import com.edaisong.entity.domain.ClienterModel;
import com.edaisong.entity.domain.GroupModel;
import com.edaisong.entity.req.ClienterBalanceRecordReq;
import com.edaisong.entity.req.ClienterReq;

@Repository
public class ClienterBalanceRecordDao extends DaoBase implements IClienterBalanceRecordDao {

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(ClienterBalanceRecord record) {
		// TODO Auto-generated method stub
		return 0;
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
//	
//	@Override
//	public List<ClienterBalanceRecord> getRecordListByCId(Long cid) {
//		Map<String, Object> paramMap = new HashMap<>();
//		paramMap.put("clienterid", cid);
//		List<ClienterBalanceRecord> list = getMasterSqlSessionUtil().selectList(
//				"com.edaisong.api.dal.dao.inter.IClienterBalanceRecordDao.getRecordListByCId",
//				paramMap);
//		return list;
//
//	}
	
	public ResponsePageList<ClienterBalanceRecord> query(ClienterBalanceRecordReq req) {

		Map<String, Object> map = new HashMap<String, Object>();		
		String Where = " 1=1 ";		
		
		
		int PageSize = 15;
		int CurrentPage = req.getCurrentPage();
		map.put("Where", Where);
		map.put("TotalRecord", 0);
		map.put("TotalPage", 0);
		map.put("PageSize", PageSize);
		map.put("CurrentPage", CurrentPage);
		List<ClienterModel> list = getMasterSqlSessionUtil()
				.selectList("com.edaisong.api.dal.dao.inter.IClienterDao.query",
						map);
		
		ResponsePageList<ClienterBalanceRecord> resp = new ResponsePageList<ClienterBalanceRecord>();		
		//resp.setResultList(list);
		resp.setPageSize(PageSize);
		resp.setCurrentPage(CurrentPage);
		//resp.setTotalRecord(ParseHelper.ToInt(map.get("TotalRecord"), 0));
		resp.setTotalPage(ParseHelper.ToInt(map.get("TotalPage"), 0));
		return resp;
	}

}
