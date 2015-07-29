package com.edaisong.admin.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IClienterService;
import com.edaisong.api.service.inter.IDeliveryCompanyService;
import com.edaisong.api.service.inter.IPublicProvinceCityService;
import com.edaisong.entity.Account;
import com.edaisong.entity.Clienter;
import com.edaisong.entity.DeliveryCompany;
import com.edaisong.entity.Group;
import com.edaisong.entity.common.ResponsePageList;
import com.edaisong.entity.domain.AreaModel;
import com.edaisong.entity.domain.ClienterModel;
import com.edaisong.entity.req.AccountReq;
import com.edaisong.entity.req.ClienterOptionReq;
import com.edaisong.entity.req.ClienterReq;
import com.edaisong.entity.resp.AccountResp;
import com.edaisong.entity.resp.ClienterResp;

@Controller
@RequestMapping("clienter")
public class ClienterController {
	 @Autowired
	 private IClienterService  clienterService;
	 
	 @Autowired
	 private IPublicProvinceCityService  publicProvinceCityService;
	 
	 @Autowired
	 private IDeliveryCompanyService  deliveryCompanyService;
	 
	@RequestMapping("list")
	public ModelAndView list(){		
	
		List<AreaModel> areaListData=publicProvinceCityService.getOpenCityListFromRedis();
		List<DeliveryCompany> dCListData=deliveryCompanyService.getDeliveryCompanyList();
		
		
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "管理员");
		model.addObject("currenttitle", "骑士管理");
		model.addObject("areaListData", areaListData);
		model.addObject("dCListData", dCListData);
		model.addObject("viewPath", "clienter/clientermanager");
		return model;
	}	
	
	@RequestMapping("clientermanagerlist")
	public ModelAndView list(ClienterReq req) {		
		
		ResponsePageList<ClienterModel> resp = clienterService.queryClienter(req);
		ModelAndView model = new ModelAndView();
		model.addObject("viewPath", "clienter/clientermanagerlist");
		model.addObject("listData", resp);
		return model;
	}
	
	
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
	
	@RequestMapping("modifymoney")
	@ResponseBody
	public void modifymoney(@ModelAttribute("clienteroptionreq") ClienterOptionReq clienteroptionreq){
		
		ClienterOptionReq  record=clienteroptionreq;	
		record.setOptName("admin");		
		clienterService.modifyMoneyById(record);
	}		

}
