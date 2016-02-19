package com.edaisong.entity.req;

import java.util.ArrayList;

import com.edaisong.entity.BusinessSetpCharge;
import com.edaisong.entity.BusinessSetpChargeChild;

public class BusinessSetpChargeReq{

	private BusinessSetpCharge setp;
	public BusinessSetpCharge getSetp() {
		return setp;
	}

	public void setSetp(BusinessSetpCharge setp) {
		this.setp = setp;
	}

	private ArrayList<BusinessSetpChargeChild> childs;

	public ArrayList<BusinessSetpChargeChild> getChilds() {
		return childs;
	}

	public void setChilds(ArrayList<BusinessSetpChargeChild> childs) {
		this.childs = childs;
	}
}
