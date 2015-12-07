package com.edaisong.toolsentity.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类Menu. (属性说明自动提取数据库字段的描述信息)
 * 
 * @author wangyuchuan
 * @date 2015-11-19 17:15:25
 *
 */
public class Menu implements Serializable {
	/**
	 * 菜单ID
	 */
	private Integer id;

	/**
	 * 菜单名称
	 */
	private String name;

	/**
	 * 父菜单ID
	 */
	private Integer parentId;

	/**
	 * 菜单URL
	 */
	private String url;

	/**
	 * 图标
	 */
	private String icon;

	/**
	 * 排序
	 */
	private Integer orderBy;

	/**
	 * 是否在新窗口中打开
	 */
	private Boolean openNewWindow;

	/**
	 * 创建人
	 */
	private String createBy;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * Jva ViewPath路径
	 */
	private String viewPath;

	/**
	 * 获取菜单ID
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置菜单ID
	 * 
	 * @param id
	 *            菜单ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取菜单名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置菜单名称
	 * 
	 * @param name
	 *            菜单名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取父菜单ID
	 */
	public Integer getParentId() {
		return parentId;
	}

	/**
	 * 设置父菜单ID
	 * 
	 * @param parentId
	 *            父菜单ID
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/**
	 * 获取菜单URL
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置菜单URL
	 * 
	 * @param url
	 *            菜单URL
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获取图标
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * 设置图标
	 * 
	 * @param icon
	 *            图标
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * 获取排序
	 */
	public Integer getOrderBy() {
		return orderBy;
	}

	/**
	 * 设置排序
	 * 
	 * @param orderBy
	 *            排序
	 */
	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * 获取是否在新窗口中打开
	 */
	public Boolean getOpenNewWindow() {
		return openNewWindow;
	}

	/**
	 * 设置是否在新窗口中打开
	 * 
	 * @param openNewWindow
	 *            是否在新窗口中打开
	 */
	public void setOpenNewWindow(Boolean openNewWindow) {
		this.openNewWindow = openNewWindow;
	}

	/**
	 * 获取创建人
	 */
	public String getCreateBy() {
		return createBy;
	}

	/**
	 * 设置创建人
	 * 
	 * @param createBy
	 *            创建人
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	/**
	 * 获取创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置创建时间
	 * 
	 * @param createTime
	 *            创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取 java viewpath
	 * 
	 * @return 返回 java viewpath
	 */
	public String getViewPath() {
		return viewPath;
	}

	/**
	 * 设置 java viewpath
	 * 
	 * @param viewPath
	 *            java viewpath
	 */
	public void setViewPath(String viewPath) {
		this.viewPath = viewPath;
	}
}
