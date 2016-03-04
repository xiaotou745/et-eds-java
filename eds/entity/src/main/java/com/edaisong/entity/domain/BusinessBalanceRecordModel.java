package com.edaisong.entity.domain;

import com.edaisong.entity.BusinessBalanceRecord;

public class BusinessBalanceRecordModel extends BusinessBalanceRecord{
    //卡号(DES加密)
    private String accountNo;
    //开户行
    private String openBank;
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getOpenBank() {
		return openBank;
	}
	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}
	
    private String BusinessName;
	public String getBusinessName() {
		return BusinessName;
	}
	public void setBusinessName(String businessName) {
		BusinessName = businessName;
	}
	
	 public String getStrRecordtype() {
		return strRecordtype;
	}
	public void setStrRecordtype(String strRecordtype) {
		this.strRecordtype = strRecordtype;
	}
	public String getStrForm() {
		return strForm;
	}
	public void setStrForm(String strForm) {
		this.strForm = strForm;
	}
	public String getStrStatus() {
		return strStatus;
	}
	public void setStrStatus(String strStatus) {
		this.strStatus = strStatus;
	}

	private String strRecordtype;
	 
	 private String strForm;
	 
	 private String strStatus;
	 
    
}
