package com.edaisong.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IUserOptRecordDao;
import com.edaisong.entity.UserOptRecord;
@Repository
public class UserOptRecordDao extends DaoBase implements IUserOptRecordDao{
	@Override
	public int insert(UserOptRecord record) {
return getMasterSqlSessionUtil().insert("IUserOptRecordDao.insert", record);
	}

}
