package com.edaisong.toolsentity.req;

/**
 *
 * @author  pengyi 
 * @date 2015年9月11日 上午10:03:42
 * @version 1.0
 * @parameter
 * @since
 */
public class CardModifyAlipayReq extends CardBindAlipayReq{
	private int id;//待修改的主键id

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
