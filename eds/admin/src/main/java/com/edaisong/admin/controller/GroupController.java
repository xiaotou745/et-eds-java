package com.edaisong.admin.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.admin.common.UserContext;
import com.edaisong.api.service.inter.IGroupApiConfigService;
import com.edaisong.api.service.inter.IGroupService;
import com.edaisong.core.enums.returnenums.GroupAddConfigReturnEnum;
import com.edaisong.core.enums.returnenums.GroupAddReturnEnum;
import com.edaisong.core.enums.returnenums.GroupEditReturnEnum;
import com.edaisong.core.enums.returnenums.GroupUpdateStatusReturnEnum;
import com.edaisong.core.util.GuidHelper;
import com.edaisong.entity.Group;
import com.edaisong.entity.GroupApiConfig;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.common.ResponseBase;
import com.edaisong.entity.domain.GroupApiConfigModel;
import com.edaisong.entity.domain.GroupModel;
import com.edaisong.entity.req.GroupReq;
import com.edaisong.entity.req.PagedGroupReq;


@Controller
@RequestMapping("group")
public class GroupController {
	 @Autowired
	 private IGroupService  groupService;
	 @Autowired
	 private IGroupApiConfigService groupApiConfigService;
	 
	 /**
	  * 第三方平台管理
	  * @update caoheyang
	  * @date 20160115
	  * @param request
	  * @param response
	  * @return
	  */
	@RequestMapping("list")
	public ModelAndView list(){
		GroupReq req=new GroupReq();	
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "管理员");
		model.addObject("currenttitle", "第三方平台管理");
		model.addObject("viewPath", "group/list");
		return model;		
	}
	
	/**
	 * 查询第三方集团  异步
	 * @author CaoHeYang
	 * @date 20160118
	 * @param req
	 * @return
	 */
	@RequestMapping("selectlist")
	@ResponseBody
	public ModelAndView selectlist(PagedGroupReq req){
		PagedResponse<GroupApiConfigModel> resultList =groupService.getGroupListByPage(req);				
		ModelAndView model = new ModelAndView("group/grouplistdo");
		model.addObject("listData", resultList);
		return model;		
	}	
	
	
	/**
	 * 
	 * @param group
	 * @return
	 */
	@RequestMapping(value="addgroup",method = RequestMethod.POST)		
	@ResponseBody
	public ResponseBase addgroup( Group group,HttpServletRequest request){		
		 if (group.getGroupname()==null||group.getGroupname().isEmpty())
         {
			 return new ResponseBase().setMessage(GroupAddReturnEnum.GroupNameError.desc())
						.setResponseCode(GroupAddReturnEnum.GroupNameError.value());
         } 
		 group.setCreatename(UserContext.getCurrentContext(request).getUserName());
         Boolean result = groupService.hasExistsGroup(group);
         if (result)
         {
        	 return new ResponseBase().setMessage(GroupAddReturnEnum.GroupExists.desc())
						.setResponseCode(GroupAddReturnEnum.GroupExists.value());
         }
         group.setIsvalid((byte)1);
         int res = groupService.addGroup( group);
         return res>0?new ResponseBase(): new ResponseBase().setMessage(GroupAddReturnEnum.ServiceError.desc())
					.setResponseCode(GroupAddReturnEnum.ServiceError.value()) ;
	}
	
	/**
	 * 修改第三方平台名称
	 * @author CaoHeYang
	 * @date 20160119
	 * @param group
	 * @return
	 */
	@RequestMapping(value="updategroup",method = RequestMethod.POST)
	@ResponseBody
	public ResponseBase updategroup(Group group,HttpServletRequest request){
		 if (group.getGroupname()==null||group.getGroupname().isEmpty())
         {
			 return new ResponseBase().setMessage(GroupEditReturnEnum.GroupNameError.desc())
						.setResponseCode(GroupEditReturnEnum.GroupNameError.value());
         } 
		 group.setModifyname(UserContext.getCurrentContext(request).getUserName());
		 int res= groupService.update(group);		
		 return res>0?new ResponseBase(): new ResponseBase().setMessage(GroupEditReturnEnum.ServiceError.desc())
					.setResponseCode(GroupEditReturnEnum.ServiceError.value()) ;
	}
	
    /**
     * 更新启用状态
     * @author CaoHeYang
     * @date 20160118
     * @param record
     * @return
     */
	@RequestMapping(value="updatestatus",method= {RequestMethod.POST})
	@ResponseBody
	public ResponseBase updatestatus(Group record ){
		if (record.getId() == 0)
        {
			return new ResponseBase().setMessage(GroupUpdateStatusReturnEnum.GroupIdError.desc())
					.setResponseCode(GroupUpdateStatusReturnEnum.GroupIdError.value());
        }
        Boolean res = groupService.updateGroupStatus(record);	
		return res?   new ResponseBase(): new ResponseBase().setMessage(GroupUpdateStatusReturnEnum.Error.desc())
				.setResponseCode(GroupUpdateStatusReturnEnum.Error.value());
	}	

	/**
	 * 
	 * @param groupapiconfig
	 * @return
	 */
	@RequestMapping(value="addgroupapiconfig",method= {RequestMethod.POST})
	@ResponseBody
	public ResponseBase addGroupApiConfig(GroupApiConfig groupapiconfig){
		if (groupapiconfig.getGroupid()== 0)
        {
			return new ResponseBase().setMessage(GroupAddConfigReturnEnum.GroupIdError.desc())
					.setResponseCode(GroupAddConfigReturnEnum.GroupIdError.value());
        }
        if (groupapiconfig.getAppkey()==null||groupapiconfig.getAppkey().isEmpty())
        {
        	return new ResponseBase().setMessage(GroupAddConfigReturnEnum.AppKeyError.desc())
					.setResponseCode(GroupAddConfigReturnEnum.AppKeyError.value());
        }
        if (groupapiconfig.getAppversion()==null||groupapiconfig.getAppversion().isEmpty())
        {
        	groupapiconfig.setAppversion("1.0");
        }
		groupapiconfig.setAppsecret(GuidHelper.guidNoSepToUpperCase());
     	int res= groupApiConfigService.add(groupapiconfig);	
	    return res>0?   new ResponseBase(): new ResponseBase().setMessage(GroupAddConfigReturnEnum.Error.desc())
 			.setResponseCode(GroupAddConfigReturnEnum.Error.value());
	}
	
}
