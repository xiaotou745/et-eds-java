package com.edaisong.toolsapi.service.inter;

import java.util.Set;

public interface IAdminToolsService {

	/**
	 * 获取redis工具
	 * @author haichao
	 * @date 2015年9月29日 11:56:34
	 * @param key 键 ,sType类型，1 模糊查询 key 2获取指定key的值 
	 * **/
	 Set<String> getReidsTools(String key,int sType);
}
