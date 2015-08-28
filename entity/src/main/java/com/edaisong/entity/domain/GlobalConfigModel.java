package com.edaisong.entity.domain;

public class GlobalConfigModel {
	private String id;
	private String keyName;
	private String value;
	private String regx;
	private String remark;
	private Integer groupId;

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String valueString) {
		this.value = valueString;
	}

	public String getRegx() {
		return regx;
	}

	public void setRegx(String regx) {
		this.regx = regx;
	}
	// /// <summary>
	// ///保本利润比例
	// /// </summary>
	// private String commissionRatio ;
	//
	// /// <summary>
	// /// 保本网站补贴
	// /// </summary>
	// private String siteSubsidies ;
	//
	// /// <summary>
	// /// 按时间补贴
	// /// 5分加1元
	// /// 8分加1元
	// /// 10分加1元
	// /// </summary>
	// private String timeSubsidies ;
	//
	// /// <summary>
	// /// 佣金方式计算方式
	// /// </summary>
	// private String commissionFormulaMode ;
	//
	// /// <summary>
	// /// 满足金额补贴利润比例
	// /// </summary>
	// private String priceCommissionRatio ;
	//
	// /// <summary>
	// /// 满足金额网站补贴
	// /// </summary>
	// private String priceSiteSubsidies ;
	//
	// /// <summary>
	// /// 是否开启动态时间补贴(0不开启,1开启)
	// /// </summary>
	// private String isStarTimeSubsidies ;
	//
	// /// <summary>
	// /// 按金额补贴
	// /// 50补贴1元
	// /// 80补贴2元
	// /// 80补贴3元
	// /// </summary>
	// private String priceSubsidies ;
	//
	// /// <summary>
	// ///普通补贴佣金比例
	// /// </summary>
	// private String commonCommissionRatio ;
	//
	// /// <summary>
	// /// 普通网站补贴
	// /// </summary>
	// private String commonSiteSubsidies ;
	//
	// /// <summary>
	// ///时间段佣金比例
	// /// </summary>
	// private String timeSpanCommissionRatio ;
	//
	//
	// /// <summary>
	// ///时间段之内补贴价钱(（A） 上午10：00-13:00 下午16：00-19:00补贴N员)
	// /// </summary>
	// private String timeSpanInPrice ;
	//
	// /// <summary>
	// ///时间段之外补贴价钱 （B） 其他时间段补贴2元或者更低
	// /// </summary>
	// private String timeSpanOutPrice ;
	//
	// /// <summary>
	// ///跨店抢单补贴
	// /// </summary>
	// private String overStoreSubsidies ;
	//
	// /// <summary>
	// /// 是否开启跨店抢单补贴(0不开启,1开启)
	// /// </summary>
	// private String isStartOverStoreSubsidies ;
	//
	// /// <summary>
	// /// 骑士端有未完成订单上传一次经纬度给到服务端的时间间隔(单位为秒)
	// /// </summary>
	// private String hasUnFinishedOrderUploadTimeInterval ;
	//
	// /// <summary>
	// /// 骑士端没有未完成订单上传一次经纬度给到服务端的时间间隔(单位为秒)
	// /// </summary>
	// private String allFinishedOrderUploadTimeInterval ;
	//
	// /// <summary>
	// /// 订单推送给骑士的区域半径(单位为公里)
	// /// </summary>
	// private String pushRadius ;
	//
	// /// <summary>
	// ///骑士订单列表每页显示条数
	// /// </summary>
	// private String clienterOrderPageSize ;
	// /// <summary>
	// ///商家专属骑士接单响应时间
	// /// </summary>
	// private String exclusiveOrderTime ;
	//
	// /// <summary>
	// /// 策略Id
	// /// </summary>
	// private int strategyId ;
	// /// <summary>
	// /// 分组Id
	// /// </summary>
	// private int groupId ;
	// /// <summary>
	// /// 分组名称
	// /// </summary>
	// private String groupName ;
	// /// <summary>
	// /// 操作人
	// /// </summary>
	// private String optName ;
	//
	// /// <summary>
	// /// 骑士完成任务时间限制
	// /// </summary>
	// private String completeTimeSet ;
	//
	// /// <summary>
	// /// 雇主任务时间限制
	// /// </summary>
	// private String employerTaskTimeSet ;
	//
	// /// <summary>
	// /// 无效订单判定时抢单点和完成点的距离(米)
	// /// </summary>
	// private String grabToCompleteDistance ;
	//
	// /// <summary>
	// /// 无效订单判定时累计完成订单数量
	// /// </summary>
	// private String orderCountSetting ;
	// /// <summary>
	// /// 骑士提现小于等于X元支付手续费
	// /// </summary>
	// private String clienterWithdrawCommissionAccordingMoney ;
	// /// <summary>
	// /// 提现扣除手续费数值
	// /// </summary>
	// private String withdrawCommission ;
	//
	// private String rabToCompleteDistance;
	//
	// public String getRabToCompleteDistance() {
	// return rabToCompleteDistance;
	// }
	// public void setRabToCompleteDistance(String rabToCompleteDistance) {
	// this.rabToCompleteDistance = rabToCompleteDistance;
	// }
	// public String getCommissionRatio() {
	// return commissionRatio;
	// }
	// public void setCommissionRatio(String commissionRatio) {
	// this.commissionRatio = commissionRatio;
	// }
	// public String getSiteSubsidies() {
	// return siteSubsidies;
	// }
	// public void setSiteSubsidies(String siteSubsidies) {
	// this.siteSubsidies = siteSubsidies;
	// }
	// public String getTimeSubsidies() {
	// return timeSubsidies;
	// }
	// public void setTimeSubsidies(String timeSubsidies) {
	// this.timeSubsidies = timeSubsidies;
	// }
	// public String getCommissionFormulaMode() {
	// return commissionFormulaMode;
	// }
	// public void setCommissionFormulaMode(String commissionFormulaMode) {
	// this.commissionFormulaMode = commissionFormulaMode;
	// }
	// public String getPriceCommissionRatio() {
	// return priceCommissionRatio;
	// }
	// public void setPriceCommissionRatio(String priceCommissionRatio) {
	// this.priceCommissionRatio = priceCommissionRatio;
	// }
	// public String getPriceSiteSubsidies() {
	// return priceSiteSubsidies;
	// }
	// public void setPriceSiteSubsidies(String priceSiteSubsidies) {
	// this.priceSiteSubsidies = priceSiteSubsidies;
	// }
	// public String getIsStarTimeSubsidies() {
	// return isStarTimeSubsidies;
	// }
	// public void setIsStarTimeSubsidies(String isStarTimeSubsidies) {
	// this.isStarTimeSubsidies = isStarTimeSubsidies;
	// }
	// public String getPriceSubsidies() {
	// return priceSubsidies;
	// }
	// public void setPriceSubsidies(String priceSubsidies) {
	// this.priceSubsidies = priceSubsidies;
	// }
	// public String getCommonCommissionRatio() {
	// return commonCommissionRatio;
	// }
	// public void setCommonCommissionRatio(String commonCommissionRatio) {
	// this.commonCommissionRatio = commonCommissionRatio;
	// }
	// public String getCommonSiteSubsidies() {
	// return commonSiteSubsidies;
	// }
	// public void setCommonSiteSubsidies(String commonSiteSubsidies) {
	// this.commonSiteSubsidies = commonSiteSubsidies;
	// }
	// public String getTimeSpanCommissionRatio() {
	// return timeSpanCommissionRatio;
	// }
	// public void setTimeSpanCommissionRatio(String timeSpanCommissionRatio) {
	// this.timeSpanCommissionRatio = timeSpanCommissionRatio;
	// }
	// public String getTimeSpanInPrice() {
	// return timeSpanInPrice;
	// }
	// public void setTimeSpanInPrice(String timeSpanInPrice) {
	// this.timeSpanInPrice = timeSpanInPrice;
	// }
	// public String getTimeSpanOutPrice() {
	// return timeSpanOutPrice;
	// }
	// public void setTimeSpanOutPrice(String timeSpanOutPrice) {
	// this.timeSpanOutPrice = timeSpanOutPrice;
	// }
	// public String getOverStoreSubsidies() {
	// return overStoreSubsidies;
	// }
	// public void setOverStoreSubsidies(String overStoreSubsidies) {
	// this.overStoreSubsidies = overStoreSubsidies;
	// }
	// public String getIsStartOverStoreSubsidies() {
	// return isStartOverStoreSubsidies;
	// }
	// public void setIsStartOverStoreSubsidies(String
	// isStartOverStoreSubsidies) {
	// this.isStartOverStoreSubsidies = isStartOverStoreSubsidies;
	// }
	// public String getHasUnFinishedOrderUploadTimeInterval() {
	// return hasUnFinishedOrderUploadTimeInterval;
	// }
	// public void setHasUnFinishedOrderUploadTimeInterval(
	// String hasUnFinishedOrderUploadTimeInterval) {
	// this.hasUnFinishedOrderUploadTimeInterval =
	// hasUnFinishedOrderUploadTimeInterval;
	// }
	// public String getAllFinishedOrderUploadTimeInterval() {
	// return allFinishedOrderUploadTimeInterval;
	// }
	// public void setAllFinishedOrderUploadTimeInterval(
	// String allFinishedOrderUploadTimeInterval) {
	// this.allFinishedOrderUploadTimeInterval =
	// allFinishedOrderUploadTimeInterval;
	// }
	// public String getPushRadius() {
	// return pushRadius;
	// }
	// public void setPushRadius(String pushRadius) {
	// this.pushRadius = pushRadius;
	// }
	// public String getClienterOrderPageSize() {
	// return clienterOrderPageSize;
	// }
	// public void setClienterOrderPageSize(String clienterOrderPageSize) {
	// this.clienterOrderPageSize = clienterOrderPageSize;
	// }
	// public String getExclusiveOrderTime() {
	// return exclusiveOrderTime;
	// }
	// public void setExclusiveOrderTime(String exclusiveOrderTime) {
	// this.exclusiveOrderTime = exclusiveOrderTime;
	// }
	// public int getStrategyId() {
	// return strategyId;
	// }
	// public void setStrategyId(int strategyId) {
	// this.strategyId = strategyId;
	// }
	// public int getGroupId() {
	// return groupId;
	// }
	// public void setGroupId(int groupId) {
	// this.groupId = groupId;
	// }
	// public String getGroupName() {
	// return groupName;
	// }
	// public void setGroupName(String groupName) {
	// this.groupName = groupName;
	// }
	// public String getOptName() {
	// return optName;
	// }
	// public void setOptName(String optName) {
	// this.optName = optName;
	// }
	// public String getCompleteTimeSet() {
	// return completeTimeSet;
	// }
	// public void setCompleteTimeSet(String completeTimeSet) {
	// this.completeTimeSet = completeTimeSet;
	// }
	// public String getEmployerTaskTimeSet() {
	// return employerTaskTimeSet;
	// }
	// public void setEmployerTaskTimeSet(String employerTaskTimeSet) {
	// this.employerTaskTimeSet = employerTaskTimeSet;
	// }
	// public String getGrabToCompleteDistance() {
	// return grabToCompleteDistance;
	// }
	// public void setGrabToCompleteDistance(String grabToCompleteDistance) {
	// this.grabToCompleteDistance = grabToCompleteDistance;
	// }
	// public String getOrderCountSetting() {
	// return orderCountSetting;
	// }
	// public void setOrderCountSetting(String orderCountSetting) {
	// this.orderCountSetting = orderCountSetting;
	// }
	// public String getClienterWithdrawCommissionAccordingMoney() {
	// return clienterWithdrawCommissionAccordingMoney;
	// }
	// public void setClienterWithdrawCommissionAccordingMoney(
	// String clienterWithdrawCommissionAccordingMoney) {
	// this.clienterWithdrawCommissionAccordingMoney =
	// clienterWithdrawCommissionAccordingMoney;
	// }
	// public String getWithdrawCommission() {
	// return withdrawCommission;
	// }
	// public void setWithdrawCommission(String withdrawCommission) {
	// this.withdrawCommission = withdrawCommission;
	// }

}
