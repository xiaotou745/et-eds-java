package com.edaisong.toolsentity.domain;

import java.io.Serializable;

import com.edaisong.toolsentity.AuthorityMenuClass;

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
