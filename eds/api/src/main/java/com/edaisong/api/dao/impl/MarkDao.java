package com.edaisong.api.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.edaisong.api.common.DaoBase;
import com.edaisong.api.dao.inter.IMarkDao;
import com.edaisong.entity.Mark;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.TagRelationModel;
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
				"IMarkDao.getMarkList", par);
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
		int result = getReadOnlySqlSessionUtil().selectOne("IMarkDao.checkExist", tagname);
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
		int result = getMasterSqlSessionUtil().update("IMarkDao.editMark", markEditReq);
		return result>0 ?true:false;
	}
	/**
	* @Des 修改标签状态 
	* @Author WangXuDan
	* @Date 2015年9月20日19:57:19
	* @Return
	*/
	@Override
	public boolean modifyMarkStatus(MarkEditReq markEditReq) {
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("isenable", markEditReq.getIsenable());
		map.put("id", markEditReq.getId());
		map.put("operator", markEditReq.getOperator());
		int result = getMasterSqlSessionUtil().update("IMarkDao.modifyMarkStatus", markEditReq);
		return result>0 ?true:false;
	}
	/**
	 * 获取所有的商家标签
	 * 茹化肖
	 * 2015年11月10日16:30:59
	 */
	@Override
	public List<Mark> getBusMarksList(int userId) {
		return getReadOnlySqlSessionUtil().selectList("IMarkDao.getBusMarksList",userId);
	}
	/***
	 * 编辑商户标签
	 * 茹化肖
	 * 2015年11月11日11:20:14
	 */
	@Override
	public int modifyBusinessTags(TagRelationModel model) {
		return getMasterSqlSessionUtil().update("IMarkDao.modifyBusinessTags", model);
	}

}
