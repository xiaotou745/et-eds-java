<<<<<<< HEAD
package com.edaisong.api.service.inter;

import com.edaisong.entity.Account;
import com.edaisong.entity.Clienter;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.ClienterModel;
import com.edaisong.entity.req.AccountReq;
import com.edaisong.entity.req.ClienterOptionReq;
import com.edaisong.entity.req.ClienterReq;
import com.edaisong.entity.resp.AccountResp;




public interface IClienterService {
		
	int modifyStatusById(Clienter record) ;
	
	int modifyMoneyById(ClienterOptionReq record);	
	
	PagedResponse<ClienterModel> query(ClienterReq req);
	
	
}
=======
package com.edaisong.api.service.inter;

import com.edaisong.entity.Account;
import com.edaisong.entity.Clienter;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.ClienterModel;
import com.edaisong.entity.req.AccountReq;
import com.edaisong.entity.req.ClienterOptionReq;
import com.edaisong.entity.req.ClienterReq;
import com.edaisong.entity.resp.AccountResp;




public interface IClienterService {
		
	int modifyStatusById(Clienter record) ;
	
	int modifyMoneyById(ClienterOptionReq record);	
	
	PagedResponse<ClienterModel> query(ClienterReq req);
}
>>>>>>> 772aaa3d48304344b9aa3741b8664c84a74d3a86
