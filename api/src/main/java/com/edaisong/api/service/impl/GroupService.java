package com.edaisong.api.service.impl;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.edaisong.api.dal.dao.inter.IGroupDao;
import com.edaisong.api.service.inter.IGroupService;
import com.edaisong.entity.Group;
import com.edaisong.entity.domain.GroupModel;
import com.edaisong.entity.req.GroupReq;

import java.util.Date;
@Service
public class GroupService implements IGroupService {

	@Autowired
	private IGroupDao dao;
	@Override
	public List<GroupModel> getGroupListByID(GroupReq req) {
		return dao.getGroupListByID(
				req.getId());
	}

	@Override
	public List<GroupModel> getGroupList(GroupReq req) {
		return dao.getGroupList(
				req);
	}

	//@Transactional(rollbackFor = Exception.class,timeout=30)
	@Override
	public int add(Group record)  {
		    // Throwable
//			record.setCreatetime(new Date());
//				record.setModifyname("");
//				record.setCreatetime(new Date());
//				record.setIsvalid((byte) 1);
//				record.setIsmodifybind(-1);		
//				dao.insert(record);
//
//		 
//			record.setCreatetime(new Date());
//			record.setModifyname("1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
//			record.setCreatetime(new Date());
//			record.setIsvalid((byte) 1);
//			record.setIsmodifybind(-1);					
//	 return dao.insert(record);
		
		
		
//		EnumTest u= EnumTest.THU.getClass().get;
//		u.getDeclaringClass();			
		 
//		Class<?> c = this.getClass();				
//		Method m = c.getMethod("update",new Class[0]);
		
		 return 1;
	}
	
	 @MyAnnotation(name="ABCupdateABC") 
	@Override
	public int  update(Group record) 
	{
		return dao.updateByPrimaryKeySelective(record);
	}
	
	 @Retention(RetentionPolicy.RUNTIME)
	 @Target({ElementType.FIELD,ElementType.METHOD, ElementType.TYPE})
	  public @interface MyAnnotation {	
	     String name() default "我是林计钦"; //为属性提供默认值	    
	 }
	 
	 @MyAnnotation(name="ABC") 
	 public enum EnumTest {
		    MON, 
		    @MyAnnotation(name="aaa") 
		    TUE, 
		    WED, 
		    THU, 
		    FRI, 
		    SAT, 
		    SUN;

		}
	 
   
}
