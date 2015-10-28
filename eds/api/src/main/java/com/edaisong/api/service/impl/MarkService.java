package com.edaisong.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edaisong.api.dao.inter.IMarkDao;
import com.edaisong.api.service.inter.IMarkService;
import com.edaisong.entity.Mark;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.common.ResponseBase;
import com.edaisong.entity.req.MarkEditReq;
import com.edaisong.entity.req.MarkReq;
@Service
public class MarkService implements IMarkService {
	@Autowired
    private IMarkDao markDao;
	/**
	* @Des 获取标签列表 
	* @Author WangXuDan
	* @Date 2015年9月19日15:16:50
	* @Return
	*/
	@Override
	public PagedResponse<Mark> getMarkList(MarkReq par) {
		return markDao.getMarkList(par);
	}
	/**
	* @Des 编辑标签（新增/修改） 
	* @Author WangXuDan
	* @Date 2015年9月19日15:15:05
	* @Return
	*/
	@Override
	public ResponseBase editMark(MarkEditReq markEditReq) {
		ResponseBase responseBase = new ResponseBase();
		responseBase.setResponseCode(-1);
		if(markEditReq.getOperateType()!=2)
		{
			if(markDao.CheckExist(markEditReq.getTagName()))
			{
				responseBase.setMessage("此标签已存在！");
				return responseBase;
			}
		}
		if(!markDao.editTag(markEditReq)){ 
			responseBase.setMessage("编辑失败！");
			return responseBase;
		}
		responseBase.setResponseCode(1);
		responseBase.setMessage("编辑成功！");
		return responseBase;
	}
	/**
	* @Des 修改标签状态 
	* @Author WangXuDan
	* @Date 2015年9月20日19:57:19
	* @Return
	*/
	@Override
	public ResponseBase modifyMarkStatus(MarkEditReq markEditReq) {
		ResponseBase responseBase = new ResponseBase();
		responseBase.setResponseCode(-1);
		if(!markDao.modifyMarkStatus(markEditReq)){ 
			responseBase.setMessage("标签状态更新失败！");
			return responseBase;
		}
		responseBase.setResponseCode(1);
		responseBase.setMessage("标签状态更新成功！");
		return responseBase;
	}

}
