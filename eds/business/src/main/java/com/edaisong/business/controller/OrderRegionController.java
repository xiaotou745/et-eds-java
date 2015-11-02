package com.edaisong.business.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IOrderRegionService;
import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.business.common.UserContext;
import com.edaisong.entity.OrderRegion;
import com.edaisong.entity.req.OrderRegionReq;


@Controller
@RequestMapping("orderregion")
public class OrderRegionController {
	@Autowired
	IOrderRegionService orderRegionService;
	@Autowired
	private IOrderService orderService;
	/**
	 * 区域列表页面
	 * 
	 * @author zhaohailong
	 * @Date 20151029
	 * @return
	 */
	@RequestMapping("list")
	public ModelAndView list(HttpServletRequest request) {
		OrderRegionReq orderRegionReq=new OrderRegionReq();
		UserContext context=UserContext.getCurrentContext(request);
/*		orderRegionReq.setBusinessId(context.getBusinessID());
		orderRegionReq.setStatus(1);*/
		List<OrderRegion> listData=orderRegionService.getOrderRegion(orderRegionReq);
		ModelAndView view = new ModelAndView("businessView");
		view.addObject("subtitle", "区域管理");
		view.addObject("currenttitle", "区域列表");
		view.addObject("viewPath", "orderregion/list");
		view.addObject("listData", listData);
		return view;
	}
	/**
	 * 新增区域
	 * 
	 * @author zhaohailong
	 * @Date 20151029
	 * @return
	 */
	@RequestMapping("insert")
	@ResponseBody
	public Integer insert(HttpServletRequest request) {
		UserContext context=UserContext.getCurrentContext(request);
		List<OrderRegion> regionList=new ArrayList<OrderRegion>();
		for (OrderRegion orderRegion : regionList) {
			orderRegion.setBusinessid(context.getBusinessID());
			orderRegion.setCreatename(context.getBusinessName());
			orderRegion.setOptname(context.getBusinessName());
		}
		return orderRegionService.insertRegionList(regionList);
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
	public int update(HttpServletRequest request) {
		UserContext context=UserContext.getCurrentContext(request);
		List<OrderRegion> regionList=new ArrayList<OrderRegion>();
		for (OrderRegion orderRegion : regionList) {
			orderRegion.setBusinessid(context.getBusinessID());
			orderRegion.setOptname(context.getBusinessName());
		}
		return orderRegionService.updateRegionList(regionList);
	}
	/**
	 * 检查区域内是否还有未完成的订单
	 * 
	 * @author zhaohailong
	 * @Date 20150806
	 * @return
	 */
	@RequestMapping("checkorder")
	@ResponseBody
	public Long checkOrder(Long regionId,HttpServletRequest request) {
		return orderService.queryIngOrderByRegionId(regionId);
	}
	/**
	 * 删除一个区域
	 * 
	 * @author zhaohailong
	 * @Date 20150806
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody
	public int delete(Integer regionId,HttpServletRequest request) {
		return orderRegionService.deleteById(regionId);
	}
}
