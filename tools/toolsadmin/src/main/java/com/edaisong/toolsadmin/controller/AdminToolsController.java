package com.edaisong.toolsadmin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.toolsadmin.common.MenuHelper;
import com.edaisong.toolsadmin.common.UserContext;
import com.edaisong.toolsapi.common.MybatisUtil;
import com.edaisong.toolsapi.common.RedisUtil;
import com.edaisong.toolsapi.common.SQLServerUtil;
import com.edaisong.toolsapi.common.ToolsHelper;
import com.edaisong.toolsapi.mongo.MongoService;
import com.edaisong.toolsapi.service.inter.IAppDbConfigService;
import com.edaisong.toolscore.enums.ServerType;
import com.edaisong.toolscore.security.AES;
import com.edaisong.toolscore.util.JsonUtil;
import com.edaisong.toolsentity.AppDbConfig;
import com.edaisong.toolsentity.AuthorityMenuClass;
import com.edaisong.toolsentity.ExportSqlManage;
import com.edaisong.toolsentity.common.PagedResponse;
import com.edaisong.toolsentity.domain.ActionLog;
import com.edaisong.toolsentity.domain.ConnectionInfo;
import com.edaisong.toolsentity.domain.LogChartModel;
import com.edaisong.toolsentity.domain.MenuDetail;
import com.edaisong.toolsentity.domain.MenuEntity;
import com.edaisong.toolsentity.domain.PairEntry;
import com.edaisong.toolsentity.req.MongoLogReq;
import com.edaisong.toolsentity.req.PagedAppDbConfigReq;
import com.edaisong.toolsentity.req.PagedExportSqlReq;
import com.edaisong.toolsentity.req.PagedMongoLogReq;


/**
 * 控制其他app
 * @author hailongzhao
 * @date 20151118
 */
