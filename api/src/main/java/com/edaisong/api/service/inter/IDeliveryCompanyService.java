package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.DeliveryCompany;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.DeliveryStatistics;
import com.edaisong.entity.req.PagedDeliveryStatisticsReq;


public interface IDeliveryCompanyService {
	//获取物流公司
	List<DeliveryCompany> getDeliveryCompanyList();
	PagedResponse<DeliveryStatistics> getStatisticsList(PagedDeliveryStatisticsReq search);
}
