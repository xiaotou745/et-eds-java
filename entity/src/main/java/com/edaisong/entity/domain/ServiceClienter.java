package com.edaisong.entity.domain;

/**
 * B端任务统计接口 日服务骑士信息
 * @author CaoHeYang
 * @date 20150910
 */
public class ServiceClienter {
	private  int clienterId;
	private  int clienterName;
	private  int clienterPhone;
	private  int clienterPhoto;
	private  int orderCount;
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
	public int getClienterName() {
		return clienterName;
	}
	/**
	 * 骑士名称
	 * @param clienterName
	 */
	public void setClienterName(int clienterName) {
		this.clienterName = clienterName;
	}
	/**
	 * 骑士手机号
	 * @return
	 */
	public int getClienterPhone() {
		return clienterPhone;
	}
	/**
	 * 骑士手机号
	 * @param clienterPhone
	 */
	public void setClienterPhone(int clienterPhone) {
		this.clienterPhone = clienterPhone;
	}
	/**
	 * 骑士头像
	 * @return
	 */
	public int getClienterPhoto() {
		return clienterPhoto;
	}
	/**
	 * 骑士头像
	 * @param clienterPhoto
	 */
	public void setClienterPhoto(int clienterPhoto) {
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
