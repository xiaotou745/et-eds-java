package com.edaisong.api.dal.dao.inter;

import java.util.List;

import com.edaisong.entity.Clienter;
import com.edaisong.entity.domain.ClienterModel;
import com.edaisong.entity.req.ClienterOptionReq;
import com.edaisong.entity.req.ClienterReq;

public interface IClienterDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Clienter record);

    int insertSelective(Clienter record);

    Clienter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Clienter record);

    int updateByPrimaryKey(Clienter record);
    
    List<ClienterModel> getClienterList(ClienterReq req);
    
    int updateMoneyById(ClienterOptionReq req) ;
}