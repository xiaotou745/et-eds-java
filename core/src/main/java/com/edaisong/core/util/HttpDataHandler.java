package com.edaisong.core.util;

/**
 * 处理请求返回结果的回调接口 如果需要返回实体对象，则可以使用Result属性。
 * 
 * @author wangyuchuan@beimai.com
 *
 */
public abstract class HttpDataHandler<T>
{
	/**
	 * 处理httpclient请求结果
	 * 
	 * @param httpResponse httpclient请求应用层结果
	 * @return BmHttpResponse
	 */
	public abstract BmHttpResponse<T> HandleHttpResult(BmHttpResponse<T> httpResponse);
}
