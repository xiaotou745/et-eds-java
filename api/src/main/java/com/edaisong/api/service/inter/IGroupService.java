package com.edaisong.api.service.inter;


import java.util.List;

import com.edaisong.entity.Group;
import com.edaisong.entity.domain.GroupModel;
import com.edaisong.entity.req.GroupReq;


public interface IGroupService {
	public List<GroupModel>   getGroupListByID(GroupReq req) ;	
	
	public List<GroupModel>  getGroupList(GroupReq req); 
	
	public int  add(Group record) ;
	
	public int  update(Group record) ;
	
}
