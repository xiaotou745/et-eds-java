package com.edaisong.entity.domain;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.edaisong.entity.common.Location;

public class OrderMapDetail {
	 @JsonProperty("OrderId")
    private long orderId;
    // 商户发单经度
    @JsonProperty("PubLongitude")
    private double pubLongitude;
    // 商户发单纬度
    @JsonProperty("HubLatitude")
    private double hubLatitude;
    // 商户发单时间
    @JsonProperty("PubDate")
    private String pubDate;
    // 骑士抢单经度
    @JsonProperty("GrabLongitude")
    private double grabLongitude;
    // 骑士抢单纬度
    @JsonProperty("GrabLatitude")
    private double grabLatitude;
    // 骑士抢单时间
    @JsonProperty("GrabTime")
    private String grabTime;
    // 骑士取货经度
    @JsonProperty("TakeLongitude")
    private double takeLongitude;
    // 骑士取货纬度
    @JsonProperty("TakeLatitude")
    private double takeLatitude;
    // 骑士取货时间
    @JsonProperty("TakeTime")
    private String takeTime;
    // 骑士完成订单经度
    @JsonProperty("CompleteLongitude")
    private double completeLongitude;
    // 骑士完成订单纬度
    @JsonProperty("CompleteLatitude")
    private double completeLatitude;
    // 骑士完成订单时间
    @JsonProperty("ActualDoneDate")
    private String actualDoneDate;
    // 抢单和完成点之间的距离
    @JsonProperty("GrabToCompleteDistance")
    private double grabToCompleteDistance;
    //订单实时坐标
    @JsonProperty("Locations")
    public List<Location> locations;
    /**
     *  订单id
     */
	public long getOrderId() {
		return orderId;
	}
	/**
     *  订单id
     */
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	/**
     *  商户发单经度
     */
	public double getPubLongitude() {
		return pubLongitude;
	}
	/**
     *  商户发单经度
     */
	public void setPubLongitude(double pubLongitude) {
		this.pubLongitude = pubLongitude;
	}
	/**
     *   商户发单纬度
     */
	public double getHubLatitude() {
		return hubLatitude;
	}
	/**
     *   商户发单纬度
     */
	public void setHubLatitude(double hubLatitude) {
		this.hubLatitude = hubLatitude;
	}
	/**
     *  商户发单时间
     */
	public String getPubDate() {
		return pubDate;
	}
	/**
     *  商户发单时间
     */
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	/**
     *  骑士抢单经度
     */
	public double getGrabLongitude() {
		return grabLongitude;
	}
	/**
     *  骑士抢单经度
     */
	public void setGrabLongitude(double grabLongitude) {
		this.grabLongitude = grabLongitude;
	}
	/**
     *  骑士抢单纬度
     */
	public double getGrabLatitude() {
		return grabLatitude;
	}
	/**
     *  骑士抢单纬度
     */
	public void setGrabLatitude(double grabLatitude) {
		this.grabLatitude = grabLatitude;
	}
	/**
     *  骑士抢单时间
     */
	public String getGrabTime() {
		return grabTime;
	}
	/**
     *  骑士抢单时间
     */
	public void setGrabTime(String grabTime) {
		this.grabTime = grabTime;
	}
	/**
     *  骑士取货经度
     */
	public double getTakeLongitude() {
		return takeLongitude;
	}
	/**
     *  骑士取货经度
     */
	public void setTakeLongitude(double takeLongitude) {
		this.takeLongitude = takeLongitude;
	}
	/**
     *  骑士取货纬度
     */
	public double getTakeLatitude() {
		return takeLatitude;
	}
	/**
     *  骑士取货纬度
     */
	public void setTakeLatitude(double takeLatitude) {
		this.takeLatitude = takeLatitude;
	}
	/**
     *  骑士取货时间
     */
	public String getTakeTime() {
		return takeTime;
	}
	/**
     *  骑士取货时间
     */
	public void setTakeTime(String takeTime) {
		this.takeTime = takeTime;
	}
	/**
     *  骑士完成订单经度
     */
	public double getCompleteLongitude() {
		return completeLongitude;
	}
	/**
     *  骑士完成订单经度
     */
	public void setCompleteLongitude(double completeLongitude) {
		this.completeLongitude = completeLongitude;
	}
	/**
     *  completeLatitude
     */
	public double getCompleteLatitude() {
		return completeLatitude;
	}
	/**
     *  completeLatitude
     */
	public void setCompleteLatitude(double completeLatitude) {
		this.completeLatitude = completeLatitude;
	}
	/**
     *  骑士完成订单时间
     */
	public String getActualDoneDate() {
		return actualDoneDate;
	}
	/**
     *  骑士完成订单时间
     */
	public void setActualDoneDate(String actualDoneDate) {
		this.actualDoneDate = actualDoneDate;
	}
	/**
     *  抢单和完成点之间的距离
     */
	public double getGrabToCompleteDistance() {
		return grabToCompleteDistance;
	}
	/**
     *  抢单和完成点之间的距离
     */
	public void setGrabToCompleteDistance(double grabToCompleteDistance) {
		this.grabToCompleteDistance = grabToCompleteDistance;
	}
	/**
	 * 实时坐标
	 * @return
	 */
	public List<Location> getLocations() {
		return locations;
	}
	/**
	 * 实时坐标
	 * @param locations
	 */
	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
    
}
