package com.edaisong.entity;

public class MenuEntity extends AuthorityMenuClass{
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
