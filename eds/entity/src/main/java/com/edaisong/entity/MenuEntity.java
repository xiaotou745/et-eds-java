package com.edaisong.entity;

import java.io.Serializable;

public class MenuEntity extends AuthorityMenuClass implements Serializable{
    public Integer getAccoutid() {
		return accoutid;
	}

	public void setAccoutid(Integer accoutid) {
		this.accoutid = accoutid;
	}

	public Integer getMenuid() {
		return menuid;
	}

	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}
	
	private Integer accoutid;

    private Integer menuid;
}
