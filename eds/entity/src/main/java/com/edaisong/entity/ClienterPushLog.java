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
	 * 新订单推送时间
	 */
	private Date createTime;


	/**
	 * 订单处理后（接单取消订单）推送时间
	 */
	private Date processTime;
	
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
	 * 新订单推送时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 新订单推送时间
	 * @param createTime 新订单推送时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 订单处理后（接单取消订单）推送时间
	 * @return
	 */
	public Date getProcessTime() {
		return processTime;
	}
	/**
	 * 订单处理后（接单取消订单）推送时间
	 * @param processTime
	 */
	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}


}