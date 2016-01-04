package com.edaisong.upload.common;

public class UploadResultModel {
    private String fileUrl;
    private String relativePath;
    private String originalName;
    private String modifyOriginalName;
    private String remark;
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getRelativePath() {
		return relativePath;
	}
	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	public String getModifyOriginalName() {
		return modifyOriginalName;
	}
	public void setModifyOriginalName(String modifyOriginalName) {
		this.modifyOriginalName = modifyOriginalName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
