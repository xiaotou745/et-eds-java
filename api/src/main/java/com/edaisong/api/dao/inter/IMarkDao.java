package com.edaisong.api.dao.inter;

import com.edaisong.entity.Mark;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.MarkReq;

public interface IMarkDao {
	 /**
	    * @Des 查询标签列表（分页）  
	    * @Author WangXuDan
	    * @Date 2015年9月18日10:19:28
	    * @Return
	    */
	    PagedResponse<Mark> getMarkList(MarkReq par);
	    /**
		* @Des  根据标签名称判断是否存在
		* @Author WangXuDan
		* @Date 2015年9月18日10:19:28
		* @Return
		*/
	   // boolean CheckExist(String tagname);
	    /**
		* @Des 编辑标签（新增/修改） 
		* @Author WangXuDan
		* @Date 2015年9月19日15:15:05
		* @Return
		*/
		//boolean editTag(TagEditReq tagEditReq);

}
