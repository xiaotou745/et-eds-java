package com.edaisong.entity.req;

import com.edaisong.entity.common.RequestBase;

/**
 *
 * @author  pengyi 
 * @date 2015年9月10日 下午2:26:08
 * @version 1.0
 * @parameter
 * @since
 */
public class AddNewMenuReq extends RequestBase{
	private Integer curId;//当前菜单id
	private String menuName;
	private String url;
	private String javaUrl;
	private Integer isButton;
	public Integer getCurId() {
		return curId;
	}
	public void setCurId(Integer curId) {
		this.curId = curId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getJavaUrl() {
		return javaUrl;
	}
	public void setJavaUrl(String javaUrl) {
		this.javaUrl = javaUrl;
	}
	public Integer getIsButton() {
		return isButton;
	}
	public void setIsButton(Integer isButton) {
		this.isButton = isButton;
	}
}
