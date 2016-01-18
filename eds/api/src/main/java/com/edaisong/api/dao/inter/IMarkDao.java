package com.edaisong.api.dao.inter;

import java.util.List;

import com.edaisong.entity.Mark;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.TagRelationModel;
import com.edaisong.entity.req.MarkEditReq;
import com.edaisong.entity.req.MarkReq;
import com.edaisong.entity.req.ModifyTagReq;

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
	    boolean CheckExist(String tagname);
	    /**
		* @Des 编辑标签（新增/修改） 
		* @Author WangXuDan
		* @Date 2015年9月19日15:15:05
		* @Return
		*/
		 boolean editTag(MarkEditReq markEditReq);
		/**
		* @Des 修改标签状态 
		* @Author WangXuDan
		* @Date 2015年9月20日19:57:19
		* @Return
		*/
		 boolean modifyMarkStatus(MarkEditReq markEditReq);
		 /**
		  * 获取所有的商家标签
		  * 茹化肖
		  * 2015年11月10日16:30:34
		  * @return
		  */
		 List<Mark> getBusMarksList(int userId);
		 /***
		  * 编辑商家标签
		  * 茹化肖
		  * 2015年11月11日11:19:33
		  * @param model
		  * @return
		  */
		 int modifyBusinessTags(TagRelationModel model);
		 
		/**
		 * 通过类型获取标签
		 * @param type
		 * @return
		 */
		 List<Mark> getMarksListByType(int type);

}
