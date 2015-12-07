package com.edaisong.taobaoopenapi.service.impl;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.edaisong.core.consts.TaoBaoConsts;
import com.edaisong.core.security.AES;
import com.edaisong.core.util.HttpUtil;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.core.util.PropertyUtils;
import com.edaisong.entity.taobao.TaoBaoResponseBase;
import com.edaisong.taobaoopenapi.service.inter.ITmcControlHttpService;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.internal.tmc.Message;
import com.taobao.api.internal.tmc.MessageHandler;
import com.taobao.api.internal.tmc.MessageStatus;
import com.taobao.api.internal.tmc.TmcClient;
import com.taobao.api.request.WaimaiOrderAckRequest;
import com.taobao.api.response.WaimaiOrderAckResponse;
import com.taobao.top.link.LinkException;

/**
 * 
 * @author CaoHeYang
 *
 */
@Service
public class TmcControlHttpService implements ITmcControlHttpService {
	/**
	 * tmc 获取通知
	 * 
	 * @throws LinkException
	 */
	@Override
	public void main() throws LinkException {
		TmcClient client = new TmcClient(TaoBaoConsts.TMCUri, TaoBaoConsts.AppKey, TaoBaoConsts.AppSecret, TaoBaoConsts.GroupName);
		client.setMessageHandler(new MessageHandler() {
			@Override
			public void onMessage(Message message, MessageStatus status) throws Exception {
				System.out.println(message.getTopic());
				
				if (message.getTopic().equals(TaoBaoConsts.OrderDispatch) ) {
					orderDispatch(message.getContent());
				} else if (message.getTopic().equals(TaoBaoConsts.OrderClose) ) {
					orderClose(message.getContent());
				} else if (message.getTopic().equals(TaoBaoConsts.OuterOrderDispatch)) {

				}
			}
		});
		client.connect();
	}

	/**
	 * 订单指派通知消息(TMC)
	 * 
	 * @author hulingbo
	 * @date 2015年12月3日 12:05:08
	 */
	private void orderDispatch(String data) {
		String r = HttpUtil.sendPost(PropertyUtils.getProperty("TaoBaoOrderDispatch"), "data=" + AES.aesEncrypt(data));
		try {
			JSONObject jsonObject = new JSONObject(r);
			if (jsonObject.get("Status").toString() == "1") {
				TaobaoClient client = new DefaultTaobaoClient(TaoBaoConsts.Uri, TaoBaoConsts.AppKey, TaoBaoConsts.AppSecret);
				WaimaiOrderAckRequest req = new WaimaiOrderAckRequest();
				req.setDeliveryOrderNo(ParseHelper.ToLong(jsonObject.get("Result"), 0));
				WaimaiOrderAckResponse response = client.execute(req, "6102406e99c58f1bdaf2d37b1ca7cd133a84e0087e3a7422532754203");
				TaoBaoResponseBase resp = new TaoBaoResponseBase();
				resp.setIs_success(response.getAckOrderResult().getSuccess());
				resp.setError_code(response.getAckOrderResult().getErrorCode());
				resp.setError_msg(response.getAckOrderResult().getErrorMsg());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 订单关闭通知消息(TMC)
	 * 
	 * @author CaoHeYang
	 * @date 20151113
	 */
	private void orderClose(String data) {
		String r = HttpUtil.sendPost(PropertyUtils.getProperty("TaoBaoCloseOrder"), "data=" + AES.aesEncrypt(data));
		// TODo 不成功时写日志
	}

	/**
	 * 外部订单指派通知消息(TMC)
	 * 
	 * @author CaoHeYang
	 * @date 20151113
	 */
	private void outerOrderDispatch() {

	}
}
