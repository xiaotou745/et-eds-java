
package com.edaisong.api.service.inter;


import com.edaisong.entity.Clienter;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusinessClientersModel;
import com.edaisong.entity.domain.ClienterModel;
import com.edaisong.entity.req.ClienterOptionReq;
import com.edaisong.entity.req.ClienterReq;
import com.edaisong.entity.req.PagedBusinessClientersReq;

public interface IClienterService {
		
	int modifyStatusById(Clienter record) ;
	
	int modifyMoneyById(ClienterOptionReq record);	
	
	PagedResponse<ClienterModel> query(ClienterReq req);
	
	PagedResponse<BusinessClientersModel> getBusinessClienters(PagedBusinessClientersReq req);
}
