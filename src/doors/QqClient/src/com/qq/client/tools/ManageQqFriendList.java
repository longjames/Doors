/**
 * ������ѡ�������..������
 */
package com.qq.client.tools;

import java.util.*;
import java.io.*;
import com.qq.client.view.*;
public class ManageQqFriendList {
	QqFriendList qqFriendListname;

	private static HashMap hm=new HashMap<String, QqFriendList>();
	
	public static void addQqFriendList(String qqid,QqFriendList qqFriendList){//���qq���ѣ�����������������ˣ�ֱ�����ߵĶ��Ǻ���
		
		hm.put(qqid, qqFriendList);
	}
	/*public static void setFriendlistname(QqFriendList qqFriendListname){
		   this.qqFriendListname=qqFriendListname;
	}
	public QqFriendList getFriendlistname(){
		  return this.qqFriendListname;
	}*/
	public static QqFriendList getQqFriendList(String qqId)//ʵ������
	{
		return (QqFriendList)hm.get(qqId);
	}
}
