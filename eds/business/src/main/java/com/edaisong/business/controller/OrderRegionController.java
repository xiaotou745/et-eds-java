package com.edaisong.business.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IBusinessService;
import com.edaisong.api.service.inter.IOrderRegionLogService;
import com.edaisong.api.service.inter.IOrderRegionService;
import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.business.common.UserContext;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.entity.BusinessMessage;
import com.edaisong.entity.OrderRegion;
import com.edaisong.entity.OrderRegionLog;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.BusinessModel;
import com.edaisong.entity.domain.LatAndLng;
import com.edaisong.entity.domain.OrderRegionModel;
import com.edaisong.entity.domain.RegionOrderDetail;
import com.edaisong.entity.domain.RegionOrderTotal;
import com.edaisong.entity.req.OrderRegionReq;
import com.edaisong.entity.req.PagedBusinessMessageReq;


@Controller
@RequestMapping("orderregion")
public class OrderRegionController {
	@Autowired
	IOrderRegionService orderRegionService;
	@Autowired 
	IOrderRegionLogService orderRegionLogService;
	@Autowired
	private IOrderService orderService;
	@Autowired
	private IBusinessService businessService;
	/**
	 * 区域列表页面
	 * 
	 * @author zhaohailong
	 * @Date 20151029
	 * @return
	 */
	@RequestMapping("regionmanage")
	public ModelAndView regionManage(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("businessView");
		view.addObject("subtitle", "区域管理");
		view.addObject("currenttitle", "区域列表");
		view.addObject("viewPath", "orderregion/manage");
		BusinessModel bus=businessService.getBusiness((long)UserContext.getCurrentContext(request).getBusinessID());
		String businessLat=bus.getLatitude()+";"+bus.getLongitude();
		view.addObject("businessLat", businessLat);
		int businessId = UserContext.getCurrentContext(request).getBusinessID();
		String json=JsonUtil.obj2string(getRegionSettings(businessId));
		view.addObject("regionjson", json);
		 return view;
	}
	
