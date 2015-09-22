package com.edaisong.admin.controller;


import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;






import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IClienterBalanceRecordService;
import com.edaisong.api.service.inter.IClienterService;
import com.edaisong.api.service.inter.IDeliveryCompanyService;
import com.edaisong.api.service.inter.IFeedbackService;
import com.edaisong.api.service.inter.IPublicProvinceCityService;
import com.edaisong.entity.Clienter;
import com.edaisong.entity.ClienterBalanceRecord;
import com.edaisong.entity.DeliveryCompany;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.AreaModel;
import com.edaisong.entity.domain.ClienterModel;
import com.edaisong.entity.domain.FeedbackModel;
import com.edaisong.entity.req.PagedClienterBalanceRecordReq;
import com.edaisong.entity.req.ClienterOptionReq;
import com.edaisong.entity.req.PagedClienterReq;
import com.edaisong.entity.req.PagedFeedbackReq;



@Controller
@RequestMapping("feedback")
public class FeedbackController {
	 //意见反馈
	 @Autowired
	 private IFeedbackService  feedbackService;
	 
	 //开放城市
	 @Autowired
	 private IPublicProvinceCityService  publicProvinceCityService;
	/**
	 * 意见反馈列表管理页面 
	 * @author hulignbo
	 * @Date 2015年9月10日 09:14:21
	 * @param search 查询条件实体
	 * @return
	 */
	@RequestMapping("list")
	public ModelAndView list(){			
		List<AreaModel> areaListData=publicProvinceCityService.getOpenCityListFromRedis();		
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "客户服务");
		model.addObject("currenttitle", "意见与反馈");
	    model.addObject("areaListData", areaListData);
		model.addObject("viewPath", "feedback/list");
		return model;
	}	
	
	/**
	 * 意见反馈列表列表页面 
	 * @author hulignbo
	 * @Date 2015年9月10日 09:14:15
	 * @param search 查询条件实体
	 * @return
	 * @throws ParseException 
	 */	
	@RequestMapping("listdo")
	public ModelAndView listdo(PagedFeedbackReq req) throws ParseException {		
		
		PagedResponse<FeedbackModel> resp = feedbackService.query(req);
		ModelAndView model = new ModelAndView("feedback/listdo");
		model.addObject("listData", resp);
		return model;
	}		
	

}
