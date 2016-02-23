package com.edaisong.toolsapi.dao.inter;

import java.util.List;

import com.edaisong.toolsentity.QuartzServiceModel;
import com.edaisong.toolsentity.common.PagedResponse;
import com.edaisong.toolsentity.req.PagedQuartzServiceReq;
import com.edaisong.toolsentity.req.QuartzUpdateReq;

/**
 * @author haichao
 *
 */
public interface IQuartzServiceDao {
	PagedResponse<QuartzServiceModel> pagedQuery(PagedQuartzServiceReq req);
	List<QuartzServiceModel> queryStartList(int appSource);
	int insert(QuartzServiceModel record);
	int update(QuartzServiceModel record);
	int delete(long id);
	/**
	 * @author haichao
	 * @date 2015年12月10日 10:18:44 修改服务状态
	 * */
	int updateStatus(QuartzUpdateReq req);
}
