package com.edaisong.business.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.entity.req.CancelOrderBusinessReq;
import com.edaisong.entity.resp.BusinessLoginResp;
import com.edaisong.entity.resp.CancelOrderBusinessResp;

@Controller
@RequestMapping("order")
public class OrderController {
	
	/**
	 * 订单详情 
	 * @author CaoHeYang
	 * @Date 20150805
	 * @return
	 */
	@RequestMapping(value = "detail", method = { RequestMethod.GET })
	public  ModelAndView detail() {
		ModelAndView model = new ModelAndView("");
		model.addObject("subtitle", "管理员");
		model.addObject("currenttitle", "开通城市管理");
		return model;
	}
	
	/**
	 * 商户后台取消订单
	 * @author CaoHeYang
	 * @Date 20150805
	 * @return
	 */
	@RequestMapping(value = "detail", method = { RequestMethod.POST })
	public @ResponseBody CancelOrderBusinessResp login(@RequestBody CancelOrderBusinessReq req) {
		CancelOrderBusinessResp resp=new CancelOrderBusinessResp();
		return resp;
	}
}
