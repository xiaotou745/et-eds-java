package com.edaisong.entity.domain;

import java.lang.Double;
import java.util.Date;
import java.util.List;

import com.edaisong.entity.OrderChild;
import com.edaisong.entity.OrderDetail;

/**
 * web站 订单详情页面实体model 
 * @author CaoHeYang
 * @Date 20150727
 */
public class OrderListModel {

    private int id ;
    private String orderNo ; 
    private String pickUpAddress ;
    private Date pubDate ;
    private String receviceName ;
    private String recevicePhoneNo ;
    private String receviceAddress ;
    private Date actualDoneDate ;
    private boolean isPay ;
    private Double amount ;
    private Double orderCommission ;
    private Double distribSubsidy ;
    private Double websiteSubsidy ;
    private String remark ;
	private byte status ;
    private int clienterId ;
    private int businessId ;
    private String receviceCity ;
    private double receviceLongitude ;
    private double receviceLatitude ;
    private int orderFrom ;
    private String orderFromName ;
    private long originalOrderId ;
    private String originalOrderNo ;
    private int quantity ;
    private Double weight ;
    private String receiveProvince ;
    private String receiveArea ;
    private String receiveProvinceCode ;
    private String receiveCityCode ;
    private String receiveAreaCode ;
    private int orderType ;
    private double km ;
    private int guoJuQty ;
    private int luJuQty ;
    private String songCanDate ;
    private int orderCount ;
    private Double commissionRate ;
    private String orderSign ;
    private String businessName ;
    private String businessPhoneNo ;
    private String businessPhoneNo2 ;
    private String businessAddress ;
    private String clienterName ;
    private String clienterPhoneNo ;
    private String groupName ;
    private String businessCity ;
    private String clienterTrueName ;
    private int groupId ;
    private Double accountBalance ;
    private Double adjustment ;
    private Double businessCommission ;
    private String pickupCode ;
    private int needUploadCount ;
    private int hadUploadCount ;
    private String receiptPic ;
    private String otherCancelReason ;
    private Double tipAmount ;
    private String payBy;
     public Double getTipAmount() {
		return tipAmount;
	}

	public void setTipAmount(Double tipAmount) {
		this.tipAmount = tipAmount;
	}

	private List<OrderChild> OrderChildList ;
     private Integer auditStatus;
    public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	/**
    / 子订单
    */
    private List<OrderDetail> OrderDetailList ;
    private Double settleMoney ;
    private int mealsSettleMode ;
    private int isJoinWithdraw ;
    private Double businessReceivable ;
    private String optUserName ;
    private double grabToCompleteDistance ;
    private Date grabTime ;
    private int isNotRealOrder ;
    private Double realOrderCommission ;
    private int isEnable ;
    private String settleType ;
    private Double settleValue ;
    private Double superManSettleValue ;
    private Date takeTime ;
    private Double deliveryCompanySettleMoney ;
    private int deliveryCompanyID ;
  
    private String deductCommissionReason ;

    private  int finishAll;
    private int isOrderChecked;
    

    
    /**
     * 是否已完成
     * @return
     */
    public int getFinishAll() {
		return finishAll;
	}

    /**
     * 是否已完成
     * @param finishAll
     */
	public void setFinishAll(int finishAll) {
		this.finishAll = finishAll;
	}

	/**
     * 订单是否需要审核
     * @return
     */
    public int getIsOrderChecked() {
		return isOrderChecked;
	}
 
    /**
     * 订单是否需要审核
     * @param isOrderChecked
     */
	public void setIsOrderChecked(int isOrderChecked) {
		this.isOrderChecked = isOrderChecked;
	}



	/**
     * 主键Id
     */
    public int getId() {
		return id;
	}



	/**
     *  主键Id
     */
	public void setId(int Id) {
		id = Id;
	}
	 /**
     * 订单号
     */
	public String getOrderNo() {
		return orderNo;
	}

    /**
     * 订单号
     */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

    /**
     * 取货地址
     */
	public String getPickUpAddress() {
		return pickUpAddress;
	}

    /**
     * 取货地址
     */
	public void setPickUpAddress(String pickUpAddress) {
		this.pickUpAddress = pickUpAddress;
	}

    /**
     * 发布时间
     */
	public Date getPubDate() {
		return pubDate;
	}

    /**
     * 发布时间
     */
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

    /**
     * 收货人
     */
	public String getReceviceName() {
		return receviceName;
	}

