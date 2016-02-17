package com.edaisong.toolsentity.req;

import com.edaisong.toolsentity.common.PagedRequestBase;

public class PagedLineHistoryReq extends PagedRequestBase{
/**
 * 	数据库
 */
	private String appName;
	/**
	 * 所属平台
	 */
	private int devPlatform;
	/**
	 * 上线产品
	 */
	private int onLineProduct;
	
	public int getDevPlatform() {
		return devPlatform;
	}

	public void setDevPlatform(int devPlatform) {
		this.devPlatform = devPlatform;
	}

	public int getOnLineProduct() {
		return onLineProduct;
	}

	public void setOnLineProduct(int onLineProduct) {
		this.onLineProduct = onLineProduct;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}
}
