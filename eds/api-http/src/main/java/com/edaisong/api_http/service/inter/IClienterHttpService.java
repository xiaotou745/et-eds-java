package com.edaisong.api_http.service.inter;

import javax.ws.rs.Consumes;  
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces; 

import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.req.ClienterBindOptionReq;
import com.edaisong.entity.req.ModifyPushShanSongOrderSetReq;
import com.edaisong.entity.req.ModifyVehicleReq;
import com.edaisong.entity.req.MyBusinessReq;
import com.edaisong.entity.req.UserStatusReq;
import com.edaisong.entity.resp.MyBusinessResp;

@Path("/clienter")
@Consumes("application/json")//当前方法接收的参数类型
@Produces("application/json; charset=utf-8")//当前类的所有方法都返回json格式的数据
public interface IClienterHttpService {
	/*
	 * 获取我的商户
	 * wangchao
	 */
	@POST
	@Path("/getmybusiness")
	public HttpResultModel<MyBusinessResp> getMyBusiness(MyBusinessReq myBusinessReq);
	
	/*
	 * 解除绑定我的商户
	 * wangchao
	 */
	@POST
	@Path("/unbindmybusiness")
	public HttpResultModel<Object> unbindMyBusiness(ClienterBindOptionReq clienterBindOptionReq);
	
	/*
	 * 获取骑士用户信息 闪送模式  wangchao
	 */
	@POST
	@Path("/getuserstatus")
	public HttpResultModel<Object> getUserStatus(UserStatusReq req);
	
	/*
	 * 修改交通工具  闪送模式  wangchao
	 */
	@POST
	@Path("/modifyvehicle")
	HttpResultModel<Object> modifyVehicle(ModifyVehicleReq req);
	 
	/**
	 * 修改 是否 接收 里程计算的推单
	 * @author CaoHeYang
	 * @date 20160108
	 * @param req
	 * @return
	 */
	@POST
	@Path("/modifypushshansongorderset")
	HttpResultModel<Object> modifyPushShanSongOrderSet(ModifyPushShanSongOrderSetReq req);
	
}
