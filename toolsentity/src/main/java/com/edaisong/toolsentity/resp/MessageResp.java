package com.edaisong.toolsentity.resp;

/**
 * app 登陆后获取顶端未读公告
 * 
 * @author CaoHeYang
 *
 */
public class MessageResp {
	private long id;
	private String Content;

	/**
	 * 消息id
	 * 
	 * @return
	 */
	public long getId() {
		return id;
	}

	/**
	 * 消息id
	 * 
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * 消息体
	 * 
	 * @return
	 */
	public String getContent() {
		return Content;
	}

	/**
	 * 消息体
	 * 
	 * @param content
	 */
	public void setContent(String content) {
		Content = content;
	}
}
