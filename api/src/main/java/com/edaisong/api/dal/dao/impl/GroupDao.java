package com.edaisong.api.dal.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edaisong.api.dal.dao.inter.IGroupDao;
import com.edaisong.entity.Group;

@Repository
public class GroupDao implements IGroupDao {

	@Autowired
	private SqlSessionFactory superManReadOnlySqlServerSessionFactory;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Group record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(Group record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Group selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Group record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Group record) {
		// TODO Auto-generated method stub
		return 0;
	}		
	
	@Override
	public List<Group> getGroupListByID(Long id)
	{
		SqlSession session = superManReadOnlySqlServerSessionFactory
				.openSession();
		try {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("id", id);
			List<Group> list = session
					.selectList(
							"com.edaisong.api.dal.dao.inter.IGroupDao.getGroupListByID",
							paramMap);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

}
