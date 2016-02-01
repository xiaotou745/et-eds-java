package com.edaisong.entity;

public class TestUserTbl {
    private Integer id;

    private String phoneno;
	private int isC;
	private int isB;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno == null ? null : phoneno.trim();
    }

	public int getIsB() {
		return isB;
	}

	public void setIsB(int isB) {
		this.isB = isB;
	}

	public int getIsC() {
		return isC;
	}

	public void setIsC(int isC) {
		this.isC = isC;
	}
}