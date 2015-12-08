package com.edaisong.toolsadmin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.toolsadmin.common.MenuUtils;
import com.edaisong.toolsapi.service.inter.IMenuService;
import com.edaisong.toolsapi.service.inter.IRolePrivilegeService;
import com.edaisong.toolsapi.service.inter.IRoleService;
import com.edaisong.toolsapi.service.inter.IUserService;
import com.edaisong.toolsentity.domain.Menu;
import com.edaisong.toolsentity.domain.Role;
import com.edaisong.toolsentity.domain.User;
import com.edaisong.toolsentity.req.RolePrivilegeSaveReq;
import com.edaisong.toolscore.util.AjaxResult;
import com.edaisong.toolscore.util.JsonUtil;
@Controller
public class ManagerController {
	@Autowired
	private IMenuService menuService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IRolePrivilegeService rolePrivilegeService;

	@RequestMapping("/menu")
	public ModelAndView menu(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "管理中心");
		model.addObject("currenttitle", "菜单管理");
		model.addObject("viewPath", "manager/menu");
		model.addObject("footerJs", "manager/_menuJs");
		return model;
	}

	@RequestMapping("/menu/refresh")
	public ModelAndView menulist() {
		List<Menu> lstAllMenus = menuService.getAll();
		if (lstAllMenus == null) {
			lstAllMenus = new ArrayList<Menu>();
		}
		List<Menu> rootMenus = lstAllMenus.stream().filter(m -> m.getParentId() == 0)
				.sorted((m1, m2) -> (m1.getParentId() - m2.getParentId())).collect(Collectors.toList());

		ModelAndView model = new ModelAndView("manager/_menulist");
		model.addObject("dataOfmenulist", lstAllMenus);
		model.addObject("dataOfRootMenus", rootMenus);
		return model;
	}

	@RequestMapping(value = "/menu/save", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult saveMenu(Menu menu) {
		if (menu == null) {
			return AjaxResult.Error("menu is null");
		}
		if (menu.getId() == 0) {
			menu.setCreateBy("wangyuchuan");
			Integer id = menuService.create(menu);
			menu.setId(id);
		} else {
			System.out.println(JsonUtil.obj2string(menu));
			menuService.modify(menu);
		}
		return AjaxResult.Success(menu.getId());
	}

	@RequestMapping(value = "/menu/remove", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult removeMenu(int id) {
		if (id <= 0) {
			return AjaxResult.Error();
		}
		menuService.remove(id);
		return AjaxResult.Success();
	}

	@RequestMapping("/user")
	public ModelAndView user() {
		List<User> allUsers = userService.getAll();
		List<Role> allRoles = roleService.getAll();

		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "管理中心");
		model.addObject("currenttitle", "用户管理");
		model.addObject("viewPath", "manager/user");
		model.addObject("footerJs", "manager/_userJs");
		model.addObject("dataOfuserlist", allUsers);
		model.addObject("dataOfRoles", allRoles);
		return model;
	}

	@RequestMapping(value = "/user/save", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult saveUser(User user) {
		if (user == null) {
			return AjaxResult.Error("user is null");
		}
		if (user.getId() == 0) {
			user.setCreateBy("wangyuchuan");
			Integer id = userService.create(user);
			user.setId(id);
		} else {
			userService.modify(user);
		}
		return AjaxResult.Success(user.getId());
	}

	@RequestMapping(value="/user/setrole",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult userSetRole(Integer userId,String roleIds){
		userService.setRoles(userId, roleIds);
		return AjaxResult.Success();
	}
	
	@RequestMapping("/user/refresh")
	public ModelAndView userRefresh() {
		List<User> allUsers = userService.getAll();
		if (allUsers == null) {
			allUsers = new ArrayList<User>();
		}
		ModelAndView model = new ModelAndView("manager/_userlist");
		model.addObject("dataOfuserlist", allUsers);
		return model;
	}

	@RequestMapping(value = "/user/remove", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult userRemove(Integer userId) {
		if (userId <= 0) {
			return AjaxResult.Error();
		}
		userService.remove(userId);
		return AjaxResult.Success();
	}

	@RequestMapping(value = "/user/disable", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult userDisable(Integer userId, boolean isDisable) {
		if (userId <= 0) {
			return AjaxResult.Error();
		}
		userService.disable(userId, isDisable);
		return AjaxResult.Success();
	}

	@RequestMapping("/role")
	public ModelAndView role() {
		List<Role> allRoles = roleService.getAll();
		Role current = null;
		if (allRoles != null && allRoles.size() > 0) {
			current = allRoles.get(0);
		}
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "管理中心");
		model.addObject("currenttitle", "角色管理");
		model.addObject("viewPath", "manager/role");
		model.addObject("footerJs", "manager/_roleJs");

		model.addObject("dataOfRoles", allRoles);
		model.addObject("dataOfCurrentRole", current);
		return model;
	}

	@RequestMapping(value = "/role/save", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult roleSave(Role role) {
		if (role == null) {
			return AjaxResult.Error("the parameter of role is null");
		}
		if (role.getId() == 0) {
			role.setCreateBy("wangyuchuan");
			Integer id = roleService.create(role);
			role.setId(id);
		} else {
			roleService.modify(role);
		}
		return AjaxResult.Success(role.getId());
	}

	@RequestMapping(value = "/role/remove", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult roleRemove(Integer roleId) {
		if (roleId <= 0) {
			return AjaxResult.Error();
		}
		roleService.remove(roleId);
		return AjaxResult.Success();
	}

	@RequestMapping("/role/refresh")
	public ModelAndView roleRefresh(int roleId) {
		List<Role> allRoles = roleService.getAll();
		if (allRoles == null) {
			allRoles = new ArrayList<Role>();
		}
		Role currentRole = allRoles.stream().filter(r -> r.getId() == roleId).findFirst().get();

		ModelAndView model = new ModelAndView("/manager/_rolelist");

		model.addObject("dataOfRoles", allRoles);
		model.addObject("dataOfCurrentRole", currentRole);

		return model;
	}

	@RequestMapping(value = "/role/getprivilege", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult getRolePrivilege(Integer roleId) {
		List<Integer> privileges = rolePrivilegeService.getRolePrivileges(roleId);
		List<Integer> childMenuIds = menuService.getAll().stream().filter(m -> m.getParentId() > 0).map(Menu::getId)
				.collect(Collectors.toList());
		List<Integer> childPrivileges = privileges.stream().filter(p -> childMenuIds.contains(p))
				.collect(Collectors.toList());
		return AjaxResult.Success(childPrivileges);
	}


	@RequestMapping(value = "/role/saveprivilege", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult setRolePrivilege(RolePrivilegeSaveReq saveParams) {
		rolePrivilegeService.saveRolePrivilege(saveParams);

		return AjaxResult.Success();
	}
}
