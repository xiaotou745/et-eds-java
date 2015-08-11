package com.edaisong.admin.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.app.event.ReferenceInsertionEventHandler.referenceInsertExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.api.service.inter.IAdminToolsService;
import com.edaisong.api.service.inter.IBusinessExpressRelationService;
import com.edaisong.api.service.inter.IBusinessGroupService;
import com.edaisong.api.service.inter.IBusinessService;
import com.edaisong.api.service.inter.IBusinessThirdRelationService;
import com.edaisong.api.service.inter.IDeliveryCompanyService;
import com.edaisong.api.service.inter.IGroupService;
import com.edaisong.api.service.inter.IPublicProvinceCityService;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.core.util.PropertyUtils;
import com.edaisong.entity.Business;
import com.edaisong.entity.BusinessExpressRelation;
import com.edaisong.entity.BusinessGroup;
import com.edaisong.entity.BusinessOptionLog;
import com.edaisong.entity.DeliveryCompany;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.domain.AreaModel;
import com.edaisong.entity.domain.BusinessDetailModel;
import com.edaisong.entity.domain.BusinessModel;
import com.edaisong.entity.domain.BusinessModifyModel;
import com.edaisong.entity.domain.BusinessThirdRelationModel;
import com.edaisong.entity.domain.GroupModel;
import com.edaisong.entity.req.PagedBusinessReq;
import com.edaisong.entity.req.GroupReq;

/*
 * 商户管理
 * 赵海龙
 * 2015年7月27
 * */
@Controller
@RequestMapping("business")
public class BusinessController {
	@Autowired
	private IBusinessService iBusinessService;

	@Autowired
	private IPublicProvinceCityService iPublicProvinceCityService;

	@Autowired
	private IBusinessGroupService iBusinessGroupService;

	@Autowired
	private IGroupService iGroupService;

	@Autowired
	private IAdminToolsService adminToolsService;

	@Autowired
	private IBusinessThirdRelationService businessThirdRelationService;

	@Autowired
	private IDeliveryCompanyService deliveryCompanyService;
	
	@Autowired
	private IBusinessExpressRelationService businessExpressRelationService;

