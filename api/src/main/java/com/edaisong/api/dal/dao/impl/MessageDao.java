package com.edaisong.api.dal.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edaisong.api.dal.dao.inter.IMessageDao;
import com.edaisong.core.util.SqlSessionUtil;
import com.edaisong.entity.Message;
import com.edaisong.entity.domain.GlobalConfigModel;

@Repository
public class MessageDao implements IMessageDao {

	@Autowired
	private SqlSessionFactory superManReadOnlySqlServerSessionFactory;

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
		List<Message> model = SqlSessionUtil.wapperSession(
				superManReadOnlySqlServerSessionFactory).selectList(statement);
		return model;
	}

}
