package com.edaisong.entity;
import java.util.Date;

/**
 * 实体类BusinessSetpCharge. (属性说明自动提取数据库字段的描述信息)
 * @author wangyuchuan
 * @date 2016-02-18 12:36:32
 *
 */
public class BusinessSetpCharge {
	/**
	 * 
	 */
	private Long id;

	/**
	 * 阶梯标题
	 */
	private String title;

	/**
	 * 描述文本
	 */
	private String remark;

	/**
	 * 创建时间
	 */
	private Date createDate;

	/**
	 * 修改时间
	 */
	private Date modifyDate;

	/**
	 * 创建人
	 */
	private String createName;

	/**
	 * 修改人
	 */
	private String modifyName;

	/**
	 * 是否启用 1 启用 0不启用
	 */
	private Integer enable;

	/**
	 * 该策略步长,默认1
	 */
	private Double setpLength;

	/**
	 * 最低下限
	 */
	private Double minLimit;

	/**
	 * 最大上限(包含)
	 */
	private Double maxLimit;


	/**
	 * 获取
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置
	 * @param id 
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取阶梯标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置阶梯标题
	 * @param title 阶梯标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取描述文本
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置描述文本
	 * @param remark 描述文本
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取创建时间
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 设置创建时间
	 * @param createDate 创建时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 获取修改时间
	 */
	public Date getModifyDate() {
		return modifyDate;
	}
	/**
	 * 设置修改时间
	 * @param modifyDate 修改时间
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
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

	/**
	 * 获取修改人
	 */
	public String getModifyName() {
		return modifyName;
	}
	/**
	 * 设置修改人
	 * @param modifyName 修改人
	 */
	public void setModifyName(String modifyName) {
		this.modifyName = modifyName;
	}

	/**
	 * 获取是否启用 1 启用 0不启用
	 */
	public Integer getEnable() {
		return enable;
	}
	/**
	 * 设置是否启用 1 启用 0不启用
	 * @param enable 是否启用 1 启用 0不启用
	 */
	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	/**
	 * 获取该策略步长,默认1
	 */
	public Double getSetpLength() {
		return setpLength;
	}
	/**
	 * 设置该策略步长,默认1
	 * @param setpLength 该策略步长,默认1
	 */
	public void setSetpLength(Double setpLength) {
		this.setpLength = setpLength;
	}

	/**
	 * 获取最低下限
	 */
	public Double getMinLimit() {
		return minLimit;
	}
	/**
	 * 设置最低下限
	 * @param minLimit 最低下限
	 */
	public void setMinLimit(Double minLimit) {
		this.minLimit = minLimit;
	}

	/**
	 * 获取最大上限(包含)
	 */
	public Double getMaxLimit() {
		return maxLimit;
	}
	/**
	 * 设置最大上限(包含)
	 * @param maxLimit 最大上限(包含)
	 */
	public void setMaxLimit(Double maxLimit) {
		this.maxLimit = maxLimit;
	}


}