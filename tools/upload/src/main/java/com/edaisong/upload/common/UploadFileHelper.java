package com.edaisong.upload.common;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;

import com.edaisong.toolscore.util.FileUtil;

public class UploadFileHelper {

/**
 * 上传图片
 * @author hailongzhao
 * @date 20160104
 * @param request
 * @param loadFrom
 * @return
 * @throws Exception
 */
	public static UploadResultModel UploadImg(HttpServletRequest request) throws Exception {
		int defaultImgWidth=Integer.parseInt(PropertyUtils.getProperty("DefaultImgWidth"));
		int defaultImgHeight=Integer.parseInt(PropertyUtils.getProperty("DefaultImgHeight"));
		int clienterSmallHeadWidth=Integer.parseInt(PropertyUtils.getProperty("ClienterSmallHeadWidth"));
		int clienterSmallHeadHeight=Integer.parseInt(PropertyUtils.getProperty("ClienterSmallHeadHeight"));

		String originalSuffix=PropertyUtils.getProperty("OriginalSuffix");
		String clienterSmallHeadSuffix=PropertyUtils.getProperty("ClienterSmallHeadSuffix");
		//先保存原图，后缀为_0_0
		UploadResultModel originalFile = doUpload(request, 0, originalSuffix);
		if (originalFile.getRemark() == null||originalFile.getRemark().isEmpty()) {
			//如果原图保存成功，则切一个150*150的图
			String diskPath = PropertyUtils.getProperty("FileUploadPath")+ "/"+ originalFile.getRelativePath();
			ImageCuter.cut(diskPath, diskPath.replace(originalSuffix, ""), defaultImgWidth, defaultImgHeight);
			//如果来源是骑士，则再切一个95*95的小图，用于任务参与人的小头像
			if (originalFile.getLoadFrom() == UploadFrom.Clienter.value()) {
				ImageCuter.cut(diskPath, diskPath.replace(originalSuffix, clienterSmallHeadSuffix),clienterSmallHeadWidth, clienterSmallHeadHeight);
			}
			originalFile.setFileUrl(originalFile.getFileUrl().replace(originalSuffix, ""));
			originalFile.setRelativePath(originalFile.getRelativePath().replace(originalSuffix, ""));
		}
		return originalFile;
	}
/**
 * 上传文件
 * @author hailongzhao
 * @date 20160104
 * @param request
 * @param loadFrom
 * @return
 * @throws Exception
 */
	public static UploadResultModel UploadFile(HttpServletRequest request) throws Exception {
		return doUpload(request, 1, "");
	}
/**
 * 根据相对路径删除对应的文件
 * @author hailongzhao
 * @date 20160104
 * @param fileUrl
 */
	public static void DeleteFile(String fileUrl){
		//fileUrl:Business/2015/10/13/13/322547f79e.jpg
		if (fileUrl==null||fileUrl.isEmpty()) {
			return;
		}
		String diskPath = PropertyUtils.getProperty("FileUploadPath")+ "/"+ fileUrl;
		File uploadedFile = new File(diskPath);
		String fileName=uploadedFile.getName();
		String originalSuffix=PropertyUtils.getProperty("OriginalSuffix");
		String clienterSmallHeadSuffix=PropertyUtils.getProperty("ClienterSmallHeadSuffix");
		int pointIndex=fileName.lastIndexOf(".");
		int pathIndex=uploadedFile.getPath().lastIndexOf(".");
		
		String filePath=uploadedFile.getPath().substring(0, pathIndex);
		String originalFile= filePath+originalSuffix+fileName.substring(pointIndex);
		String clienterSmallHeadFile= filePath+clienterSmallHeadSuffix+fileName.substring(pointIndex);
		
		FileUtil.delete(diskPath);
		FileUtil.delete(originalFile);
		FileUtil.delete(clienterSmallHeadFile);
	}
	/**
	 * 根据来源获取文件存放的子目录
	 * @author hailongzhao
	 * @date 20160104
	 * @param loadFrom
	 * @return
	 */
	private static String getPathByFrom(UploadFrom loadFrom) {
		switch (loadFrom) {
		case Business:
			return PropertyUtils.getProperty("FileUploadFolderNameBusiness");
		case Clienter:
			return PropertyUtils.getProperty("FileUploadFolderNameClienter");
		case Order:
			return PropertyUtils.getProperty("FileUploadFolderNameOrder");
		case Other:
			return PropertyUtils.getProperty("FileUploadFolderNameCustomerIcon");
		default:
			break;
		}
		return "";
	}
/**
 * 上传文件的核心逻辑
 * @author hailongzhao
 * @date 20160104
 * @param request
 * @param loadFrom
 * @param fileType
 * @param originalSufix
 * @return
 * @throws Exception
 */
	private static UploadResultModel doUpload(HttpServletRequest request,int fileType, String originalSufix)
			throws Exception {

		UploadResultModel result = new UploadResultModel();

		if (!ServletFileUpload.isMultipartContent(request)) {
			result.setRemark("请选择文件");
			return result;
		}
		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
		List<String> ext = Arrays.<String> asList(extMap.get("image").split(","));
		if (fileType > 0) {
			ext = Arrays.<String> asList(extMap.get("file").split(","));
			List<String> mediaExt = Arrays.<String> asList(extMap.get("media").split(","));
			ext.addAll(mediaExt);
		}
		String regionPath =""; 
		String rootPath = "";
		List<FileItem> fileList=new ArrayList<FileItem>();
		DiskFileItemFactory dff = new DiskFileItemFactory();
		dff.setSizeThreshold(1024000);
		ServletFileUpload sfu = new ServletFileUpload(dff);
		sfu.setHeaderEncoding("UTF-8");
		Iterator fii = sfu.parseRequest(request).iterator();
		while (fii.hasNext()) {
			FileItem fis = (FileItem) fii.next();
			if (fis.isFormField()) {
				if (fis.getFieldName().equals("uploadFrom")) {
					String loadFrom = getLoadFrom(fis);
					if (loadFrom.length() > 1) {
						result.setRemark(loadFrom);
						return result;
					}
					result.setLoadFrom(Integer.parseInt(loadFrom));
					regionPath = getPathByFrom(UploadFrom.getEnum(Integer.parseInt(loadFrom)));
					rootPath = PropertyUtils.getProperty("FileUploadPath")+ "/" + regionPath;
					FileUtil.createDirectory(rootPath);// 创建目录
					continue;
				}
			} else {
				if (fis.getSize() > 1000000) {
					result.setRemark("上传文件大小超过限制");
					return result;
				}
				// 检查扩展名
				String fileExt = fis.getName().substring(fis.getName().lastIndexOf(".") + 1).toLowerCase();
				if (!ext.contains(fileExt)) {
					result.setRemark("上传文件扩展名是不允许的扩展名。\n只允许"+ String.join(",", ext));
					return result;
				}
				fileList.add(fis);
			}
		}
		if (rootPath==null||rootPath.isEmpty()) {
			result.setRemark("uploadFrom不能为空");
			return result;
		}

		saveFiles(fileList,originalSufix,regionPath,rootPath,result);
		return result;
	}
	private static void saveFiles(List<FileItem> fileList,String originalSufix,String regionPath, String rootPath,UploadResultModel result) throws Exception{
		if (fileList.size()==0) {
			result.setRemark("请选择文件");
		}
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);// 获取年份
		int month = cal.get(Calendar.MONTH) + 1;// 获取月份
		int day = cal.get(Calendar.DATE);// 获取日
		int hour = cal.get(Calendar.HOUR_OF_DAY);// 小时
		String temp = year + "/" + String.format("%02d", month) + "/" + String.format("%02d", day) + "/" + String.format("%02d", hour);
		String fullPath = rootPath +"/" + temp;
		// 创建目录
		FileUtil.createDirectory(fullPath);
		for (FileItem fileItem : fileList) {
			String uploadFileName = fileItem.getName();
			// 上传文件重命名
			String realFileName = new Date().getTime()
					+ originalSufix
					+ uploadFileName.substring(uploadFileName.lastIndexOf("."));
			try {
				File uploadedFile = new File(rootPath +"/" + temp, realFileName);
				fileItem.write(uploadedFile);
			} catch (Exception e) {
				result.setRemark("上传文件失败");
			}
			String relativePath=regionPath +"/" + temp + "/" + realFileName;
			result.setFileUrl(PropertyUtils.getProperty("WebApiAddress")+"/"+ relativePath);
			result.setModifyOriginalName(realFileName);
			result.setOriginalName(uploadFileName);
			result.setRelativePath(relativePath);
		}
	} 
	private static String getLoadFrom(FileItem fis) throws Exception{
		String loadFromValue=fis.getString("utf-8");
		if (loadFromValue==null||loadFromValue.trim().isEmpty()) {
			return "uploadFrom不能为空";
		}
		if(!StringUtils.isNumeric(loadFromValue.trim())){
			return "uploadFrom值错误,只能为数字";
		}
		UploadFrom loadFrom=UploadFrom.getEnum(Integer.parseInt(loadFromValue));
		if (loadFrom==null) {
			return "uploadFrom值错误";
		}
		return String.valueOf(loadFrom.value());
	}
}