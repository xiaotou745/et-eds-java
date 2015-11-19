package com.edaisong.toolsapi.service.inter;

import java.util.List;

import com.edaisong.toolsentity.AppDbConfig;
import com.edaisong.toolsentity.common.PagedRequestBase;
import com.edaisong.toolsentity.common.PagedResponse;
import com.edaisong.toolsentity.req.PagedAppDbConfigReq;

public interface IAppDbConfigService {
    int deleteById(Long id);

    int insert(AppDbConfig record);

    int updateById(AppDbConfig record);
    PagedResponse<AppDbConfig> query(PagedAppDbConfigReq req);
    
    List<AppDbConfig> queryList(PagedAppDbConfigReq req);
}
