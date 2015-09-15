package com.edaisong.api.service.inter;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edaisong.entity.GroupBusinessLog;

@Service
public interface IGroupBusinessLogService {
	List<GroupBusinessLog> getList(int id);
	
	int insert(GroupBusinessLog gbl);
}
