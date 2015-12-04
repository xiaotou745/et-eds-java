package com.edaisong.toolsentity;

import java.io.Serializable;

public class AuthorityMenuClass implements Serializable{
    private Integer id;
    private Integer parid;
    private String menuname;
    private Boolean belock;
    private String url;
    private Boolean isbutton;
    private String authCode;
    private String javaUrl;

    public String getJavaUrl() {
		return javaUrl;
	}

	public void setJavaUrl(String javaUrl) {
		this.javaUrl = javaUrl;
	}

	public String getAuthCode() {
		return authCode==null?"":authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParid() {
        return parid;
    }

    public void setParid(Integer parid) {
        this.parid = parid;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname == null ? null : menuname.trim();
    }

    public Boolean getBelock() {
        return belock;
    }

    public void setBelock(Boolean belock) {
        this.belock = belock;
    }

    public String getUrl() {
        return url==null?"":url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Boolean getIsbutton() {
        return isbutton;
    }

    public void setIsbutton(Boolean isbutton) {
        this.isbutton = isbutton;
    }
}