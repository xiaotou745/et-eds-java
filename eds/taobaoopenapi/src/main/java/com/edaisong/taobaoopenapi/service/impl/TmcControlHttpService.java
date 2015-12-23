package com.edaisong.taobaoopenapi.service.impl;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.common.LogServiceBLL;
import com.edaisong.core.consts.TaoBaoConsts;
import com.edaisong.core.security.AES;
import com.edaisong.core.util.HttpUtil;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.core.util.PropertyUtils;
import com.edaisong.core.util.StringUtils;
import com.edaisong.core.util.SystemUtils;
import com.edaisong.entity.domain.ActionLog;
import com.edaisong.entity.taobao.TaoBaoResponseBase;
import com.edaisong.taobaoopenapi.service.inter.ITmcControlHttpService;
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
	@Autowired
	LogServiceBLL logServiceBLL;
	/**
	 * tmc 获取通知
	 * 
	 * @throws LinkException
	 */
	@Override
	public TaoBaoResponseBase main() throws Exception {
		TmcClient client = new TmcClient(TaoBaoConsts.TMCUri, TaoBaoConsts.AppKey, TaoBaoConsts.AppSecret, TaoBaoConsts.GroupName);
		client.setMessageHandler(new MessageHandler() {
			@Override
			public void onMessage(Message message, MessageStatus status) throws Exception {				
				if (message.getTopic().equals(TaoBaoConsts.OrderDispatch) ) {
					orderDispatch(message.getContent());
				} else if (message.getTopic().equals(TaoBaoConsts.OrderClose) ) {
					orderClose(message.getContent());
				} else if (message.getTopic().equals(TaoBaoConsts.OuterOrderDispatch)) {

				}
			}
		});
		client.connect();
		TaoBaoResponseBase rep=new TaoBaoResponseBase();
		rep.setIs_success(true);
		rep.setError_code("");
		rep.setError_msg("");
		rep.setResult(true);
		return rep;
	}

	/**
	 * 订单指派通知消息(TMC)
	 * 
	 * @author hulingbo
	 * @date 2015年12月3日 12:05:08
	 */
	private void orderDispatch(String data) {
		Date requestTime = new Date();
		try {
			String resultJson = "";
			String exceptionMsg = "";
			String stackTrace = "";
			String param="";
			String url="";
			try {
				param = AES.aesEncrypt(data);
				url = PropertyUtils.getProperty("TaoBaoOrderDispatch");
				resultJson = HttpUtil.sendPost(url, "data=" + param);
			} catch (Exception e) {
				exceptionMsg = e.getMessage();
				stackTrace = StringUtils.getStackTrace(e);
			}
			//记录调用openapi的日志
			writeLog("TmcControlHttpService.orderDispatch==" + url, param, data, resultJson,exceptionMsg, stackTrace, requestTime);
			if (resultJson != null && !resultJson.isEmpty()) {
				JSONObject jsonObject = new JSONObject(resultJson);
				if (jsonObject.getInt("Status") == 1) {
					String taobaoExceptionMsg = "";
					String taobaoStackTrace = "";
					Date taobaoRequestTime = new Date();
					WaimaiOrderAckResponse response = new WaimaiOrderAckResponse();
					Long deliveryOrderNo = ParseHelper.ToLong(jsonObject.getLong("Result"), 0);
					try {
						TaobaoClient client = new DefaultTaobaoClient(TaoBaoConsts.Uri, TaoBaoConsts.AppKey,TaoBaoConsts.AppSecret);
						WaimaiOrderAckRequest req = new WaimaiOrderAckRequest();
						req.setDeliveryOrderNo(deliveryOrderNo);
						response = client.execute(req, TaoBaoConsts.SessionKey);
						// TaoBaoResponseBase resp = new TaoBaoResponseBase();
						// resp.setIs_success(response.getAckOrderResult().getSuccess());
						// resp.setError_code(response.getAckOrderResult().getErrorCode());
						// resp.setError_msg(response.getAckOrderResult().getErrorMsg());
					} catch (Exception e) {
						taobaoExceptionMsg = e.getMessage();
						taobaoStackTrace = StringUtils.getStackTrace(e);
					}
					//记录调用taobaoapi的日志
					writeLog("TmcControlHttpService.orderDispatch==" + TaoBaoConsts.Uri,deliveryOrderNo.toString(),deliveryOrderNo.toString(),JsonUtil.obj2string(response), taobaoExceptionMsg,taobaoStackTrace, taobaoRequestTime);
				}
			}
		} catch (Exception e) {
			String stackTrace = StringUtils.getStackTrace(e);
			//记录当前方法的错误日志
			writeLog("TmcControlHttpService.orderDispatch", data, data, "",e.getMessage(), stackTrace, requestTime);
		}
	}

	/**
	 * 订单关闭通知消息(TMC)
	 * 
	 * @author CaoHeYang
	 * @date 20151113
	 */
	private void orderClose(String data) {
		String param ="";
		String url ="";
		String resultJson = "";
		String exceptionMsg = "";
		String stackTrace = "";
		Date requestTime = new Date();
		try {
			param = AES.aesEncrypt(data);
			url = PropertyUtils.getProperty("TaoBaoCloseOrder");
			resultJson = HttpUtil.sendPost(url, "data=" + param);
		} catch (Exception e) {
			exceptionMsg = e.getMessage();
			stackTrace = StringUtils.getStackTrace(e);
		}
		writeLog("TmcControlHttpService.orderClose"+url, param, data, resultJson,exceptionMsg, stackTrace, requestTime);
	}

	/**
	 * 外部订单指派通知消息(TMC)
	 * 
	 * @author CaoHeYang
	 * @date 20151113
	 */
	private void outerOrderDispatch() {

	}
	private void writeLog(String url,String param,String decryptMsg,
			String resultJson,String exceptionMsg,String stackTrace,Date requestTime){
		Date endDate=new Date();
		List<String> ipinfoList = SystemUtils.getLocalIpInfo();
		String appServerIP = JsonUtil.obj2string(ipinfoList);
		ActionLog logEngity = new ActionLog();
		logEngity.setUserID(-1);
		logEngity.setUserName("");
		logEngity.setRequestType(0);
		logEngity.setClientIp("");
		logEngity.setSourceSys("taobaoopenapi");
		logEngity.setRequestUrl(url);
		logEngity.setParam(param);
		logEngity.setDecryptMsg(decryptMsg);
		logEngity.setContentType("application/json; charset=utf-8");
		logEngity.setHeader("");
		logEngity.setRequestMethod("POST");
		logEngity.setMethodName("");
		logEngity.setResultJson(resultJson);
		logEngity.setAppServer(appServerIP);
		logEngity.setException(exceptionMsg);
		logEngity.setStackTrace(stackTrace);
		logEngity.setExecuteTime(endDate.getTime() - requestTime.getTime());
		logEngity.setRequestTime(ParseHelper.ToDateString(requestTime, ""));
		logEngity.setRequestEndTime(ParseHelper.ToDateString(endDate, ""));
		logServiceBLL.SystemActionLog(logEngity);
	}
}