    /**
     * 收货人
     */
	public void setReceviceName(String receviceName) {
		this.receviceName = receviceName;
	}

    /**
     * 收货电话
     */
	public String getRecevicePhoneNo() {
		return recevicePhoneNo;
	}

    /**
     *  收货电话
     */
	public void setRecevicePhoneNo(String recevicePhoneNo) {
		this.recevicePhoneNo = recevicePhoneNo;
	}

    /**
     * 收货地址
     */
	public String getReceviceAddress() {
		return receviceAddress;
	}

    /**
     *  收货地址
     */
	public void setReceviceAddress(String receviceAddress) {
		this.receviceAddress = receviceAddress;
	}

    /**
     * 实际完成时间
     */
	public Date getActualDoneDate() {
		return actualDoneDate;
	}

    /**
     *  实际完成时间
     */
	public void setActualDoneDate(Date actualDoneDate) {
		this.actualDoneDate = actualDoneDate;
	}

    /**
     * 是否已付款
     */
	public boolean getIsPay() {
		return isPay;
	}

    /**
     * 是否已付款
     */
	public void setIsPay(boolean isPay) {
		this.isPay = isPay;
	}

    /**
     * 订单金额
     */
	public Double getAmount() {
		return amount;
	}

    /**
     *  订单金额
     */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

    /**
     * 订单佣金
     */
	public Double getOrderCommission() {
		return orderCommission;
	}

    /**
     *  订单佣金
     */
	public void setOrderCommission(Double orderCommission) {
		this.orderCommission = orderCommission;
	}

	/**
	 * 扣除补贴原因
	 * @return
	 */
    public String getDeductCommissionReason() {
		return deductCommissionReason;
	}

    /**
     * 扣除补贴原因
     * @param deductCommissionReason
     */
	public void setDeductCommissionReason(String deductCommissionReason) {
		this.deductCommissionReason = deductCommissionReason;
	}
    /**
     * 外送费
     */
	public Double getDistribSubsidy() {
		return distribSubsidy;
	}

    /**
     *  外送费
     */
	public void setDistribSubsidy(Double distribSubsidy) {
		this.distribSubsidy = distribSubsidy;
	}

    /**
     * 网站补贴
     */
	public Double getWebsiteSubsidy() {
		return websiteSubsidy;
	}

    /**
     *  网站补贴
     */
	public void setWebsiteSubsidy(Double websiteSubsidy) {
		this.websiteSubsidy = websiteSubsidy;
	}

    /**
     * 配送说明
     */
	public String getRemark() {
		return remark;
	}

    /**
     *  配送说明
     */
	public void setRemark(String remark) {
		this.remark = remark;
	}

    /**
     * 订单状态
     */
	public byte getStatus() {
		return status;
	}

    /**
     * 订单状态
     */
	public void setStatus(byte status) {
		this.status = status;
	}

    /**
     *  骑士Id
     */
	public int getClienterId() {
		return clienterId;
	}

    /**
     *  骑士Id
     */
	public void setClienterId(int clienterId) {
		this.clienterId = clienterId;
	}

    /**
     *  商户Id
     */
	public int getBusinessId() {
		return businessId;
	}

