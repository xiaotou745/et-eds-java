package com.alipay.parmodel;

public class ParamBatchTransModel {
	private String email;//付款账号,需http://格式的完整路径，不允许加?id=123这类自定义参数
	private String account_name;//必填,付款账户名
	private String pay_date;//必填,付款当天日期，个人支付宝账号是真实姓名公司支付宝账号是公司名称
	private String batch_no;//批次号,必填，格式：年[4位]月[2位]日[2位]，如：20100801
	private String batch_fee;//付款总金额,必填，格式：当天日期[8位]+序列号[3至16位]，如：201008010000001
	private String batch_num;//付款笔数,必填，即参数detail_data的值中所有金额的总和
	
	//必填，格式：流水号1^收款方帐号1^真实姓名^付款金额1^备注说明1|流水号2^收款方帐号2^真实姓名^付款金额2^备注说明2....
	private String detail_info;//付款详细数据,必填，即参数detail_data的值中，“|”字符出现的数量加1，最大支持1000笔（即“|”字符出现的数量999个）
	
	public String getDetail_info() {
		return detail_info;
	}
	public void setDetail_info(String detail_info) {
		this.detail_info = detail_info;
	}
	private String notify_url;//服务器异步通知页面路径
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public String getPay_date() {
		return pay_date;
	}
	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
	}
	public String getBatch_no() {
		return batch_no;
	}
	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}
	public String getBatch_fee() {
		return batch_fee;
	}
	public void setBatch_fee(String batch_fee) {
		this.batch_fee = batch_fee;
	}
	public String getBatch_num() {
		return batch_num;
	}
	public void setBatch_num(String batch_num) {
		this.batch_num = batch_num;
	}
	
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	
}
