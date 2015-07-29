/*消息管理
 * danny-20150721
 * */
package com.edaisong.admin.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IMessageService;
import com.edaisong.entity.Message;
@Controller
@RequestMapping("message")
public class MessageManagerController {
	@Autowired
	private IMessageService messageService;
	@RequestMapping("list")
	public ModelAndView messageManager(HttpServletRequest request, HttpServletResponse res)
	{
		List<Message> data= messageService.getMessageList();
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "消息管理");
		model.addObject("currenttitle", "消息列表");
		model.addObject("viewPath", "message/list");
		model.addObject("DataList",data);
		return model;
	}
	

}
