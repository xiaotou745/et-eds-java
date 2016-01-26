package com.edaisong.toolsentity;

import java.io.Serializable;
import java.util.Date;

public class LineHistory implements Serializable{
	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 所属平台
	 */
	private String devPlatform;

	/**
	 * 上线产品
	 */
	private String onLineProduct;

	/**
	 * 版本号
	 */
	private String devVersion;

	/**
	 * 上线时间
	 */
	private Date onLineTime;

	/**
	 * 上线内容
	 */
	private String onLineContent;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 创建人
	 */
	private String createName;


	/**
	 * 获取主键
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置主键
	 * @param id 主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取所属平台
	 */
	public String getDevPlatform() {
		return devPlatform;
	}
	/**
	 * 设置所属平台
	 * @param devPlatform 所属平台
	 */
	public void setDevPlatform(String devPlatform) {
		this.devPlatform = devPlatform;
	}

	/**
	 * 获取上线产品
	 */
	public String getOnLineProduct() {
		return onLineProduct;
	}
	/**
	 * 设置上线产品
	 * @param onLineProduct 上线产品
	 */
	public void setOnLineProduct(String onLineProduct) {
		this.onLineProduct = onLineProduct;
	}

	/**
	 * 获取版本号
	 */
	public String getDevVersion() {
		return devVersion;
	}
	/**
	 * 设置版本号
	 * @param devVersion 版本号
	 */
	public void setDevVersion(String devVersion) {
		this.devVersion = devVersion;
	}

	/**
	 * 获取上线时间
	 */
	public Date getOnLineTime() {
		return onLineTime;
	}
	/**
	 * 设置上线时间
	 * @param onLineTime 上线时间
	 */
	public void setOnLineTime(Date onLineTime) {
		this.onLineTime = onLineTime;
	}

	/**
	 * 获取上线内容
	 */
	public String getOnLineContent() {
		return onLineContent;
	}
	/**
	 * 设置上线内容
	 * @param onLineContent 上线内容
	 */
	public void setOnLineContent(String onLineContent) {
		this.onLineContent = onLineContent;
	}

	/**
	 * 获取备注
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置备注
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置创建时间
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取创建人
	 */
	public String getCreateName() {
		return createName;
	}
	/**
	 * 设置创建人
	 * @param createName 创建人
	 */
	public void setCreateName(String createName) {
		this.createName = createName;
	}

}
