package com.edaisong.toolsentity.domain;

import com.edaisong.toolsentity.AuthorityMenuClass;

public class MenuDetail extends AuthorityMenuClass {

	private String parMenuName;
	private int jibie; 

	public int getJibie() {
		return jibie;
	}

	public void setJibie(int jibie) {
		this.jibie = jibie;
	}

	public String getParMenuName() {
		return parMenuName;
	}

	public void setParMenuName(String parMenuName) {
		this.parMenuName = parMenuName;
	}
}
