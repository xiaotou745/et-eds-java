package com.edaisong.api.dal.dao.impl;


import java.util.List;



import org.springframework.stereotype.Repository;

import com.edaisong.api.dal.dao.inter.IMessageDao;
import com.edaisong.entity.Message;

@Repository
public class MessageDao extends DaoBase implements IMessageDao {
	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Message record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Message record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Message selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Message record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Message record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Message> getMessageList() {
		String statement = "com.edaisong.api.dal.dao.inter.IMessageDao.getMessageList";
		List<Message> model = getReadOnlySqlSessionUtil().selectList(statement);
		return model;
	}

}
