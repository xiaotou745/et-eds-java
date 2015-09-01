package com.edaisong.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IAccountService;
import com.edaisong.api.service.inter.IAuthorityMenuClassService;
import com.edaisong.entity.Account;
import com.edaisong.entity.AuthorityMenuClass;
import com.edaisong.entity.MenuEntity;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.PagedAccountReq;

@Controller
@RequestMapping("authmanage")
public class AuthManageController {
	@Autowired
	private IAccountService accountService;
	@Autowired
	private IAuthorityMenuClassService authorityMenuClassService;

	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "管理员");
		model.addObject("currenttitle", "个人账户权限管理");
		model.addObject("viewPath", "authmanage/list");
		return model;
	}

	@RequestMapping("listdo")
	public ModelAndView listdo(PagedAccountReq req) {
		PagedResponse<Account> resp = accountService.queryAccount(req);
		ModelAndView model = new ModelAndView("authmanage/listdo");
		model.addObject("listData", resp);
		return model;
	}

	@RequestMapping(value="authlist", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getAuthList(int userID) {
		List<MenuEntity> menuList = authorityMenuClassService
				.getAuthList(userID);

		if (menuList != null && menuList.size() > 0) {
			StringBuffer htmlStrBuffer = new StringBuffer();
			htmlStrBuffer.append("[");
			submenu(htmlStrBuffer,0,menuList);
			//createMenu(htmlStrBuffer, 0, menuList);
			htmlStrBuffer.append("]");
			String ab = htmlStrBuffer.toString();
			return ab;
		}

		return "";
	}

	private void submenu(StringBuffer htmlStrBuffer, int parentID,
			List<MenuEntity> menuList) {
	boolean hasdata=false;
		for (MenuEntity menuEntity : menuList) {

			if (menuEntity.getParid() == 0) {
				hasdata=true;
				htmlStrBuffer.append("{");
				htmlStrBuffer.append("\"text\":\"" + menuEntity.getMenuname() + "\"");
				htmlStrBuffer.append(",\"id\":\"" + menuEntity.getId()+"\"");
				htmlStrBuffer.append(",\"parentid\":\"" + menuEntity.getParid()+"\"");
				 if (menuEntity.getMenuid() != null&& menuEntity.getMenuid() >
				 0) {
				 htmlStrBuffer.append(",\"state\":{\"checked\": \"true\"}");
				 }
				htmlStrBuffer.append(",\"nodes\":[");
				submenulist(htmlStrBuffer, menuEntity.getId(), menuList);
				htmlStrBuffer.append("]},");
			}
		}
		if (hasdata) {
			htmlStrBuffer.deleteCharAt(htmlStrBuffer.length() - 1);
		}

	}

	private void submenulist(StringBuffer htmlStrBuffer, int parentID,
			List<MenuEntity> menuList) {
		boolean hasdata=false;
		for (MenuEntity menuEntity : menuList) {
			if (menuEntity.getParid() == parentID) {
				hasdata=true;
				htmlStrBuffer.append("{");
				htmlStrBuffer.append("\"text\":\"" + menuEntity.getMenuname() + "\"");
				htmlStrBuffer.append(",\"id\":\"" + menuEntity.getId()+"\"");
				htmlStrBuffer.append(",\"parentid\":\"" + menuEntity.getParid()+"\"");
				 if (menuEntity.getMenuid() != null&& menuEntity.getMenuid() >
				 0) {
				 htmlStrBuffer.append(",\"state\":{\"checked\": \"true\"}");
				 }
				htmlStrBuffer.append(",\"nodes\":[");
				buttonmenu(htmlStrBuffer, menuEntity.getId(), menuList);
				htmlStrBuffer.append("]},");
			}
		}
		if (hasdata) {
			htmlStrBuffer.deleteCharAt(htmlStrBuffer.length() - 1);
		}
	}

	private void buttonmenu(StringBuffer htmlStrBuffer, int parentID,
			List<MenuEntity> menuList) {
		boolean hasdata=false;
		for (MenuEntity menuEntity : menuList) {
			if (menuEntity.getParid() == parentID) {
				hasdata=true;
				htmlStrBuffer.append("{");
				htmlStrBuffer.append("\"text\":\"" + menuEntity.getMenuname() + "\"");
				htmlStrBuffer.append(",\"id\":\"" + menuEntity.getId()+"\"");
				htmlStrBuffer.append(",\"parentid\":\"" + menuEntity.getParid()+"\"");
				 if (menuEntity.getMenuid() != null&& menuEntity.getMenuid() >
				 0) {
				 htmlStrBuffer.append(",\"state\":{\"checked\": \"true\"}");
				 }
				htmlStrBuffer.append("},");
			}
		}
		if (hasdata) {
			htmlStrBuffer.deleteCharAt(htmlStrBuffer.length() - 1);
		}
	}

	private int createMenu(StringBuffer htmlStrBuffer, int parentID,
			List<MenuEntity> menuList) {
		int subMenuStatus = 0;
		int hasSub = 0;
		for (MenuEntity menuEntity : menuList) {
			if (menuEntity.getParid() == parentID) {
				hasSub = 1;
				if (subMenuStatus == 0 && parentID != 0) {
					subMenuStatus = 1;
					htmlStrBuffer.append(",nodes:[");
				}

				htmlStrBuffer.append("{");
				htmlStrBuffer.append("text:'" + menuEntity.getMenuname() + "'");
				htmlStrBuffer.append(",id:" + menuEntity.getId());
				htmlStrBuffer.append(",parentid:" + menuEntity.getParid());
				// if (menuEntity.getIsbutton()) {
				//
				// }
				// 当前用户有权限的菜单处于选中状态
				// if (menuEntity.getMenuid() != null&& menuEntity.getMenuid() >
				// 0) {
				// htmlStrBuffer.append(",state:{checked: true}");
				// }
				int hassub = createMenu(htmlStrBuffer, menuEntity.getId(),
						menuList);
				if (hassub > 0) {

				}

				if (subMenuStatus == 1 && parentID != 0) {
					subMenuStatus = 2;
					htmlStrBuffer.append("]},");
				}else{
					htmlStrBuffer.append("},");
				}
			}
		}
		if(hasSub>0){
			htmlStrBuffer.deleteCharAt(htmlStrBuffer.length() - 1);
		}
		return hasSub;
	}
}
