package com.edaisong.taobaoopenapi.service.inter;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import com.edaisong.entity.taobao.TaoBaoResponseBase;
import com.edaisong.entity.taobao.req.TaoBaoAsk;
import com.edaisong.entity.taobao.req.TaoBaoConfirm;
import com.edaisong.entity.taobao.req.TaoBaoLocationUpdate;
import com.edaisong.entity.taobao.req.TaoBaoPickUp;
import com.edaisong.entity.taobao.req.TaoBaoUpdate;

/**
 * 淘宝订单
 * 
 * @author CaoHeYang
 * @date 20151113
 */
@Path("/order")
@Consumes("application/json")
// 当前方法接收的参数类型
@Produces("application/json; charset=utf-8")
// 当前类的所有方法都返回json格式的数据
public interface ITaoBaoOrderHttpService {
	/**
	 * 确认接单接口(API)
	 * 
	 * @param req
	 * @return
	 */
	@POST
	@Path("/ask")
	public TaoBaoResponseBase ask(TaoBaoAsk req);

	/**
	 * 更新配送员信息接口（API）
	 * 
	 * @param req
	 * @return
	 */
	@POST
	@Path("update")
	public TaoBaoResponseBase update(TaoBaoUpdate req);

	/**
	 * 取件（API）
	 * 
	 * @param req
	 * @return
	 */
	@POST
	@Path("/pickup")
	public TaoBaoResponseBase pickUp(TaoBaoPickUp req);

	/**
	 * 妥投（API）
	 * 
	 * @param req
	 * @return
	 */
	@POST
	@Path("/confirm")
	public TaoBaoResponseBase confirm(TaoBaoConfirm req);

	/**
	 * 更新配送员位置信息（API）
	 * 
	 * @param req
	 * @return
	 */
	@POST
	@Path("/locationupdate")
	public TaoBaoResponseBase locationUpdate(TaoBaoLocationUpdate req);
}