@Controller
@RequestMapping("admintools")
public class AdminToolsController {
	@Autowired
	private ToolsHelper toolsHelper;
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
		view.addObject("appNameList", toolsHelper.getAppNameList(ServerType.Redis,null));
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
		List<AppDbConfig> appConfig=toolsHelper.getAppConfigList(ServerType.Redis,appName);
		if (appConfig.size()>0) {
			ConnectionInfo conInfo=JsonUtil.str2obj(appConfig.get(0).getConfigvalue(), ConnectionInfo.class) ;
			RedisUtil redisUtil=new RedisUtil(conInfo);
			if(sType.equals("1")){
				Set<String> mSet=redisUtil.keys(key);
				return String.join(",", mSet);
			}else {
				try {
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
				} catch (Exception e) {
					return "值无法反序列化"+e.getMessage();
				}

			}
		}
		return "";
	}
	@RequestMapping("redisremove")
	@ResponseBody
	public Integer redisRemove(String appName,String key) {
		if (appName==null||appName.isEmpty()||key==null||key.isEmpty()) {
			return 1;
		}
		List<AppDbConfig> appConfig=toolsHelper.getAppConfigList(ServerType.Redis,appName);
		if (appConfig.size()>0) {
			ConnectionInfo conInfo=JsonUtil.str2obj(appConfig.get(0).getConfigvalue(), ConnectionInfo.class) ;
			RedisUtil redisUtil=new RedisUtil(conInfo);
			redisUtil.remove(key);
		}
		return 1;
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
		view.addObject("appNameList", toolsHelper.getConnList());
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
		List<AppDbConfig> appConfig=toolsHelper.getAppConfigList(ServerType.SqlServer,appName);//构造数据库连接
		if (appConfig.size()>0) {
			ConnectionInfo conInfo=JsonUtil.str2obj(appConfig.get(0).getConfigvalue(), ConnectionInfo.class) ;
			List<MenuEntity> menuList = MybatisUtil.getSqlSessionUtil(conInfo).selectList("IAuthorityMenuClassDao.getListMenuAll");
			return MenuHelper.getAuthJson(menuList);
		}
		return "";
		
		
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
		List<AppDbConfig> appConfig=toolsHelper.getAppConfigList(ServerType.SqlServer,appName);//构造数据库连接
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
		List<AppDbConfig> appConfig=toolsHelper.getAppConfigList(ServerType.SqlServer,appName);//构造数据库连接
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
		List<AppDbConfig> appConfig=toolsHelper.getAppConfigList(ServerType.SqlServer,appName);//构造数据库连接
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
		view.addObject("appNameList", toolsHelper.getAppNameList(null,null));
		return view;
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
		boolean isPass=toolsHelper.checkConnection(conInfo,ServerType.getEnum(req.getConfigtype()));
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
		//目前只有superman支持导出数据
		List<AppDbConfig> appConfig = toolsHelper.getAppConfigList(ServerType.SqlServer, null);
		List<String> appNameList = appConfig
				.stream()
				.filter(m -> m.getConfigvalue().toLowerCase()
						.indexOf("superman") > 0).map(t -> t.getAppname())
				.distinct().collect(Collectors.toList());
		view.addObject("appNameList", appNameList);
		//view.addObject("appNameList", toolsHelper.getAppNameList(ServerType.SqlServer,null));
		return view;
	}
	@RequestMapping("exportinsert")
	@ResponseBody
	public int exportInsert(ExportSqlManage req,String appName) {
		List<AppDbConfig> appConfig=toolsHelper.getAppConfigList(ServerType.SqlServer,appName);//构造数据库连接
		if (appConfig.size()>0) {
			ConnectionInfo conInfo=JsonUtil.str2obj(appConfig.get(0).getConfigvalue(), ConnectionInfo.class) ;
			return MybatisUtil.getSqlSessionUtil(conInfo).insert("IExportSqlManageDao.insert",req);
		}
		return -1;
	}
	@RequestMapping("exportdelete")
	@ResponseBody
	public int exportDelete(Integer id,String appName) {
		List<AppDbConfig> appConfig=toolsHelper.getAppConfigList(ServerType.SqlServer,appName);//构造数据库连接
		if (appConfig.size()>0) {
			ConnectionInfo conInfo=JsonUtil.str2obj(appConfig.get(0).getConfigvalue(), ConnectionInfo.class) ;
			return MybatisUtil.getSqlSessionUtil(conInfo).delete("IExportSqlManageDao.delete",id);
		}
		return -1;
	}
	@RequestMapping("exportupdate")
	@ResponseBody
	public int exportUpdate(ExportSqlManage req,String appName) {
		List<AppDbConfig> appConfig=toolsHelper.getAppConfigList(ServerType.SqlServer,appName);//构造数据库连接
		if (appConfig.size()>0) {
			ConnectionInfo conInfo=JsonUtil.str2obj(appConfig.get(0).getConfigvalue(), ConnectionInfo.class) ;
			return  MybatisUtil.getSqlSessionUtil(conInfo).update("IExportSqlManageDao.update",req);
		}
		return -1;
	}
	@RequestMapping("exportquery")
	public ModelAndView exportQuery(PagedExportSqlReq req,String appName) {
		ModelAndView view = new ModelAndView("admintools/exportdo");
		List<AppDbConfig> appConfig=toolsHelper.getAppConfigList(ServerType.SqlServer,appName);//构造数据库连接
		if (appConfig.size()>0) {
			ConnectionInfo conInfo=JsonUtil.str2obj(appConfig.get(0).getConfigvalue(), ConnectionInfo.class) ;
			PagedResponse<ExportSqlManage> resp= MybatisUtil.getSqlSessionUtil(conInfo).selectPageList("IExportSqlManageDao.query",req);
			view.addObject("listData", resp);
		}
		return view;
	}
	/**
	 * 日志分析
	 * @author hailongzhao
	 * @date 20151118
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("log")
	public ModelAndView log() throws Exception{
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "APP控制");
		view.addObject("currenttitle", "日志分析");
		view.addObject("viewPath", "admintools/log");
		view.addObject("appVersionList", toolsHelper.queryAllVersion());
		view.addObject("appNameList",  toolsHelper.getAppNameList());
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
		toolsHelper.fillQuery(req);
		PagedResponse<ActionLog> resp=mongoService.selectPart(toolsHelper.getMongoTableName(req), req);
		view.addObject("listData", resp);
		return view;
	}
	
	/**
	 * 日志分析
	 * @author hailongzhao
	 * @date 20151118
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("logchart")
	public ModelAndView logChart() throws Exception{
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "APP控制");
		view.addObject("currenttitle", "日志图表分析");
		view.addObject("viewPath", "admintools/logchart");
		view.addObject("appVersionList", toolsHelper.queryAllVersion());
		view.addObject("appNameList",  toolsHelper.getAppNameList());
		return view;
	}
	/**
	 * 日志分析
	 * @author hailongzhao
	 * @date 20151118
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("methodstatistics")
	public ModelAndView methodStatistics() throws Exception{
		ModelAndView view = new ModelAndView("adminView");
		view.addObject("subtitle", "APP控制");
		view.addObject("currenttitle", "日志方法统计");
		view.addObject("viewPath", "admintools/methodstatistics");
		view.addObject("appVersionList", toolsHelper.queryAllVersion());
		view.addObject("appNameList",  toolsHelper.getAppNameList());
		return view;
	}
	/**
	 * 每个方法分析
	 * @author hailongzhao
	 * @date 20151228
	 * @return
	 */
	@RequestMapping("methodstatisticsdo")
	@ResponseBody
	public LogChartModel methodStatisticsDo(MongoLogReq req) throws Exception{
		List<ActionLog> data = toolsHelper.queryAll(req, toolsHelper.getTimeColumns());
		Map<String, List<ActionLog>> group = data.parallelStream().collect(
				Collectors.groupingBy(ActionLog::getRequestUrl));
		Map<String, Integer> numData = group
				.entrySet()
				.parallelStream()
				.collect(
						Collectors.toMap(
								Entry<String, List<ActionLog>>::getKey,
								m ->m.getValue().size()));
		Map<String, Double> timeData = group
				.entrySet()
				.parallelStream()
				.collect(
						Collectors.toMap(
								Entry<String, List<ActionLog>>::getKey,
								m -> m.getValue().parallelStream()
										.mapToLong(t -> t.getExecuteTime())
										.summaryStatistics().getAverage()));
		Map<String, Double> rateData = group
				.entrySet()
				.parallelStream()
				.collect(
						Collectors.toMap(
								Entry<String, List<ActionLog>>::getKey,
								m -> {
									long errNum = m.getValue()
											.parallelStream()
											.filter(k -> k.getStackTrace() != null
													&& !k.getStackTrace()
															.isEmpty()).count();
									return errNum * 100.00d
											/ m.getValue().size();
								}));
		Map<String, Long> maxTimeData = group
				.entrySet()
				.parallelStream()
				.collect(
						Collectors.toMap(
								Entry<String, List<ActionLog>>::getKey,
								m -> m.getValue().parallelStream()
										.mapToLong(t -> t.getExecuteTime())
										.summaryStatistics().getMax()));

		Map<String, Long> minTimeData = group
				.entrySet()
				.parallelStream()
				.collect(
						Collectors.toMap(
								Entry<String, List<ActionLog>>::getKey,
								m -> m.getValue().parallelStream()
										.mapToLong(t -> t.getExecuteTime())
										.summaryStatistics().getMin()));

		List<String> urlsSet=null;
		switch (req.getOrderBy()) {
		case 0://调用次数
			if(req.getOrderType()==-1){//降序
				urlsSet=numData.entrySet().stream().sorted((b,a)->{return a.getValue().compareTo(b.getValue());}).map(t->t.getKey()).collect(Collectors.toList());
			}else {
				urlsSet=numData.entrySet().stream().sorted((a,b)->{return a.getValue().compareTo(b.getValue());}).map(t->t.getKey()).collect(Collectors.toList());
			}
			break;
		case 1://平均耗时
			if(req.getOrderType()==-1){//降序
				urlsSet=timeData.entrySet().stream().sorted((b,a)->{return a.getValue().compareTo(b.getValue());}).map(t->t.getKey()).collect(Collectors.toList());
			}else {
				urlsSet=timeData.entrySet().stream().sorted((a,b)->{return a.getValue().compareTo(b.getValue());}).map(t->t.getKey()).collect(Collectors.toList());
			}
			break;
		case 2://异常率
			if(req.getOrderType()==-1){//降序
				urlsSet=rateData.entrySet().stream().sorted((b,a)->{return a.getValue().compareTo(b.getValue());}).map(t->t.getKey()).collect(Collectors.toList());
			}else {
				urlsSet=rateData.entrySet().stream().sorted((a,b)->{return a.getValue().compareTo(b.getValue());}).map(t->t.getKey()).collect(Collectors.toList());
			}
			break;
		default:
			break;
		}
		LogChartModel result = new LogChartModel();
		result.setUrls(new ArrayList<String>());
		result.setTimeData(new ArrayList<Double>());
		result.setMinTimeData(new ArrayList<Long>());
		result.setMaxTimeData(new ArrayList<Long>());
		result.setNumData(new ArrayList<Integer>());
		result.setRateData(new ArrayList<Double>());
		urlsSet.forEach(t -> {
			result.getUrls().add(t);
			result.getNumData().add(numData.get(t));
			result.getTimeData().add(timeData.get(t));
			result.getMinTimeData().add(minTimeData.get(t));
			result.getMaxTimeData().add(maxTimeData.get(t));
			result.getRateData().add(rateData.get(t));
		});
		return result;
	}

    /**
     * 请求次数
     * @author hailongzhao
     * @date 20151228
     * @return
     * @throws Exception 
     */
	@RequestMapping("requestnum")
	@ResponseBody
    public List<PairEntry<Long, Integer>> queryRequestNum(MongoLogReq req) throws Exception{
		List<ActionLog> data = toolsHelper.queryAll(req,toolsHelper.getColumns());
		Map<Long, List<PairEntry<Long, ActionLog>>> groupData = toolsHelper.groupByStep(req.getBegin(), req.getEnd(), data, req.getMinStep());
		List<PairEntry<Long, Integer>> mEntries= groupData
				.entrySet()
				.parallelStream().map(t -> {
					return new PairEntry<Long, Integer>(t.getKey(),t.getValue().size());
				})
				.collect(Collectors.toList());
		mEntries.sort((a, b) -> {return a.getKey().compareTo(b.getKey());});
		return mEntries;
    }
    /**
     * 平均执行时间
     * @author hailongzhao
     * @date 20151228
     * @param begin
     * @param end
     * @param data
     * @param minStep
     * @return
     */
	@RequestMapping("averagetime")
	@ResponseBody
    public List<PairEntry<Long, Integer>> queryAverageTime(MongoLogReq req) throws Exception{
    	List<ActionLog> data=toolsHelper.queryAll(req,toolsHelper.getColumns());
    	Map<Long, List<PairEntry<Long, ActionLog>>> groupData=toolsHelper.groupByStep(req.getBegin(), req.getEnd(),data, req.getMinStep());
    	List<PairEntry<Long, Integer>> mEntries= groupData
				.entrySet()
				.parallelStream().map(t -> {
					Integer rate = (int)t.getValue()
							.stream()
							.mapToLong(x -> x.getValue().getExecuteTime())
							.summaryStatistics()
							.getAverage();
					return new PairEntry<Long, Integer>(t.getKey(),rate);
				})
				.collect(Collectors.toList());
		mEntries.sort((a, b) -> {return a.getKey().compareTo(b.getKey());});
		return mEntries;
    }
    /**
     * 查询异常率
     * @author hailongzhao
     * @date 20151228
     * @param begin
     * @param end
     * @param data
     * @param minStep
     * @return
     */
	@RequestMapping("errorrate")
	@ResponseBody
    public List<PairEntry<Long, Double>> queryErrorRate(MongoLogReq req) throws Exception{
    	List<ActionLog> data=toolsHelper.queryAll(req,toolsHelper.getColumns());
    	Map<Long, List<PairEntry<Long, ActionLog>>> groupData=toolsHelper.groupByStep(req.getBegin(), req.getEnd(),data, req.getMinStep());    		
    	List<PairEntry<Long, Double>> mEntries= groupData
				.entrySet()
				.parallelStream()
				.map(t -> {
					Double rate =0d;
					if (t.getValue().size()>0) {
						long errNum = t
								.getValue()
								.stream()
								.filter(k -> k.getValue().getStackTrace() != null
										&& !k.getValue().getStackTrace().isEmpty())
								.count();
						 rate = errNum * 100.00d / t.getValue().size();
					}
					return new PairEntry<Long, Double>(t.getKey(),rate);
				}).collect(Collectors.toList());
    	mEntries.sort((a, b) -> {return a.getKey().compareTo(b.getKey());});
    	return mEntries;
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
		view.addObject("appNameList", toolsHelper.getAppNameList(ServerType.SqlServer,null));
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
			List<AppDbConfig> appConfig=toolsHelper.getAppConfigList(ServerType.SqlServer,appName);//获取数据库配置
			if (appConfig.size()>0) 
			{
				ConnectionInfo conInfo=JsonUtil.str2obj(appConfig.get(0).getConfigvalue(), ConnectionInfo.class) ;
				if(type.equals("1"))
				{
					//查询SQL
					if(sql.toUpperCase().indexOf("UPDATE ")>=0)
					{
						return "查询时SQL不可以带UPDATE关键字";
					}
					if(sql.toUpperCase().indexOf("INSERT ")>=0)
					{
						return "查询时SQL不可以带INSERT关键字";
					}
					if(sql.toUpperCase().indexOf("DELETE ")>=0)
					{
						return "查询时SQL不可以带DELETE关键字";
					}
					if(sql.toUpperCase().indexOf("NOLOCK")<=0)
					{
						return "查询时SQL必须加NOLOCK关键字";
					}
					if(sql.toUpperCase().indexOf(" TOP ")<0)
					{
						return "查询时SQL必须加TOP关键字";
					}
					return toolsHelper.List2Table(SQLServerUtil.executeResultSet(conInfo, sql))	;
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