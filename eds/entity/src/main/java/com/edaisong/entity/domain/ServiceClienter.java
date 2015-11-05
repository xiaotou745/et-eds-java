package com.edaisong.entity.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * B端任务统计接口 日服务骑士信息
 * @author CaoHeYang
 * @date 20150910
 */
public class ServiceClienter {
	private  int clienterId;
	private  String clienterName;
	private  String clienterPhone;
	private  String clienterPhoto;
	private  int orderCount;
	@JsonIgnore
	private  String pubDate; 
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	/**
	 * 骑士id
	 * @return
	 */
	public int getClienterId() {
		return clienterId;
	}
	/**
	 * 骑士id
	 * @param clienterId
	 */
	public void setClienterId(int clienterId) {
		this.clienterId = clienterId;
	}
	/**
	 *骑士名称
	 * @return
	 */
	public String getClienterName() {
		return clienterName;
	}
	/**
	 * 骑士名称
	 * @param clienterName
	 */
	public void setClienterName(String clienterName) {
		this.clienterName = clienterName;
	}
	/**
	 * 骑士手机号
	 * @return
	 */
	public String getClienterPhone() {
		return clienterPhone;
	}
	/**
	 * 骑士手机号
	 * @param clienterPhone
	 */
	public void setClienterPhone(String clienterPhone) {
		this.clienterPhone = clienterPhone;
	}
	/**
	 * 骑士头像
	 * @return
	 */
	public String getClienterPhoto() {
		return clienterPhoto;
	}
	/**
	 * 骑士头像
	 * @param clienterPhoto
	 */
	public void setClienterPhoto(String clienterPhoto) {
		this.clienterPhoto = clienterPhoto;
	}
	/**
	 * 骑士完成订单量
	 * @return
	 */
	public int getOrderCount() {
		return orderCount;
	}
	/**
	 * 骑士完成订单量
	 * @param orderCount
	 */
	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}
}
