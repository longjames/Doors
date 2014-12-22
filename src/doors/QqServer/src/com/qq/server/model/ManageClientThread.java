package com.qq.server.model;

import java.util.*;
public class ManageClientThread {

	public static HashMap hm=new HashMap<String, SerConClientThread>();
	
	//向hm中添加一个客户端通讯线程,以uid作为标志
	public static  void addClientThread(String uid,SerConClientThread ct)
	{
		hm.put(uid, ct);
	}
	
	public static SerConClientThread getClientThread(String uid)//从ID得到其线程
	{
		return (SerConClientThread)hm.get(uid);
	}
	
	//返回当前在线的人的情况
	public static String getAllOnLineUserid()
	{
		//使用迭代器完成
		Iterator it=hm.keySet().iterator();
		String res="";
		while(it.hasNext())//判断序列中是否还有元素
		{
			res+=it.next().toString()+" ";
		}
		return res;
	}
}
