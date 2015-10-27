package com.edaisong.toolsentity.common;

import com.edaisong.toolscore.enums.returnenums.HttpReturnEnums;

public class HttpResultModel<T> {
	private int status = HttpReturnEnums.Success.value();
	private String message = HttpReturnEnums.Success.desc();
	private T result;

	/**
	 * 状态
	 * 
	 * @return
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * 状态
	 * 
	 * @param status
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * 状态
	 * 
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 状态
	 * 
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 
	 * @return
	 */
	public T getResult() {
		return result;
	}

	/**
	 * 
	 * @param result
	 */
	public void setResult(T result) {
		this.result = result;
	}

}
