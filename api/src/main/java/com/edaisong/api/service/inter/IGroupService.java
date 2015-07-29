package com.edaisong.api.service.inter;


import com.edaisong.entity.Group;
import com.edaisong.entity.req.GroupReq;
import com.edaisong.entity.resp.GroupResp;

public interface IGroupService {
	public GroupResp   getGroupListByID(GroupReq req) ;	
	
	public GroupResp  getGroupList(GroupReq req); 
	
	public int  add(Group record) ;
	
	public int  update(Group record) ;
	
}
