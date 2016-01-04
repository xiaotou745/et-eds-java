package com.edaisong.api.dao.inter;

import java.util.Date;
import java.util.List;

import com.edaisong.entity.ClienterLocation;
import com.edaisong.entity.common.Location;
import com.edaisong.entity.req.GetPushClienterIdsReq;

public interface IClienterLocationDao {
    int deleteByPrimaryKey(Long id);

    int insert(ClienterLocation record);

    int insertSelective(ClienterLocation record);

    ClienterLocation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClienterLocation record);

    int updateByPrimaryKey(ClienterLocation record);
    
    /**
     * 获得实时坐标
     * @author CaoHeYang
     * @param start
     * @param end
     * @param clienterId
     * @return
     */
    List<Location> getLocationsByTime(Date start, Date end, int clienterId);
    
    /**
     * 里程计算获取商家指定范围内的骑士 
     * @author CaoHeYang
     * @date 20160104
     * @param req
     * @return
     */
    List<String>  getPushClienterIds(GetPushClienterIdsReq req);
}