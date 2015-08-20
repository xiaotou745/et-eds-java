
package com.edaisong.business.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("clienter")
public class ClienterController {

	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView model = new ModelAndView("businessView");
		model.addObject("subtitle", "骑士管理");
		model.addObject("currenttitle", "骑士管理");
		return null;
	}
}
