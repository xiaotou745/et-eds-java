package com.edaisong.entity.req;

/**
 *  B端验证验证码是否录入正确     
 *  B端更新商家手机号用到
 * @author CaoHeYang
 * @date  20151110
 */
public class BCheckCodeReq {
	private String phoneNo;//账号
	private String code;
	private Integer businessId;
	/**
	 * 手机号
	 * @return
	 */
	public String getPhoneNo() {
		return phoneNo;
	}
	/**
	 * 手机号
	 * @param phoneNo
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	/**
	 * 验证码
	 * @return
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 验证码
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}
}
