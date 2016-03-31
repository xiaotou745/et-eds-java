package com.edaisong.toolsapi.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edaisong.toolsapi.mongo.MongoService;
import com.edaisong.toolsapi.redis.RedisService;
import com.edaisong.toolsapi.service.inter.IAppDbConfigService;
import com.edaisong.toolscore.consts.RedissCacheKey;
import com.edaisong.toolscore.enums.ServerType;
import com.edaisong.toolscore.util.JsonUtil;
import com.edaisong.toolscore.util.ParseHelper;
import com.edaisong.toolscore.util.PropertyUtils;
import com.edaisong.toolsentity.AppDbConfig;
import com.edaisong.toolsentity.domain.ActionLog;
import com.edaisong.toolsentity.domain.ConnectionInfo;
import com.edaisong.toolsentity.domain.PairEntry;
import com.edaisong.toolsentity.domain.SelectAppModel;
import com.edaisong.toolsentity.req.MongoLogReq;
import com.edaisong.toolsentity.req.PagedAppDbConfigReq;
import com.edaisong.toolsentity.req.PagedMongoLogReq;
import com.mongodb.BasicDBObject;

@Component
public class ToolsHelper {	
	@Autowired
	private IAppDbConfigService appDbConfigService;
	@Autowired
	private MongoService mongoService;
	@Autowired
	private RedisService redisService;
	 
