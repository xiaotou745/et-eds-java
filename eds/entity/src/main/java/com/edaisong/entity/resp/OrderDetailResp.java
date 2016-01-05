package com.edaisong.entity.resp;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import com.edaisong.core.util.ParseHelper;
import com.edaisong.entity.common.ResponseBase;
import com.edaisong.entity.OrderChild;;

/**
 * 获取订单详情
 * @author 胡灵波
 * @Date 2015年11月27日 11:44:38
 */
public class OrderDetailResp extends ResponseBase{
    public Double getPickuplongitude() {
		return pickuplongitude;
	}

	public void setPickuplongitude(Double pickuplongitude) {
		this.pickuplongitude = pickuplongitude;
	}

	public Double getPickuplatitude() {
		return pickuplatitude;
	}

	public void setPickuplatitude(Double pickuplatitude) {
		this.pickuplatitude = pickuplatitude;
	}
	private Double pickuplongitude;

    private Double pickuplatitude;
    
	private String clienterName;
	    public int getId() {
		return id;
	}
	private boolean ismodifyticket;
	private String clienterPhoneNo;
	public void setId(int id) {
		this.id = id;
	}
	private double balancePrice;
	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public String getPickupaddress() { 
		return pickupaddress;
	}

	public void setPickupaddress(String pickupaddress) {
		this.pickupaddress = pickupaddress;
	}

	public Date getPubdate() {
		return pubdate;
	}

	public void setPubdate(Date pubdate) {
		this.pubdate = pubdate;
	}

	public String getRecevicename() {
		return recevicename;
	}

	public void setRecevicename(String recevicename) {
		this.recevicename = recevicename;
	}

	public String getRecevicephoneno() {
		return recevicephoneno;
	}

	public void setRecevicephoneno(String recevicephoneno) {
		this.recevicephoneno = recevicephoneno;
	}

	public String getReceviceaddress() {
		if(receviceaddress == null || receviceaddress == ""){return "附近3公里，由商户指定";}
		return receviceaddress;
	}

	public void setReceviceaddress(String receviceaddress) {
		this.receviceaddress = receviceaddress;
	}

	public Date getActualdonedate() {
		return actualdonedate;
	}

	public void setActualdonedate(Date actualdonedate) {
		this.actualdonedate = actualdonedate;
	}

	public Boolean getIspay() {
		return ispay;
	}

