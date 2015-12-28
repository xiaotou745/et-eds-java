package com.edaisong.toolsentity.domain;

import java.util.List;

public class LogChartModel {
private List<String> urls;
private List<Double> timeData;
private List<Double> rateData;
private List<Integer> numData;
public List<String> getUrls() {
	return urls;
}
public void setUrls(List<String> urls) {
	this.urls = urls;
}
public List<Double> getTimeData() {
	return timeData;
}
public void setTimeData(List<Double> timeData) {
	this.timeData = timeData;
}
public List<Double> getRateData() {
	return rateData;
}
public void setRateData(List<Double> rateData) {
	this.rateData = rateData;
}
public List<Integer> getNumData() {
	return numData;
}
public void setNumData(List<Integer> numData) {
	this.numData = numData;
}
}
