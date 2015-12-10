package com.edaisong.api.service.inter;

import com.taobao.top.link.LinkException;

public interface ITaoBaoOrder {
	/**
	 * 淘点点发单
	 * @date 2015年11月11日 13:35:57
	 * @author haichao
	 * @return
	 * @throws LinkException 
	 * */
	void releaseOrder() throws LinkException;
	
	String getAccessToken();
}
