package com.edaisong.entity.resp;

import java.util.Date;
import java.util.List;

import com.edaisong.entity.common.ResponseBase;
import com.edaisong.entity.OrderChild;;

/**
 * 获取订单详情
 * @author 胡灵波
 * @Date 2015年11月27日 11:44:38
 */
public class OrderTipDetailResp extends ResponseBase{
	  private Integer id;

	    private Double amount;

	    private String createname;

	    private Date createtime;

	    private String modifyname;

	    private Date modifytime;

	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    public Double getAmount() {
	        return amount;
	    }

	    public void setAmount(Double amount) {
	        this.amount = amount;
	    }

	    public String getCreatename() {
	        return createname;
	    }

	    public void setCreatename(String createname) {
	        this.createname = createname == null ? null : createname.trim();
	    }

	    public Date getCreatetime() {
	        return createtime;
	    }

	    public void setCreatetime(Date createtime) {
	        this.createtime = createtime;
	    }

	    public String getModifyname() {
	        return modifyname;
	    }

	    public void setModifyname(String modifyname) {
	        this.modifyname = modifyname == null ? null : modifyname.trim();
	    }

	    public Date getModifytime() {
	        return modifytime;
	    }

	    public void setModifytime(Date modifytime) {
	        this.modifytime = modifytime;
	    }

}
