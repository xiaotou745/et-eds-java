package com.edaisong.toolsadmin.controller;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.toolsadmin.common.UserContext;
import com.edaisong.toolsapi.redis.RedisService;
import com.edaisong.toolsapi.service.inter.IAppDbConfigService;
import com.edaisong.toolsapi.service.inter.IAuthorityMenuClassService;
import com.edaisong.toolscore.enums.ServerType;
import com.edaisong.toolscore.util.JsonUtil;
import com.edaisong.toolscore.util.StringUtils;
import com.edaisong.toolsentity.Account;
import com.edaisong.toolsentity.AppDbConfig;
import com.edaisong.toolsentity.AuthorityMenuClass;
import com.edaisong.toolsentity.common.PagedRequestBase;
import com.edaisong.toolsentity.common.PagedResponse;
import com.edaisong.toolsentity.common.ResponseBase;
import com.edaisong.toolsentity.domain.ConnectionInfo;
import com.edaisong.toolsentity.req.PagedAccountReq;
import com.edaisong.toolsentity.req.PagedAppDbConfigReq;
/**
 * 控制其他app
 * @author hailongzhao
 * @date 20151118
 */
@Controller
@RequestMapping("admintools")
public class AdminToolsController {
	@Autowired
	private RedisService redisService;
	@Autowired
	private IAuthorityMenuClassService authorityMenuClassService;
	@Autowired
	private IAppDbConfigService appDbConfigService;
	/**
	 * 根据key模糊查询或精确查询
	 * @param key
	 * @author hailongzhao
	 * @date 20151118
	 * @return
	 */
	@RequestMapping("redismanage")
	public ModelAndView redisManage() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "APP控制");
		view.addObject("currenttitle", "redis查询工具"); 
		view.addObject("viewPath", "admintools/redismanage");
		return view;
	}
	/**
	 * 根据key模糊查询或精确查询
	 * @param key
	 * @author hailongzhao
	 * @date 20151118
	 * @return
	 */
	@RequestMapping("redisdo")
	@ResponseBody
	public String redisdo(String key,String sType) {
		if(sType.equals("1")){
			Set<String> mSet=redisService.keys(key);
			return String.join(",", mSet);
		}else {
			return redisService.get(key, String.class, false);
		}
	}
	/**
	 * 菜单管理
	 * @author hailongzhao
	 * @date 20151118
	 * @return
	 */
	@RequestMapping("menulist")
	public ModelAndView menuList(Integer parId) {
		parId = (parId == null ? 0 : parId);
		List<AuthorityMenuClass> resp = authorityMenuClassService.getListMenuByParId(parId);
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "APP控制");
		view.addObject("currenttitle", "菜单管理");
		view.addObject("viewPath", "admintools/menulist");
		view.addObject("listData", resp);
		view.addObject("currentMenu", authorityMenuClassService.getMenuById(parId));
		view.addObject("appNameList", getappNameList(ServerType.SqlServer,null));
		return view;
	}
	/**
	 * 添加菜单
	 * @author hailongzhao
	 * @date 20151118
	 * @return
	 */
	@RequestMapping("addnewmenu")
	@ResponseBody
	public ResponseBase addNewMenu(AuthorityMenuClass req){
		ResponseBase resp = new ResponseBase();
		if(StringUtils.isEmpty(req.getMenuname())){
			resp.setMessage("请填写菜单名称");
		}else{
			int curId = req.getParid() == null ? 0 : req.getParid();
			req.setParid(curId);
			req.setBelock(false);
			authorityMenuClassService.addMenu(req);
			
			resp.setMessage("添加菜单成功");
			resp.setResponseCode(1);
		}
		
		
		return resp;
	}
	/**
	 * app版本控制
	 * @author hailongzhao
	 * @date 20151118
	 * @return
	 */
	@RequestMapping("appversion")
	public ModelAndView appversion() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "APP控制");
		view.addObject("currenttitle", "版本控制");
		view.addObject("viewPath", "admintools/appversion");
		return view;
	}
	/**
	 * app数据库配置
	 * @author hailongzhao
	 * @date 20151118
	 * @return
	 */
	@RequestMapping("serverconfig")
	public ModelAndView serverconfig() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "APP控制");
		view.addObject("currenttitle", "App服务器配置");
		view.addObject("viewPath", "admintools/serverconfig");
		view.addObject("appNameList", getappNameList(null,null));
		return view;
	}
	/**
	 * 查询配置了给定类型的服务器的appName
	 * @param serverType
	 * @return
	 */
	private List<String> getappNameList(ServerType serverType,String appName){
		PagedAppDbConfigReq req=new PagedAppDbConfigReq();
		if (serverType!=null) {
			req.setConfigtype(serverType.value());
		}
		if (appName!=null&&!appName.isEmpty()) {
			req.setAppname(appName);
		}
		List<AppDbConfig> resp = appDbConfigService.queryList(req);
		List<String> appNameList =resp.stream().map(t->t.getAppname()).distinct().collect(Collectors.toList());
		return appNameList;
	}
	/**
	 * app数据库配置分页
	 * @author hailongzhao
	 * @date 20151118
	 * @return
	 */
	@RequestMapping("serverconfigdo")
	public ModelAndView serverconfigdo(PagedAppDbConfigReq req) {
		PagedResponse<AppDbConfig> resp = appDbConfigService.query(req);
		ModelAndView view = new ModelAndView();
		view.addObject("viewPath", "admintools/serverconfigdo");
		view.addObject("listData", resp);
		return view;
	}
	/**
	 * 删除app数据库配置
	 * @author hailongzhao
	 * @date 20151118
	 * @return
	 */
	@RequestMapping("deleteApp")
	@ResponseBody
	public int deleteApp(Long id) {
		return appDbConfigService.deleteById(id);
	}
	/**
	 * 修改或新增app数据库配置
	 * @author hailongzhao
	 * @date 20151118
	 * @return
	 */
	@RequestMapping("saveapp")
	@ResponseBody
	public int saveapp(ConnectionInfo conInfo,AppDbConfig req,Integer optype,HttpServletRequest request) {
		UserContext context = UserContext.getCurrentContext(request);
		req.setConfigvalue(JsonUtil.obj2string(conInfo));
		req.setCreatename(context.getUserName());
		req.setUpdatename(context.getUserName());
		if (optype.equals(0)) {//0表示修改，1表示新增
			return appDbConfigService.updateById(req);
		}else {
			return appDbConfigService.insert(req);
		}
	}
	/**
	 * 导出数据管理
	 * @author hailongzhao
	 * @date 20151118
	 * @return
	 */
	@RequestMapping("export")
	public ModelAndView export() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "APP控制");
		view.addObject("currenttitle", "导出数据管理");
		view.addObject("viewPath", "admintools/export");
		return view;
	}
	/**
	 * 日志分析
	 * @author hailongzhao
	 * @date 20151118
	 * @return
	 */
	@RequestMapping("log")
	public ModelAndView log() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "APP控制");
		view.addObject("currenttitle", "日志分析");
		view.addObject("viewPath", "admintools/log");
		return view;
	}
	/**
	 * sql查询工具
	 * @author hailongzhao
	 * @date 20151118
	 * @return
	 */
	@RequestMapping("sql")
	public ModelAndView sql() {
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "APP控制");
		view.addObject("currenttitle", "sql工具");
		view.addObject("viewPath", "admintools/sql");
		return view;
	}
}