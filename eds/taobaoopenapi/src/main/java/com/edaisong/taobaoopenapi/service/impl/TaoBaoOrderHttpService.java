package com.edaisong.taobaoopenapi.service.impl;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.edaisong.core.consts.TaoBaoConsts;
import com.edaisong.core.entity.Gps;
import com.edaisong.core.security.AES;
import com.edaisong.core.util.HttpUtil;
import com.edaisong.core.util.MapUtils;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.core.util.PropertyUtils;
import com.edaisong.entity.taobao.TaoBaoResponseBase;
import com.edaisong.entity.taobao.req.TaoBaoAsk;
import com.edaisong.entity.taobao.req.TaoBaoConfirm;
import com.edaisong.entity.taobao.req.TaoBaoLocationUpdate;
import com.edaisong.entity.taobao.req.TaoBaoPickUp;
import com.edaisong.entity.taobao.req.TaoBaoUpdate;
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
	private String sessionKey = "6100326184627d82ebfa6e1a6e44dfc0881cd93d40ee3222532754203";
	/**
	 * 确认接单接口(API)
	 * @author CaoHeYang
	 * @param req
	 * @return
	 */
	@Override
	public TaoBaoResponseBase ask(TaoBaoAsk r) {
		 TaobaoClient client = new DefaultTaobaoClient(TaoBaoConsts.Uri, TaoBaoConsts.AppKey, TaoBaoConsts.AppSecret);
		WaimaiOrderAckRequest req=new WaimaiOrderAckRequest();
		req.setDeliveryOrderNo(r.getDeliveryOrderNo());
		try {
			WaimaiOrderAckResponse response = client.execute(req, sessionKey);
			TaoBaoResponseBase resp = new TaoBaoResponseBase();
			resp.setIs_success(response.getAckOrderResult().getSuccess());
			resp.setError_code(response.getAckOrderResult().getErrorCode());
			resp.setError_msg(response.getAckOrderResult().getErrorMsg());
			return resp;
		} catch (Exception e) {
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
	public TaoBaoResponseBase update(TaoBaoUpdate r) {
		 TaobaoClient client = new DefaultTaobaoClient(TaoBaoConsts.Uri, TaoBaoConsts.AppKey, TaoBaoConsts.AppSecret);
		WaimaiDeliveryUpdateRequest request=new WaimaiDeliveryUpdateRequest ();
		Gps gps=MapUtils.bd09_To_Gcj02(ParseHelper.ToDouble(r.getLat(), 0d),ParseHelper.ToDouble(r.getLng(), 0d));
		request.setDeliveryOrderNo(r.getDeliveryOrderNo());
		request.setDelivererPhone(r.getDelivererPhone());
		request.setDelivererName(r.getDelivererName());
		request.setLng(gps.getWgLon()+"");
		request.setLat(gps.getWgLat()+"");
		request.setCardNo(r.getCardNo());
		request.setDelivererId(r.getDelivererId());
		try {
			WaimaiDeliveryUpdateResponse response = client.execute(request, sessionKey);
			TaoBaoResponseBase resp = new TaoBaoResponseBase();
			resp.setIs_success(response.getDeliveryUpdateResult().getSuccess());
			resp.setError_code(response.getDeliveryUpdateResult().getErrorCode());
			resp.setError_msg(response.getDeliveryUpdateResult().getErrorMsg());
			return resp;
		} catch (Exception e) {
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
	public TaoBaoResponseBase pickUp(TaoBaoPickUp r) {
		 TaobaoClient client = new DefaultTaobaoClient(TaoBaoConsts.Uri, TaoBaoConsts.AppKey, TaoBaoConsts.AppSecret);
		WaimaiDeliveryPickupRequest req=new WaimaiDeliveryPickupRequest();
		Gps gps=MapUtils.bd09_To_Gcj02(ParseHelper.ToDouble(r.getLat(), 0d),ParseHelper.ToDouble(r.getLng(), 0d));
		req.setDeliveryOrderNo(r.getDeliveryOrderNo());
		req.setLng(gps.getWgLon()+"");
		req.setLat(gps.getWgLat()+"");
		try {
			WaimaiDeliveryPickupResponse response = client.execute(req, sessionKey);
			TaoBaoResponseBase resp = new TaoBaoResponseBase();			
			resp.setIs_success(response.getDeliveryOrderResult().getIsSuccess());
			resp.setError_code(response.getDeliveryOrderResult().getErrorCode());
			resp.setError_msg(response.getDeliveryOrderResult().getErrorMsg());
			resp.setResult(response.getDeliveryOrderResult().getResult());
			return resp;
		} catch (Exception e) {
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
	public TaoBaoResponseBase confirm(TaoBaoConfirm r) {
		 TaobaoClient client = new DefaultTaobaoClient(TaoBaoConsts.Uri, TaoBaoConsts.AppKey, TaoBaoConsts.AppSecret);
		WaimaiDeliveryConfirmRequest req=new WaimaiDeliveryConfirmRequest() ;
		Gps gps=MapUtils.bd09_To_Gcj02(ParseHelper.ToDouble(r.getLat(), 0d),ParseHelper.ToDouble(r.getLng(), 0d));
		req.setDeliveryOrderNo(r.getDeliveryOrderNo());
		req.setLng(gps.getWgLon()+"");
		req.setLat(gps.getWgLat()+"");
		try {
			WaimaiDeliveryConfirmResponse response = client.execute(req, sessionKey);
			TaoBaoResponseBase resp = new TaoBaoResponseBase();
			resp.setIs_success(response.getDeliveryOrderResult().getIsSuccess());
			resp.setError_code(response.getDeliveryOrderResult().getErrorCode());
			resp.setError_msg(response.getDeliveryOrderResult().getErrorMsg());
			resp.setResult(response.getDeliveryOrderResult().getResult());
			return resp;
		} catch (Exception e) {
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
	public TaoBaoResponseBase locationUpdate(TaoBaoLocationUpdate r) {	
		 TaobaoClient client = new DefaultTaobaoClient(TaoBaoConsts.Uri, TaoBaoConsts.AppKey, TaoBaoConsts.AppSecret);
		WaimaiDeliveryLocationUpdateRequest req =new WaimaiDeliveryLocationUpdateRequest ();
		Gps gps=MapUtils.bd09_To_Gcj02(ParseHelper.ToDouble(r.getLat(), 0d),ParseHelper.ToDouble(r.getLng(), 0d));
		req.setDelivererPhone(r.getDelivererPhone());
		req.setDelivererName(r.getDelivererName());
		req.setLng(gps.getWgLon()+"");
		req.setLat(gps.getWgLat()+"");
		try {
			WaimaiDeliveryLocationUpdateResponse response = client.execute(req, sessionKey);
			TaoBaoResponseBase resp = new TaoBaoResponseBase();
			resp.setIs_success(response.getDeliveryLocationResult().getIsSuccess());
			resp.setError_code(response.getDeliveryLocationResult().getErrorCode());
			resp.setError_msg(response.getDeliveryLocationResult().getErrorMsg());
			resp.setResult(response.getDeliveryLocationResult().getResult());
			return resp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
