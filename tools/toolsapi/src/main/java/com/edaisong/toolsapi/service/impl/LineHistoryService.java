package com.edaisong.toolsapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.toolsapi.common.MybatisUtil;
import com.edaisong.toolsapi.common.ToolsHelper;
import com.edaisong.toolsapi.service.inter.ILineHistoryService;
import com.edaisong.toolscore.enums.ServerType;
import com.edaisong.toolscore.util.JsonUtil;
import com.edaisong.toolsentity.AppDbConfig;
import com.edaisong.toolsentity.LineHistory;
import com.edaisong.toolsentity.common.PagedResponse;
import com.edaisong.toolsentity.domain.ConnectionInfo;
import com.edaisong.toolsentity.req.LineHistoryReq;
import com.edaisong.toolsentity.req.PagedLineHistoryReq;

@Service
public class LineHistoryService implements ILineHistoryService{

	@Autowired
	private ToolsHelper toolsHelper;
	/**
	 * 分页查询
	 * 茹化肖
	 */
	@Override
	public PagedResponse<LineHistory> getList(PagedLineHistoryReq req) {
		List<AppDbConfig> appConfig=toolsHelper.getAppConfigList(ServerType.SqlServer,req.getAppName());//构造数据库连接
		if (appConfig.size()>0) {
			ConnectionInfo conInfo=JsonUtil.str2obj(appConfig.get(0).getConfigvalue(), ConnectionInfo.class) ;
			return  MybatisUtil.getSqlSessionUtil(conInfo).selectPageList("ILineHistoryDao.getPageList", req);
		}
		return null;
	}
	/**
	 * 添加一个上线记录
	 */
	@Override
	public int addLineHistory(LineHistoryReq req) { 
		List<AppDbConfig> appConfig=toolsHelper.getAppConfigList(ServerType.SqlServer,req.getAppName());//构造数据库连接
		if (appConfig.size()>0) {
			ConnectionInfo conInfo=JsonUtil.str2obj(appConfig.get(0).getConfigvalue(), ConnectionInfo.class) ;
			return  MybatisUtil.getSqlSessionUtil(conInfo).insert("ILineHistoryDao.addLineHistory",req);
		}
		return 0;
	}
	@Override
	public int deleteLineHistory(LineHistoryReq req) {
		List<AppDbConfig> appConfig=toolsHelper.getAppConfigList(ServerType.SqlServer,req.getAppName());//构造数据库连接
		if (appConfig.size()>0) {
			ConnectionInfo conInfo=JsonUtil.str2obj(appConfig.get(0).getConfigvalue(), ConnectionInfo.class) ;
			return  MybatisUtil.getSqlSessionUtil(conInfo).update("ILineHistoryDao.deleteLineHistory",req);
		}
		return 0;
	}
	@Override
	public int modifyLineHistory(LineHistoryReq req) {
		List<AppDbConfig> appConfig=toolsHelper.getAppConfigList(ServerType.SqlServer,req.getAppName());//构造数据库连接
		if (appConfig.size()>0) {
			ConnectionInfo conInfo=JsonUtil.str2obj(appConfig.get(0).getConfigvalue(), ConnectionInfo.class) ;
			return  MybatisUtil.getSqlSessionUtil(conInfo).update("ILineHistoryDao.modifyLineHistory",req);
		}
		return 0;
	}

}
