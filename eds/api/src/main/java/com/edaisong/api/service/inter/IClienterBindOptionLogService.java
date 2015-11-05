package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.ClienterBindOptionLog;

public interface IClienterBindOptionLogService {
    int insert(ClienterBindOptionLog record);
    List<ClienterBindOptionLog> selectList(Long businessId,long clienterId);
}
