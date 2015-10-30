package com.edaisong.business.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.entity.req.PagedOrderSearchReq;

@Controller
@RequestMapping("orderregion")
public class OrderRegionController {
	@Autowired
	IOrderService orderService;
	/**
	 * 区域列表页面
	 * 
	 * @author zhaohailong
	 * @Date 20151029
	 * @return
	 */
	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("businessView");
		view.addObject("subtitle", "区域管理");
		view.addObject("currenttitle", "区域列表");
		view.addObject("viewPath", "orderregion/list");
		return view;
	}
	/**
	 * 区域列表分页
	 * 
	 * @author zhaohailong
	 * @Date 20151029
	 * @return
	 */
	@RequestMapping("listdo")
	public ModelAndView listdo(PagedOrderSearchReq searchWebReq,HttpServletRequest request) {
		ModelAndView view = new ModelAndView("orderregion/listdo");
		view.addObject("listData", null);
		return view;
	}
	/**
	 * 区域详情
	 * 
	 * @author zhaohailong
	 * @Date 20151029
	 * @return
	 */
	@RequestMapping("detail")
	public ModelAndView detail(PagedOrderSearchReq searchWebReq,HttpServletRequest request) {
		ModelAndView view = new ModelAndView("orderregion/detail");
		view.addObject("listData", null);
		return view;
	}
	/**
	 * 修改区域信息
	 * 
	 * @author zhaohailong
	 * @Date 20150806
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody
	public int update(Long regionId,HttpServletRequest request) {
		return 0;
	}
}
