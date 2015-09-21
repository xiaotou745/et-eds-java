package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.DeliveryCompany;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.DeliveryStatistics;
import com.edaisong.entity.req.PagedDeliveryStatisticsReq;

public interface IDeliveryCompanyDao {
    int deleteByPrimaryKey(Integer id);

    int insert(DeliveryCompany record);

    int insertSelective(DeliveryCompany record);

    DeliveryCompany selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DeliveryCompany record);

    int updateByPrimaryKey(DeliveryCompany record);
    
    List<DeliveryCompany> getDeliveryCompanyList();
	PagedResponse<DeliveryStatistics> getStatisticsList(PagedDeliveryStatisticsReq search);
}