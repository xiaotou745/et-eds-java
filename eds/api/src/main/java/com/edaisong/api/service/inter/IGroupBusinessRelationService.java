package com.edaisong.api.service.inter;

import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.GroupBusinessRelationModel;
import com.edaisong.entity.req.BusinessBindOptionReq;
import com.edaisong.entity.req.PagedBizBindsReq;

/**
 *
 * @author  pengyi 
 * @date 2015年9月9日 上午10:54:36
 * @version 1.0
 * @parameter
 * @since
 */
public interface IGroupBusinessRelationService {
	
	PagedResponse<GroupBusinessRelationModel> getBusinessBindList(PagedBizBindsReq req);
	
	boolean removeBusinessBind(BusinessBindOptionReq req);
	
	boolean addBusinessBind(BusinessBindOptionReq req);
	
	boolean checkHaveBind(BusinessBindOptionReq req);
	
	PagedResponse<GroupBusinessRelationModel> getBusinessList(PagedBizBindsReq req);
}
