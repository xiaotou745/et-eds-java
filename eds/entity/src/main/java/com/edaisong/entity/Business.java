package com.edaisong.entity;

import java.io.Serializable;
import java.lang.Double;
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

    private Double distribsubsidy;

    private Double businesscommission;

    private Integer commissiontype;

    private Double commissionfixvalue;

    private Integer businessgroupid;

    private Double balanceprice;

    private Double allowwithdrawprice;

    private Double haswithdrawprice;

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
    
    private Integer isAllowCashPay;
    
    private Integer pushOrderType;
    
    private Integer registerFrom;
    
    private String timespan;
    
    private String appkey;
    
    private Integer isenable; 

	public Integer getIsenable() {
		return isenable;
	}

	public void setIsenable(Integer isenable) {
		this.isenable = isenable;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public Date getLastlogintime() {
		return lastlogintime;
	}

	public void setLastlogintime(Date lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	public String getTimespan() {
		return timespan;
	}

	public void setTimespan(String timespan) {
		this.timespan = timespan;
	}

	public Integer getRegisterFrom() {
		return registerFrom;
	}

	public void setRegisterFrom(Integer registerFrom) {
		this.registerFrom = registerFrom;
	}

	public Integer getPushOrderType() {
		return pushOrderType;
	}

	public void setPushOrderType(Integer pushOrderType) {
		this.pushOrderType = pushOrderType;
	}

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

    public Double getDistribsubsidy() {
        return distribsubsidy;
    }

    public void setDistribsubsidy(Double distribsubsidy) {
        this.distribsubsidy = distribsubsidy;
    }

    public Double getBusinesscommission() {
        return businesscommission;
    }

    public void setBusinesscommission(Double businesscommission) {
        this.businesscommission = businesscommission;
    }

    public Integer getCommissiontype() {
        return commissiontype;
    }

    public void setCommissiontype(Integer commissiontype) {
        this.commissiontype = commissiontype;
    }

    public Double getCommissionfixvalue() {
        return commissionfixvalue;
    }

    public void setCommissionfixvalue(Double commissionfixvalue) {
        this.commissionfixvalue = commissionfixvalue;
    }

    public Integer getBusinessgroupid() {
        return businessgroupid;
    }

    public void setBusinessgroupid(Integer businessgroupid) {
        this.businessgroupid = businessgroupid;
    }

    public Double getBalanceprice() {
        return balanceprice;
    }

    public void setBalanceprice(Double balanceprice) {
        this.balanceprice = balanceprice;
    }

    public Double getAllowwithdrawprice() {
        return allowwithdrawprice;
    }

    public void setAllowwithdrawprice(Double allowwithdrawprice) {
        this.allowwithdrawprice = allowwithdrawprice;
    }

    public Double getHaswithdrawprice() {
        return haswithdrawprice;
    }

    public void setHaswithdrawprice(Double haswithdrawprice) {
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

	public Integer getIsAllowCashPay() {
		return isAllowCashPay;
	}

	public void setIsAllowCashPay(Integer isAllowCashPay) {
		this.isAllowCashPay = isAllowCashPay;
	}
}