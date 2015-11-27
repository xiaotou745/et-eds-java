package com.edaisong.entity.req;

public class BusinessRegisterReq {
	private String phoneNo;
	
	private String passWord;
	
	private String code;
 
    private String appkey;
    
  
    private String ssid;
    private String timespan;
    /*
     * 注册来源,默认1原注册商户;2闪送商户
     */
    private int registerFrom;
	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public String getSsid() {
		return ssid;
	}

	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	public String getTimespan() {
		return timespan;
	}

	public void setTimespan(String timespan) {
		this.timespan = timespan;
	}

	public int getRegisterFrom() {
		return registerFrom;
	}

	public void setRegisterFrom(int registerFrom) {
		this.registerFrom = registerFrom;
	}
}
