package com.qq.client.model;

import com.qq.common.*;
public class QqClientUser {

	public boolean checkUser(User u)
	{
		return new QqClientConServer().sendLoginInfoToServer(u);
	}
	
	public boolean checkSignup (User u)
	{
		return new QqClientConServer().SendSignInfoToServer(u);
	}
	
	
}
