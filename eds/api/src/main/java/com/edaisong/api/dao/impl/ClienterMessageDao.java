package com.edaisong.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IClienterMessageDao;
import com.edaisong.entity.ClienterMessage;

/**
 * 
 * @author CaoHeYang
 * @date 20150909
 */
@Repository
public class ClienterMessageDao extends DaoBase implements IClienterMessageDao {

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(ClienterMessage record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(ClienterMessage record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ClienterMessage selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(ClienterMessage record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(ClienterMessage record) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 获取最新消息
	 * @author CaoHeYang
	 * @param clienterId
	 * @date 20150909
	 * @return
	 */
	@Override
	public ClienterMessage getLatestMessage(int clienterId) {
		return getReadOnlySqlSessionUtil()
				.selectOne("IClienterMessageDao.getLatestMessage"
						, clienterId);
	}

}
