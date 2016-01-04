package com.edaisong.entity;

import java.util.Date;

/**
 * 实体类ClienterPushLog. (属性说明自动提取数据库字段的描述信息)
 * @author wangyuchuan
 * @date 2016-01-04 09:48:19
 *
 */
public class ClienterPushLog {
	/**
	 * 主键 自增ID
	 */
	private Long iD;

	/**
	 * 订单id
	 */
	private Long orderId;

	/**
	 * 推送骑士id集合 以;id;id;格式存储
	 */
	private String clienterIds;

	/**
	 * 推送时间
	 */
	private Date createTime;


	/**
	 * 获取主键 自增ID
	 */
	public Long getID() {
		return iD;
	}
	/**
	 * 设置主键 自增ID
	 * @param iD 主键 自增ID
	 */
	public void setID(Long iD) {
		this.iD = iD;
	}

	/**
	 * 获取订单id
	 */
	public Long getOrderId() {
		return orderId;
	}
	/**
	 * 设置订单id
	 * @param orderId 订单id
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	/**
	 * 获取推送骑士id集合 以;id;id;格式存储
	 */
	public String getClienterIds() {
		return clienterIds;
	}
	/**
	 * 设置推送骑士id集合 以;id;id;格式存储
	 * @param clienterIds 推送骑士id集合 以;id;id;格式存储
	 */
	public void setClienterIds(String clienterIds) {
		this.clienterIds = clienterIds;
	}

	/**
	 * 推送时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 推送时间
	 * @param createTime 推送时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


}