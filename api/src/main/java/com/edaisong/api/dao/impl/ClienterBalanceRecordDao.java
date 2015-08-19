package com.edaisong.api.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IClienterBalanceRecordDao;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.core.util.StringUtils;
import com.edaisong.entity.ClienterBalanceRecord;
import com.edaisong.entity.common.PagedResponse;
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
	
	@Override
	public PagedResponse<ClienterBalanceRecord> query(ClienterBalanceRecordReq req) {

		Map<String, Object> map = new HashMap<String, Object>();		
		String Where = " 1=1 ";				

		if (req.getClienterId()>0) {
			Where += " and clienterid='" + req.getClienterId() + "'";
		}
		
		int PageSize = 15;
		int CurrentPage = req.getCurrentPage();
		map.put("Where", Where);
		map.put("TotalRecord", 0);
		map.put("TotalPage", 0);
		map.put("PageSize", PageSize);
		map.put("CurrentPage", CurrentPage);
		List<ClienterBalanceRecord> list = getMasterSqlSessionUtil()
				.selectList("com.edaisong.api.dao.inter.IClienterBalanceRecordDao.query",
						map);
		
		PagedResponse<ClienterBalanceRecord> resp = new PagedResponse<ClienterBalanceRecord>();		
		resp.setResultList(list);
		resp.setPageSize(PageSize);
		resp.setCurrentPage(CurrentPage);
		resp.setTotalRecord(ParseHelper.ToInt(map.get("TotalRecord"), 0));
		resp.setTotalPage(ParseHelper.ToInt(map.get("TotalPage"), 0));
		return resp;
	}

}
