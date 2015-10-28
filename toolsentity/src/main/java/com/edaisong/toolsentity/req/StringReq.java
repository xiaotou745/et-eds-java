package com.edaisong.toolsentity.req;

import com.edaisong.toolsentity.common.RequestBase;

/**
 * 通用String参数请求
 * @author pengyi
 * @date 2015-07-21
 */
public class StringReq extends RequestBase{
	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
