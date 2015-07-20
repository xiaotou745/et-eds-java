package com.edaisong.api.service.inter;


import com.edaisong.entity.req.GroupReq;
import com.edaisong.entity.resp.GroupResp;

public interface IGroupService {
	public GroupResp   getGroupListByID(GroupReq req) ;	
	
}
