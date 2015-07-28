package com.edaisong.api.service.inter;

import com.edaisong.entity.Clienter;
import com.edaisong.entity.req.ClienterOptionReq;
import com.edaisong.entity.req.ClienterReq;
import com.edaisong.entity.resp.ClienterResp;




public interface IClienterService {
	
	ClienterResp  getClienterList(ClienterReq record); 		
		
	int modifyStatusById(Clienter record) ;
	
	int modifyMoneyById(ClienterOptionReq record);		
}
