package com.edaisong.taobaoopenapi.service.impl;

import org.springframework.stereotype.Service;
import com.edaisong.core.consts.TaoBaoConsts;
import com.edaisong.entity.taobao.TaoBaoResponseBase;
import com.edaisong.taobaoopenapi.service.inter.ITaoBaoOrderHttpService;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.WaimaiDeliveryConfirmRequest;
import com.taobao.api.request.WaimaiDeliveryLocationUpdateRequest;
import com.taobao.api.request.WaimaiDeliveryPickupRequest;
import com.taobao.api.request.WaimaiDeliveryUpdateRequest;
import com.taobao.api.request.WaimaiOrderAckRequest;
import com.taobao.api.response.WaimaiDeliveryConfirmResponse;
import com.taobao.api.response.WaimaiDeliveryLocationUpdateResponse;
import com.taobao.api.response.WaimaiDeliveryPickupResponse;
import com.taobao.api.response.WaimaiDeliveryUpdateResponse;
import com.taobao.api.response.WaimaiOrderAckResponse;

/**
 * 订单模块
 * 
 * @author CaoHeYang
 * @date 20151113
 */
@Service
public class TaoBaoOrderHttpService implements ITaoBaoOrderHttpService {
	/**
	 * 
	 */
	private String sessionKey = "ss";
	/**
	 * 
	 */
	private TaobaoClient client = new DefaultTaobaoClient(TaoBaoConsts.Uri, TaoBaoConsts.AppKey, TaoBaoConsts.AppSecret);

	/**
	 * 确认接单接口(API)
	 * @author CaoHeYang
	 * @param req
	 * @return
	 */
	@Override
	public TaoBaoResponseBase ask(WaimaiOrderAckRequest req) {
		try {
			WaimaiOrderAckResponse response = client.execute(req, sessionKey);
			TaoBaoResponseBase resp = new TaoBaoResponseBase();
			resp.setIs_success(response.getAckOrderResult().getSuccess());
			resp.setError_code(response.getAckOrderResult().getErrorCode());
			resp.setError_msg(response.getAckOrderResult().getErrorMsg());
			return resp;
		} catch (ApiException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 更新配送员信息接口（API）
	 * @author CaoHeYang
	 * @param req
	 * @return
	 */
	@Override
	public TaoBaoResponseBase update(WaimaiDeliveryUpdateRequest request) {
		try {
			WaimaiDeliveryUpdateResponse response = client.execute(request, sessionKey);
			TaoBaoResponseBase resp = new TaoBaoResponseBase();
			resp.setIs_success(response.getDeliveryUpdateResult().getSuccess());
			resp.setError_code(response.getDeliveryUpdateResult().getErrorCode());
			resp.setError_msg(response.getDeliveryUpdateResult().getErrorMsg());
			return resp;
		} catch (ApiException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 取件（API）
	 * @author CaoHeYang
	 * @param req
	 * @return
	 */
	@Override
	public TaoBaoResponseBase pickUp(WaimaiDeliveryPickupRequest req) {
		try {
			WaimaiDeliveryPickupResponse response = client.execute(req, sessionKey);
			TaoBaoResponseBase resp = new TaoBaoResponseBase();
			resp.setIs_success(response.getDeliveryOrderResult().getIsSuccess());
			resp.setError_code(response.getDeliveryOrderResult().getErrorCode());
			resp.setError_msg(response.getDeliveryOrderResult().getErrorMsg());
			resp.setResult(response.getDeliveryOrderResult().getResult());
			return resp;
		} catch (ApiException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 妥投（API）
	 * @author CaoHeYang
	 * @param req
	 * @return
	 */
	@Override
	public TaoBaoResponseBase confirm(WaimaiDeliveryConfirmRequest req) {
		try {
			WaimaiDeliveryConfirmResponse response = client.execute(req, sessionKey);
			TaoBaoResponseBase resp = new TaoBaoResponseBase();
			resp.setIs_success(response.getDeliveryOrderResult().getIsSuccess());
			resp.setError_code(response.getDeliveryOrderResult().getErrorCode());
			resp.setError_msg(response.getDeliveryOrderResult().getErrorMsg());
			resp.setResult(response.getDeliveryOrderResult().getResult());
		} catch (ApiException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 更新配送员位置信息（API）
	 * @author CaoHeYang
	 * @param req
	 * @return
	 */
	@Override
	public TaoBaoResponseBase locationUpdate(WaimaiDeliveryLocationUpdateRequest req) {
		try {
			WaimaiDeliveryLocationUpdateResponse response = client.execute(req, sessionKey);
			TaoBaoResponseBase resp = new TaoBaoResponseBase();
			resp.setIs_success(response.getDeliveryLocationResult().getIsSuccess());
			resp.setError_code(response.getDeliveryLocationResult().getErrorCode());
			resp.setError_msg(response.getDeliveryLocationResult().getErrorMsg());
			resp.setResult(response.getDeliveryLocationResult().getResult());
		} catch (ApiException e) {
			e.printStackTrace();
		}
		return null;
	}

}