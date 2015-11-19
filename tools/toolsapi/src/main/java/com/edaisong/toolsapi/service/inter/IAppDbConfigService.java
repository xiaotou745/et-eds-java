package com.edaisong.toolsapi.service.inter;

import com.edaisong.toolsentity.AppDbConfig;
import com.edaisong.toolsentity.common.PagedRequestBase;
import com.edaisong.toolsentity.common.PagedResponse;

public interface IAppDbConfigService {
    int deleteById(Long id);

    int insert(AppDbConfig record);

    int updateById(AppDbConfig record);
    PagedResponse<AppDbConfig> query(PagedRequestBase req);
}
