/**
 * 这是用户信息类
 */
package com.qq.common;

public class User implements java.io.Serializable {

	private String userId;
	private String passwd;
	private String state;//用于设置状态，1登录状态，2隐身状态，还是3注册状态
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
