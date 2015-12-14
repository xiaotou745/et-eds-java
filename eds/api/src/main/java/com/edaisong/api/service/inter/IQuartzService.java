package com.edaisong.api.service.inter;


import com.edaisong.entity.QuartzServiceModel;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedQuartzServiceReq;
import com.edaisong.entity.req.QuartzUpdateReq;

public interface IQuartzService {
	/**
	 * @author haichao
	 * @date 2015年12月10日 10:15:58 获取所有服务列表
	 * */
	PagedResponse<QuartzServiceModel> pagedQuery(PagedQuartzServiceReq req);

	int insert(QuartzServiceModel record);
	int update(QuartzServiceModel record);
	boolean checkCron(String cron);
	/**
	 * @author haichao
	 * @date 2015年12月10日 10:18:44 设置启动关闭服务
	 * */
	int mainJob(QuartzUpdateReq req);
}
