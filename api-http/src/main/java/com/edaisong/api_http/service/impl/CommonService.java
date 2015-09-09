package com.edaisong.api_http.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edaisong.api.common.RecordtypeHelper;
import com.edaisong.api_http.entity.ResultModel;
import com.edaisong.api_http.service.inter.ICommonService;
import com.edaisong.entity.common.RecordType;

/**
 * 通用模块 
 * @author CaoHeYang
 * @date 20150909
 */
@Service
public class CommonService implements ICommonService {
	/**
	 * B端获取所有的筛选条件类型
	 * @author CaoHeYang
	 * @date 20150909
	 * @return
	 */
	@Override
	public ResultModel<List<RecordType>> getRecordtypeB() {
	   ResultModel<List<RecordType>> resultModel=new ResultModel<List<RecordType>>();
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
	public ResultModel<List<RecordType>> getRecordtypeC() {
	   ResultModel<List<RecordType>> resultModel=new ResultModel<List<RecordType>>();
       resultModel.setResult(RecordtypeHelper.getRecordtypeC());
       return resultModel;
	}
}
