package com.edaisong.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.admin.common.UserContext;
import com.edaisong.api.service.inter.IMarkService;
import com.edaisong.entity.Mark;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.common.ResponseBase;
import com.edaisong.entity.req.MarkEditReq;
import com.edaisong.entity.req.MarkReq;

@Controller
@RequestMapping("mark")
public class MarkManagerController {
	
	@Autowired
	private IMarkService markService;
	/**
	 * @Des 标签管理
	 * @Author WangXuDan
	 * @Date 2015年9月18日10:19:28
	 * @Return
	 */
	@RequestMapping("list")
	public ModelAndView marklist(){	
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "标签管理");
		model.addObject("currenttitle", "标签管理");
		model.addObject("viewPath", "markmanager/list");
		return model;
	}
	/**
	 * @Des 标签管理列表
	 * @Author WangXuDan
	 * @Date 2015年9月18日10:19:28
	 * @Return
	 */
	@RequestMapping("listdo")
	public ModelAndView listdo(MarkReq req) {	
		PagedResponse<Mark> resp =markService.getMarkList(req);
		ModelAndView model = new ModelAndView("markmanager/listdo");
		model.addObject("listData", resp);
		return model;
	}
	/**
	* @Des 编辑标签（新增/修改） 
	* @Author WangXuDan
	* @Date 2015年9月19日14:48:55
	* @Return
	*/
	@RequestMapping(value="editmark",method= {RequestMethod.POST})
	@ResponseBody
	public ResponseBase editMark(MarkEditReq markEditReq,HttpServletRequest request){
		ResponseBase response = new ResponseBase();
		markEditReq.setOperator(UserContext.getCurrentContext(request).getName());
		response = markService.editMark(markEditReq);
		return response;		
	}
	/**
	* @Des 修改标签状态 
	* @Author WangXuDan
	* @Date 2015年9月20日19:57:19
	* @Return
	*/
	@RequestMapping(value="modifyMarkStatus",method= {RequestMethod.POST})
	@ResponseBody
	public ResponseBase modifyMarkStatus(MarkEditReq markEditReq,HttpServletRequest request){
		ResponseBase response = new ResponseBase();
		markEditReq.setOperator(UserContext.getCurrentContext(request).getName());
		response = markService.modifyMarkStatus(markEditReq);
		return response;	
	}
}