	@RequestMapping("loglist")
	public ModelAndView logList(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("businessView");
		view.addObject("subtitle", "区域管理");
		view.addObject("currenttitle", "区域操作记录");
		view.addObject("viewPath", "orderregion/loglist");
		 return view;
	}
	/**
	 * 列表分页
	 * @param 分页请求信息
	 * @param request
	 * @return
	 */
	@RequestMapping("listdo")
	public ModelAndView listdo(PagedBusinessMessageReq searchWebReq,HttpServletRequest request) {
		ModelAndView view = new ModelAndView("orderregion/listdo");
		int businessId = UserContext.getCurrentContext(request).getBusinessID();
		searchWebReq.setBusinessId(businessId);
		PagedResponse<OrderRegionLog> resp = orderRegionLogService.getPagedList(searchWebReq);
		view.addObject("listData", resp);
		return view;
	}
	private List<OrderRegionModel> getRegionSettings(int businessid){
		List<OrderRegionModel> result=new ArrayList<OrderRegionModel>();	
		OrderRegionReq orderRegionReq=new OrderRegionReq();
		orderRegionReq.setBusinessId(businessid);
		orderRegionReq.setStatus(1);
		List<OrderRegion> listData=orderRegionService.getOrderRegion(orderRegionReq);
		for (OrderRegion orderRegion : listData) {
			OrderRegionModel tempItem=new OrderRegionModel();
			tempItem.setOverlayId(orderRegion.getId());
			tempItem.setOverlayName(orderRegion.getName());
			tempItem.setOpttime(orderRegion.getOpttime());
			tempItem.setOverlayPointList(createList(orderRegion.getCoordinate()));
			tempItem.setSubLists(new ArrayList<OrderRegionModel>());
			if (orderRegion.getParentid()==0) {
				result.add(tempItem);
			}else {
				OrderRegionModel  temp=result.stream().filter(t ->t.getOverlayId().equals(orderRegion.getParentid())).findFirst().get();
				if (temp!=null) {
					temp.getSubLists().add(tempItem);
				}else {
					OrderRegion  tempOldParent=listData.stream().filter(t ->t.getId().equals(orderRegion.getParentid())).findFirst().get();
					OrderRegionModel tempParent=new OrderRegionModel();
					tempParent.setOverlayId(tempOldParent.getId());
					tempParent.setOverlayName(tempOldParent.getName());
					tempItem.setOpttime(tempOldParent.getOpttime());
					tempParent.setOverlayPointList(createList(tempOldParent.getCoordinate()));
					tempParent.setSubLists(new ArrayList<OrderRegionModel>());
					tempParent.getSubLists().add(tempItem);
					result.add(tempParent);
				}
			}
		}
		sort(result);
		return result;
	}
	private void sort(List<OrderRegionModel> listdata){
		//先按照二级区域个数排序，在按照操作时间升序
		Comparator<OrderRegionModel> c = (a, b) -> {return a.getSubLists().size() - b.getSubLists().size();};
		c = c.thenComparing((p, m) -> {return (int) (p.getOpttime().getTime() - m.getOpttime().getTime());});
		listdata.sort(c);
		// 二级区域按照操作时间排序
		for (OrderRegionModel orderRegionModel : listdata) {
			orderRegionModel.getSubLists().stream().sorted((a, b) -> {
				return (int) (a.getOpttime().getTime() - b.getOpttime().getTime());
			});
		}
	}
	private List<LatAndLng> createList(String coordinate){
		List<LatAndLng> result=new ArrayList<LatAndLng>();
		String[] points=coordinate.split(";");
		String[] lats=null;
		for (String point : points) {
			lats=point.split(",");
			LatAndLng temp=new LatAndLng();
			temp.setLat(ParseHelper.ToDouble(lats[0], 0d));
			temp.setLng(ParseHelper.ToDouble(lats[1], 0d));
			result.add(temp);
		}
		return result;
	}
	/**
	 * 保存所有区域
	 * 
	 * @author zhaohailong
	 * @Date 20151029
	 * @return
	 */
	@RequestMapping("saveall")
	@ResponseBody
	public String saveAll(String regions,HttpServletRequest request) {
		UserContext context=UserContext.getCurrentContext(request);
		List<OrderRegion> regionList=JsonUtil.str2list(regions, OrderRegion.class);
		for (OrderRegion orderRegion : regionList) {
			orderRegion.setBusinessid(context.getBusinessID());
			orderRegion.setCreatename(context.getBusinessName());
			orderRegion.setOptname(context.getBusinessName());
			orderRegion.setHaschild(false);
			if (orderRegion.getParentid().equals(0)) {
				long  temp=regionList.stream().filter(t ->t.getParentid().equals(orderRegion.getId())).count();
				if (temp>0) {
					orderRegion.setHaschild(true);
				}
			}
		}
		OrderRegionReq orderRegionReq=new OrderRegionReq();
		orderRegionReq.setBusinessId(context.getBusinessID());
		orderRegionReq.setStatus(1);
		List<OrderRegion> oldData=orderRegionService.getOrderRegion(orderRegionReq);
	
		List<Integer> oldList =oldData.stream().map(t->t.getId()).collect(Collectors.toList());
		List<Integer> newList = regionList.stream().map(t->t.getId()).collect(Collectors.toList());
		List<Integer> commonList = new ArrayList<Integer>();
		List<Integer> oldNewList = new ArrayList<Integer>();
		commonList.addAll(newList);
		oldNewList.addAll(newList);
		
		commonList.retainAll(oldList);//取交集,这部分需要update
		newList.removeAll(oldList);// newList中剩余的是新增的区域id
		oldList.removeAll(oldNewList);// oldList中剩余的是被删掉的区域id
		List<OrderRegion>  updateList=regionList.stream().filter(t ->commonList.contains(t.getId())).collect(Collectors.toList());
		int updateResult=orderRegionService.updateRegionList(updateList,oldData);
		List<OrderRegion>  insertList=regionList.stream().filter(t ->newList.contains(t.getId())).collect(Collectors.toList());
		int insertResult=orderRegionService.insertRegionList(insertList,oldData);
		List<String> deleteResult=orderRegionService.deleteRegionList(oldList, oldData);
		if (deleteResult!=null&&deleteResult.size()>0) {
			return "以下区域或其二级区域今日还存在未完成的订单，不能删除"+String.join("\n", deleteResult);
		}
		if (updateResult==0&&insertResult==0&&oldList.size()==0) {
			return "没有任何修改，不需要保存";
		}
		return "操作成功";
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
	 * e代送新流程中的h5今日订单页面区域统计(给手机app用，当前站点不显示)
	 * 根据区域查询订单数量
	 * @author zhaohailong
	 * @Date 20151029
	 * @return
	 */
	@RequestMapping("todayone")
	public ModelAndView todayList(Long businessid) {	
		ModelAndView model = new ModelAndView("orderregion/todayone");
		String json=JsonUtil.obj2string(getRegionSettings(businessid.intValue()));
		model.addObject("regionjson", json);
		List<RegionOrderTotal> totalData=orderService.queryTodayOrderTotal(businessid);
		List<RegionOrderDetail> detailData=orderService.queryTodayOrderDetail(businessid);
		String totalJson=JsonUtil.obj2string(totalData);
		String detailJson=JsonUtil.obj2string(detailData);
		model.addObject("totalJson", totalJson);
		model.addObject("detailJson", detailJson);
		BusinessModel bus=businessService.getBusiness(businessid);
		String businessLat=bus.getLatitude()+";"+bus.getLongitude();
		model.addObject("businessLat", businessLat);
		model.addObject("businessid", businessid);
		return model;
	}
//	/**
//	 * e代送新流程中的h5今日订单页面区域统计(给手机app用，当前站点不显示)
//	 * 根据区域查询订单数量
//	 * @author zhaohailong
//	 * @Date 20151029
//	 * @return
//	 */
//	@RequestMapping(value = "todaytotal", produces= "application/json; charset=utf-8")
//	@ResponseBody
//	public List<RegionOrderTotal> todayTotal(HttpServletRequest request) {
//		UserContext context = UserContext.getCurrentContext(request);
//		return orderService.queryTodayOrderTotal((long)context.getBusinessID());
//	}
//	/**
//	 * e代送新流程中的h5今日订单页面区域详情
//	 * 根据区域查询订单数量
//	 * @author zhaohailong
//	 * @Date 20151029
//	 * @return
//	 */
//	@RequestMapping(value = "todaydetail", produces= "application/json; charset=utf-8")
//	@ResponseBody
//	public List<RegionOrderDetail> todayDetail(HttpServletRequest request) {
//		UserContext context = UserContext.getCurrentContext(request);
//		return orderService.queryTodayOrderDetail((long)context.getBusinessID());
//	}
}
