package com.edaisong.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.admin.common.UserContext;
import com.edaisong.api.service.inter.ITestUserTblService;
import com.edaisong.entity.UserOptRecord;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.TestUserRecord;
import com.edaisong.entity.req.PagedTestUserReq;


@Controller
@RequestMapping("testuser")
public class TestUserController {
	@Autowired
	private ITestUserTblService testUserTblService;

	@RequestMapping("list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "管理员");
		model.addObject("currenttitle", "管理测试账号");
		model.addObject("viewPath", "testuser/list");
		return model;
	}
	@RequestMapping("listdo")
	public ModelAndView listdo(PagedTestUserReq req) {
		PagedResponse<TestUserRecord> list = testUserTblService.getPagedList(req);
		ModelAndView model = new ModelAndView("testuser/listdo");
		model.addObject("listData", list);
		return model;
	}

	@ResponseBody
	@RequestMapping("add")
	public int add(String phoneNo,HttpServletRequest request) {
		UserOptRecord record=getUserOptRecord(phoneNo,0,request);
		return testUserTblService.insert(phoneNo,record);
	}
	
	@ResponseBody
	@RequestMapping("deleteinfo")
	public int delete(String phoneNo,int infoType,HttpServletRequest request) {
		UserOptRecord record=getUserOptRecord(phoneNo,infoType,request);
		switch (infoType) {
		case 1://删除骑士
			return testUserTblService.deleteTestClienter(phoneNo,record);
		case 2://删除订单
			return testUserTblService.deleteTestOrder(phoneNo,record);
		case 3://删除门店
			return testUserTblService.deleteTestBusiness(phoneNo,record);
		default:
			break;
		}
		return -1;
	}
	private UserOptRecord getUserOptRecord(String phoneNo,int infoType,HttpServletRequest request){
		String remark="测试账号管理-";
		switch (infoType) {
		case 0:
			remark+="添加测试手机号:";
			break;
		case 1:
			remark+="删除骑士账户:";
			break;
		case 2:
			remark+="删除订单:";
			break;
		case 3:
			remark+="删除门店:";
			break;
		}
		UserContext context=UserContext.getCurrentContext(request);
		UserOptRecord record=new UserOptRecord();
		record.setOpttype(1);
		record.setOptuserid(context.getId());
		record.setOptusername(context.getLoginName());
		record.setOptusertype(0);
		record.setRemark(remark+phoneNo);
		record.setUserid(0);
		record.setUsertype(0);
		return record;
	}
}