	@RequestMapping("list")
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse res) {

		GroupReq groupReq = new GroupReq();
		groupReq.setIsValid(1);
		List<GroupModel> resultList = iGroupService.getGroupList(groupReq);

		int accountID = 0;// 如果管理后台的类型是所有权限就传0，否则传管理后台id

		List<AreaModel> openCityList = iPublicProvinceCityService
				.getOpenCityListByAccountID(accountID);
		List<BusinessGroup> businessGroupListData = iBusinessGroupService
				.getBusinessGroupList();

		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "商户");
		model.addObject("currenttitle", "商户管理");
		model.addObject("groupId", 0);
		model.addObject("groupListData", resultList);
		model.addObject("openCityList", openCityList);
		model.addObject("businessGroupListData", businessGroupListData);
		model.addObject("viewPath", "business/index");
		return model;
	}

	@RequestMapping("selectlist")
	public ModelAndView list(PagedBusinessReq req) {
		PagedResponse<BusinessModel> resp = iBusinessService
				.getBusinessList(req);

		ModelAndView model = new ModelAndView("business/list");
		model.addObject("listData", resp);
		return model;
	}

	@RequestMapping("detail")
	public ModelAndView detail(int businessID) throws Exception{
		BusinessDetailModel detail = iBusinessService
				.getBusinessDetailByID(businessID);
		if (detail==null) {
		  throw new Exception("没找到businessID为"+businessID+"的详细信息");
		}

		String isStarTimeSubsidies = adminToolsService.getConfigValueByKey(
				detail.getBusinessgroupid(), "IsStarTimeSubsidies");
		String isStartOverStoreSubsidies = adminToolsService
				.getConfigValueByKey(detail.getBusinessgroupid(),
						"IsStartOverStoreSubsidies");

		String subsidyConfig = "";
		if (isStartOverStoreSubsidies == "1") {
			subsidyConfig = "全局补贴：跨店抢单奖励";
		}
		if (isStarTimeSubsidies == "1") {
			subsidyConfig = subsidyConfig.isEmpty() ? "全局补贴：动态时间奖励"
					: "全局补贴：跨店抢单奖励和动态时间奖励";
		}
		// 从常量配置中获取
		
		String relativePath = PropertyUtils.getProperty("RelativePath");
		String picHost = PropertyUtils.getProperty("WebApiAddress");
		String parentRelativePath = PropertyUtils.getProperty("ParentRelativePath");
		String fileUploadFolderNameBusiness = PropertyUtils.getProperty("FileUploadFolderNameBusiness");

		String originSize = "_0_0";
		String bigFileName = relativePath + "/nopic.jpg";
		String bigFileNameb = relativePath + "/nopic.jpg";
		String checkPicUrl = relativePath + "/nopic.jpg";
		String businessLicensePic = relativePath + "/nopic.jpg";
		if (detail.getCheckpicurl()!=null&&!detail.getCheckpicurl().isEmpty()) {
			int fileLastDot = detail.getCheckpicurl().lastIndexOf('.');
			String fileHandHouZhui = detail.getCheckpicurl()
					.substring(fileLastDot,detail.getCheckpicurl().length());
			bigFileName = parentRelativePath + "/"
					+ fileUploadFolderNameBusiness
					+ detail.getCheckpicurl().substring(0, fileLastDot)
					+ originSize + fileHandHouZhui;
			checkPicUrl = parentRelativePath + "/"
					+ fileUploadFolderNameBusiness + detail.getCheckpicurl();
		}
		if (detail.getBusinesslicensepic()!=null&&!detail.getBusinesslicensepic().isEmpty()) {
			int fileLastDotb = detail.getBusinesslicensepic().lastIndexOf('.');
			String fileHandHouZhuib = detail.getBusinesslicensepic().substring(fileLastDotb,detail.getBusinesslicensepic().length());
			bigFileNameb = parentRelativePath + "/"
					+ fileUploadFolderNameBusiness
					+ detail.getBusinesslicensepic().substring(0, fileLastDotb)
					+ originSize + fileHandHouZhuib;
			businessLicensePic = parentRelativePath + "/"
					+ fileUploadFolderNameBusiness
					+ detail.getBusinesslicensepic();
		}
		String finalCheckPicUrl = picHost + checkPicUrl;
		String finalBigCheckPicUrl = picHost + bigFileName;

		String finalBusinessPicUrl = picHost + businessLicensePic;
		String finalBigBusinessPicUrl = picHost + bigFileNameb;

		int accountID = 0;// 如果管理后台的类型是所有权限就传0，否则传管理后台id
		List<AreaModel> openCityList = iPublicProvinceCityService
				.getOpenCityListByAccountID(accountID);

		List<AreaModel> openAreaList = iPublicProvinceCityService
				.getOpenCityDistrict(ParseHelper.ToInt(detail.getCityid()));

		List<BusinessThirdRelationModel> businessThirdRelation = businessThirdRelationService
				.getListByBusinessID(ParseHelper.ToInt(detail.getId()));
		List<BusinessOptionLog> businessOpLog = iBusinessService
				.getOpLogByBusinessID(ParseHelper.ToInt(detail.getId()));
		List<DeliveryCompany> deliveryCompany = deliveryCompanyService
				.getDeliveryCompanyList();
		List<BusinessGroup> businessGroupListData = iBusinessGroupService
				.getBusinessGroupList();

		GroupReq groupReq = new GroupReq();
		groupReq.setIsValid(1);
		List<GroupModel> groupListData = iGroupService.getGroupList(groupReq);
		
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "商户管理");
		model.addObject("currenttitle", "修改商铺信息");
		model.addObject("subsidyConfig", subsidyConfig);

		model.addObject("finalCheckPicUrl", finalCheckPicUrl);
		model.addObject("finalBigCheckPicUrl", finalBigCheckPicUrl);
		model.addObject("finalBusinessPicUrl", finalBusinessPicUrl);
		model.addObject("finalBigBusinessPicUrl", finalBigBusinessPicUrl);

		model.addObject("openCityList", openCityList);
		model.addObject("openAreaList", openAreaList);
		model.addObject("detail", detail);
		model.addObject("businessThirdRelation", businessThirdRelation);
		model.addObject("deliveryCompany", deliveryCompany);
		model.addObject("businessOpLog", businessOpLog);
		model.addObject("businessGroupListData", businessGroupListData);
		model.addObject("groupListData", groupListData);
		model.addObject("viewPath", "business/detail");
		return model;
	}
	@RequestMapping("businessexpress")
	@ResponseBody
	public List<BusinessExpressRelation> getExpressRelation(int businessID) {
		List<BusinessExpressRelation> result=businessExpressRelationService.selectByBusinessID(businessID);
		return result;
	}
	@RequestMapping("modifybusiness")
	@ResponseBody
	public int modifyBusiness(BusinessModifyModel detail) {
		detail.setOptId("123");
		detail.setOptName("admin");
		return iBusinessService.modifyBusiness(detail);
	}
	@RequestMapping("getCityDistrict")
	@ResponseBody
	public List<AreaModel> getCityDistrict(int cityID) {
		return iPublicProvinceCityService.getOpenCityDistrict(cityID);
	}
	@RequestMapping("modifyexpress")
	@ResponseBody
	public int modifyExpress(int busiId,String deliveryCompanyList) {
		if (deliveryCompanyList==null||deliveryCompanyList.isEmpty()) {
			return -1;
		}
		List<BusinessExpressRelation> listData=new ArrayList<>();
		String [] expressList= deliveryCompanyList.split(";");
		for (String express : expressList) {
			if (!express.isEmpty()) {
				String [] itemsStrings= express.split(",");
				BusinessExpressRelation item=new BusinessExpressRelation();
				item.setExpressid(ParseHelper.ToInt(itemsStrings[0]));
				item.setIsenable(ParseHelper.ToShort(itemsStrings[1]));
				item.setBusinessid(busiId);
				item.setCreateby("admin");
				item.setUpdateby("admin");
				listData.add(item);
			}
		}

		return iBusinessService.modifyExpress(listData);
	}
	@RequestMapping("audit")
	@ResponseBody
	 public int businessAudit(int businessID,int status)
     {
         return iBusinessService.updateAuditStatus(businessID, status);
     }
}
