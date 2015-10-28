package com.edaisong.toolsapi.service.impl;

import java.util.HashSet;
import java.util.Set;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.toolsapi.redis.RedisService;
import com.edaisong.toolsapi.service.inter.IAdminToolsService; 
@Service
public class AdminToolsService implements IAdminToolsService {

	@Autowired
	RedisService redisService = null;

	/**
	 * 获取redis工具
	 * 
	 * @author haichao
	 * @date 2015年9月29日 11:56:34
	 * @param key
	 *            键 ,sType类型，1 模糊查询 key 2获取指定key的值
	 * **/
	@Override
	public Set<String> getReidsTools(String key, int sType) {
		if (sType == 1) {
			// keys
			return redisService.keys(key);
		}
		Set<String> set = new HashSet<String>();
		if (sType == 2) {
			// 查询指定值
			String val=redisService.get(key, String.class,false);
			set.add(val);
			return set;
		}
		set.add("");
		return set;
	}

}