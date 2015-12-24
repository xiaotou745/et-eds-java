package com.edaisong.toolsadmin.controller;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.toolsadmin.common.MenuHelper;
import com.edaisong.toolsadmin.common.UserContext;
import com.edaisong.toolsapi.common.SQLServerUtil;
import com.edaisong.toolsapi.common.MybatisUtil;
import com.edaisong.toolsapi.common.RedisUtil;
import com.edaisong.toolsapi.mongo.MongoService;
import com.edaisong.toolsapi.service.inter.IAppDbConfigService;
import com.edaisong.toolsapi.service.inter.IAuthorityMenuClassService;
import com.edaisong.toolscore.enums.ServerType;
import com.edaisong.toolscore.security.AES;
import com.edaisong.toolscore.util.JsonUtil;
import com.edaisong.toolscore.util.ParseHelper;
import com.edaisong.toolsentity.AppDbConfig;
import com.edaisong.toolsentity.AuthorityMenuClass;
import com.edaisong.toolsentity.common.PagedResponse;
import com.edaisong.toolsentity.domain.ActionLog;
import com.edaisong.toolsentity.domain.ConnectionInfo;
import com.edaisong.toolsentity.domain.MenuDetail;
import com.edaisong.toolsentity.domain.MenuEntity;
import com.edaisong.toolsentity.domain.SelectAppModel;
import com.edaisong.toolsentity.req.MongoLogReq;
import com.edaisong.toolsentity.req.PagedAppDbConfigReq;
import com.edaisong.toolsentity.req.PagedMongoLogReq;
import com.mongodb.BasicDBObject;

/**
 * 控制其他app
 * @author hailongzhao
 * @date 20151118
 */
