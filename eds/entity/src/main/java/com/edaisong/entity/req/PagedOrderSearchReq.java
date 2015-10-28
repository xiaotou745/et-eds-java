package com.edaisong.entity.req;

import com.edaisong.entity.common.PagedRequestBase;

/**
 * web后台 订单查询参数实体
 * @author CaoHeYang
 * @Date 20150728
 */
public class PagedOrderSearchReq extends PagedRequestBase{
     private String superManPhone ; 
     private String businessPhone ;
     private String orderId ;          
     private String originalOrderNo ; 
     private String superManName ; 
     private String businessName ; 
     private Integer orderStatus;  
     private String orderPubStart ;
     private String orderPubEnd ;
     private Integer groupId ;
     private String businessCity ;  
     private String hidDaochu ;  
     private String deliveryCompany ;
     private Integer businessID ; 
     private Integer auditStatus;
     /**
      * 超人电话
      */
	public String getSuperManPhone() {
		return superManPhone;
	}
	/**
     * 超人电话
     */
	public void setSuperManPhone(String superManPhone) {
		this.superManPhone = superManPhone;
	}
	/**
     * 商家电话
     */
	public String getBusinessPhone() {
		return businessPhone;
	}
	/**
     * 商家电话
     */
	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}
	/**
     * 订单号
     */
	public String getOrderId() {
		return orderId;
	}
	/**
     * 订单号
     */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
     * 原平台订单号
     */
	public String getOriginalOrderNo() {
		return originalOrderNo;
	}
	/**
     * 原平台订单号
     */
	public void setOriginalOrderNo(String originalOrderNo) {
		this.originalOrderNo = originalOrderNo;
	}
	/**
     * 超人姓名
     */
	public String getSuperManName() {
		return superManName;
	}
	/**
     * 超人姓名
     */
	public void setSuperManName(String superManName) {
		this.superManName = superManName;
	}
	/**
     * 商家姓名
     */
	public String getBusinessName() {
		return businessName;
	}
	/**
     * 商家姓名
     */
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	/**
     * 订单状态
     */
	public Integer getOrderStatus() {
		return orderStatus;
	}
	/**
     * 订单状态
     */
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	/**
     * 开始日期
     */
	public String getOrderPubStart() {
		return orderPubStart;
	}
	/**
     *  开始日期
     */
	public void setOrderPubStart(String orderPubStart) {
		this.orderPubStart = orderPubStart;
	}
	/**
     * 结束日期
     */
	public String getOrderPubEnd() {
		return orderPubEnd;
	}
	/**
     * 结束日期
     */
	public void setOrderPubEnd(String orderPubEnd) {
		this.orderPubEnd = orderPubEnd;
	}
	/**
     * 集团
     */
	public Integer getGroupId() {
		return groupId;
	}
	/**
     *  集团
     */
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	/**
     * 商户城市
     */
	public String getBusinessCity() {
		return businessCity;
	}
	/**
     *商户城市
     */
	public void setBusinessCity(String businessCity) {
		this.businessCity = businessCity;
	}
	/**
     * 是否导出数据
     */
	public String getHidDaochu() {
		return hidDaochu;
	}
	/**
     * 是否导出数据
     */
	public void setHidDaochu(String hidDaochu) {
		this.hidDaochu = hidDaochu;
	}
	/**
     * 物流公司
     */
	public String getDeliveryCompany() {
		return deliveryCompany;
	}
	/**
     *  物流公司
     */
	public void setDeliveryCompany(String deliveryCompany) {
		this.deliveryCompany = deliveryCompany;
	}
	public Integer getBusinessID() {
		return businessID;
	}
	public void setBusinessID(Integer businessID) {
		this.businessID = businessID;
	}
	/**
	 * 订单审核状态
	 * @return
	 */
	public Integer getAuditStatus() {
		return auditStatus;
	}
	/**
	 *  订单审核状态
	 * @param auditStatus
	 */
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}


}
