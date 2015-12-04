package com.edaisong.taobaoopenapi.service.inter;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.edaisong.entity.taobao.TaoBaoResponseBase;
import com.taobao.api.request.WaimaiDeliveryConfirmRequest;
import com.taobao.api.request.WaimaiDeliveryLocationUpdateRequest;
import com.taobao.api.request.WaimaiDeliveryPickupRequest;
import com.taobao.api.request.WaimaiDeliveryUpdateRequest;
import com.taobao.api.request.WaimaiOrderAckRequest;
import com.taobao.top.link.LinkException;

/**
 * tmc
 * 
 * @author CaoHeYang
 * @date 20151113
 */
public interface ITmcControlHttpService {
	/**
	 * tmc 获取通知
	 * @throws LinkException 
	 */
	public  void main() throws LinkException;
}
