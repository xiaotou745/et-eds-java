package com.edaisong.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Business implements Serializable{
    private Integer id;

    private String name;

    private String city;

    private String district;

    private String phoneno;

    private String phoneno2;

    private String password;

    private String checkpicurl;

    private String idcard;

    private String address;

    private String landline;

    private Double longitude;

    private Double latitude;

    private Byte status;

    private Date inserttime;

    private String districtid;

    private String cityid;

    private Integer groupid;

    private Integer originalbusiid;

    private String provincecode;

    private String citycode;

    private String areacode;

    private String province;

    private Integer commissiontypeid;

    private BigDecimal distribsubsidy;

    private BigDecimal businesscommission;

    private Integer commissiontype;

    private BigDecimal commissionfixvalue;

    private Integer businessgroupid;

    private BigDecimal balanceprice;

    private BigDecimal allowwithdrawprice;

    private BigDecimal haswithdrawprice;

    private Integer mealssettlemode;

    private String contactway;

    private String businesslicensepic;

    private Short isbind;

    private Integer onekeypuborder;

    private Integer isallowoverdraft;

    private Short isemployertask;

    private String recommendphone;
    
    private Date lastlogintime;
    
    private Integer isOrderChecked;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno == null ? null : phoneno.trim();
    }

    public String getPhoneno2() {
        return phoneno2;
    }

    public void setPhoneno2(String phoneno2) {
        this.phoneno2 = phoneno2 == null ? null : phoneno2.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getCheckpicurl() {
        return checkpicurl;
    }

    public void setCheckpicurl(String checkpicurl) {
        this.checkpicurl = checkpicurl == null ? null : checkpicurl.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getLandline() {
        return landline;
    }

    public void setLandline(String landline) {
        this.landline = landline == null ? null : landline.trim();
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getInserttime() {
        return inserttime;
    }

    public void setInserttime(Date inserttime) {
        this.inserttime = inserttime;
    }

    public String getDistrictid() {
        return districtid;
    }

    public void setDistrictid(String districtid) {
        this.districtid = districtid == null ? null : districtid.trim();
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid == null ? null : cityid.trim();
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public Integer getOriginalbusiid() {
        return originalbusiid;
    }

    public void setOriginalbusiid(Integer originalbusiid) {
        this.originalbusiid = originalbusiid;
    }

    public String getProvincecode() {
        return provincecode;
    }

    public void setProvincecode(String provincecode) {
        this.provincecode = provincecode == null ? null : provincecode.trim();
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode == null ? null : citycode.trim();
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode == null ? null : areacode.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public Integer getCommissiontypeid() {
        return commissiontypeid;
    }

    public void setCommissiontypeid(Integer commissiontypeid) {
        this.commissiontypeid = commissiontypeid;
    }

    public BigDecimal getDistribsubsidy() {
        return distribsubsidy;
    }

    public void setDistribsubsidy(BigDecimal distribsubsidy) {
        this.distribsubsidy = distribsubsidy;
    }

    public BigDecimal getBusinesscommission() {
        return businesscommission;
    }

    public void setBusinesscommission(BigDecimal businesscommission) {
        this.businesscommission = businesscommission;
    }

    public Integer getCommissiontype() {
        return commissiontype;
    }

    public void setCommissiontype(Integer commissiontype) {
        this.commissiontype = commissiontype;
    }

    public BigDecimal getCommissionfixvalue() {
        return commissionfixvalue;
    }

    public void setCommissionfixvalue(BigDecimal commissionfixvalue) {
        this.commissionfixvalue = commissionfixvalue;
    }

    public Integer getBusinessgroupid() {
        return businessgroupid;
    }

    public void setBusinessgroupid(Integer businessgroupid) {
        this.businessgroupid = businessgroupid;
    }

    public BigDecimal getBalanceprice() {
        return balanceprice;
    }

    public void setBalanceprice(BigDecimal balanceprice) {
        this.balanceprice = balanceprice;
    }

    public BigDecimal getAllowwithdrawprice() {
        return allowwithdrawprice;
    }

    public void setAllowwithdrawprice(BigDecimal allowwithdrawprice) {
        this.allowwithdrawprice = allowwithdrawprice;
    }

    public BigDecimal getHaswithdrawprice() {
        return haswithdrawprice;
    }

    public void setHaswithdrawprice(BigDecimal haswithdrawprice) {
        this.haswithdrawprice = haswithdrawprice;
    }

    public Integer getMealssettlemode() {
        return mealssettlemode;
    }

    public void setMealssettlemode(Integer mealssettlemode) {
        this.mealssettlemode = mealssettlemode;
    }

    public String getContactway() {
        return contactway;
    }

    public void setContactway(String contactway) {
        this.contactway = contactway == null ? null : contactway.trim();
    }

    public String getBusinesslicensepic() {
        return businesslicensepic;
    }

    public void setBusinesslicensepic(String businesslicensepic) {
        this.businesslicensepic = businesslicensepic == null ? null : businesslicensepic.trim();
    }

    public Short getIsbind() {
        return isbind;
    }

    public void setIsbind(Short isbind) {
        this.isbind = isbind;
    }

    public Integer getOnekeypuborder() {
        return onekeypuborder;
    }

    public void setOnekeypuborder(Integer onekeypuborder) {
        this.onekeypuborder = onekeypuborder;
    }

    public Integer getIsallowoverdraft() {
        return isallowoverdraft;
    }

    public void setIsallowoverdraft(Integer isallowoverdraft) {
        this.isallowoverdraft = isallowoverdraft;
    }

    public Short getIsemployertask() {
        return isemployertask;
    }

    public void setIsemployertask(Short isemployertask) {
        this.isemployertask = isemployertask;
    }

    public String getRecommendphone() {
        return recommendphone;
    }

    public void setRecommendphone(String recommendphone) {
        this.recommendphone = recommendphone == null ? null : recommendphone.trim();
    }
    public Date getLastLoginTime() {
        return lastlogintime;
    }

    public void setLastLoginTime(Date lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

	public Integer getIsOrderChecked() {
		return isOrderChecked;
	}

	public void setIsOrderChecked(Integer isOrderChecked) {
		this.isOrderChecked = isOrderChecked;
	}
}