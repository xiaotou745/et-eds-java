package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.GroupBusinessLog;

public interface IGroupBusinessLogDao { 
    List<GroupBusinessLog> getList(int id);
}