package com.edaisong.entity.req;

import com.edaisong.entity.common.RequestBase;

public class AccountReq extends RequestBase{
	private int Id;
	private String Name;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
}
