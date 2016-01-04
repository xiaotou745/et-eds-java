package com.edaisong.upload.controllor;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edaisong.upload.common.UploadFileHelper;
import com.edaisong.upload.common.UploadFrom;
import com.edaisong.upload.common.UploadResultModel;


@Controller
@RequestMapping("fileupload")
public class FileUploadController {	
	/**
	 * 上传图片（会裁剪图片）
	 * @author hailongzhao
	 * @date 20160104
	 * @param request
	 * @param loadFrom
	 * @return
	 * @throws ServletException
	 * @throws Exception
	 */
	@RequestMapping("uploadimg")
	@ResponseBody
	public UploadResultModel uploadImg(HttpServletRequest request,UploadFrom loadFrom) throws ServletException, Exception {
		if (loadFrom==null) {
			UploadResultModel resultModel=new UploadResultModel();
			resultModel.setRemark("loadFrom不能为空");
			return resultModel;
		}
		loadFrom=UploadFrom.Clienter;
		return UploadFileHelper.UploadImg(request,loadFrom);
	}
	/**
	 * 上传文件
	 * @author hailongzhao
	 * @date 20160104
	 * @param request
	 * @param loadFrom
	 * @return
	 * @throws ServletException
	 * @throws Exception
	 */
	@RequestMapping("uploadfile")
	@ResponseBody
	public UploadResultModel uploadFile(HttpServletRequest request,UploadFrom loadFrom) throws ServletException, Exception {
		if (loadFrom==null) {
			UploadResultModel resultModel=new UploadResultModel();
			resultModel.setRemark("loadFrom不能为空");
			return resultModel;
		}
		loadFrom=UploadFrom.Clienter;
		return UploadFileHelper.UploadFile(request,loadFrom);
	}
	/**
	 * 删除文件
	 * @author hailongzhao
	 * @date 20160104
	 * @param fileUrl
	 * @throws Exception
	 */
	@RequestMapping("deletefile")
	@ResponseBody
	public String deleteFile(String fileUrl) throws Exception {
		if (fileUrl==null||fileUrl.isEmpty()) {
			return "文件路径不能为空";
		}
		UploadFileHelper.DeleteFile(fileUrl);
		return "";
	}	
}
