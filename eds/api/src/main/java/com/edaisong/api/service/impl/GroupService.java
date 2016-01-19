package com.edaisong.api.service.impl;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IGroupDao;
import com.edaisong.api.service.inter.IGroupService;
import com.edaisong.entity.Group;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.GroupApiConfigModel;
import com.edaisong.entity.domain.GroupModel;
import com.edaisong.entity.req.GroupReq;
import com.edaisong.entity.req.PagedGroupReq;
@Service
public class GroupService implements IGroupService {

	@Autowired
	private IGroupDao dao;
	@Override
	public List<GroupModel> getGroupListByID(GroupReq req) {
		return dao.getGroupListByID(
				req.getId());
	}

	@Override
	public List<GroupModel> getGroupList(GroupReq req) {
		return dao.getGroupList(
				req);
	}

	@Override
	public int add(Group record)  {
		 return 1;
	}
	
	@Override
	public int  update(Group record) 
	{
		return dao.updateByPrimaryKeySelective(record);
	}
	
	/**
	 * 查询第三方集团 
	 * @author CaoHeYang
	 * @param req
	 * @date 20160118
	 * @return
	 */
	@Override
	public PagedResponse<GroupApiConfigModel>  getGroupListByPage(PagedGroupReq req){
		return dao.getGroupListByPage(req);
	}
	/**
	 * 更新集团状态
	 * @author CaoHeYang
	 * @date 20160118
	 * @param group
	 * @return
	 */
	@Override
	public   Boolean  updateGroupStatus(Group group){
		return dao.updateGroupStatus(group)>0;
	}
	
	/**
	 * 判断集团是否已经存在
	 * @author CaoHeYang
	 * @date 20160118
	 * @param req
	 * @return
	 */
	@Override
	public Boolean hasExistsGroup(Group req){
		return dao.hasExistsGroup(req)>0;
	}
	
	/**
	 * 创建集团
	 *  @date 20160118
	 *  @author CaoHeYang
	 * @param record
	 * @return
	 */
	public int  addGroup(Group record) {
		return dao.addGroup(record);
	}
}
