package com.edaisong.entity.taobao;

/**
 * 淘宝 api 调用 返回值数据结构
 * @author CaoHeYang
 * @date 2015年11月13日 14:48:49
 */
public class TaoBaoResponseBase {
	private Boolean is_success;
	private String error_code;
	private String error_msg;
	private Boolean result;

	/**
	 * 调用是否成功
	 * 
	 * @return
	 */
	public Boolean isIs_success() {
		return is_success;
	}

	/**
	 * 调用是否成功
	 * 
	 * @param is_success
	 */
	public void setIs_success(Boolean is_success) {
		this.is_success = is_success;
	}

	/**
	 * 错误代码
	 * 
	 * @return
	 */
	public String getError_code() {
		return error_code;
	}

	/**
	 * 错误代码
	 * 
	 * @param error_code
	 */
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	/**
	 * 错误信息
	 * 
	 * @return
	 */
	public String getError_msg() {
		return error_msg;
	}

	/**
	 * 错误信息
	 * 
	 * @param error_msg
	 */
	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}

	/**
	 * 取件是否成功
	 * 
	 * @return
	 */
	public Boolean getResult() {
		return result;
	}

	/**
	 * 取件是否成功
	 * 
	 * @param result
	 */
	public void setResult(Boolean result) {
		this.result = result;
	}
}
