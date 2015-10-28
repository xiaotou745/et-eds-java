package com.edaisong.entity.domain;

import com.edaisong.entity.BusinessThirdRelation;

public class BusinessThirdRelationModel extends BusinessThirdRelation {
	private int isModifyBind;

	/**
	 * 是否可以修改绑定(0:否 1:是)
	 * 
	 * @return the isModifyBind
	 */
	public int getIsModifyBind() {
		return isModifyBind;
	}

	/**
	 * 是否可以修改绑定(0:否 1:是)
	 * 
	 * @param isModifyBind
	 *            the isModifyBind to set
	 */
	public void setIsModifyBind(int isModifyBind) {
		this.isModifyBind = isModifyBind;
	}
}