@Controller
@RequestMapping("admintools")
public class AdminToolsController {
	@Autowired
	private IAuthorityMenuClassService authorityMenuClassService;
	@Autowired
	private IAppDbConfigService appDbConfigService;
	@Autowired
	private MongoService mongoService;
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
		view.addObject("appNameList", getappNameList(ServerType.Redis,null));
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
	public String redisdo(String appName,String key,String sType) {
		List<AppDbConfig> appConfig=getAppConfigList(ServerType.Redis,appName);
		if (appConfig.size()>0) {
			ConnectionInfo conInfo=JsonUtil.str2obj(appConfig.get(0).getConfigvalue(), ConnectionInfo.class) ;
			RedisUtil redisUtil=new RedisUtil(conInfo);
			if(sType.equals("1")){
				Set<String> mSet=redisUtil.keys(key);
				return String.join(",", mSet);
			}else {
				Object m= redisUtil.get(key, Object.class, false);
				if (m==null) {
					return "";
				}
				if (m instanceof List) {
					List<Object> mt=(ArrayList<Object>)m;
					List<String> jsonList=mt.stream().map(t->JsonUtil.obj2string(t)).collect(Collectors.toList());
					return String.join(",", jsonList);
				}else {
					return JsonUtil.obj2string(m);
				}
			}
		}
		return "";
	}
	/**
	 * 菜单管理
	 * @author hailongzhao
	 * @date 20151118
	 * @return
	 */
	@RequestMapping("menulist")
	public ModelAndView menuList(Integer parId) {
		
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "APP控制");
		view.addObject("currenttitle", "菜单管理");
		view.addObject("viewPath", "admintools/menulist");
		view.addObject("appNameList", getConnList());
		return view;
	}
	/**
	 * 菜单列表
	 * @author 茹化肖
	 * @date 20151118
	 * @return
	 */
	@RequestMapping("menulistdo")
	@ResponseBody
	public String menuListdo(String appName) {
		List<AppDbConfig> appConfig=getAppConfigList(ServerType.SqlServer,appName);//构造数据库连接
		if (appConfig.size()>0) {
			ConnectionInfo conInfo=JsonUtil.str2obj(appConfig.get(0).getConfigvalue(), ConnectionInfo.class) ;
			List<MenuEntity> menuList = MybatisUtil.getSqlSessionUtil(conInfo).selectList("IAuthorityMenuClassDao.getListMenuAll");
			return MenuHelper.getAuthJson(menuList);
		}
		return "";
		
		
	}
	/**
	 * 将配置文件转conn集合
	 * 茹化肖
	 * @param list
	 * @return
	 */
	private List<SelectAppModel> getConnList(){
		List<SelectAppModel> lista=new ArrayList<SelectAppModel>();
		List<AppDbConfig> list=getAppConfigList(ServerType.SqlServer,null);
		for (int i = 0; i < list.size(); i++) {
			SelectAppModel selectAppModel=new SelectAppModel();
			selectAppModel.setAppName(list.get(i).getAppname());
			selectAppModel.setDbName(JsonUtil.str2obj(list.get(i).getConfigvalue(), ConnectionInfo.class).getDataBase());
			lista.add(selectAppModel);
		}
		return lista;
	}
	/**
	 * 菜单详情
	 * @author 茹化肖
	 * @date 20151118
	 * @return
	 */
	@RequestMapping("menudetail")
	@ResponseBody
	public String menudetail(String appName,int parId) {
		List<AppDbConfig> appConfig=getAppConfigList(ServerType.SqlServer,appName);//构造数据库连接
		if (appConfig.size()>0) {
			ConnectionInfo conInfo=JsonUtil.str2obj(appConfig.get(0).getConfigvalue(), ConnectionInfo.class) ;
			MenuDetail detail = new MenuDetail();
			if(conInfo.getDataBase().toLowerCase().equals("superman"))
			{
				detail=MybatisUtil.getSqlSessionUtil(conInfo).selectOne("IAuthorityMenuClassDao.menudetailjava",parId);
			}
			else
			{
				detail=MybatisUtil.getSqlSessionUtil(conInfo).selectOne("IAuthorityMenuClassDao.menudetail",parId);
			}
			return JsonUtil.obj2string(detail);
		}
		return "";
	}
	/**
	 * 添加菜单
	 * @author hailongzhao
	 * @date 20151118
	 * @return
	 */
	@RequestMapping("addnewmenu")
	@ResponseBody
	public int addNewMenu(AuthorityMenuClass req,String appName ){
		List<AppDbConfig> appConfig=getAppConfigList(ServerType.SqlServer,appName);//构造数据库连接
		if (appConfig.size()>0) {
			ConnectionInfo conInfo=JsonUtil.str2obj(appConfig.get(0).getConfigvalue(), ConnectionInfo.class) ;
			return MybatisUtil.getSqlSessionUtil(conInfo).insert("IAuthorityMenuClassDao.addMenu", req);
		}
		return 0;
	}
	/**
	 * 编辑菜单
	 * @author 茹化肖
	 * @date 20151118
	 * @return
	 */
	@RequestMapping("editmenu")
	@ResponseBody
	public int editMenu(AuthorityMenuClass req,String appName ){
		List<AppDbConfig> appConfig=getAppConfigList(ServerType.SqlServer,appName);//构造数据库连接
		if (appConfig.size()>0) {
			ConnectionInfo conInfo=JsonUtil.str2obj(appConfig.get(0).getConfigvalue(), ConnectionInfo.class) ;
			return MybatisUtil.getSqlSessionUtil(conInfo).update("IAuthorityMenuClassDao.updateMenu", req);
		}
		return 0;
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
		List<AppDbConfig> resp = getAppConfigList(serverType,appName);
		List<String> appNameList =resp.stream().map(t->t.getAppname()).distinct().collect(Collectors.toList());
		return appNameList;
	}
	/**
	 * 查询配置了给定类型的服务器的设置list
	 * @param serverType
	 * @param appName
	 * @return
	 */
	private List<AppDbConfig> getAppConfigList(ServerType serverType,String appName){
		PagedAppDbConfigReq req=new PagedAppDbConfigReq();
		if (serverType!=null) {
			req.setConfigtype(serverType.value());
		}
		if (appName!=null&&!appName.isEmpty()) {
			req.setAppname(appName);
		}
		return appDbConfigService.queryList(req);	
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
		boolean isPass=checkConnection(conInfo,ServerType.getEnum(req.getConfigtype()));
		if (!isPass) {
			return -1;
		}
		if (optype.equals(0)) {//0表示修改，1表示新增
			return appDbConfigService.updateById(req);
		}else {
			return appDbConfigService.insert(req);
		}
	}
	/**
	 * 检查服务器是否可以连接通
	 * @param conInfo
	 * @param serverType
	 * @author hailongzhao
	 * @date 20151201
	 * @return
	 */
	private boolean checkConnection(ConnectionInfo conInfo,ServerType serverType){
		try {
			switch (serverType) {
			case SqlServer:
				List<Map<String, String>> tList=MybatisUtil.dynamicSelectList(conInfo, "SELECT 1");
				break;
			case Redis:
				RedisUtil redisUtil=new RedisUtil(conInfo);
				Object m= redisUtil.get("abc", Object.class, false);
				break;
			case MongoDb:
				break;
			default:
				break;
			}	
			return true;
		} catch (Exception e) {
			return false;
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
	public ModelAndView log(){
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "APP控制");
		view.addObject("currenttitle", "日志分析");
		view.addObject("viewPath", "admintools/log");
		return view;
	}
	/**
	 * 日志分析
	 * @author hailongzhao
	 * @date 20151118
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("logdo")
	public ModelAndView logdo(PagedMongoLogReq req) throws Exception {
		ModelAndView view = new ModelAndView("admintools/logdo");
		fillQuery(req);
		PagedResponse<ActionLog> resp=mongoService.selectPart(getMongoTableName(req.getMonthInfo()), req);
		view.addObject("listData", resp);
		return view;
	}
	/**
	 * 根据月份生产mongo中的表名称
	 * @param monthInfo
	 * @author hailongzhao
	 * @date 20151208
	 * @return
	 */
    private String getMongoTableName(String monthInfo){
    	Calendar a=Calendar.getInstance();
    	return "logtb_"+a.get(Calendar.YEAR)+"_"+monthInfo;
    }
    /**
     * 根据页面上的查询条件组织对monmgo的查询request
     * @author hailongzhao
     * @date 20151208
     * @param req
     */
    private void fillQuery(PagedMongoLogReq req){
		BasicDBObject query=new BasicDBObject();
        query.put("sourceSys", req.getSourceSys());
		if (req.getRequestType()>-1) {
	        query.put("requestType", req.getRequestType());
		}
		if (req.getExceptionShowType()==1) {//只看正常
			query.put("stackTrace", "");
		}
		else if (req.getExceptionShowType()==2) {//只看异常
			query.put("stackTrace", new BasicDBObject("$ne", ""));//stackTrace!=""
		}
		if (req.getRequestUrl()!=null&&!req.getRequestUrl().isEmpty()) {
			//类似于sql中的like
			Pattern pattern = Pattern.compile("^.*" + req.getRequestUrl()+ ".*$", Pattern.CASE_INSENSITIVE); 
			query.put("requestUrl", pattern);
		}
		req.setQueryObject(query);
		BasicDBObject sort=new BasicDBObject();
		sort.put(req.getOrderBy(), req.getOrderType());//按照执行时间倒序（1是升序，-1是降序）
		req.setSortObject(sort);
    }
    /**
     * 根据页面上的查询条件组织对monmgo的查询request
     * @author hailongzhao
     * @date 20151208
     * @param req
     */
    private BasicDBObject getQueryObj(MongoLogReq req){
		BasicDBObject query=new BasicDBObject();
		if (req.getSourceSys()!=null&&!req.getSourceSys().isEmpty()) {
	        query.put("sourceSys", req.getSourceSys());
		}
		if (req.getRequestType()>-1) {
	        query.put("requestType", req.getRequestType());
		}
		if (req.getExceptionShowType()==1) {//只看正常
			query.put("stackTrace", "");
		}
		else if (req.getExceptionShowType()==2) {//只看异常
			query.put("stackTrace", new BasicDBObject("$ne", ""));//stackTrace!=""
		}
		if (req.getRequestUrl()!=null&&!req.getRequestUrl().isEmpty()) {
			//类似于sql中的like
			Pattern pattern = Pattern.compile("^.*" + req.getRequestUrl()+ ".*$", Pattern.CASE_INSENSITIVE); 
			query.put("requestUrl", pattern);
		}
		return query;
    }
    private String getTableName(String beginDate){
    	return "logtb_"+ParseHelper.ToDateString(ParseHelper.ToDate(beginDate), "yyyy_MM");
    }
    /**
     * 并行查询多个mongodb中的表
     * @param req
     * @return
     * @date 20151221
     * @author hailongzhao
     * @throws Exception
     */
    private List<ActionLog> queryAll(MongoLogReq req) throws Exception{
		List<ActionLog> result=Collections.synchronizedList(new ArrayList<>());
		LinkedHashMap<String, String> timeHashMap= splitTimeRange(req.getBegin(),req.getEnd());
		Calendar a=Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int currentDay=a.get(Calendar.YEAR)+a.get(Calendar.MONTH)+1;
		int lastDay=2015+9;
		timeHashMap.entrySet().parallelStream().forEach(t->{
			try {
				a.setTime(sdf.parse(t.getValue()));
				int tempDay=a.get(Calendar.YEAR)+a.get(Calendar.MONTH)+1;
				if(lastDay<=tempDay&&tempDay<=currentDay){
					BasicDBObject tempReq=getQueryObj(req);
					if (!t.getKey().endsWith("00:00:00")) {
						tempReq.put("requestTime", new BasicDBObject("$gte", t.getKey()));//>=
					}
					if (!t.getValue().endsWith("00:00:00")) {
						tempReq.put("requestTime", new BasicDBObject("$lte", t.getValue()));//<=
					}
					String mongoName=getTableName(t.getKey());
					System.out.println("表名称:"+mongoName);
					List<ActionLog> partialResult= mongoService.selectResult(mongoName,tempReq);
					result.addAll(partialResult);
				}
			} catch (Exception e) {
				System.out.println("并行查询mongo时出错:"+e.getMessage());
				e.printStackTrace();
			}
		});
		return result;
	}
    /**
     * 将日期段按照指定的步长拆段
     * @param begin
     * @param end
     * @param minStep
     * @date 20151222
     * @author hailongzhao
     * @return
     */
    private LinkedHashMap<Date, Date> splitStep(Date begin,Date end,int minStep){
    	LinkedHashMap<Date, Date> result = new LinkedHashMap<Date, Date>();
		if (begin.before(end)) {
			Date tempBegin = begin;
			Calendar cl = Calendar.getInstance();
			while (tempBegin.before(end)) {
				cl.setTime(tempBegin);
				cl.add(Calendar.MINUTE, minStep);// 日期加n分钟
				if (cl.getTime().compareTo(end) > 0) {
					result.put(tempBegin, end);
					break;
				}else {
					result.put(tempBegin, cl.getTime());
				} 
				tempBegin = cl.getTime();
			}
		}
		return result;
    }
    /**
     * 将指定日期范围内的数据按照指定的步长分组
     * @param begin
     * @param end
     * @param data
     * @author hailongzhao
     * @date 20151222
     * @param minStep
     */
    private Map<Date, List<Entry<Date, ActionLog>>> groupByStep(Date begin,Date end,List<ActionLog> data,int minStep){
    	 Map<Date,ActionLog> resultMap = Collections.synchronizedMap(new HashMap<Date,ActionLog>()); 
 		LinkedHashMap<Date, Date> timeHashMap= splitStep(begin,end,minStep);
 		
 		//并行将在日期段内的ActionLog放入resultMap
 		data.parallelStream().forEach(t->{
 			timeHashMap.keySet().parallelStream().forEach(m->{
 				Date mb=ParseHelper.ToDate(t.getRequestTime());
 				if (mb.compareTo(m)>=0&&mb.compareTo(timeHashMap.get(m))<=0) {
 					resultMap.put(timeHashMap.get(m), t);
				}
 			});
    	});
 		//按照日期段的结束日期分组
		 return resultMap
				.entrySet().parallelStream()
				.collect(Collectors.groupingBy(Entry<Date, ActionLog>::getKey));
    }
	/**
	 * 日志分析
	 * @author hailongzhao
	 * @date 20151118
	 * @return
	 */
	@RequestMapping("logchart")
	public ModelAndView logChart(){
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "APP控制");
		view.addObject("currenttitle", "日志图表分析");
		view.addObject("viewPath", "admintools/logchart");
		return view;
	}
    /**
     * 请求时间和执行时间
     * @return
     * @throws Exception 
     */
	@RequestMapping("requestexetime")
	@ResponseBody
    public Map<String, Long> queryRequestExecuteTime(MongoLogReq req) throws Exception{
		List<ActionLog> data = queryAll(req);
		return data.parallelStream().collect(Collectors.toMap(ActionLog::getRequestTime, t->t.getExecuteTime()));
    }
    /**
     * 请求次数
     * @return
     * @throws Exception 
     */
	@RequestMapping("requestnum")
	@ResponseBody
    public Map<Date, Integer> queryRequestNum(MongoLogReq req) throws Exception{
		List<ActionLog> data = queryAll(req);
		Map<Date, List<Entry<Date, ActionLog>>> groupData = groupByStep(req.getBegin(), req.getEnd(), data, req.getMinStep());
		return groupData
				.entrySet()
				.parallelStream()
				.collect(
						Collectors
								.toMap(Entry<Date, List<Entry<Date, ActionLog>>>::getKey,
										m -> m.getValue().size()));
    }
    /**
     * 平均执行时间
     * @param begin
     * @param end
     * @param data
     * @param minStep
     * @return
     */
	@RequestMapping("averagetime")
	@ResponseBody
    public Map<Date, Double> queryAverageTime(MongoLogReq req) throws Exception{
    	List<ActionLog> data=queryAll(req);
    	Map<Date, List<Entry<Date, ActionLog>>> groupData=groupByStep(req.getBegin(), req.getEnd(),data, req.getMinStep());
		return groupData
				.entrySet()
				.parallelStream()
				.collect(
						Collectors
								.toMap(Entry<Date, List<Entry<Date, ActionLog>>>::getKey,
										m -> m.getValue()
												.stream()
												.mapToLong(x -> x.getValue().getExecuteTime())
												.summaryStatistics()
												.getAverage()));
    }
    /**
     * 查询异常率
     * @param begin
     * @param end
     * @param data
     * @param minStep
     * @return
     */
	@RequestMapping("errorrate")
	@ResponseBody
    public Map<Date, Double> queryErrorRate(MongoLogReq req) throws Exception{
    	List<ActionLog> data=queryAll(req);
    	Map<Date, List<Entry<Date, ActionLog>>> groupData=groupByStep(req.getBegin(), req.getEnd(),data, req.getMinStep());    		
		return groupData
				.entrySet()
				.parallelStream()
				.collect(
						Collectors
								.toMap(Entry<Date, List<Entry<Date, ActionLog>>>::getKey,
										m -> {
											long errNum = m
													.getValue()
													.stream()
													.filter(k -> k.getValue().getStackTrace() != null
															&& !k.getValue().getStackTrace().isEmpty())
													.count();
											return errNum * 100.00d / m.getValue().size();
										}));
    }
    /**
	 * 把日期范围按月拆成日期段
	 * @param begin
	 * @param end
	 * @date 20151221
	 * @author hailongzhao
	 * @return
	 * @throws Exception 
	 */
    private LinkedHashMap<String, String> splitTimeRange(Date begin,Date end) throws Exception{
    	LinkedHashMap<String, String> result = new LinkedHashMap<String, String>();
		if (begin.before(end)) {
			String monthBegin = "";
			String monthEnd = "";
			Date tempBegin = begin;
			Calendar cl = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			while (tempBegin.before(end)) {
				if (tempBegin.compareTo(begin) > 0) {
					cl.setTime(sdf1.parse(sdf1.format(tempBegin)));
					cl.set(Calendar.DAY_OF_MONTH,cl.getActualMinimum(Calendar.DAY_OF_MONTH));
				}else {
					cl.setTime(tempBegin);
				}

				if (cl.getTime().compareTo(end) > 0) {
					break;
				}
				monthBegin = sdf.format(cl.getTime());
				cl.setTime(sdf1.parse(sdf1.format(tempBegin)));
				cl.set(Calendar.DAY_OF_MONTH,cl.getActualMaximum(Calendar.DAY_OF_MONTH));
				if (cl.getTime().compareTo(end) > 0) {
					monthEnd = sdf.format(end);
				} else {
					monthEnd = sdf.format(cl.getTime());
				}
				result.put(monthBegin, monthEnd);
				cl.add(Calendar.DAY_OF_YEAR, 1);
				tempBegin = cl.getTime();
			}
		}
		return result;
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
		view.addObject("appNameList", getappNameList(ServerType.SqlServer,null));
		view.addObject("subtitle", "APP控制");
		view.addObject("currenttitle", "sql工具");
		view.addObject("viewPath", "admintools/sql");
		return view;
	}
	/**
	 * 执行sql语句 
	 * 茹化肖
	 * 2015年12月2日10:14:18
	 * @return
	 */
	@RequestMapping("execsql")
	@ResponseBody
	public String execSQl(String sql,String type, String appName)
	{
		try {
			List<AppDbConfig> appConfig=getAppConfigList(ServerType.SqlServer,appName);//获取数据库配置
			if (appConfig.size()>0) 
			{
				ConnectionInfo conInfo=JsonUtil.str2obj(appConfig.get(0).getConfigvalue(), ConnectionInfo.class) ;
				if(type.equals("1"))
				{
					//查询SQL
					if(sql.toUpperCase().indexOf("UPDATE")>=0)
					{
						return "查询时SQL不可以带UPDATE关键字";
					}
					if(sql.toUpperCase().indexOf("INSERT ")>=0)
					{
						return "查询时SQL不可以带INSERT关键字";
					}
					if(sql.toUpperCase().indexOf("DELETE")>=0)
					{
						return "查询时SQL不可以带DELETE关键字";
					}
					if(sql.toUpperCase().indexOf("NOLOCK")<=0)
					{
						return "查询时SQL必须加NOLOCK关键字";
					}
					if(sql.toUpperCase().indexOf("SELECT TOP")<0)
					{
						return "查询时SQL必须加TOP关键字";
					}
					return List2Table(SQLServerUtil.executeResultSet(conInfo, sql))	;
					//return List2Table(MybatisUtil.dynamicSelectList(conInfo, sql))	;
				}
				if(type.equals("2"))
				{
					//非查询SQL
					int res=SQLServerUtil.executeUpdate(conInfo, sql);
					//int res=MybatisUtil.dynamicUpdateDb(conInfo, sql);
					return "执行成功,影响行数:"+res;
				}
			}
			return"";
		} catch (Exception e) {
			return "出错了!!!错误信息:"+e.getMessage();
		}
	}
	
	private String List2Table(List<Map<String, String>> listMap)
	{
		StringBuilder sbBuilder=new StringBuilder();
		sbBuilder.append("<table class=\"table table-striped table-bordered table-hover dataTables-example\"><thead>");
		if (listMap.size()==0) {
			return "没有查到数据";
		}
		Map<String, String> map=listMap.get(0);
		Set<String> sets=map.keySet();//获取所有的key
		sbBuilder.append("<tr>");
		for (String str : sets) {//生成表头
			sbBuilder.append("<th>"+str+"</th>");	
		}
		sbBuilder.append("</tr>");
		sbBuilder.append("</thead><tbody>");
		for (Map<String, String> maps : listMap) {//生成行数据
			sbBuilder.append("<tr>");
			for (String key : sets) {
				sbBuilder.append("<td>"+String.valueOf(maps.get(key))+"</td>");
			}
			sbBuilder.append("</tr>");
		}
		sbBuilder.append("</tbody></table>");
		return sbBuilder.toString();
	}
	/**
	 * 
	 * AES加密解密
	 * 茹化肖
	 * 2015年12月7日16:40:30
	 * @return
	 */
	@RequestMapping("aes")
	public ModelAndView aes()
	{
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "APP控制");
		view.addObject("currenttitle", "AES加解密");
		view.addObject("viewPath", "admintools/aes");
		return view;
	}
	/**
	 * 
	 * AES加密解密
	 * 茹化肖
	 * 2015年12月7日16:40:30
	 * @return
	 */
	@RequestMapping("aesdo")
	@ResponseBody
	public String aesdo(int type,String str)
	{
		if(type==1)//加密
		{
			return AES.aesEncrypt(str);
		}
		else {//解密
			return AES.aesDecrypt(str.replace("\\", ""));
		}
	}
}