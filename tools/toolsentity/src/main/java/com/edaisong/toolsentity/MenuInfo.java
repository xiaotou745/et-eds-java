package com.edaisong.toolsentity;

import java.io.Serializable;

public class MenuInfo  implements Serializable{
    private Integer id;

    private Integer parId;

    private String menuName;

    private Boolean beLock;

    private String url;

    private Boolean isButton;
    private String authCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

	public Integer getParId() {
		return parId;
	}

	public void setParId(Integer parId) {
		this.parId = parId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public Boolean getBeLock() {
		return beLock;
	}

	public void setBeLock(Boolean beLock) {
		this.beLock = beLock;
	}

	public Boolean getIsButton() {
		return isButton;
	}

	public void setIsButton(Boolean isButton) {
		this.isButton = isButton;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

    
}