	/**
	 * 将配置文件转conn集合
	 * 茹化肖
	 * @param list
	 * @return
	 */
	public List<SelectAppModel> getConnList(){
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
	 * 查询配置了给定类型的服务器的appName
	 * @param serverType
	 * @return
	 */
	public List<String> getAppNameList(ServerType serverType,String appName){
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
	public List<AppDbConfig> getAppConfigList(ServerType serverType,String appName){
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
	 * 检查服务器是否可以连接通
	 * @param conInfo
	 * @param serverType
	 * @author hailongzhao
	 * @date 20151201
	 * @return
	 */
	public boolean checkConnection(ConnectionInfo conInfo,ServerType serverType){
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
	 
	public List<String> getAppNameList() throws Exception{
		List<String> redisAppNameList=new ArrayList<String>();
		redisAppNameList=redisService.get(RedissCacheKey.AppName_Key,List.class);
		if (redisAppNameList!=null&&redisAppNameList.size()>0) {
			return redisAppNameList;
		}
		List<String> appNameList=Collections.synchronizedList(new ArrayList<>());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LinkedHashMap<String, String> timeHashMap= splitTimeRange(sdf.parse("2015-10-01 00:00:00"),new Date());
		timeHashMap.entrySet().parallelStream().forEach(t->{
			try {
				String mongoName = getTableName(t.getKey());
				System.out.println("表名称:" + mongoName);
				List<String> partialResult = mongoService.selectDistinct(mongoName, "sourceSys");
				appNameList.addAll(partialResult);
			} catch (Exception e) {
				System.out.println("并行查询mongo时出错:"+ e.getMessage());
				e.printStackTrace();
			}
		});
		System.out.println("并行查询mongo完成");
		redisAppNameList=appNameList.parallelStream().distinct().collect(Collectors.toList());
		if (redisAppNameList!=null&&redisAppNameList.size()>0) {
			redisService.set(RedissCacheKey.AppName_Key,redisAppNameList,Integer.parseInt(PropertyUtils.getProperty("redisCacheTime")),TimeUnit.DAYS);
		}
		return redisAppNameList;
	}
	/**
	 * 根据月份生产mongo中的表名称
	 * @param monthInfo
	 * @author hailongzhao
	 * @date 20151208
	 * @return
	 */
	public String getMongoTableName(PagedMongoLogReq req){
    	return "logtb_"+req.getYearInfo()+"_"+req.getMonthInfo();
    }

    /**
     * 根据页面上的查询条件组织对monmgo的查询request
     * @author hailongzhao
     * @date 20151208
     * @param req
     * @throws Exception 
     */
	public void fillQuery(PagedMongoLogReq req) throws Exception{
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		MongoLogReq whereReq=new MongoLogReq();
		whereReq.setAppversion(req.getAppversion());
		whereReq.setRequestUrl(req.getRequestUrl());
		String begin="";
		String end="";
		if (!req.getBeginDay().isEmpty()) {
			begin=req.getYearInfo()+"-"+req.getMonthInfo()+"-"+req.getBeginDay()+" 00:00:00";
		}
		if (!req.getEndDay().isEmpty()) {
			end=req.getYearInfo()+"-"+req.getMonthInfo()+"-"+req.getEndDay()+" 23:59:59";
		}
		getWhere(whereReq,query,begin,end,sdf);
		req.setQueryObject(query);
		BasicDBObject sort=new BasicDBObject();
		sort.put(req.getOrderBy(), req.getOrderType());//按照执行时间倒序（1是升序，-1是降序）
		req.setSortObject(sort);
    }
	/**
	 * 总体统计时用到的列
	 * @return
	 */
	public  BasicDBObject getColumns(){
		BasicDBObject columns= new BasicDBObject();// 指定需要显示列
		columns.put("stackTrace", 1);
		columns.put("executeTime", 1);
		columns.put("requestTime", 1);
		return columns;
	} 
	/**
	 * 每个方法查询时用到的列
	 * @return
	 */
	public  BasicDBObject getTimeColumns(){
		BasicDBObject columns= new BasicDBObject();// 指定需要显示列
		columns.put("stackTrace", 1);
		columns.put("executeTime", 1);
		columns.put("requestUrl", 1);
		return columns;
	} 
    /**
     * 根据页面上的查询条件组织对monmgo的查询request
     * @author hailongzhao
     * @date 20151208
     * @param req
     */
	public BasicDBObject getQueryObj(MongoLogReq req){
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
		return query;
    }
	private String getTableName(String beginDate){
    	return "logtb_"+ParseHelper.ToDateString(ParseHelper.ToDate(beginDate), "yyyy_MM");
    }
	public List<String> queryAllVersion() throws Exception{	
		List<String> appNames= getAppNameList();
		List<String> resultVersion=new ArrayList<>();
		List<String> tempVersion=new ArrayList<>();
		for (String name : appNames) {
			tempVersion.clear();
			if (name.endsWith("http")||name.endsWith("api")) {
				tempVersion=queryVersion(name);
			}
			resultVersion.add(name+":"+String.join(",", tempVersion));
		}
		return resultVersion;
	}
	private List<String> queryVersion(String sourceSys) throws Exception{
		List<String> result=new ArrayList<String>();
		result=redisService.get(RedissCacheKey.AppVersion_Key+sourceSys,List.class);
		//redisService.remove(RedissCacheKey.AppVersion_Key+sourceSys);
		if (result!=null&&result.size()>0) {
			return result;
		}
		List<ActionLog> resultList=Collections.synchronizedList(new ArrayList<>());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LinkedHashMap<String, String> timeHashMap= splitTimeRange(sdf.parse("2015-10-01 00:00:00"),new Date());
		BasicDBObject query=new BasicDBObject();
		query.put("sourceSys", sourceSys);
		BasicDBObject columns=new BasicDBObject();
		columns.put("requestUrl", 1);
		
		timeHashMap.entrySet().parallelStream().forEach(t->{
			try {
					String mongoName=getTableName(t.getKey());
					System.out.println("表名称:"+mongoName);
					List<ActionLog> partialResult= mongoService.selectResult(mongoName,query,columns);
					resultList.addAll(partialResult);
			} catch (Exception e) {
				System.out.println("并行查询mongo时出错:"+e.getMessage());
				e.printStackTrace();
			}
		});
		System.out.println("并行查询mongo完成");

		//String m="http://japi.edaisong.com/20151023/services/common/getrecordtypec";
		result = resultList.parallelStream().map(t -> {
			if (t.getRequestUrl().indexOf("http://")==0&&
				t.getRequestUrl().indexOf("http://localhost")<0) {
				String mkkString = t.getRequestUrl().substring("http://".length());
				int index = mkkString.indexOf("/");
				if(index>0){
					String url = mkkString.substring(index + 1);
					int end = url.indexOf("/");
					if(end>0){
						String tempVersion= url.substring(0, end);
						if(!tempVersion.isEmpty()&&StringUtils.isNumeric(tempVersion)){
							return tempVersion;
						}
					}
				}
			}
			return "";
		}).filter(k -> !k.isEmpty()).distinct().collect(Collectors.toList());
		if (result.size()==1) {
			result.clear();
		}
		result.sort((a,b)->{return a.compareTo(b);});
		if (result!=null&&result.size()>0) {
			redisService.set(RedissCacheKey.AppVersion_Key+sourceSys,result,Integer.parseInt(PropertyUtils.getProperty("redisCacheTime")),TimeUnit.DAYS);
		}
		
		return result;
	}
    /**
     * 并行查询多个mongodb中的表
     * @param req
     * @return
     * @date 20151221
     * @author hailongzhao
     * @throws Exception
     */
	public List<ActionLog> queryAll(MongoLogReq req,BasicDBObject columns) throws Exception{
		List<ActionLog> result=Collections.synchronizedList(new ArrayList<>());
		LinkedHashMap<String, String> timeHashMap= splitTimeRange(req.getBegin(),req.getEnd());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long currentDay=(new Date()).getTime();
		long lastDay=sdf.parse("2015-10-01 00:00:00").getTime();
		timeHashMap.entrySet().stream().forEach(t->{
			try {
				long tempDay=sdf.parse(t.getKey()).getTime();
				if(lastDay<=tempDay&&tempDay<=currentDay){
					BasicDBObject tempReq=getQueryObj(req);
					getWhere(req,tempReq,t.getKey(),t.getValue(),sdf);
					String mongoName=getTableName(t.getKey());
					System.out.println("表名称:"+mongoName);
					List<ActionLog> partialResult= mongoService.selectResult(mongoName,tempReq,columns);
					result.addAll(partialResult);
				}
			} catch (Exception e) {
				System.out.println("并行查询mongo时出错:"+e.getMessage());
				e.printStackTrace();
			}
		});
		System.out.println("并行查询mongo完成");
		return result;
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
					monthEnd = sdf.format(ParseHelper.plusDate(cl.getTime(), 5, 24*60*60-1));
				}
				result.put(monthBegin, monthEnd);
				cl.add(Calendar.DAY_OF_YEAR, 1);
				tempBegin = cl.getTime();
			}
		}
		return result;
	}
    /**
     * mongo的范围查询
     * @author hailongzhao
     * @date 20151228
     * @param tempReq
     * @param begin
     * @param end
     * @param sdf
     * @throws Exception
     */
	private void getWhere(MongoLogReq req,BasicDBObject tempReq,String begin,String end,SimpleDateFormat sdf) throws Exception{
    	String isbegin="";
    	String isend="";
		String isversion="";
    	String isurl="";

    	if (begin!=null&&!begin.isEmpty()) {
    		isbegin="var isbegin="+ sdf.parse(begin).getTime() + "<=initMills;";
    	}
    	if (end!=null&&!end.isEmpty()) {
    		isend="var isend=initMills<="+ sdf.parse(end).getTime()+";";
    	}
    	if (req.getAppversion()!=null&&!req.getAppversion().isEmpty()) {
    		isversion="var isversion=this.requestUrl.indexOf('"+req.getAppversion()+"')>0;";
    	}
    	if (req.getRequestUrl()!=null&&!req.getRequestUrl().isEmpty()) {
    		isurl="var isurl=this.requestUrl.indexOf('"+req.getRequestUrl()+"')>=0;";
    	}
    	if (!isbegin.isEmpty()||!isend.isEmpty()||!isversion.isEmpty()||!isurl.isEmpty()) {
    		String condition = "function(){"
    				+ "var init = this.requestTime.replace(new RegExp('-','gm'),'/'); "
    				+ "var initMills = (new Date(init)).getTime();"
    				+isbegin
    				+isend
    				+isversion
    				+isurl
    				+ "return "
    				+(isbegin==""?"true":"isbegin")
    				+(isend==""?"&&true":"&&isend")
    				+(isversion==""?"&&true":"&&isversion")
    				+(isurl==""?"&&true":"&&isurl")
    				+";}";
    		tempReq.put("$where", condition);
		}
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
	private Map<Long, Long> splitStep(Date begin,Date end,int minStep){
    	Map<Long, Long> result = new HashMap<Long, Long>();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (begin.before(end)) {
			Date tempBegin = begin;
			Calendar cl = Calendar.getInstance();
			while (tempBegin.before(end)) {
				cl.setTime(tempBegin);
				cl.add(Calendar.MINUTE, minStep);// 日期加n分钟
				if (cl.getTime().getTime()>end.getTime()) {
					result.put(tempBegin.getTime(), end.getTime());
					//System.out.println("splitStep:"+sdf.format(tempBegin)+"--"+sdf.format(end));
					break;
				}else {
					result.put(tempBegin.getTime(), cl.getTime().getTime());
					//System.out.println("splitStep:"+sdf.format(tempBegin)+"--"+sdf.format(cl.getTime()));
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
	public Map<Long, List<PairEntry<Long, ActionLog>>> groupByStep(Date begin,Date end,List<ActionLog> data,int minStep){
		List<PairEntry<Long, ActionLog>> resultList = Collections.synchronizedList(new ArrayList<PairEntry<Long, ActionLog>>());
		System.out.println("splitStep开始");
		Map<Long, Long> timeHashMap = splitStep(begin, end, minStep);
		System.out.println("分组开始");
		//记录按照请求时间升序
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		data.sort((a, b) -> {
			try {
				Long aValue=sdf.parse(a.getRequestTime()).getTime();
				Long bValue=sdf.parse(b.getRequestTime()).getTime();
				return aValue.compareTo(bValue);	
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		});
				
		List<Long> timeList = new ArrayList<Long>(timeHashMap.keySet());
		//时间段按照开始日期升序
		timeList.sort((a, b) -> {
			return a.compareTo(b);
		});
		// 并行将在日期段内的ActionLog放入resultMap
		int j = 0;
		Long mb = 0l;
		for (int i = 0; i < data.size(); i++) {
			mb = ParseHelper.ToDate(data.get(i).getRequestTime()).getTime();
			while (j < timeList.size()) {
				if (timeList.get(j) <= mb && mb < timeHashMap.get(timeList.get(j))) {
					resultList.add(new PairEntry<>(timeHashMap.get(timeList.get(j)), data.get(i)));
					break;
				} else {
					j++;
				}
			}
		}
		System.out.println("分组结束");
		// 按照日期段的结束日期分组
		Map<Long, List<PairEntry<Long, ActionLog>>> result = resultList
				.parallelStream()
				.collect(
						Collectors
								.groupingBy(PairEntry<Long, ActionLog>::getKey));

		// 补齐没有记录的日期段
		List<Long> timeList1 = new ArrayList<Long>(result.keySet());
		timeHashMap.values().removeAll(timeList1);
		timeHashMap.values().forEach(t -> result.put(t, new ArrayList<>()));
		return result;

    }	
	public String List2Table(List<Map<String, String>> listMap)
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
}
