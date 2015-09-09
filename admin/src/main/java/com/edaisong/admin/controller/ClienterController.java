package com.edaisong.admin.controller;


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
import com.edaisong.api.service.inter.IClienterForzenService;
import com.edaisong.api.service.inter.IClienterService;
import com.edaisong.api.service.inter.IDeliveryCompanyService;
import com.edaisong.api.service.inter.IPublicProvinceCityService;
import com.edaisong.entity.Clienter;
import com.edaisong.entity.ClienterBalanceRecord;
import com.edaisong.entity.ClienterForzen;
import com.edaisong.entity.DeliveryCompany;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.AreaModel;
import com.edaisong.entity.domain.ClienterModel;
import com.edaisong.entity.req.PagedClienterBalanceRecordReq;
import com.edaisong.entity.req.ClienterOptionReq;
import com.edaisong.entity.req.PagedClienterForzenReq;
import com.edaisong.entity.req.PagedClienterReq;



@Controller
@RequestMapping("clienter")
public class ClienterController {
	 //骑士
	 @Autowired
	 private IClienterService  clienterService;
	 //骑士流水
	 @Autowired
	 private IClienterBalanceRecordService clienterBalanceRecordService;	 
	 //开放城市
	 @Autowired
	 private IPublicProvinceCityService  publicProvinceCityService;
	 //集团
	 @Autowired
	 private IDeliveryCompanyService  deliveryCompanyService;
	//冻结单
	 @Autowired
	 private IClienterForzenService clienterForzenService;
	 
	/**
	 * 骑士列表管理页面 
	 * @author hulignbo
	 * @Date 20150730
	 * @param search 查询条件实体
	 * @return
	 */
	@RequestMapping("list")
	public ModelAndView list(){		
	
		List<AreaModel> areaListData=publicProvinceCityService.getOpenCityListFromRedis();
		List<DeliveryCompany> dCListData=deliveryCompanyService.getDeliveryCompanyList();	
		
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "管理员");
		model.addObject("currenttitle", "骑士管理");
		model.addObject("areaListData", areaListData);
		model.addObject("dCListData", dCListData);
		model.addObject("viewPath", "clienter/list");
		return model;
	}	
	
	/**
	 * 骑士列表列表页面 
	 * @author hulignbo
	 * @Date 20150730
	 * @param search 查询条件实体
	 * @return
	 */	
	@RequestMapping("listdo")
	public ModelAndView listdo(PagedClienterReq req) {		
		
		PagedResponse<ClienterModel> resp = clienterService.query(req);
		//ModelAndView model = new ModelAndView();//默认listdo.jsp
		ModelAndView model = new ModelAndView("clienter/listdo");
		model.addObject("listData", resp);
		return model;
	}	
	
	/**
	 * 骑士余额管理页面 
	 * @author hulignbo
	 * @Date 20150730
	 * @param search 查询条件实体
	 * @return
	 */	
	@RequestMapping("clienterbalancerecordlist")
	public ModelAndView clienterbalancerecordlist(HttpServletRequest request){				

		int clienterId=Integer.parseInt(request.getParameter("clienterId"));
		
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "管理员");
		model.addObject("currenttitle", "骑士管理/收支记录");
		model.addObject("clienterId", clienterId);		
		model.addObject("viewPath", "clienterbalancerecord/list");
		return model;
	}	
	
	/**
	 * 骑士余额列表页面 
	 * @author hulignbo
	 * @Date 20150730
	 * @param search 查询条件实体
	 * @return
	 */	
	@RequestMapping("clienterbalancerecordlistdo")
	public ModelAndView clienterbalancerecordlist(PagedClienterBalanceRecordReq req) {		
		
		PagedResponse<ClienterBalanceRecord> resp = clienterBalanceRecordService.query(req);
		ModelAndView model = new ModelAndView("clienterbalancerecord/listdo");		
		model.addObject("listData", resp);
		return model;
	}	
	
	/**
	 * 审核通过
	 * @author hulignbo
	 * @Date 20150730
	 * @param search 查询条件实体
	 * @return
	 */	
	@RequestMapping("auditok")
	@ResponseBody
	public void auditok(HttpServletRequest request){

		int id=Integer.parseInt(request.getParameter("id"));
		String status="1";
		Clienter record=new Clienter();
		record.setId(id);;	
		record.setStatus(Byte.parseByte(status));
		clienterService.modifyStatusById(record);
	}	
	
	/**
	 * 审核拒绝 
	 * @author hulignbo
	 * @Date 20150730
	 * @param search 查询条件实体
	 * @return
	 */	
	@RequestMapping("auditcancel")
	@ResponseBody
	public void auditcancel(HttpServletRequest request){

		int id=Integer.parseInt(request.getParameter("id"));
		String status="0";
		Clienter record=new Clienter();
		record.setId(id);;	
		record.setStatus(Byte.parseByte(status));
	 clienterService.modifyStatusById(record);
	}	
	
	/**
	 * 余额变更
	 * @author hulignbo
	 * @Date 20150730
	 * @param search 查询条件实体
	 * @return
	 */	
	@RequestMapping("modifymoney")
	@ResponseBody
	public void modifymoney(@ModelAttribute("clienteroptionreq") ClienterOptionReq clienteroptionreq){
		
		ClienterOptionReq  record=clienteroptionreq;	
		record.setOptName("admin");		
		clienterService.modifyMoneyById(record);
	}	
	/**
	 * 冻结单列表管理页面 
	 * @author 茹化肖
	 * @Date 2015年9月9日11:43:32
	 * @param search 查询条件实体
	 * @return
	 */
	@RequestMapping("forzenlist")
	public ModelAndView forzenlist(){		
		
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "骑士管理");
		model.addObject("currenttitle", "冻结单管理");
		model.addObject("viewPath", "clienter/forzenlist");
		return model;
	}
	
	/**
	 * 冻结单列表管理页面 --异步列表
	 * @author 茹化肖
	 * @Date 2015年9月9日11:43:32
	 * @param search 查询条件实体
	 * @return
	 */
	@RequestMapping("forzenlistdo")
	public ModelAndView forzenlistdo(PagedClienterForzenReq par){		
		PagedResponse<ClienterForzen> result= clienterForzenService.getForzenList(par);
		ModelAndView model = new ModelAndView("clienter/forzenlistdo");
		model.addObject("listData", result);
		return model;
	}

}
