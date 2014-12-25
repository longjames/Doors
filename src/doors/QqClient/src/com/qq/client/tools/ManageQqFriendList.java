/**
 * 管理好友、黑名单..界面类
 */
package com.qq.client.tools;

import java.util.*;
import java.io.*;
import com.qq.client.view.*;
public class ManageQqFriendList {

	private static HashMap hm=new HashMap<String, QqFriendList>();
	
	public static void addQqFriendList(String qqid,QqFriendList qqFriendList){//添加qq好友，不打算做好友这块了，直接在线的都是好友
		
		hm.put(qqid, qqFriendList);
	}
	
	public static QqFriendList getQqFriendList(String qqId)//返回qq好友
	{
		return (QqFriendList)hm.get(qqId);
	}
}
