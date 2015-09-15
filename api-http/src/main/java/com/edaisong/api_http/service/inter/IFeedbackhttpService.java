package com.edaisong.api_http.service.inter;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.common.EnumRecord;
import com.edaisong.entity.resp.FeedbackResp;

/**
 * 意见反馈
 * @author 胡灵波
 * @date 2015年9月10日 18:38:48
 */
@Path("/feedback")
@Consumes("application/json")//当前方法接收的参数类型
@Produces("application/json; charset=utf-8")//当前类的所有方法都返回json格式的数据
public interface IFeedbackhttpService {	             
	/**
	 * B端门店增加意见反馈
	 * @author 胡灵波
	 * @date 2015年9月10日 18:39:58
	 * @param data 
	 * @return
	 */
	@POST
	@Path("/feedbackb")
	public HttpResultModel<FeedbackResp> feedbackB(String data);	 
	
	/**
	 * B端骑士增加意见反馈
	 * @author 胡灵波
	 * @date 2015年9月10日 20:39:29
	 * @param data 
	 * @return
	 */
	@POST
	@Path("/feedbackc")
	public HttpResultModel<FeedbackResp> feedbackC(String data);	 

	/**
	 * 意见反馈类型列表
	 * 
	 * @author 胡灵波
	 * @date 2015年9月10日 21:45:07
	 * @param data
	 * @return
	 */
	@POST
	@Path("/getfeedbacktype")
	public List<EnumRecord> getFeedbackType();
}
