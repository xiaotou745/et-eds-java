package com.edaisong.api_http.service.inter;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.edaisong.api_http.entity.ResultModel;
import com.edaisong.entity.common.RecordType;

/**
 * 通用模块 
 * @author CaoHeYang
 * @date 20150909
 */
@Path("/common")
@Consumes("application/json")//当前方法接收的参数类型
@Produces("application/json")//当前类的所有方法都返回json格式的数据
public interface ICommonService {
	/**
	 * B端获取所有的筛选条件类型
	 * @author CaoHeYang
	 * @date 20150909
	 * @return
	 */
	@Post
	@Path("/getrecordtypeb")
	 public ResultModel<List<RecordType>> getRecordtypeB();

}
