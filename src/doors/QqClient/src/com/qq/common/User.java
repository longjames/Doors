/**
 * �����û���Ϣ��
 */
package com.qq.common;

public class User implements java.io.Serializable {

	private String userId;
	private String passwd;
	private String state;//��������״̬��1��¼״̬��2����״̬������3ע��״̬
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
