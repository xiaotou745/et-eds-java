package com.edaisong.api.dal.dao.inter;

import java.util.List;

import com.edaisong.entity.GlobalConfig;
import com.edaisong.entity.domain.GlobalConfigModel;
import com.edaisong.entity.param.ConfigSavePram;

public interface IGlobalConfigDao {
    int deleteByPrimaryKey(Integer id);

    int insert(GlobalConfig record);

    int insertSelective(GlobalConfig record);

    GlobalConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GlobalConfig record);

    int updateByPrimaryKey(GlobalConfig record);
    
     List<GlobalConfigModel> getGlobalConfigByGroupId(Integer id) ;
     Boolean SaveConfig(ConfigSavePram par);
}