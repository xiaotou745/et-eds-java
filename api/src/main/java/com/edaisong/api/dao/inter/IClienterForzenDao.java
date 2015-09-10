package com.edaisong.api.dao.inter;

import com.edaisong.entity.ClienterForzen;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedClienterForzenReq;

public interface IClienterForzenDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ClienterForzen record);

    int insertSelective(ClienterForzen record);

    ClienterForzen selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClienterForzen record);

    int updateByPrimaryKey(ClienterForzen record);
    /*
     * 获取冻结单列表
     * 茹化肖
     * 2015年9月9日11:08:27
     * */
    PagedResponse<ClienterForzen> getForzenList(PagedClienterForzenReq par);
}