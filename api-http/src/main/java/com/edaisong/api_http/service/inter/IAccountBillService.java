package com.edaisong.api_http.service.inter;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.edaisong.api_http.entity.ResultModel;
import com.edaisong.entity.AccountBillResultModel;
import com.edaisong.entity.req.AccountBillBReq;
/*
 * 商户 骑士对账单接口
 * 茹化肖
 * 2015年9月10日10:00:54
 * */
@Path("/accountbill")
@Consumes("application/json")//当前方法接收的参数类型
@Produces("application/json")//当前类的所有方法都返回json格式的数据
public interface IAccountBillService {
	/**
	 * B端获取月账单
	 * @author 茹化肖
	 * @date 2015年9月10日12:54:55
	 * @return
	 */
	@POST
	@Path("/getbilllistb")
	 public ResultModel<AccountBillResultModel> getBillListB(AccountBillBReq par);

}
