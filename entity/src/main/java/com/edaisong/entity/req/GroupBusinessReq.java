package com.edaisong.entity.req;

public class GroupBusinessReq {
	
	private Integer id;
	private String groupBusinessName;
	private String loginName;
	private String passWord;
	private Integer isAllowOverdraft;
	
	public Integer getId() {
		return id;
	}
 
	public void setId(Integer id) {
		this.id = id;
	}	
	
	public String getbusinessGroupName() {
		return groupBusinessName;
	}
 
	public void setgroupBusinessName(String groupBusinessName) {
		this.groupBusinessName = groupBusinessName;
	}
	
	public String getloginName() {
		return loginName;
	}
 
	public void setloginName(String loginName) {
		this.loginName = loginName;
	}
	
	public String getpassWord() {
		return passWord;
	}
 
	public void setpassWord(String passWord) {
		this.passWord = passWord;
	}
	
	public Integer getIsAllowOverdraft() {
		return isAllowOverdraft;
	}
 
	public void setIsAllowOverdraft(Integer isAllowOverdraft) {
		this.isAllowOverdraft = isAllowOverdraft;
	}
}