	public void setIspay(Boolean ispay) {
		this.ispay = ispay;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getOrdercommission() {
		return ordercommission;
	}

	public void setOrdercommission(Double ordercommission) {
		this.ordercommission = ordercommission;
	}

	public Double getDistribsubsidy() {
		return distribsubsidy;
	}

	public void setDistribsubsidy(Double distribsubsidy) {
		this.distribsubsidy = distribsubsidy;
	}

	public Double getWebsitesubsidy() {
		return websitesubsidy;
	}

	public void setWebsitesubsidy(Double websitesubsidy) {
		this.websitesubsidy = websitesubsidy;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Integer getClienterid() {
		if(clienterid == null){return 0;}
		return clienterid;
	}

	public void setClienterid(Integer clienterid) {
		this.clienterid = clienterid;
	}

	public Integer getBusinessid() {
		return businessid;
	}

	public void setBusinessid(Integer businessid) {
		this.businessid = businessid;
	}

	public String getRecevicecity() {
		return recevicecity;
	}

	public void setRecevicecity(String recevicecity) {
		this.recevicecity = recevicecity;
	}

	public Double getRecevicelongitude() {
		return recevicelongitude;
	}

	public void setRecevicelongitude(Double recevicelongitude) {
		this.recevicelongitude = recevicelongitude;
	}

	public Double getRecevicelatitude() {
		return recevicelatitude;
	}

	public void setRecevicelatitude(Double recevicelatitude) {
		this.recevicelatitude = recevicelatitude;
	}

	public Integer getOrderfrom() {
		return orderfrom;
	}

	public void setOrderfrom(Integer orderfrom) {
		this.orderfrom = orderfrom;
	}

	public Long getOriginalorderid() {
		return originalorderid;
	}

	public void setOriginalorderid(Long originalorderid) {
		this.originalorderid = originalorderid;
	}

	public String getOriginalorderno() {
		return originalorderno;
	}

	public void setOriginalorderno(String originalorderno) {
		this.originalorderno = originalorderno;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getWeight() {
		if(weight == null){return 0.0;}
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getReceiveprovince() {
		return receiveprovince;
	}

	public void setReceiveprovince(String receiveprovince) {
		this.receiveprovince = receiveprovince;
	}

	public String getReceivearea() {
		return receivearea;
	}

	public void setReceivearea(String receivearea) {
		this.receivearea = receivearea;
	}

	public String getReceiveprovincecode() {
		return receiveprovincecode;
	}

	public void setReceiveprovincecode(String receiveprovincecode) {
		this.receiveprovincecode = receiveprovincecode;
	}

	public String getReceivecitycode() {
		return receivecitycode;
	}

	public void setReceivecitycode(String receivecitycode) {
		this.receivecitycode = receivecitycode;
	}

	public String getReceiveareacode() {
		return receiveareacode;
	}

	public void setReceiveareacode(String receiveareacode) {
		this.receiveareacode = receiveareacode;
	}

	public Integer getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(Integer ordertype) {
		this.ordertype = ordertype;
	}

	public Double getKm() {
		if(km == null){return 0.0;}
		else{
			return	ParseHelper.ToDouble(new DecimalFormat("0.0").format(km),0);
		}
	}

	public void setKm(Double km) {
		this.km = km;
	}

	public Integer getGuojuqty() {
		return guojuqty;
	}

	public void setGuojuqty(Integer guojuqty) {
		this.guojuqty = guojuqty;
	}

	public Integer getLujuqty() {
		return lujuqty;
	}

	public void setLujuqty(Integer lujuqty) {
		this.lujuqty = lujuqty;
	}

	public Date getSongcandate() {
		return songcandate;
	}

	public void setSongcandate(Date songcandate) {
		this.songcandate = songcandate;
	}

	public Integer getOrdercount() {
		return ordercount;
	}

	public void setOrdercount(Integer ordercount) {
		this.ordercount = ordercount;
	}

	public Double getCommissionrate() {
		return commissionrate;
	}

	public void setCommissionrate(Double commissionrate) {
		this.commissionrate = commissionrate;
	}

	public Integer getPayment() {
		return payment;
	}

	public void setPayment(Integer payment) {
		this.payment = payment;
	}

	public Integer getCommissionformulamode() {
		return commissionformulamode;
	}

	public void setCommissionformulamode(Integer commissionformulamode) {
		this.commissionformulamode = commissionformulamode;
	}

	public Double getAdjustment() {
		return adjustment;
	}

	public void setAdjustment(Double adjustment) {
		this.adjustment = adjustment;
	}

	public Double getBusinesscommission() {
		return businesscommission;
	}

	public void setBusinesscommission(Double businesscommission) {
		this.businesscommission = businesscommission;
	}

	public Double getSettlemoney() {
		return settlemoney;
	}

	public void setSettlemoney(Double settlemoney) {
		this.settlemoney = settlemoney;
	}

	public Integer getDealcount() {
		return dealcount;
	}

	public void setDealcount(Integer dealcount) {
		this.dealcount = dealcount;
	}

	public String getPickupcode() {
		return pickupcode;
	}

	public void setPickupcode(String pickupcode) {
		this.pickupcode = pickupcode;
	}

	public String getOthercancelreason() {
		return othercancelreason;
	}

	public void setOthercancelreason(String othercancelreason) {
		this.othercancelreason = othercancelreason;
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

	public String getTimespan() {
		return timespan;
	}

	public void setTimespan(String timespan) {
		this.timespan = timespan;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public Double getBusinessreceivable() {
		return businessreceivable;
	}

	public void setBusinessreceivable(Double businessreceivable) {
		this.businessreceivable = businessreceivable;
	}

	public Integer getMealssettlemode() {
		return mealssettlemode;
	}

	public void setMealssettlemode(Integer mealssettlemode) {
		this.mealssettlemode = mealssettlemode;
	}

	public Integer getFinishdatelength() {
		return finishdatelength;
	}

	public void setFinishdatelength(Integer finishdatelength) {
		this.finishdatelength = finishdatelength;
	}

	public Integer getFinishall() {
		return finishall;
	}

	public void setFinishall(Integer finishall) {
		this.finishall = finishall;
	}

	public Double getRealordercommission() {
		return realordercommission;
	}

	public void setRealordercommission(Double realordercommission) {
		this.realordercommission = realordercommission;
	}

	public Integer getGroupbusinessid() {
		return groupbusinessid;
	}

	public void setGroupbusinessid(Integer groupbusinessid) {
		this.groupbusinessid = groupbusinessid;
	}

	public Double getBasecommission() {
		return basecommission;
	}

	public void setBasecommission(Double basecommission) {
		this.basecommission = basecommission;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}

	public String getPubname() {
		return pubname;
	}

	public void setPubname(String pubname) {
		this.pubname = pubname;
	}

	public String getPubphoneno() {
		return pubphoneno;
	}

	public void setPubphoneno(String pubphoneno) {
		this.pubphoneno = pubphoneno;
	}

	public String getPubaddress() {
		return pubaddress;
	}

	public void setPubaddress(String pubaddress) {
		this.pubaddress = pubaddress;
	}

	public Short getTaketype() {
		return taketype;
	}

	public void setTaketype(Short taketype) {
		this.taketype = taketype;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public List<OrderChild> getListOrderChild() {
		return listOrderChild;
	}

	public void setListOrderChild(List<OrderChild> listOrderChild) {
		this.listOrderChild = listOrderChild;
	}

	public Integer getNeeduploadcount() {
		return needuploadcount;
	}

	public void setNeeduploadcount(Integer needuploadcount) {
		this.needuploadcount = needuploadcount;
	}

	public String getReceiptpic() {
		return receiptpic;
	}

	public void setReceiptpic(String receiptpic) {
		this.receiptpic = receiptpic;
	}

	public Integer getHaduploadcount() {
		return haduploadcount;
	}

	public void setHaduploadcount(Integer haduploadcount) {
		this.haduploadcount = haduploadcount;
	}

	public Integer getIsjoinwithdraw() {
		return isjoinwithdraw;
	}

	public void setIsjoinwithdraw(Integer isjoinwithdraw) {
		this.isjoinwithdraw = isjoinwithdraw;
	}

	public Double getPublongitude() {
		return publongitude;
	}

	public void setPublongitude(Double publongitude) {
		this.publongitude = publongitude;
	}

	public Double getPublatitude() {
		return publatitude;
	}

	public void setPublatitude(Double publatitude) {
		this.publatitude = publatitude;
	}

	public Date getGrabtime() {
		return grabtime;
	}

	public void setGrabtime(Date grabtime) {
		this.grabtime = grabtime;
	}

	public Double getGrablongitude() {
		return grablongitude;
	}

	public void setGrablongitude(Double grablongitude) {
		this.grablongitude = grablongitude;
	}

	public Double getGrablatitude() {
		return grablatitude;
	}

	public void setGrablatitude(Double grablatitude) {
		this.grablatitude = grablatitude;
	}

	public Double getCompletelongitude() {
		return completelongitude;
	}

	public void setCompletelongitude(Double completelongitude) {
		this.completelongitude = completelongitude;
	}

	public Double getCompletelatitude() {
		return completelatitude;
	}

	public void setCompletelatitude(Double completelatitude) {
		this.completelatitude = completelatitude;
	}

	public Date getTaketime() {
		return taketime;
	}

	public void setTaketime(Date taketime) {
		this.taketime = taketime;
	}

	public Double getTakelongitude() {
		return takelongitude;
	}

	public void setTakelongitude(Double takelongitude) {
		this.takelongitude = takelongitude;
	}

	public Double getTakelatitude() {
		return takelatitude;
	}

	public void setTakelatitude(Double takelatitude) {
		this.takelatitude = takelatitude;
	}

	public Double getPubtograbdistance() {
		return pubtograbdistance;
	}

	public void setPubtograbdistance(Double pubtograbdistance) {
		this.pubtograbdistance = pubtograbdistance;
	}

	public Double getGrabtocompletedistance() {
		return grabtocompletedistance;
	}

	public void setGrabtocompletedistance(Double grabtocompletedistance) {
		this.grabtocompletedistance = grabtocompletedistance;
	}

	public Double getPubtocompletedistance() {
		return pubtocompletedistance;
	}

	public void setPubtocompletedistance(Double pubtocompletedistance) {
		this.pubtocompletedistance = pubtocompletedistance;
	}

	public Integer getOnekeypuborder() {
		return onekeypuborder;
	}

	public void setOnekeypuborder(Integer onekeypuborder) {
		this.onekeypuborder = onekeypuborder;
	}

	public Integer getIsnotrealorder() {
		return isnotrealorder;
	}

	public void setIsnotrealorder(Integer isnotrealorder) {
		this.isnotrealorder = isnotrealorder;
	}

	public Integer getIsorderchecked() {
		return isorderchecked;
	}

	public void setIsorderchecked(Integer isorderchecked) {
		this.isorderchecked = isorderchecked;
	}

	public Date getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}

	public Integer getIsAllowCashPay() {
		return isAllowCashPay;
	}

	public void setIsAllowCashPay(Integer isAllowCashPay) {
		this.isAllowCashPay = isAllowCashPay;
	}

		private int id;

	    private String orderno;

	    private String pickupaddress;

	    private Date pubdate;

	    private String recevicename;

	    private String recevicephoneno;

	    private String receviceaddress;

	    private Date actualdonedate;

	    private Boolean ispay;

	    private Double amount;

	    private Double ordercommission;

	    private Double distribsubsidy;

	    private Double websitesubsidy;

	    private String remark;

	    private Byte status;

	    private Integer clienterid;

	    private Integer businessid;

	    private String recevicecity;

	    private Double recevicelongitude;

	    private Double recevicelatitude;

	    private Integer orderfrom;

	    private Long originalorderid;

	    private String originalorderno;

	    private Integer quantity;

	    private Double weight;

	    private String receiveprovince;

	    private String receivearea;

	    private String receiveprovincecode;

	    private String receivecitycode;

	    private String receiveareacode;

	    private Integer ordertype;

	    private Double km;

	    private Integer guojuqty;

	    private Integer lujuqty;

	    private Date songcandate;

	    private Integer ordercount;

	    private Double commissionrate;

	    private Integer payment;

	    private Integer commissionformulamode;

	    private Double adjustment;

	    private Double businesscommission;

	    private Double settlemoney;

	    private Integer dealcount;

	    private String pickupcode;
	    
	    private String receivecode;

	    public String getReceivecode() {
			return receivecode;
		}

		public void setReceivecode(String receivecode) {
			this.receivecode = receivecode;
		}
		private String othercancelreason;

	    private Integer commissiontype;

	    private Double commissionfixvalue;

	    private Integer businessgroupid;

	    private String timespan;

	    private String invoice;

	    private Double businessreceivable;

	    private Integer mealssettlemode;

	    private Integer finishdatelength;

	    private Integer finishall;

	    private Double realordercommission;
	    
	    private Integer groupbusinessid;
	    
	    private Double basecommission;
	    
	    private Integer platform;
	    
	    private String platformstr;
	    
	    private Double amountAndTip;
	    
	    private String paymentstr;

		public String getPaymentstr() {
			return paymentstr;
		}

		public void setPaymentstr(String paymentstr) {
			this.paymentstr = paymentstr;
		}

		public Double getAmountAndTip() {
			return amountAndTip;
		}

		public void setAmountAndTip(Double amountAndTip) {
			this.amountAndTip = amountAndTip;
		}

		public String getPlatformstr() {
			return platformstr;
		}

		public void setPlatformstr(String platformstr) {
			this.platformstr = platformstr;
		}
		private String pubname;
		
	    private String pubphoneno;
	    
	    private String pubaddress;

	    private Short taketype;	 	 

	    private String productname;

	    private List<OrderChild> listOrderChild;
	    
	    private Integer needuploadcount;

	    private String receiptpic;

	    private Integer haduploadcount;

	    private Integer isjoinwithdraw;

	    private Double publongitude;

	    private Double publatitude;

	    private Date grabtime;

	    private Double grablongitude;

	    private Double grablatitude;

	    private Double completelongitude;

	    private Double completelatitude;

	    private Date taketime;

	    private Double takelongitude;

	    private Double takelatitude;

	    private Double pubtograbdistance;

	    private Double grabtocompletedistance;

	    private Double pubtocompletedistance;

	    private Integer onekeypuborder;

	    private Integer isnotrealorder;
	    
	    private Integer isorderchecked;
	    
	    private Date cancelTime;
	    
	    private Integer isAllowCashPay; 
	    public String getPubtocurrentdistance() {
			return pubtocurrentdistance;
		}

		public void setPubtocurrentdistance(String pubtocurrentdistance) {
			this.pubtocurrentdistance = pubtocurrentdistance;
		}

		public String getRecevicetocurrentdistance() {
			return recevicetocurrentdistance;
		}

		public void setRecevicetocurrentdistance(String recevicetocurrentdistance) {
			this.recevicetocurrentdistance = recevicetocurrentdistance;
		}

		private String pubtocurrentdistance;
	    
	    private String recevicetocurrentdistance;
	    
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
		private Double longitude;

	    private Double latitude;
	    
	    
		
	    public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPhoneno() {
			return phoneno;
		}

		public void setPhoneno(String phoneno) {
			this.phoneno = phoneno;
		}

		public String getPhoneno2() {
			return phoneno2;
		}

		public void setPhoneno2(String phoneno2) {
			this.phoneno2 = phoneno2;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getLandline() {
			return landline;
		}

		public void setLandline(String landline) {
			this.landline = landline;
		}

		private String name;

	    private String phoneno;

	    private String phoneno2;

	    private String address;
	    
	    private String landline;	  
	    private String city;
	    public String getCity() {
	        return city;
	    }

	    public void setCity(String city) {
	        this.city = city == null ? null : city.trim();
	    }

	    
	    
	    private int iscomplain;
	    
	    
	    public int getIscomplain() {
			return iscomplain;
		}

		public void setIscomplain(int iscomplain) {
			this.iscomplain = iscomplain;
		}
		private Date expectedTakeTime;
		private Date expectedDelivery;
		public Date getExpectedTakeTime() {
			return expectedTakeTime;
		}

		public void setExpectedTakeTime(Date expectedTakeTime) {
			this.expectedTakeTime = expectedTakeTime;
		}

		public Date getExpectedDelivery() {
			return expectedDelivery;
		}

		public void setExpectedDelivery(Date expectedDelivery) {
			this.expectedDelivery = expectedDelivery;
		}

		public String getClienterName() {
			if(clienterName == null){return "";}
			return clienterName;
		}

		public void setClienterName(String clienterName) {
			this.clienterName = clienterName;
		}

		public String getClienterPhoneNo() {
			if(clienterPhoneNo == null){return "";}
			return clienterPhoneNo;
		}

		public void setClienterPhoneNo(String clienterPhoneNo) {
			this.clienterPhoneNo = clienterPhoneNo;
		}

		public double getBalancePrice() {
			return balancePrice;
		}

		public void setBalancePrice(double balancePrice) {
			this.balancePrice = balancePrice;
		}

		public boolean isIsmodifyticket() {
			return ismodifyticket;
		}

		public void setIsmodifyticket(boolean ismodifyticket) {
			this.ismodifyticket = ismodifyticket;
		}
		
		
}
