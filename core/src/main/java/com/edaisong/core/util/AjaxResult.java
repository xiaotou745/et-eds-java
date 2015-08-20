package com.edaisong.core.util;

/**
 * action返回ajaxresult通用类，封闭了返回成功标记，错误信息，数据对象等
 * 
 * @author wangyuchuan
 * @version 1.0
 */
public class AjaxResult {
	private AjaxResult() {
	}

	private boolean iserror = false;
	private String message = "";
	private Object data = null;

	/**
	 * 获取是否发生错误
	 * 
	 * @return 是否发生错误信息，如果为true,则Message保存的错误信息
	 */
	public boolean getIserror() {
		return this.iserror;
	}

	/**
	 * 获取错误信息或成功信息（提示信息）
	 * 
	 * @return 错误信息或成功信息（提示信息）
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * 设置错误信息或成功信息（提示信息）
	 * 
	 * @param message
	 *            提示信息
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 获取成功时返回给ajax请求的json数据
	 * 
	 * @return 成功时返回给ajax请求的json数据
	 */
	public Object getData() {
		return this.data;
	}

	/**
	 * 设计返回给ajax请求的json数据
	 * 
	 * @param data
	 *            返回给ajax请求的json数据
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * 返回一个错误结果
	 * 
	 * @return 一个错误结果
	 */
	public static AjaxResult Error() {
		AjaxResult result = new AjaxResult();
		result.iserror = true;
		return result;
	}

	/**
	 * 返回一个错误结果，且带错误信息
	 * 
	 * @param message
	 *            错误信息
	 * @return 带错误信息的结果
	 */
	public static AjaxResult Error(String message) {
		AjaxResult result = new AjaxResult();
		result.iserror = true;
		result.message = message;
		return result;
	}

	/**
	 * 返回一个成功的结果
	 * 
	 * @return 一个成功结果
	 */
	public static AjaxResult Success() {
		AjaxResult result = new AjaxResult();
		result.iserror = false;
		return result;
	}

	/**
	 * 返回一个成功的结果
	 * 
	 * @param message
	 *            成功信息
	 * @return 带成功信息的结果
	 */
	public static AjaxResult Success(String message) {
		AjaxResult result = new AjaxResult();
		result.iserror = false;
		result.message = message;
		return result;
	}

	/**
	 * 返回一个成功结果
	 * 
	 * @param data
	 *            成功的数据
	 * @return 带成功数据的结果
	 */
	public static AjaxResult Success(Object data) {
		AjaxResult result = new AjaxResult();
		result.iserror = false;
		result.data = data;
		return result;
	}

	/**
	 * 返回一个带成功信息和数据的结果
	 * 
	 * @param data
	 *            成功数据
	 * @param message
	 *            成功信息
	 * @return 带成功信息和数据的结果
	 */
	public static AjaxResult Success(Object data, String message) {
		AjaxResult result = new AjaxResult();
		result.iserror = false;
		result.data = data;
		result.message = message;
		return result;
	}
	/*
	 * @@override public String toString() { return new
	 * JavaScriptSerializer().Serialize(this);
	 * 
	 * }
	 */
}
