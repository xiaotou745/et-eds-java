package com.edaisong.entity.resp;

import com.edaisong.entity.common.ResponseBase;

/**
 * 通用boolean response
 * @author pengyi
 * @date 20150721
 */
public class BooleanResp extends ResponseBase{
	private boolean data;

	public boolean isData() {
		return data;
	}

	public void setData(boolean data) {
		this.data = data;
	}
}
