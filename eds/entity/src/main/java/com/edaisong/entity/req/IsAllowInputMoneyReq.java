package com.edaisong.entity.req;

/**
 *  获取商家是否需要录入金额才可以发单  入参
 * @author CaoHeYang
 * @date 20151030
 */
public class IsAllowInputMoneyReq {
	private Long businessId;

	/**
	 *  商家id
	 * @return
	 */
	public Long getBusinessId() {
		return businessId;
	}
  /**
   * 商家id
   * @param businessId
   */
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

}
