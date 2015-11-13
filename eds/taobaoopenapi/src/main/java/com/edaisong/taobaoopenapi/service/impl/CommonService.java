package com.edaisong.taobaoopenapi.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edaisong.api.common.RecordtypeHelper;
import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.common.RecordType;
import com.edaisong.taobaoopenapi.service.inter.ICommonService;

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
}
