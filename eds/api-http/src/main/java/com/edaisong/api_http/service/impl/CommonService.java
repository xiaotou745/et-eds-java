package com.edaisong.api_http.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.common.RecordtypeHelper;
import com.edaisong.api.dao.inter.ITaskDistributionConfigDao;
import com.edaisong.api.service.inter.ITaskDistributionConfigService;
import com.edaisong.api_http.service.inter.ICommonService;
import com.edaisong.entity.TaskDistributionConfig;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.common.RecordType;
import com.edaisong.entity.resp.TaskDistributionConfigResp;

/**
 * 通用模块 
 * @author CaoHeYang
 * @date 20150909
 */
@Service
public class CommonService implements ICommonService {
	
	@Autowired
	private ITaskDistributionConfigService taskDistributionConfigService;
	/**
	 * B端获取所有的筛选条件类型
	 * @author CaoHeYang
	 * @date 20150909
	 * @return
	 */
	@Override
	public HttpResultModel<List<RecordType>> getRecordtypeB() {
		HttpResultModel<List<RecordType>> resultModel=new HttpResultModel<List<RecordType>>();
       resultModel.setResult(RecordtypeHelper.getRecordtypeB());
       return resultModel;
	}

	/**
	 * C端获取所有的筛选条件类型
	 * @author CaoHeYang
	 * @date 20150909
	 * @return
	 */
	@Override
	public HttpResultModel<List<RecordType>> getRecordtypeC() {
		HttpResultModel<List<RecordType>> resultModel=new HttpResultModel<List<RecordType>>();
       resultModel.setResult(RecordtypeHelper.getRecordtypeC());
       return resultModel;
	}
	
	/**
	* @author haichao
	* @date 2015年11月26日 09:54:08
	* 获取普通任务配送费配置
	* */
	@Override
	public HttpResultModel<List<TaskDistributionConfig>> getTaskDistributionConfig() {
		return taskDistributionConfigService.getTaskDistributionConfig();
	}
	
}
