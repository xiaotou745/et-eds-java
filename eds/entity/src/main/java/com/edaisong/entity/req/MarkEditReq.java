package com.edaisong.entity.req;

import com.edaisong.entity.Mark;



public class MarkEditReq extends Mark{
	private String operator;//0:新增  1:修改(标签名称有改动) 2:修改(标签名称无改动)
	private int operateType;
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public int getOperateType() {
		return operateType;
	}
	public void setOperateType(int operateType) {
		this.operateType = operateType;
	}
}
