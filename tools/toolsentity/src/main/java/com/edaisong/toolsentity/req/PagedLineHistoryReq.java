package com.edaisong.toolsentity.req;

import com.edaisong.toolsentity.common.PagedRequestBase;

public class PagedLineHistoryReq extends PagedRequestBase{
	private String appName;
	/**
	 * 所属平台
	 */
	private String devPlatform;

	public String getDevPlatform() {
		return devPlatform;
	}

	public void setDevPlatform(String devPlatform) {
		this.devPlatform = devPlatform;
	}

	public String getOnLineProduct() {
		return onLineProduct;
	}

	public void setOnLineProduct(String onLineProduct) {
		this.onLineProduct = onLineProduct;
	}

	/**
	 * 上线产品
	 */
	private String onLineProduct;
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}
}
