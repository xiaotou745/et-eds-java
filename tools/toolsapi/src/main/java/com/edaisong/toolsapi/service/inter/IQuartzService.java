package com.edaisong.toolsapi.service.inter;


import java.util.List;

import com.edaisong.toolsentity.QuartzServiceModel;
import com.edaisong.toolsentity.common.PagedResponse;
import com.edaisong.toolsentity.domain.QuartzHttpModel;
import com.edaisong.toolsentity.req.PagedQuartzServiceReq;
import com.edaisong.toolsentity.req.QuartzUpdateReq;

public interface IQuartzService {
	/**
	 * @author haichao
	 * @date 2015年12月10日 10:15:58 获取所有服务列表
	 * */
	PagedResponse<QuartzServiceModel> pagedQuery(PagedQuartzServiceReq req);
	QuartzHttpModel queryStartList(int appSource);
	int insert(QuartzServiceModel record);
	int update(QuartzServiceModel record);
	int delete(long id);
	boolean checkCron(String cron);
	/**
	 * @author haichao
	 * @date 2015年12月10日 10:18:44 设置启动关闭服务
	 * */
	int updateStatus(QuartzUpdateReq req);
}
