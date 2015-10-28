package com.edaisong.api.service.inter;

import com.edaisong.entity.Mark;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.common.ResponseBase;
import com.edaisong.entity.req.MarkEditReq;
import com.edaisong.entity.req.MarkReq;

public interface IMarkService {
	
	/**
	* @Des 获取标签列表 
	* @Author WangXuDan
	* @Date 2015年9月19日15:16:50
	* @Return
	*/
    PagedResponse<Mark> getMarkList(MarkReq par);
    /**
	* @Des 编辑标签（新增/修改） 
	* @Author WangXuDan
	* @Date 2015年9月19日15:15:05
	* @Return
	*/
	ResponseBase editMark(MarkEditReq markEditReq);
	/**
	* @Des 修改标签状态 
	* @Author WangXuDan
	* @Date 2015年9月20日19:57:19
	* @Return
	*/
	ResponseBase modifyMarkStatus(MarkEditReq markEditReq);

}
