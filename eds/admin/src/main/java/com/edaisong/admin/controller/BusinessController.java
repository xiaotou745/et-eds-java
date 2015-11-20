package com.edaisong.admin.controller;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

import javassist.compiler.ast.NewExpr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.edaisong.admin.common.UserContext;
import com.edaisong.api.service.inter.IBusinessBalanceRecordService;
import com.edaisong.api.service.inter.IBusinessClienterRelationService;
import com.edaisong.api.service.inter.IBusinessExpressRelationService;
import com.edaisong.api.service.inter.IBusinessFinanceAccountService;
import com.edaisong.api.service.inter.IBusinessGroupService;
import com.edaisong.api.service.inter.IBusinessService;
import com.edaisong.api.service.inter.IBusinessThirdRelationService;
import com.edaisong.api.service.inter.IClienterBindOptionLogService;
import com.edaisong.api.service.inter.IClienterService;
import com.edaisong.api.service.inter.IDeliveryCompanyService;
import com.edaisong.api.service.inter.IGlobalConfigService;
import com.edaisong.api.service.inter.IGroupService;
import com.edaisong.api.service.inter.IMarkService;
import com.edaisong.api.service.inter.IPublicProvinceCityService;
import com.edaisong.core.consts.GlobalSettings;
import com.edaisong.core.enums.BusinessClienterRelationAuditStatus;
import com.edaisong.core.enums.ClienterGradeType;
import com.edaisong.core.util.ExcelUtils;
import com.edaisong.core.util.JsonUtil;
import com.edaisong.core.util.ParseHelper;
import com.edaisong.core.util.PropertyUtils;
import com.edaisong.core.util.StringUtils;
import com.edaisong.entity.BusinessBalanceRecord;
import com.edaisong.entity.BusinessClienterRelation;
import com.edaisong.entity.BusinessExpressRelation;
import com.edaisong.entity.BusinessFinanceAccount;
import com.edaisong.entity.BusinessGroup;
import com.edaisong.entity.BusinessOptionLog;
import com.edaisong.entity.Clienter;
import com.edaisong.entity.ClienterBindOptionLog;
import com.edaisong.entity.DeliveryCompany;
import com.edaisong.entity.Mark;
import com.edaisong.entity.common.PagedResponse;
import com.edaisong.entity.common.ResponseBase;
import com.edaisong.entity.domain.AreaModel;
import com.edaisong.entity.domain.BusinesRechargeModel;
import com.edaisong.entity.domain.BusinessBalanceRecordModel;
import com.edaisong.entity.domain.BusinessBindClienter;
import com.edaisong.entity.domain.BusinessClienterRelationModel;
import com.edaisong.entity.domain.BusinessDetailModel;
import com.edaisong.entity.domain.BusinessModel;
import com.edaisong.entity.domain.BusinessModifyModel;
import com.edaisong.entity.domain.BusinessRechargeDetailModel;
import com.edaisong.entity.domain.BusinessThirdRelationModel;
import com.edaisong.entity.domain.ClienterBindInfoModel;
import com.edaisong.entity.domain.GroupModel;
import com.edaisong.entity.domain.ImportClienterInfo;
import com.edaisong.entity.req.BusinessClienterRelationReq;
import com.edaisong.entity.req.BussinessBalanceQueryReq;
import com.edaisong.entity.req.ClienterBindOptionReq;
import com.edaisong.entity.req.GroupReq;
import com.edaisong.entity.req.ModifyTagReq;
import com.edaisong.entity.req.PagedBusinessReq;
import com.edaisong.entity.req.PagedClienterSearchReq;
import com.edaisong.entity.req.PagedCustomerSearchReq;
import com.edaisong.entity.req.PagedTransDetailReq;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.IntData;

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
	private IGlobalConfigService adminToolsService;

	@Autowired
	private IBusinessThirdRelationService businessThirdRelationService;

	@Autowired
	private IDeliveryCompanyService deliveryCompanyService;

	@Autowired
	private IBusinessExpressRelationService businessExpressRelationService;

	@Autowired
	private IBusinessFinanceAccountService businessFinanceAccountService;

	@Autowired
	private IBusinessBalanceRecordService businessBalanceRecordService;

	@Autowired
	private IBusinessClienterRelationService businessClienterRelationService;

	@Autowired
	private IClienterService clienterService;
	@Autowired
	private IClienterBindOptionLogService clienterBindOptionLogService;
	
	@Autowired
	private IMarkService markService;


	@RequestMapping("list")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse res) {
		String bNameString= request.getParameter("businessName");
		GroupReq groupReq = new GroupReq();
		groupReq.setIsValid(1);
		List<GroupModel> resultList = iGroupService.getGroupList(groupReq);

		int accountID = 0;// 如果管理后台的类型是所有权限就传0，否则传管理后台id
		//获取城市
		List<AreaModel> openCityList = iPublicProvinceCityService.getOpenCityListByAccountID(accountID);
		List<BusinessGroup> businessGroupListData = iBusinessGroupService.getBusinessGroupList();

		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "商户");
		model.addObject("currenttitle", "商户管理");
		model.addObject("groupId", 0);
		model.addObject("groupListData", resultList);
		model.addObject("openCityList", openCityList);
		model.addObject("businessGroupListData", businessGroupListData);
		model.addObject("businessName", bNameString);
		model.addObject("viewPath", "business/list");
		return model;
	}

	@RequestMapping("selectlist")
	public ModelAndView list(PagedBusinessReq req) {
		PagedResponse<BusinessModel> resp = iBusinessService.getBusinessList(req);

		ModelAndView model = new ModelAndView("business/listdo");
		model.addObject("listData", resp);
		return model;
	}

	@RequestMapping("detail")
	public ModelAndView detail(int businessID) throws Exception {
		BusinessDetailModel detail = iBusinessService.getBusinessDetailByID(businessID);
		if (detail == null) {
			throw new Exception("没找到businessID为" + businessID + "的详细信息");
		}

		String isStarTimeSubsidies = adminToolsService.getConfigValueByKey(detail.getBusinessgroupid(),
				"IsStarTimeSubsidies");
		String isStartOverStoreSubsidies = adminToolsService.getConfigValueByKey(detail.getBusinessgroupid(),
				"IsStartOverStoreSubsidies");

		String subsidyConfig = "";
		if (isStartOverStoreSubsidies.equals("1")) {
			subsidyConfig = "全局补贴：跨店抢单奖励";
		}
		if (isStarTimeSubsidies.equals("1")) {
			subsidyConfig = subsidyConfig.isEmpty() ? "全局补贴：动态时间奖励" : "全局补贴：跨店抢单奖励和动态时间奖励";
		}
		// 从常量配置中获取
		String defaultPic = PropertyUtils.getProperty("ImageServicePath");
		String imageBusinessPath = PropertyUtils.getProperty("ImageBusinessServicePath");
		String originSize = "_0_0";
		String bigFileName = defaultPic + "/nopic.jpg";
		String bigFileNameb = defaultPic + "/nopic.jpg";
		String checkPicUrl = defaultPic + "/nopic.jpg";
		String businessLicensePic = defaultPic + "/nopic.jpg";
		if (detail.getCheckpicurl() != null && !detail.getCheckpicurl().isEmpty()) {
			int fileLastDot = detail.getCheckpicurl().lastIndexOf('.');
			String fileHandHouZhui = detail.getCheckpicurl().substring(fileLastDot, detail.getCheckpicurl().length());
			checkPicUrl = imageBusinessPath + detail.getCheckpicurl();
			bigFileName = imageBusinessPath + detail.getCheckpicurl().substring(0, fileLastDot) + originSize + fileHandHouZhui;

		}
		if (detail.getBusinesslicensepic() != null && !detail.getBusinesslicensepic().isEmpty()) {
			int fileLastDotb = detail.getBusinesslicensepic().lastIndexOf('.');
			String fileHandHouZhuib = detail.getBusinesslicensepic().substring(fileLastDotb,
					detail.getBusinesslicensepic().length());
			businessLicensePic = imageBusinessPath + detail.getBusinesslicensepic();
			bigFileNameb = imageBusinessPath + detail.getBusinesslicensepic().substring(0, fileLastDotb) + originSize + fileHandHouZhuib;
		}
		String finalCheckPicUrl =  checkPicUrl;
		String finalBigCheckPicUrl = bigFileName;

		String finalBusinessPicUrl = businessLicensePic;
		String finalBigBusinessPicUrl = bigFileNameb;

		int accountID = 0;// 如果管理后台的类型是所有权限就传0，否则传管理后台id
		List<AreaModel> openCityList = iPublicProvinceCityService.getOpenCityListByAccountID(accountID);

		List<AreaModel> openAreaList = iPublicProvinceCityService.getOpenCityDistrict(ParseHelper.ToInt(detail
				.getCityid()));

		List<BusinessThirdRelationModel> businessThirdRelation = businessThirdRelationService
				.getListByBusinessID(ParseHelper.ToInt(detail.getId()));
		List<BusinessOptionLog> businessOpLog = iBusinessService
				.getOpLogByBusinessID(ParseHelper.ToInt(detail.getId()));
		List<DeliveryCompany> deliveryCompany = deliveryCompanyService.getDeliveryCompanyList();
		List<BusinessGroup> businessGroupListData = iBusinessGroupService.getBusinessGroupList();
		//商家全部标签
		List<Mark> marks=markService.getBusMarksList(businessID);
		
		GroupReq groupReq = new GroupReq();
		groupReq.setIsValid(1);
		List<GroupModel> groupListData = iGroupService.getGroupList(groupReq);

		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "门店管理");
		model.addObject("currenttitle", "修改门店信息");
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
		model.addObject("tagsData", marks);
		model.addObject("viewPath", "business/detail");
		return model;
	}

	@RequestMapping("businessexpress")
	@ResponseBody
	public List<BusinessExpressRelation> getExpressRelation(int businessID) {
		List<BusinessExpressRelation> result = businessExpressRelationService.selectByBusinessID(businessID);
		return result;
	}
	/**
	 * 修改商户的基本信息
	 * @param detail
	 * @return
	 */
	@RequestMapping("modifybusiness")
	@ResponseBody
	public int modifyBusiness(BusinessModifyModel detail,HttpServletRequest request) {
		int id=UserContext.getCurrentContext(request).getId();
		detail.setOptId(String.valueOf(id));
		detail.setOptName(UserContext.getCurrentContext(request).getUserName());
		return iBusinessService.modifyBusiness(detail);
	}
	
	/**
	 * 修改商户的标签关系
	 * @param detail
	 * @return
	 */
	@RequestMapping("modifybusinesstags")
	@ResponseBody
	public int modifyBusinessTags(ModifyTagReq req,HttpServletRequest request) {
		req.setOptName(UserContext.getCurrentContext(request).getUserName());
		return iBusinessService.modifyBusinessTags(req);
	}

	@RequestMapping("getCityDistrict")
	@ResponseBody
	public List<AreaModel> getCityDistrict(int cityID) {
		return iPublicProvinceCityService.getOpenCityDistrict(cityID);
	}

	@RequestMapping("modifyexpress")
	@ResponseBody
	public int modifyExpress(int busiId, String deliveryCompanyList) {
		if (deliveryCompanyList == null || deliveryCompanyList.isEmpty()) {
			return -1;
		}
		List<BusinessExpressRelation> listData = new ArrayList<>();
		String[] expressList = deliveryCompanyList.split(";");
		for (String express : expressList) {
			if (!express.isEmpty()) {
				String[] itemsStrings = express.split(",");
				BusinessExpressRelation item = new BusinessExpressRelation();
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
	public int businessAudit(int businessID, int status) {
		return iBusinessService.updateAuditStatus(businessID, status);
	}

	@RequestMapping("getfinanceaccount")
	@ResponseBody
	public BusinessFinanceAccount getFinanceAccount(int businessID) {
		BusinessFinanceAccount resultAccount = businessFinanceAccountService.getDetailByBusinesID(businessID);
		return resultAccount;
	}

	@RequestMapping("recharge")
	@ResponseBody
	public int businessRecharge(BusinesRechargeModel param) {
		return 0;
	}

	/**
	 * 根据单号获得商家提现详情
	 * 
	 * @author pengyi
	 * @date 20150831
	 * @param param
	 * @return
	 */
	@RequestMapping("rechargedetail")
	@ResponseBody
	public BusinessRechargeDetailModel getRechargeDetail(String orderNo) {
		return iBusinessService.getRechargeDetail(orderNo);
	}

	@RequestMapping("withdraw")
	@ResponseBody
	public BusinessFinanceAccount businessWithdraw(int businessID) {
		BusinessFinanceAccount resultAccount = businessFinanceAccountService.getDetailByBusinesID(businessID);
		return resultAccount;
	}

	@RequestMapping("balancedetail")
	public ModelAndView getBalanceDetail(int businessId) throws Exception {
		BusinessDetailModel detail = iBusinessService.getBusinessDetailByID(businessId);
		if (detail == null) {
			throw new Exception("没找到businessID为" + businessId + "的详细信息");
		}
		BussinessBalanceQueryReq queryReq = new BussinessBalanceQueryReq();
		queryReq.setBusinessId(businessId + "");
		double chargeTotalAmount = businessBalanceRecordService.queryBusinessRechargeTotalAmount(queryReq);
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "商户");
		model.addObject("currenttitle", "收支记录");
		model.addObject("detail", detail);
		model.addObject("chargeTotalAmount", chargeTotalAmount);
		model.addObject("viewPath", "business/balancedetail");
		return model;
	}

	@RequestMapping("balancedetaillistdo")
	public ModelAndView getBalanceDetailListDo(PagedTransDetailReq req) {
		PagedResponse<BusinessBalanceRecord> resp = businessBalanceRecordService.getTransDetailList(req);
		ModelAndView model = new ModelAndView("business/balancedetaillistdo");
		model.addObject("listData", resp);
		return model;
	}

	@RequestMapping("clienterbindlist")
	public ModelAndView clienterBindList(int businessId) throws Exception {
		BusinessDetailModel detail = iBusinessService.getBusinessDetailByID(businessId);
		if (detail == null) {
			throw new Exception("没找到businessID为" + businessId + "的详细信息");
		}
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "商户");
		model.addObject("currenttitle", "骑士绑定");
		model.addObject("viewPath", "business/clienterbindlist");
		model.addObject("detail", detail);
		model.addObject("bindClienterQty", businessClienterRelationService.getBusinessBindClienterQty(businessId));
		return model;
	}

	@RequestMapping("clienterbindlistdo")
	public ModelAndView clienterBindListDo(PagedCustomerSearchReq req) throws Exception {
		ModelAndView model = new ModelAndView("business/clienterbindlistdo");
		PagedResponse<BusinessClienterRelationModel> resp = businessClienterRelationService
				.getBusinessClienterRelationList(req);
		model.addObject("listData", resp);
		return model;
	}
	@RequestMapping("getisfulltime")
	@ResponseBody
	public int getIsFullTime(Integer clienterId){
		Clienter clienter=clienterService.selectByPrimaryKey(clienterId);
		if(ClienterGradeType.getEnum(clienter.getGradeType())==ClienterGradeType.FullTimeClienter){
			return 1;
		}
		return 0;
	}
	@RequestMapping("updateClienterBindRelationCooperation")
	@ResponseBody
	public int UpdateClienterBindRelationCooperation(BusinessClienterRelationReq req){
		return businessClienterRelationService.updateClienterBindRelationCooperation(req);
//		return tmpval;
	}

	@RequestMapping("modifyclienterbind")
	@ResponseBody
	public int modifyClienterBind(ClienterBindOptionReq req, HttpServletRequest request) {
		req.setOptId(UserContext.getCurrentContext(request).getId());
		req.setOptName(UserContext.getCurrentContext(request).getLoginName());
		if (req.getIsBind()==0) {
			req.setRemark("解除绑定");
			req.setAuditStatus(BusinessClienterRelationAuditStatus.Refuse.value());
		}else {
			req.setRemark("添加绑定");
			req.setAuditStatus(BusinessClienterRelationAuditStatus.Pass.value());
		}

		if (businessClienterRelationService.modifyClienterBind(req)) {
			return 1;
		}
		return 0;
	}

	@RequestMapping("removeclienterbind")
	@ResponseBody
	public int removeclienterbind(ClienterBindOptionReq req, HttpServletRequest request) {
		req.setOptId(UserContext.getCurrentContext(request).getId());
		req.setOptName(UserContext.getCurrentContext(request).getLoginName());
		req.setRemark("删除绑定");
		if (businessClienterRelationService.removeclienterbind(req)) {
			return 1;
		}
		return 0;
	}

	@RequestMapping("addclienterbindlist")
	public ModelAndView addclienterbindlist(int businessId) throws Exception {
		BusinessDetailModel detail = iBusinessService.getBusinessDetailByID(businessId);
		if (detail == null) {
			throw new Exception("没找到businessID为" + businessId + "的详细信息");
		}
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "商户");
		model.addObject("currenttitle", "添加骑士绑定");
		model.addObject("viewPath", "business/addclienterbindlist");
		model.addObject("detail", detail);
		return model;
	}

	@RequestMapping("addclienterbindlistdo")
	public ModelAndView addclienterbindlistdo(PagedClienterSearchReq req)throws Exception {
		ModelAndView model = new ModelAndView("business/addclienterbindlistdo");
		PagedResponse<ClienterBindInfoModel> resp = clienterService.getClienterList(req);
		model.addObject("listData", resp);
		return model;
	}

	@RequestMapping("addclienterbind")
	public @ResponseBody ResponseBase addclienterbind(int businessId, int clienterId, HttpServletRequest request) {
		ResponseBase response = new ResponseBase();
		ClienterBindOptionReq req = new ClienterBindOptionReq();
		req.setBusinessId(businessId);
		req.setClienterId(clienterId);
		if (businessClienterRelationService.checkHaveBind(req)) {
			response.setMessage("此条绑定关系已存在！");
		} else {
			req.setOptId(UserContext.getCurrentContext(request).getId());
			req.setOptName(UserContext.getCurrentContext(request).getLoginName());
			req.setRemark("添加绑定");
			req.setIsBind(1);
			req.setAuditStatus(BusinessClienterRelationAuditStatus.Pass.value());
			if (!businessClienterRelationService.addClienterBind(req)) {
				response.setMessage("绑定关系失败！");
			} else {
				response.setMessage("绑定关系成功！");
				response.setResponseCode(1);
			}
		}
		return response;
	}

	@RequestMapping("clienterbatchbind")
	public ModelAndView clienterBatchBind(int businessId) throws Exception {
		BusinessDetailModel detail = iBusinessService.getBusinessDetailByID(businessId);
		if (detail == null) {
			throw new Exception("没找到businessID为" + businessId + "的详细信息");
		}
		ModelAndView model = new ModelAndView("adminView");
		model.addObject("subtitle", "商户");
		model.addObject("currenttitle", "批量添加骑士绑定");
		model.addObject("viewPath", "business/clienterbatchbind");
		model.addObject("detail", detail);
		return model;
	}

	@RequestMapping("clienterbatchbindlistdo")
	public ModelAndView clienterBatchBindlistdo(int businessId, String clienterName, String clienterPhone)
			throws Exception {
		ModelAndView model = new ModelAndView("business/addclienterbindlistdo");
		PagedClienterSearchReq req = new PagedClienterSearchReq();
		req.setClienterName(clienterName);
		req.setClienterPhone(clienterPhone);
		PagedResponse<ClienterBindInfoModel> resp = clienterService.getClienterList(req);
		model.addObject("listData", resp);
		return model;
	}

	/**
	 * 导出商户收支记录报表
	 * 
	 * @author pengyi
	 * @date 2015年9月2日 下午3:31:40
	 * @version 1.0
	 * @param businessId
	 * @param recordType
	 * @param startDate
	 * @param endDate
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("exportbusinessbalancerecord")
	public void exportBusinessBalanceRecord(int businessId, String recordType, String startDate, String endDate,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BusinessDetailModel detail = iBusinessService.getBusinessDetailByID(businessId);
		if (detail == null) {
			throw new Exception("没找到businessID为" + businessId + "的详细信息");
		}
		PagedTransDetailReq req = new PagedTransDetailReq();
		req.setEndDate(endDate);
		req.setStartDate(startDate);
		req.setRecordType(recordType);
		req.setBusinessID(businessId);
		req.setCurrentPage(1);
		req.setPageSize(Integer.MAX_VALUE);
		List<BusinessBalanceRecordModel> records = businessBalanceRecordService
				.getBusinessBalanceRecordListForExport(req);
		// 导出数据
		String filename = "商户提款流水记录%s";
		if (!StringUtils.isEmpty(req.getStartDate()) && !StringUtils.isEmpty(req.getEndDate())) {
			filename = String.format(filename, req.getStartDate() + "~" + req.getEndDate());
		}
		// 解密records的账号
		for (int i = 0; i < records.size(); i++) {
			records.get(i).setAccountNo(ParseHelper.toDecrypt(records.get(i).getAccountNo()));
		}
		if (records!=null&&records.size()>0) {
			exportBusinessBalanceRecord(filename, records,request,response);
		}

		return;
	}

	/**
	 * 上传文件导入骑士
	 * 
	 * @author pengyi
	 * @date 2015年9月7日 上午10:43:40
	 * @version 1.0
	 * @param businessId
	 * @param files
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("clienterimport")
	@ResponseBody
	public String clienterImport(@RequestParam(value = "businessId", required = false) Integer businessId,
			@RequestParam(value = "file1", required = true) MultipartFile files[], HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (files.length <= 0) {
			return "上传文件不能为空";
		} else {
			// 读取文件记录
			MultipartFile file = files[0];
			byte[] buffers = file.getBytes();
			if (buffers.length <= 0) {
				return "上传文件不能为空";
			} else {
				if (file.getContentType().indexOf("excel")<0) {
					return "上传的文件必须为excel";
				}
				try {
					List<ImportClienterInfo> infos = ExcelUtils.readExcel(buffers, 0, 50, ImportClienterInfo.class);
					return JsonUtil.obj2string(checkClienterImport(infos));
				} catch (Exception e) {
					return "上传的excel有误";
				}

			}
		}
	}

	@RequestMapping("clienterbatchsave")
	@ResponseBody
	public ResponseBase clienterBatchSave(int businessId, String overallS, HttpServletRequest request) {
		ResponseBase rsp = new ResponseBase();
		List<BusinessBindClienter> binds = JsonUtil.str2list(overallS, BusinessBindClienter.class);
		if (binds == null || binds.size() <= 0) {
			rsp.setMessage("导入数据不能为空或数据格式有问题");
			rsp.setResponseCode(0);
		} else {
			int successCount = 0;
			for (BusinessBindClienter bind : binds) {
				if (bind.isBind()) {
					String phone = bind.getClienterPhoneNo();
					String name = bind.getClienterName();
					Integer clienterId = clienterService.getId(phone, name);
					if (clienterId == null)
						continue;
					BusinessClienterRelation model = businessClienterRelationService.getDetails(businessId, clienterId);
					ClienterBindOptionReq req = new ClienterBindOptionReq();
					req.setBusinessId(businessId);
					req.setClienterId(clienterId);
					req.setOptId(UserContext.getCurrentContext(request).getId());
					req.setOptName(UserContext.getCurrentContext(request).getLoginName());
					if (model == null) {// 插入
						req.setRemark("添加绑定");
						req.setIsBind(1);
						req.setAuditStatus(BusinessClienterRelationAuditStatus.Pass.value());
						businessClienterRelationService.addClienterBind(req);
					} else if (model != null && model.getIsbind() == 0)// 更新
					{
						req.setRemark("修改绑定");
						req.setIsBind(1);
						req.setAuditStatus(BusinessClienterRelationAuditStatus.Pass.value());
						businessClienterRelationService.modifyClienterBind(req);
					}
					successCount++;
				}
			}
			rsp.setMessage("导入数据成功,共导入数据:" + successCount + "条");
			rsp.setResponseCode(1);
		}
		return rsp;
	}

	/**
	 * 导出商户收支记录excel二进制数据
	 * 
	 * @author pengyi
	 * @date 20150902
	 * @param fileName
	 * @param records
	 * @return
	 * @throws Exception
	 */
	private void exportBusinessBalanceRecord(String fileName, List<BusinessBalanceRecordModel> records,
			HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		//add data
		LinkedHashMap<String,String> columnTitiles=new LinkedHashMap<String,String>();
		columnTitiles.put("任务单号/交易流水号", "relationno");
		columnTitiles.put("所属银行", "openBank");
		columnTitiles.put("卡号", "accountNo");
		columnTitiles.put("收支金额", "amount");
		columnTitiles.put("余额", "balance");
		columnTitiles.put("完成时间", "operatetime");
		columnTitiles.put("操作人", "operator");
	
		ExcelUtils.export2Excel(fileName,"商户提款流水记录",columnTitiles,records,request,response);

	}

	private List<BusinessBindClienter> checkClienterImport(List<ImportClienterInfo> infos) {
		List<BusinessBindClienter> binds = new ArrayList<BusinessBindClienter>();
		for (int i = 0; i < infos.size(); i++) {
			ImportClienterInfo info = infos.get(i);
			BusinessBindClienter bind = new BusinessBindClienter();
			bind.setRowCount(i + 1);
			bind.setClienterName(info.getName());
			bind.setClienterPhoneNo(info.getPhoneNo());
			if (StringUtils.isEmpty(info.getPhoneNo())) {// 手机号为空
				bind.setClienterRemarks("骑士手机号错误");
				binds.add(bind);
				continue;
			}
			if (!Pattern.matches("^1\\d{10}$", info.getPhoneNo())) {// 验证收货人手机号
				bind.setClienterRemarks("骑士手机号错误");
				binds.add(bind);
				continue;
			}
			String trueName = clienterService.getNameByPhone(info.getPhoneNo());
			if (StringUtils.isEmpty(trueName)) {
				bind.setClienterRemarks("骑士手机号不存在");
				binds.add(bind);
				continue;
			}
			if (!trueName.equals(info.getName())) {
				bind.setClienterRemarks("骑士名称错误");
				binds.add(bind);
				continue;
			}
			bind.setClienterRemarks("");
			bind.setBind(true);
			bind.setEnable(true);
			binds.add(bind);
		}
		return binds;
	}
	@RequestMapping("getbindlist")
	public ModelAndView getBindList(Long businessId, Long clienterId) {
		ModelAndView result=new ModelAndView("business/bindrecord");
		 List<ClienterBindOptionLog> data= clienterBindOptionLogService.selectList(businessId, clienterId);
		 result.addObject("listData", data);
		 return result;
	}
}
