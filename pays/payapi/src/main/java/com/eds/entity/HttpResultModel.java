package com.eds.entity;

public class HttpResultModel<T> {
	private int status = HttpReturnRnums.Success.value();
	private String message = HttpReturnRnums.Success.desc();
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
	public HttpResultModel<T> setStatus(int status) {
		this.status = status;
		return this;
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
	public HttpResultModel<T>  setMessage(String message) {
		this.message = message;
		return this;
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
	public HttpResultModel<T>  setResult(T result) {
		this.result = result;
		return this;
	}

}