package com.edaisong.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IAlipayBatchService;
import com.edaisong.api.service.inter.IClienterWithdrawFormService;
import com.edaisong.api.service.inter.IOrderService;
import com.edaisong.api.service.inter.IPublicProvinceCityService;
import com.edaisong.entity.AlipayBatch;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.AlipayBatchClienterWithdrawForm;
import com.edaisong.entity.domain.AreaModel;
import com.edaisong.entity.domain.BusTaskList;
import com.edaisong.entity.req.PagedAlipayBatchListReq;
import com.edaisong.entity.req.PagedBusTaskListReq;

@Controller
@RequestMapping("finance")
public class FinanceController {
	@Autowired
	private IOrderService orderService;
	//开放城市
	 @Autowired
	 private IPublicProvinceCityService  publicProvinceCityService;
	 @Autowired
	 private IAlipayBatchService alipayBatchService;
	 @Autowired
	 private IClienterWithdrawFormService clienterWithdrawFormService;
	 
	@RequestMapping("bustasklist")
	public ModelAndView list() {
		List<AreaModel> areaListData=publicProvinceCityService.getOpenCityByJiBie(3);
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("areaListData", areaListData);
		model.addObject("subtitle", "财务管理");
		model.addObject("currenttitle", "门店任务审核");
		model.addObject("viewPath", "finance/bustasklist");
		return model;
	}
	
	@RequestMapping("bustasklistdo")
	public ModelAndView listdo(PagedBusTaskListReq req) {
		String startString=req.getStartDate();
		String endString=req.getEndDate();
		ModelAndView model = new ModelAndView("finance/bustasklistdo");
		PagedResponse<BusTaskList>  listPagedResponse=orderService.busTaskList(req);
		model.addObject("listData",listPagedResponse);
		model.addObject("sDate",startString);
		model.addObject("eDate",endString);
		return model;
	}
	
	/**
	 *  支付宝批次单列表页查询
	 *  @author CaoHeYang
	 *  @date 20151020
	 * @param req
	 * @return
	 */
	@RequestMapping("alipaybatchlist")
	public ModelAndView alipaybatchlist() {
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "财务管理");
		model.addObject("currenttitle", "支付宝批次进度查询");
		model.addObject("viewPath", "finance/alipaybatchlist");
		return model;
	}
	/**
	 *  支付宝批次单列表页查询
	 *  @author CaoHeYang
	 *  @date 20151020
	 * @param req
	 * @return
	 */
	@RequestMapping("alipaybatchlistdo")
	public ModelAndView alipaybatchlistdo(PagedAlipayBatchListReq req) {
		ModelAndView model = new ModelAndView("finance/alipaybatchlistdo");
		PagedResponse<AlipayBatch>  datas=alipayBatchService.getAlipayBatchPagedList(req);
		model.addObject("listData",datas);
		return model;
	}
	/**
	 *  支付宝批次单详情
	 *  @author CaoHeYang
	 *  @date 20151020
	 * @param req
	 * @return
	 */
	@RequestMapping("alipaybatchlistdetail")
	public ModelAndView alipaybatchlistdetail(Long id) {
		ModelAndView model = new ModelAndView("adminView");
	    AlipayBatch alipayBatch=alipayBatchService.getAlipayBatchById(id);
	    List<AlipayBatchClienterWithdrawForm> withdrawForms=clienterWithdrawFormService.getClienterWithdrawFormByBatchNo(id);
		model.addObject("alipayBatch",alipayBatch);
		model.addObject("withdrawForms",withdrawForms);
		model.addObject("subtitle", "财务管理");
		model.addObject("currenttitle", "支付宝批次进度查询> 批次详情");
		model.addObject("viewPath","finance/alipaybatchlistdetail");
		return model;
	}
}
