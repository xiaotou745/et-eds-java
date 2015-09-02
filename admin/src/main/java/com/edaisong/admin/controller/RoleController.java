package com.edaisong.admin.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.admin.common.MenuHelper;
import com.edaisong.api.service.inter.IAuthorityMenuClassService;
import com.edaisong.api.service.inter.IAuthorityRoleMentMenuSetService;
import com.edaisong.api.service.inter.IAuthorityRoleService;
import com.edaisong.entity.AuthorityAccountMenuSet;
import com.edaisong.entity.AuthorityRole;
import com.edaisong.entity.AuthorityRoleMentMenuSet;
import com.edaisong.entity.MenuEntity;
import com.edaisong.entity.req.PagedAccountReq;

@Controller
@RequestMapping("role")
public class RoleController {
	@Autowired
	private IAuthorityMenuClassService authorityMenuClassService;
	@Autowired
	private IAuthorityRoleService authorityRoleService;
	@Autowired
	private IAuthorityRoleMentMenuSetService authorityRoleMentMenuSetService;
	
	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "管理员");
		model.addObject("currenttitle", "角色管理");
		model.addObject("viewPath", "role/list");
		return model;
	}

	@RequestMapping("listdo")
	public ModelAndView listdo(PagedAccountReq req) {
		ModelAndView model = new ModelAndView("role/listdo");
		List<AuthorityRole> datalist=authorityRoleService.selectList();
		model.addObject("listData", datalist);
		return model;
	}
	@RequestMapping("add")
	@ResponseBody
	public int add(String roleName) {
		AuthorityRole record=new AuthorityRole();
		record.setBelock(false);
		record.setRolename(roleName);
		return authorityRoleService.insert(record);
	}
	@RequestMapping(value = "authlist", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getAuthList(int roleID) {
		List<MenuEntity> menuList = authorityMenuClassService.getRoleAuthSettingList(roleID);
		return MenuHelper.getAuthJson(menuList);
	}

	@RequestMapping("saveauth")
	@ResponseBody
	public String saveauth(int roleID, String newAuth, String oldAuth) {
		List<String> newList = new ArrayList<>();
		List<String> oldList = new ArrayList<>();
		List<String> diffList = new ArrayList<>();
		if (newAuth != null && !newAuth.isEmpty()) {
			Collections.addAll(newList, newAuth.split(","));
			Collections.addAll(diffList, newAuth.split(","));
		}
		if (oldAuth != null && !oldAuth.isEmpty()) {
			Collections.addAll(oldList, oldAuth.split(","));
		}

		diffList.removeAll(oldList);// diffList中剩余的是新增的权限id
		oldList.removeAll(newList);// oldList中剩余的是被删掉的权限id
		diffList.addAll(oldList);// diffList中剩余的是发生了变更的权限id
		if (diffList.size() == 0) {
			return "没有任何修改，不需要保存";
		}
		List<AuthorityRoleMentMenuSet> authList = new ArrayList<>();
		for (String authid : diffList) {
			AuthorityRoleMentMenuSet authset = new AuthorityRoleMentMenuSet();
			authset.setRoleid(roleID);
			authset.setMenuid(Integer.parseInt(authid));
			authList.add(authset);
		}
		authorityRoleMentMenuSetService.modifyAuthList(authList);

		return "";
	}
}
