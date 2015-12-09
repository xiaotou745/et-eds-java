package com.edaisong.toolsentity.view;

import com.edaisong.toolsentity.common.RequestBase;

public class ShortcutKeysQueryParams extends RequestBase {
	private String toolsName;

	private String keyword;

	public String getToolsName() {
		return toolsName;
	}

	public void setToolsName(String toolsName) {
		this.toolsName = toolsName;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
