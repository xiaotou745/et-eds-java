package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.QuartzServiceModel;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedQuartzServiceReq;
import com.edaisong.entity.req.QuartzUpdateReq;

/**
 * @author haichao
 *
 */
public interface IQuartzServiceDao {
	PagedResponse<QuartzServiceModel> pagedQuery(PagedQuartzServiceReq req);
	QuartzServiceModel selectById(long id);
	int insert(QuartzServiceModel record);
	int update(QuartzServiceModel record);
	/**
	 * @author haichao
	 * @date 2015年12月10日 10:18:44 修改服务状态
	 * */
	int updateStatus(QuartzUpdateReq req);
}
