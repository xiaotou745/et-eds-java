package com.edaisong.api.dal.dao.impl;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edaisong.api.dal.dao.inter.IGroupDao;
import com.edaisong.entity.Group;
import com.edaisong.entity.req.GroupReq;

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
		SqlSession session = superManReadOnlySqlServerSessionFactory
				.openSession();
		try {			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("groupname", record.getGroupname());
			paramMap.put("createname", "admin");
			paramMap.put("createtime", new Date());
			paramMap.put("modifyname", "");
			paramMap.put("modifytime",  new Date());
			paramMap.put("isvalid", 1);
			paramMap.put("ismodifybind",0);
	
			int result = session
					.insert("com.edaisong.api.dal.dao.inter.IGroupDao.insert",
							paramMap);
			session.commit(); 
			System.out.println("GroupDao-insert影响行数" + result);
		} catch (Exception e) {
			System.out.println("GroupDao-insert-Exception异常" + e.getMessage());
		} finally {
			session.close();
		}
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
		//return 0;
		
		SqlSession session = superManReadOnlySqlServerSessionFactory
				.openSession();
		try {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("id", record.getId());
			paramMap.put("groupname", record.getGroupname());
			
			int result = session
					.update("com.edaisong.api.dal.dao.inter.IGroupDao.updateByPrimaryKeySelective",
							paramMap);
			session.commit();
			System.out.println("GroupDao-updateByPrimaryKeySelective影响行数" + result);
		} catch (Exception e) {
			System.out.println("GroupDao-updateByPrimaryKeySelective-Exception异常" + e.getMessage());
		} finally {
			session.close();
		}
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
	
	@Override
	public List<Group> getGroupList(GroupReq req)
	{
		SqlSession session = superManReadOnlySqlServerSessionFactory
				.openSession();
		try {
			Map<String, Object> paramMap = new HashMap<>();
			//paramMap.put("id", id);
			if(req.getGroupName()!="")
				paramMap.put("groupname", req.getGroupName());
			List<Group> list = session
					.selectList(
							"com.edaisong.api.dal.dao.inter.IGroupDao.getGroupList",
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
