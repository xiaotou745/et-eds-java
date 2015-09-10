package com.edaisong.api.service.inter;

import org.springframework.stereotype.Service;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.GroupBusinessModel;
import com.edaisong.entity.req.PagedGroupBusinessReq;
 
@Service
public interface IGroupBusinessService { 
	
	PagedResponse<GroupBusinessModel> getPageList(PagedGroupBusinessReq req);
	 
}
