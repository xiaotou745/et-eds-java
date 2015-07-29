package com.edaisong.api.service.inter;

import com.edaisong.entity.Account;
import com.edaisong.entity.Clienter;
import com.edaisong.entity.common.ResponsePageList;
import com.edaisong.entity.domain.ClienterModel;
import com.edaisong.entity.req.AccountReq;
import com.edaisong.entity.req.ClienterOptionReq;
import com.edaisong.entity.req.ClienterReq;
import com.edaisong.entity.resp.AccountResp;
import com.edaisong.entity.resp.ClienterResp;




public interface IClienterService {
		
	int modifyStatusById(Clienter record) ;
	
	int modifyMoneyById(ClienterOptionReq record);	
	
	ResponsePageList<ClienterModel> queryClienter(ClienterReq req);
}
