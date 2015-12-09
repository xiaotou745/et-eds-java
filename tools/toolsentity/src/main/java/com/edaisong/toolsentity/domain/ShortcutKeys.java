package com.edaisong.toolsentity.domain;

import java.util.Date;

/**
 * 实体类ShortcutKeys. (属性说明自动提取数据库字段的描述信息)
 * 
 * @author wangyuchuan
 * @date 2015-12-09 15:52:46
 *
 */
public class ShortcutKeys {
	/**
	 * 自增ID
	 */
	private Integer id;

	/**
	 * 工具，哪个工具的快捷键
	 */
	private String toolsName;

	/**
	 * 快捷键
	 */
	private String key;

	/**
	 * 快捷键功能
	 */
	private String desc;

	/**
	 * 是否常用
	 */
	private Boolean isCommonUse;

	/**
	 * 创建人
	 */
	private String createBy;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 获取自增ID
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置自增ID
	 * 
	 * @param id
	 *            自增ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取工具，哪个工具的快捷键
	 */
	public String getToolsName() {
		return toolsName;
	}

	/**
	 * 设置工具，哪个工具的快捷键
	 * 
	 * @param toolsName
	 *            工具，哪个工具的快捷键
	 */
	public void setToolsName(String toolsName) {
		this.toolsName = toolsName;
	}

	/**
	 * 获取快捷键
	 */
	public String getKey() {
		return key;
	}

	/**
	 * 设置快捷键
	 * 
	 * @param key
	 *            快捷键
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * 获取快捷键功能
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * 设置快捷键功能
	 * 
	 * @param desc
	 *            快捷键功能
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * 获取是否常用
	 */
	public Boolean getIsCommonUse() {
		return isCommonUse;
	}

	/**
	 * 设置是否常用
	 * 
	 * @param isCommonUse
	 *            是否常用
	 */
	public void setIsCommonUse(Boolean isCommonUse) {
		this.isCommonUse = isCommonUse;
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
}