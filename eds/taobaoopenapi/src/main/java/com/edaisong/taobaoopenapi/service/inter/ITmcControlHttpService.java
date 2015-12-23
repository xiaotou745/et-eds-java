package com.edaisong.taobaoopenapi.service.inter;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.edaisong.entity.taobao.TaoBaoResponseBase;
import com.taobao.top.link.LinkException;

/**
 * tmc
 * 
 * @author CaoHeYang
 * @date 20151113
 */
@Path("/taobao")
@Consumes("application/json")
@Produces("application/json; charset=utf-8")
public interface ITmcControlHttpService {
	/**
	 * tmc 获取通知
	 * @throws LinkException 
	 */
	@POST
	@Path("/main")
	public  TaoBaoResponseBase main() throws Exception;
}
