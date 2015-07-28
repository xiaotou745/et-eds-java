
package com.edaisong.entity.resp;

import java.util.List;

import com.edaisong.entity.common.ResponseBase;
import com.edaisong.entity.domain.ClienterModel;


public class ClienterResp extends ResponseBase{
	private	List<ClienterModel> clienterList;	
	
	public List<ClienterModel> getClienterList() {
		return clienterList;
	}
	
	public void setClienterList(List<ClienterModel> clienterList) {
		this.clienterList = clienterList;
	}
}
