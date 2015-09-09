package com.edaisong.entity.resp;

/**
 * app 登陆后获取顶端未读公告
 * 
 * @author CaoHeYang
 *
 */
public class MessageResp {
	private int id;
	private String Content;

	/**
	 * 消息id
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * 消息id
	 * 
	 * @param id
	 */
	public void setId(int id) {
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
