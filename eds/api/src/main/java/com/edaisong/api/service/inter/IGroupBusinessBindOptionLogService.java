package com.edaisong.api.service.inter;

import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.GroupBusinessBindOptionLogModel;
import com.edaisong.entity.req.PagedBusinessBindLogReq;

/**
 *
 * @author  pengyi 
 * @date 2015年9月10日 上午9:59:57
 * @version 1.0
 * @parameter
 * @since
 */
public interface IGroupBusinessBindOptionLogService {
	PagedResponse<GroupBusinessBindOptionLogModel> getBusinessBindLogList(PagedBusinessBindLogReq req);
}
