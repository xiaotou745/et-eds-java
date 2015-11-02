package com.edaisong.entity.req;

import java.lang.Double;
import java.util.Date;
import java.util.List;

import com.edaisong.entity.OrderChild;

public class OrderPushReq {

		private Integer businessid;				
		
		
		
		private String timeSpan;
				
	
		public String getTimeSpan() {
			return timeSpan;
		}

		public void setTimeSpan(String timeSpan) {
			this.timeSpan = timeSpan;
		}

		public Integer getBusinessid() {
		      return businessid;
		    }

		public void setBusinessid(Integer businessid) {
		        this.businessid = businessid;
		}		

		private String recevicename;

	    private String recevicephoneno;

	    private String receviceaddress;

	    private Boolean ispay;

	    private Double amount;

	    private String remark;
	    

	    private String recevicecity;

	    private Integer orderfrom;

	    private Integer ordercount;

	    private Integer mealssettlemode;
	    
	    private List<OrderChild> listOrderChild;
	    
	    private String childstr;

	    public String getChildstr() {
			return childstr;
		}

		public void setChildstr(String childstr) {
			this.childstr = childstr;
		}	  

		public String getRecevicename() {
	        return recevicename;
	    }

	    public void setRecevicename(String recevicename) {
	        this.recevicename = recevicename == null ? null : recevicename.trim();
	    }

	    public String getRecevicephoneno() {
	        return recevicephoneno;
	    }

	    public void setRecevicephoneno(String recevicephoneno) {
	        this.recevicephoneno = recevicephoneno == null ? null : recevicephoneno.trim();
	    }

	    public String getReceviceaddress() {
	        return receviceaddress;
	    }

	    public void setReceviceaddress(String receviceaddress) {
	        this.receviceaddress = receviceaddress == null ? null : receviceaddress.trim();
	    }

	    /**
	     * 0 未付款  1 已付款
	     * @return
	     */
	    public Boolean getIspay() {
	        return ispay;
	    }
	  /**
	   *  0 未付款  1 已付款
	   * @param ispay
	   */
	    public void setIspay(Boolean ispay) {
	        this.ispay = ispay;
	    }

	    public Double getAmount() {
	        return amount;
	    }

	    public void setAmount(Double amount) {
	        this.amount = amount;
	    }

	    public String getRemark() {
	        return remark;
	    }

	    public void setRemark(String remark) {
	        this.remark = remark == null ? null : remark.trim();
	    }
	 
	    public String getRecevicecity() {
	        return recevicecity;
	    }

	    public void setRecevicecity(String recevicecity) {
	        this.recevicecity = recevicecity == null ? null : recevicecity.trim();
	    }

	    public Integer getOrderfrom() {
	        return orderfrom;
	    }

	    public void setOrderfrom(Integer orderfrom) {
	        this.orderfrom = orderfrom;
	    }

	    public Integer getOrdercount() {
	        return ordercount;
	    }

	    public void setOrdercount(Integer ordercount) {
	        this.ordercount = ordercount;
	    }


	    public Integer getMealssettlemode() {
	        return mealssettlemode;
	    }

	    public void setMealssettlemode(Integer mealssettlemode) {
	        this.mealssettlemode = mealssettlemode;
	    }


		public List<OrderChild> getListOrderChild() {
			return listOrderChild;
		}

		public void setListOrderChild(List<OrderChild> listOrderChild) {
			this.listOrderChild = listOrderChild;
		}
}
