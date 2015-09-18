package com.edaisong.business.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IGroupBusinessRechargeService;
import com.edaisong.business.common.UserContext;
import com.edaisong.business.pay.AlipayNotify;
import com.edaisong.core.util.OrderNoHelper;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.entity.GroupBusinessRecharge;

@Controller
@RequestMapping("group")
public class GroupController {
	@Autowired
	private IGroupBusinessRechargeService groupBusinessRechargeService;

	/**
	 * @author hailongzhao
	 * @date 20150911
	 * @return
	 */
	@RequestMapping("recharge")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("businessView");
		view.addObject("subtitle", "");
		view.addObject("currenttitle", "充值");
		view.addObject("viewPath", "group/recharge");
		return view;
	}

	@RequestMapping("getrechargestatus")
	@ResponseBody
	public GroupBusinessRecharge getRechargeStatus(String orderNO) {
	   return	groupBusinessRechargeService.getByOrderNo(orderNO);
	}
	@RequestMapping("createorderno")
	@ResponseBody
	public String createOrderNO(HttpServletRequest request) {
		return OrderNoHelper.generateOrderCode(UserContext.getCurrentContext(
				request).getBusinessID());
	}
	// *******以下方法为集团商户充值支付宝相关页面********//
	@RequestMapping("alipayapi")
	public ModelAndView alipayapi(HttpServletRequest request) throws Exception {
		GroupBusinessRecharge record = new GroupBusinessRecharge();
		record.setGroupbusinessid(UserContext.getCurrentContext(request)
				.getBusinessID());
		record.setPaytype(request.getParameter("WIDdefaultbank"));
		record.setOrderno(request.getParameter("WIDout_trade_no"));
		record.setPayamount(ParseHelper.ToDouble(request.getParameter("WIDtotal_fee"), 0));
		record.setPaystatus(0);
		record.setPayby("");
		record.setRequesttime(new Date());
		record.setPaytime(null);
		record.setOriginalorderno("");
		groupBusinessRechargeService.insert(record);

		ModelAndView view = new ModelAndView("group/alipayapi");
		return view;
	}


	@RequestMapping("return_url")
	public void return_url(HttpServletRequest request) throws Exception {
		Map<String, String> paramMap=parseParamMap(request);
		boolean verify_result =false;
		if (paramMap!=null&&!paramMap.isEmpty()) {
			GroupBusinessRecharge record = new GroupBusinessRecharge();
			record.setOrderno(paramMap.get("out_trade_no"));// 商户订单号
			record.setOriginalorderno(paramMap.get("trade_no"));// 支付宝交易号
			record.setPayby(paramMap.get("buyer_email"));// 付款人的账号
			record.setPaytime(new Date());
			// 计算得出通知验证结果
		    verify_result = AlipayNotify.verify(paramMap);
			if (verify_result) {// 验证成功
				String trade_status = paramMap.get("trade_status");				// 交易状态
				if (trade_status.equals("TRADE_FINISHED")|| 
					trade_status.equals("TRADE_SUCCESS")) {
					record.setPaystatus(1);
				}
			} else {
				record.setPaystatus(-1);
			}
			groupBusinessRechargeService.recharge(record);
		}
	}

	private Map<String, String> parseParamMap(HttpServletRequest request)
			throws UnsupportedEncodingException {
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		if (requestParams!=null) {
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}
		}

		return params;
	}
	// *******以上方法为集团商户充值支付宝相关页面********//
}
