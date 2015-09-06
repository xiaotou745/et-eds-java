package com.edaisong.api.service.inter;

import com.edaisong.entity.Clienter;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusinessClientersModel;
import com.edaisong.entity.domain.ClienterBindInfoModel;
import com.edaisong.entity.domain.ClienterModel;
import com.edaisong.entity.req.ClienterMoney;
import com.edaisong.entity.req.ClienterOptionReq;
import com.edaisong.entity.req.PagedClienterReq;
import com.edaisong.entity.req.PagedBusinessClientersReq;
import com.edaisong.entity.req.PagedClienterSearchReq;

public interface IClienterService {

	int modifyStatusById(Clienter record);

	int modifyMoneyById(ClienterOptionReq record);

	PagedResponse<ClienterModel> query(PagedClienterReq req);

	PagedResponse<BusinessClientersModel> getBusinessClienters(
			PagedBusinessClientersReq req);

	/**
	 * 更新骑士余额
	 * 
	 * @param clienterMoney
	 * @author CaoHeYang
	 * @date 20150831
	 */
	void updateCAccountBalance(ClienterMoney clienterMoney);

	/**
	 * 更新骑士可提现余额
	 * 
	 * @param clienterMoney
	 * @author CaoHeYang
	 * @date 20150831
	 */
	void updateCAllowWithdrawPrice(ClienterMoney clienterMoney);

	/**
	 * 更新骑士余额、可提现余额
	 * 
	 * @param clienterMoney
	 * @author CaoHeYang
	 * @date 20150831
	 */
	void updateCBalanceAndWithdraw(ClienterMoney clienterMoney);

	/**
	 * 获得骑士列表
	 * @author pengyi
	 * @date 20150901
	 */
	PagedResponse<ClienterBindInfoModel> getClienterList(PagedClienterSearchReq req);
}
