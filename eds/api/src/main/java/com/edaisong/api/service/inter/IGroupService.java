package com.edaisong.api.service.inter;


import java.util.List;

import com.edaisong.entity.Group;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.GroupApiConfigModel;
import com.edaisong.entity.domain.GroupModel;
import com.edaisong.entity.req.GroupReq;
import com.edaisong.entity.req.PagedGroupReq;


public interface IGroupService {
	public List<GroupModel>   getGroupListByID(GroupReq req) ;	
	
	public List<GroupModel>  getGroupList(GroupReq req); 
	
	public int  add(Group record) ;
	
	public int  update(Group record) ;
	
	
	/**
	 * 查询第三方集团 
	 * @author CaoHeYang
	 * @param req
	 * @date 20160118
	 * @return
	 */
	public PagedResponse<GroupApiConfigModel>  getGroupListByPage(PagedGroupReq req); 
	
	/**
	 * 更新集团状态
	 * @author CaoHeYang
	 * @date 20160118
	 * @param group
	 * @return
	 */
	public   Boolean  updateGroupStatus(Group group);
}
