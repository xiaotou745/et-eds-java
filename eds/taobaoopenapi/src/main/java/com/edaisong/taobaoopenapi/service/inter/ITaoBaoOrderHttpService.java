package com.edaisong.taobaoopenapi.service.inter;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.edaisong.entity.common.HttpResultModel;
import com.edaisong.entity.domain.BusinessDetailModel;
import com.edaisong.entity.taobao.TaoBaoResponseBase;
import com.taobao.api.request.WaimaiDeliveryConfirmRequest;
import com.taobao.api.request.WaimaiDeliveryLocationUpdateRequest;
import com.taobao.api.request.WaimaiDeliveryPickupRequest;
import com.taobao.api.request.WaimaiDeliveryUpdateRequest;
import com.taobao.api.request.WaimaiOrderAckRequest;

/**
 * 淘宝订单
 * 
 * @author CaoHeYang
 * @date 20151113
 */
@Path("/taobaoorder")
@Consumes("application/json")
@Produces("application/json; charset=utf-8")
public interface ITaoBaoOrderHttpService {
	/**
	 * 确认接单接口(API)
	 * @author CaoHeYang
	 * @param req
	 * @return
	 */
	@POST
	@Path("/ask")
	public TaoBaoResponseBase ask(WaimaiOrderAckRequest req);

	/**
	 * 更新配送员信息接口（API）
	 * @author CaoHeYang
	 * @param req
	 * @return
	 */
	@POST
	@Path("/update")
	public TaoBaoResponseBase update(WaimaiDeliveryUpdateRequest req);

	/**
	 * 取件（API）
	 * @author CaoHeYang
	 * @param req
	 * @return
	 */
	@POST
	@Path("/pickup")
	public TaoBaoResponseBase pickUp(WaimaiDeliveryPickupRequest  req);

	/**
	 * 妥投（API）
	 * @author CaoHeYang
	 * @param req
	 * @return
	 */
	@POST
	@Path("/confirm")
	public TaoBaoResponseBase confirm(WaimaiDeliveryConfirmRequest  req);

	/**
	 * 更新配送员位置信息（API）
	 * @author CaoHeYang
	 * @param req
	 * @return
	 */
	@POST
	@Path("/locationupdate")
	public TaoBaoResponseBase locationUpdate(WaimaiDeliveryLocationUpdateRequest  req);
	
}
