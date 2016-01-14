package com.edaisong.upload.controllor;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edaisong.upload.common.HttpResultModel;
import com.edaisong.upload.common.HttpReturnRnums;
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
	public HttpResultModel<UploadResultModel> uploadImg(HttpServletRequest request) throws Exception {
		UploadResultModel resp= UploadFileHelper.UploadImg(request);
		HttpResultModel<UploadResultModel> result=new HttpResultModel<UploadResultModel>();
		if (resp.getRemark()!=null&&!resp.getRemark().isEmpty()) {
			result.setCode(HttpReturnRnums.ParaError.value());
			result.setMsg(resp.getRemark());
			return result;
		}
		result.setData(resp);
		return result;
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
	public HttpResultModel<UploadResultModel> uploadFile(HttpServletRequest request) throws Exception {
		UploadResultModel resp= UploadFileHelper.UploadFile(request);
		HttpResultModel<UploadResultModel> result=new HttpResultModel<UploadResultModel>();
		if (resp.getRemark()!=null&&!resp.getRemark().isEmpty()) {
			result.setCode(HttpReturnRnums.ParaError.value());
			result.setMsg(resp.getRemark());
			return result;
		}
		result.setData(resp);
		return result;
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
	public HttpResultModel<String> deleteFile(String fileUrl) throws Exception {
		HttpResultModel<String> result=new HttpResultModel<String>();
		if (fileUrl==null||fileUrl.isEmpty()) {
			result.setCode(HttpReturnRnums.ParaError.value());
			result.setMsg("文件路径不能为空");
			return result;
		}
		UploadFileHelper.DeleteFile(fileUrl);
		return result;
	}	
}
