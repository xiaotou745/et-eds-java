package com.edaisong.entity.req;

/**
 *
 * @author  pengyi 
 * @date 2015年9月10日 下午4:29:49
 * @version 1.0
 * @parameter
 * @since
 */
public class CardBindAlipayReq {
	private Integer userId;//用户id(如果是商家则为商家id,如果是骑士则为骑士id)
	private String trueName;
	private String account;
	private String account2;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getAccount2() {
		return account2;
	}
	public void setAccount2(String account2) {
		this.account2 = account2;
	}
}
