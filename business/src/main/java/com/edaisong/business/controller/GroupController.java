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
		String orderNO = new String(request.getParameter("WIDout_trade_no")
				.getBytes("ISO-8859-1"), "UTF-8");
		String defaultbank = new String(request.getParameter("WIDdefaultbank")
				.getBytes("ISO-8859-1"), "UTF-8");
		String total_fee = new String(request.getParameter("WIDtotal_fee")
				.getBytes("ISO-8859-1"), "UTF-8");

		GroupBusinessRecharge record = new GroupBusinessRecharge();
		record.setGroupbusinessid(UserContext.getCurrentContext(request)
				.getBusinessID());
		record.setPaytype(defaultbank);
		record.setOrderno(orderNO);
		record.setPayamount(ParseHelper.ToDouble(total_fee, 0));
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
	public ModelAndView return_url(HttpServletRequest request) {
		boolean verify_result = false;
		GroupBusinessRecharge record = new GroupBusinessRecharge();
		record.setPaystatus(-1);
		try {
			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			// 商户订单号
			String out_trade_no = new String(request.getParameter(
					"out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

			// 支付宝交易号
			String trade_no = new String(request.getParameter("trade_no")
					.getBytes("ISO-8859-1"), "UTF-8");

			// 交易状态
			String trade_status = new String(request.getParameter(
					"trade_status").getBytes("ISO-8859-1"), "UTF-8");

			// 付款人的账号
			String buyer_email = new String(request.getParameter("buyer_email")
					.getBytes("ISO-8859-1"), "UTF-8");
			record.setOrderno(out_trade_no);
			record.setOriginalorderno(trade_no);
			record.setPayby(buyer_email);
			record.setPaytime(new Date());
			// 计算得出通知验证结果
			verify_result = AlipayNotify.verify(parseParamMap(request));
			if (verify_result) {// 验证成功
				if (trade_status.equals("TRADE_FINISHED")
						|| trade_status.equals("TRADE_SUCCESS")) {
					record.setPaystatus(1);
				}
			} else {
				record.setPaystatus(-1);
			}
		} catch (UnsupportedEncodingException e) {
			verify_result = false;
		}
		groupBusinessRechargeService.recharge(record,"集团商家充值",UserContext.getCurrentContext(request)
				.getBusinessName());
		ModelAndView view = new ModelAndView("group/return_url");
		view.addObject("verify_result", verify_result);
		return view;
	}

	private Map<String, String> parseParamMap(HttpServletRequest request)
			throws UnsupportedEncodingException {
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		return params;
	}
	// *******以上方法为集团商户充值支付宝相关页面********//
}
