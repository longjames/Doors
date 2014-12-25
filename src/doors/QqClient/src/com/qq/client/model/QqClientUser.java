package com.qq.client.model;

import com.qq.common.*;
public class QqClientUser {

	public boolean checkUser(User u)//登录结果
	{
		return new QqClientConServer().sendLoginInfoToServer(u);
	}
	
	public boolean checkSignup (User u)//注册结果
	{
		return new QqClientConServer().SendSignInfoToServer(u);
	}
	
	
}
