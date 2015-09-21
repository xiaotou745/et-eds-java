package com.edaisong.entity.domain;

public class GroupBusinessBalance {
private Integer moneytype;
private Double amount;
/**
 * 0是集团账户余额，0是集团账户累计充值金额
 * @author hailongzhao
 * @date 20150921
 * @return
 */
public Integer getMoneytype() {
	return moneytype;
}
/**
 * 0是集团账户余额，0是集团账户累计充值金额
 * @author hailongzhao
 * @date 20150921
 * @return
 */
public void setMoneytype(Integer moneytype) {
	this.moneytype = moneytype;
}
/**
 * 金额
 * @author hailongzhao
 * @date 20150921
 * @return
 */
public Double getAmount() {
	return amount;
}
/**
 * 金额
 * @author hailongzhao
 * @date 20150921
 * @return
 */
public void setAmount(Double amount) {
	this.amount = amount;
}

}
