package com.edaisong.entity.req;

import java.util.Date;

import com.edaisong.entity.common.PagedRequestBase;


public class PagedFeedbackReq extends PagedRequestBase{	 	
	    
	    private Short usertype;

	    private Short feedbacktype;
	    
	    private String city;

	    private String statrTime;
	    
	    private String endTime;

		public Short getUsertype() {
			return usertype;
		}

		public void setUsertype(Short usertype) {
			this.usertype = usertype;
		}

		public Short getFeedbacktype() {
			return feedbacktype;
		}

		public void setFeedbacktype(Short feedbacktype) {
			this.feedbacktype = feedbacktype;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getStatrTime() {
			return statrTime;
		}

		public void setStatrTime(String statrTime) {
			this.statrTime = statrTime;
		}

		public String getEndTime() {
			return endTime;
		}

		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}
	    
}
