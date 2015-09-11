package com.edaisong.api.service.inter;

import java.util.List;

import com.edaisong.entity.Clienter;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusinessClientersModel;
import com.edaisong.entity.domain.ClienterBindInfoModel;
import com.edaisong.entity.domain.ClienterModel;
import com.edaisong.entity.domain.ClienterStatus;
import com.edaisong.entity.domain.ImportClienterInfo;
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
	
	/**
	 * 根据电话获得骑士姓名
	 * @author pengyi
	 * @date 2015年9月7日 下午1:49:29
	 * @version 1.0
	 * @param phoneNos
	 * @return
	 */
	List<ImportClienterInfo> getInfosByPhones(List<String> phoneNos);
	
	/**
	 * 根据电话获得名字
	 * @author pengyi
	 * @date 2015年9月7日 下午2:18:08
	 * @version 1.0
	 * @param phoneNo
	 * @return
	 */
	String getNameByPhone(String phoneNo);
	
	Integer getId(String phoneNo, String trueName);
	
	/**
	 * 根据骑士id获得实体对象
	 * @author pengyi
	 * @date 2015年9月11日 上午9:51:13
	 * @version 1.0
	 * @param id
	 * @return
	 */
	Clienter selectByPrimaryKey(Integer id);
	
	/**
	 * 获取用户状态信息
	 * @author CaoHeYang
	 * @param userid
	 * @date 20150911
	 * @return
	 */
	ClienterStatus getUserStatus(int userid);
}
