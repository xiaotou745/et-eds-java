package com.edaisong.api.dal.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.edaisong.api.dal.dao.inter.IServiceTestDao;
import com.edaisong.core.util.SqlSessionUtil;
import com.edaisong.entity.BusinessBalanceRecord;

@Repository
public class ServiceTestDao implements IServiceTestDao {
	@Autowired
	private SqlSessionFactory superManSqlServerSessionFactory;

	@Autowired
	private SqlSessionFactory superManReadOnlySqlServerSessionFactory;

	@Override
	public List<BusinessBalanceRecord> selectBusinessBalanceByID(
			int RecordType, String OperateTime) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("RecordType", RecordType);
		paramMap.put("OperateTime", OperateTime);
		List<BusinessBalanceRecord> list = SqlSessionUtil
				.wapperSession(superManReadOnlySqlServerSessionFactory)
				.selectList(
						"com.edaisong.api.dal.dao.inter.IServiceTestDao.selectBusinessBalanceByID",
						paramMap);
		return list;
	}

	@Override
	public int addBusinessBalance(BusinessBalanceRecord record) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("BusinessId", "902");
		paramMap.put("Amount", 500);
		paramMap.put("Status", 1);
		paramMap.put("Balance", 900);
		paramMap.put("RecordType", 9);
		paramMap.put("Operator", "zhaohl");
		paramMap.put("OperateTime", "2015-1-1");

		paramMap.put("WithwardId", "123");
		paramMap.put("RelationNo", "55555");
		paramMap.put("Remark", "mybatistest");
		return SqlSessionUtil
				.wapperSession(superManSqlServerSessionFactory)
				.insert("com.edaisong.api.dal.dao.inter.IServiceTestDao.addBusinessBalance",
						paramMap);
	}

	@Override
	public int updateBusinessBalance(BusinessBalanceRecord record) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", 164);
		paramMap.put("Amount", 100);
		paramMap.put("Balance", 100);
		return SqlSessionUtil
				.wapperSession(superManSqlServerSessionFactory)
				.update("com.edaisong.api.dal.dao.inter.IServiceTestDao.updateBusinessBalance",
						paramMap);
	}

	@Override
	public int deleteBusinessBalance(int id) {
		return SqlSessionUtil
				.wapperSession(superManSqlServerSessionFactory)
				.delete("com.edaisong.api.dal.dao.inter.IServiceTestDao.deleteBusinessBalance",
						id);
	}
}
