package com.edaisong.entity.req;

import com.edaisong.entity.common.PagedRequestBase;

public class PagedClienterSearchReq extends PagedRequestBase{
	private Integer status = -1; //默认查询所有状态
    private String clienterName; //商户名称
    private String clienterPhone; //商户电话
    // 查询类型,按当天,本周,还是本月
    private Integer searchType;
    // 集团id
    private Integer GroupId;
    // 商户城市
    private String businessCity;
    // 推荐人电话
    private String recommonPhone;
    // 时间
    private String txtPubStart;
    // 商户Id
    private Integer businessId;
    private String deliveryCompany;
	public String getClienterName() {
		return clienterName;
	}
	public void setClienterName(String clienterName) {
		this.clienterName = clienterName;
	}
	public String getClienterPhone() {
		return clienterPhone;
	}
	public void setClienterPhone(String clienterPhone) {
		this.clienterPhone = clienterPhone;
	}
	public Integer getSearchType() {
		return searchType;
	}
	public void setSearchType(Integer searchType) {
		this.searchType = searchType;
	}
	public Integer getGroupId() {
		return GroupId;
	}
	public void setGroupId(Integer groupId) {
		GroupId = groupId;
	}
	public String getBusinessCity() {
		return businessCity;
	}
	public void setBusinessCity(String businessCity) {
		this.businessCity = businessCity;
	}
	public String getRecommonPhone() {
		return recommonPhone;
	}
	public void setRecommonPhone(String recommonPhone) {
		this.recommonPhone = recommonPhone;
	}
	public String getTxtPubStart() {
		return txtPubStart;
	}
	public void setTxtPubStart(String txtPubStart) {
		this.txtPubStart = txtPubStart;
	}
	public Integer getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}
	public String getDeliveryCompany() {
		return deliveryCompany;
	}
	public void setDeliveryCompany(String deliveryCompany) {
		this.deliveryCompany = deliveryCompany;
	}
    public Integer getStatus(){
    	return status;
    }
	public void setStatus(Integer status) {
		this.status = status;
	}
}
