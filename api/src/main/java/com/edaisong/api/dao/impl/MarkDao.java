package com.edaisong.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IMarkDao;
import com.edaisong.entity.Mark;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.req.MarkEditReq;
import com.edaisong.entity.req.MarkReq;
@Repository
public class MarkDao extends DaoBase implements IMarkDao {
	/**
	* @Des 获取标签列表（分页）  
	* @Author WangXuDan
	* @Date 2015年9月18日10:19:28
	* @Return
	*/
	@Override
	public PagedResponse<Mark> getMarkList(MarkReq par) {
		PagedResponse<Mark> resp=new PagedResponse<Mark>();
		resp = getReadOnlySqlSessionUtil().selectPageList(
				"com.edaisong.api.dao.inter.IMarkDao.getMarkList", par);
		return resp;
	}
	/**
	* @Des  根据标签名称判断是否存在
	* @Author WangXuDan
	* @Date 2015年9月18日10:19:28
	* @Return
	*/
	@Override
	public boolean CheckExist(String tagname) {
		int result = getReadOnlySqlSessionUtil().selectOne("com.edaisong.api.dao.inter.IMarkDao.checkExist", tagname);
		return result>0 ?true:false;
		 
	}
	/**
	* @Des 编辑标签（新增/修改） 
	* @Author WangXuDan
	* @Date 2015年9月19日15:15:05
	* @Return
	*/
	@Override
	public boolean editTag(MarkEditReq markEditReq) {
		int result = getReadOnlySqlSessionUtil().update("com.edaisong.api.dao.inter.IMarkDao.editMark", markEditReq.getMarkName());
		return result>0 ?true:false;
	}

}