    /**
     *  商户Id
     */
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}

    /**
     * 收获人所在城市
     */
	public String getReceviceCity() {
		return receviceCity;
	}

    /**
     * 收获人所在城市
     */
	public void setReceviceCity(String receviceCity) {
		this.receviceCity = receviceCity;
	}

    /**
     * 收获地址经度
     */
	public double getReceviceLongitude() {
		return receviceLongitude;
	}

    /**
     * 收获地址经度
     */
	public void setReceviceLongitude(double receviceLongitude) {
		this.receviceLongitude = receviceLongitude;
	}

    /**
     *  收货地址纬度
     */
	public double getReceviceLatitude() {
		return receviceLatitude;
	}
    /**
     *  收货地址纬度
     */
	public void setReceviceLatitude(double receviceLatitude) {
		this.receviceLatitude = receviceLatitude;
	}

    /**
     * 订单来源
     */
	public int getOrderFrom() {
		return orderFrom;
	}
    /**
     * 订单来源
     */
	public void setOrderFrom(int orderFrom) {
		this.orderFrom = orderFrom;
	}

    /**
     *  订单来源
     */
	public String getOrderFromName() {
		return orderFromName;
	}

    /**
     *  订单来源
     */
	public void setOrderFromName(String orderFromName) {
		this.orderFromName = orderFromName;
	}

    /**
     * 原始订单Id
     */
	public long getOriginalOrderId() {
		return originalOrderId;
	}

    /**
     * 原始订单Id
     */
	public void setOriginalOrderId(long originalOrderId) {
		this.originalOrderId = originalOrderId;
	}

    /**
     * 原始订单号
     */
	public String getOriginalOrderNo() {
		return originalOrderNo;
	}

    /**
     * 原始订单号
     */
	public void setOriginalOrderNo(String originalOrderNo) {
		this.originalOrderNo = originalOrderNo;
	}

    /**
     * 数量
     */
	public int getQuantity() {
		return quantity;
	}

    /**
     * 数量
     */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

    /**
     * 重量
     */
	public Double getWeight() {
		return weight;
	}

    /**
     * 重量
     */
	public void setWeight(Double weight) {
		this.weight = weight;
	}

    /**
     * 收货省份
     */
	public String getReceiveProvince() {
		return receiveProvince;
	}

    /**
     * 收货省份
     */
	public void setReceiveProvince(String receiveProvince) {
		this.receiveProvince = receiveProvince;
	}

    /**
     * 收货地区
     */
	public String getReceiveArea() {
		return receiveArea;
	}

    /**
     * 收货地区
     */
	public void setReceiveArea(String receiveArea) {
		this.receiveArea = receiveArea;
	}

    /**
     * 收货省份编码
     */
	public String getReceiveProvinceCode() {
		return receiveProvinceCode;
	}

    /**
     * 收货省份编码
     */
	public void setReceiveProvinceCode(String receiveProvinceCode) {
		this.receiveProvinceCode = receiveProvinceCode;
	}

    /**
     * 收货城市编码
     */
	public String getReceiveCityCode() {
		return receiveCityCode;
	}

    /**
     * 收货城市编码
     */
	public void setReceiveCityCode(String receiveCityCode) {
		this.receiveCityCode = receiveCityCode;
	}

    /**
     * 收货地区编码
     */
	public String getReceiveAreaCode() {
		return receiveAreaCode;
	}

    /**
     * 收货地区编码
     */
	public void setReceiveAreaCode(String receiveAreaCode) {
		this.receiveAreaCode = receiveAreaCode;
	}

    /**
     * 订单类型
     */
	public int getOrderType() {
		return orderType;
	}

    /**
     * 订单类型
     */
	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

    /**
     * 
     */
	public double getKm() {
		return km;
	}

    /**
     * 
     */
	public void setKm(double km) {
		this.km = km;
	}

    /**
     * 锅具数量
     */
	public int getGuoJuQty() {
		return guoJuQty;
	}

    /**
     * 锅具数量
     */
	public void setGuoJuQty(int guoJuQty) {
		this.guoJuQty = guoJuQty;
	}

    /**
     *  炉具数量
     */
	public int getLuJuQty() {
		return luJuQty;
	}

    /**
     * 炉具数量
     */
	public void setLuJuQty(int luJuQty) {
		this.luJuQty = luJuQty;
	}

    /**
     * 送餐时间
     */
	public String getSongCanDate() {
		return songCanDate;
	}

    /**
     * 送餐时间
     */
	public void setSongCanDate(String songCanDate) {
		this.songCanDate = songCanDate;
	}

    /**
     * 订单数量
     */
	public int getOrderCount() {
		return orderCount;
	}

    /**
     * 订单数量
     */
	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}

    /**
     * 佣金比例
     */
	public Double getCommissionRate() {
		return commissionRate;
	}

    /**
     * 佣金比例
     */
	public void setCommissionRate(Double commissionRate) {
		this.commissionRate = commissionRate;
	}

    /**
     * 
     */
	public String getOrderSign() {
		return orderSign;
	}

    /**
     * 
     */
	public void setOrderSign(String orderSign) {
		this.orderSign = orderSign;
	}

    /**
     * 商家名称
     */
	public String getBusinessName() {
		return businessName;
	}

    /**
     * 商家名称
     */
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

    /**
     * 商家手机号
     */
	public String getBusinessPhoneNo() {
		return businessPhoneNo;
	}

    /**
     * 商家手机号
     */
	public void setBusinessPhoneNo(String businessPhoneNo) {
		this.businessPhoneNo = businessPhoneNo;
	}

    /**
     * 商家手机号2
     */
	public String getBusinessPhoneNo2() {
		return businessPhoneNo2;
	}

    /**
     * 商家手机号2
     */
	public void setBusinessPhoneNo2(String businessPhoneNo2) {
		this.businessPhoneNo2 = businessPhoneNo2;
	}

    /**
     * 商家地址
     */
	public String getBusinessAddress() {
		return businessAddress;
	}

    /**
     * 商家地址
     */
	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}

    /**
     * 骑士姓名
     */
	public String getClienterName() {
		return clienterName;
	}

    /**
     * 骑士姓名
     */
	public void setClienterName(String clienterName) {
		this.clienterName = clienterName;
	}

    /**
     * 骑士手机号
     */
	public String getClienterPhoneNo() {
		return clienterPhoneNo;
	}

    /**
     * 骑士手机号
     */
	public void setClienterPhoneNo(String clienterPhoneNo) {
		this.clienterPhoneNo = clienterPhoneNo;
	}

    /**
     *订单所属商家的集团名称
     */
	public String getGroupName() {
		return groupName;
	}

    /**
     *订单所属商家的集团名称
     */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

    /**
     * 骑士姓名
     */
	public String getBusinessCity() {
		return businessCity;
	}

    /**
     * 商家所在城市
     */
	public void setBusinessCity(String businessCity) {
		this.businessCity = businessCity;
	}

    /**
     * 商家所在城市
     */
	public String getClienterTrueName() {
		return clienterTrueName;
	}

    /**
     * 超人姓名
     */
	public void setClienterTrueName(String clienterTrueName) {
		this.clienterTrueName = clienterTrueName;
	}

    /**
     * 集团id
     */
	public int getGroupId() {
		return groupId;
	}

    /**
     * 集团id
     */
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

    /**
     * 骑士收入
     */
	public Double getAccountBalance() {
		return accountBalance;
	}

    /**
     * 骑士收入
     */
	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}

    /**
     * 补贴金额
     */
	public Double getAdjustment() {
		return adjustment;
	}

    /**
     * 补贴金额
     */
	public void setAdjustment(Double adjustment) {
		this.adjustment = adjustment;
	}

    /**
     * 商家结算比例
     */
	public Double getBusinessCommission() {
		return businessCommission;
	}

    /**
     * 商家结算比例
     */
	public void setBusinessCommission(Double businessCommission) {
		this.businessCommission = businessCommission;
	}

    /**
     *   取货码（目前只有全时再用）
     */
	public String getPickupCode() {
		return pickupCode;
	}

    /**
     *    取货码（目前只有全时再用）
     */
	public void setPickupCode(String pickupCode) {
		this.pickupCode = pickupCode;
	}

    /**
     * 需要上传的小票图片张数
     */
	public int getNeedUploadCount() {
		return needUploadCount;
	}

    /**
     * 需要上传的小票图片张数
     */
	public void setNeedUploadCount(int needUploadCount) {
		this.needUploadCount = needUploadCount;
	}

    /**
     * 已上传小票数量
     */
	public int getHadUploadCount() {
		return hadUploadCount;
	}

    /**
     * 已上传小票数量
     */
	public void setHadUploadCount(int hadUploadCount) {
		this.hadUploadCount = hadUploadCount;
	}

    /**
     * 小票图片路径用竖线分隔（|）
     */
	public String getReceiptPic() {
		return receiptPic;
	}

    /**
     *  小票图片路径用竖线分隔（|）
     */
	public void setReceiptPic(String receiptPic) {
		this.receiptPic = receiptPic;
	}

    /**
     * 取消原因
     */
	public String getOtherCancelReason() {
		return otherCancelReason;
	}

    /**
     * 取消原因
     */
	public void setOtherCancelReason(String otherCancelReason) {
		this.otherCancelReason = otherCancelReason;
	}

    /**
     * 商户结算金额
     */
	public Double getSettleMoney() {
		return settleMoney;
	}

    /**
     * 商户结算金额
     */
	public void setSettleMoney(Double settleMoney) {
		this.settleMoney = settleMoney;
	}

    /**
     *  餐费结算方式（0：线下结算 1：线上结算）
     */
	public int getMealsSettleMode() {
		return mealsSettleMode;
	}

    /**
     *  餐费结算方式（0：线下结算 1：线上结算）
     */
	public void setMealsSettleMode(int mealsSettleMode) {
		this.mealsSettleMode = mealsSettleMode;
	}

    /**
     *  订单是否已分账（0：否 1：是）
     */
	public int getIsJoinWithdraw() {
		return isJoinWithdraw;
	}

    /**
     *  订单是否已分账（0：否 1：是）
     */
	public void setIsJoinWithdraw(int isJoinWithdraw) {
		this.isJoinWithdraw = isJoinWithdraw;
	}

    /**
     * 商户应收
     */
	public Double getBusinessReceivable() {
		return businessReceivable;
	}

    /**
     * 商户应收
     */
	public void setBusinessReceivable(Double businessReceivable) {
		this.businessReceivable = businessReceivable;
	}

    /**
     * 操作人名称
     */
	public String getOptUserName() {
		return optUserName;
	}

    /**
     * 操作人名称
     */
	public void setOptUserName(String optUserName) {
		this.optUserName = optUserName;
	}

    /**
     * 抢单-完成的距离
     */
	public double getGrabToCompleteDistance() {
		return grabToCompleteDistance;
	}

    /**
     * 抢单-完成的距离
     */
	public void setGrabToCompleteDistance(double grabToCompleteDistance) {
		this.grabToCompleteDistance = grabToCompleteDistance;
	}

    /**
     * 任务接单时间
     */
	public Date getGrabTime() {
		return grabTime;
	}

    /**
     * 任务接单时间
     */
	public void setGrabTime(Date grabTime) {
		this.grabTime = grabTime;
	}

    /**
     * 是否无效订单
     */
	public int getIsNotRealOrder() {
		return isNotRealOrder;
	}

    /**
     * 是否无效订单
     */
	public void setIsNotRealOrder(int isNotRealOrder) {
		this.isNotRealOrder = isNotRealOrder;
	}

    /**
     * 最终给骑士的佣金
     */
	public Double getRealOrderCommission() {
		return realOrderCommission;
	}

    /**
     * 最终给骑士的佣金
     */
	public void setRealOrderCommission(Double realOrderCommission) {
		this.realOrderCommission = realOrderCommission;
	}

    /**
     * 是否删除
     */
	public int getIsEnable() {
		return isEnable;
	}

    /**
     * 是否删除
     */
	public void setIsEnable(int isEnable) {
		this.isEnable = isEnable;
	}

    /**
     * 结算类型
     */
	public String getSettleType() {
		return settleType;
	}

    /**
     * 结算类型
     */
	public void setSettleType(String settleType) {
		this.settleType = settleType;
	}

    /**
     * 配送公司结算数值
     */
	public Double getSettleValue() {
		return settleValue;
	}

    /**
     * 配送公司结算数值
     */
	public void setSettleValue(Double settleValue) {
		this.settleValue = settleValue;
	}

    /**
     * 骑士结算数值
     */
	public Double getSuperManSettleValue() {
		return superManSettleValue;
	}

    /**
     * 骑士结算数值
     */
	public void setSuperManSettleValue(Double superManSettleValue) {
		this.superManSettleValue = superManSettleValue;
	}

    /**
     * 取货时间
     */
	public Date getTakeTime() {
		return takeTime;
	}

    /**
     * 取货时间
     */
	public void setTakeTime(Date takeTime) {
		this.takeTime = takeTime;
	}

    /**
     * 物流公司结算金额
     */
	public Double getDeliveryCompanySettleMoney() {
		return deliveryCompanySettleMoney;
	}

    /**
     * 物流公司结算金额
     */
	public void setDeliveryCompanySettleMoney(Double deliveryCompanySettleMoney) {
		this.deliveryCompanySettleMoney = deliveryCompanySettleMoney;
	}

    /**
     * 物流公司id
     */
	public int getDeliveryCompanyID() {
		return deliveryCompanyID;
	}
	/**
	 * 物流公司id
	 * @param deliveryCompanyID
	 */
	public void setDeliveryCompanyID(int deliveryCompanyID) {
		this.deliveryCompanyID = deliveryCompanyID;
	}

	public List<OrderChild> getOrderChildList() {
		return OrderChildList;
	}

	public void setOrderChildList(List<OrderChild> orderChildList) {
		OrderChildList = orderChildList;
	}

	public List<OrderDetail> getOrderDetailList() {
		return OrderDetailList;
	}

	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
		OrderDetailList = orderDetailList;
	}

	
	/**
	 * 集团 订单列表 支出方
	 * @return
	 */
	public String getPayBy() {
		return payBy;
	}

	/**
	 * 集团 订单列表 支出方
	 * @return
	 */
	public void setPayBy(String payBy) {
		this.payBy = payBy;
	}
	
	
}
