package com.qq.server.model;

import java.util.*;
public class ManageClientThread {

	public static HashMap hm=new HashMap<String, SerConClientThread>();
	
	//��hm�����һ���ͻ���ͨѶ�߳�,��uid��Ϊ��־
	public static  void addClientThread(String uid,SerConClientThread ct)
	{
		hm.put(uid, ct);
	}
	
	public static SerConClientThread getClientThread(String uid)//��ID�õ����߳�
	{
		return (SerConClientThread)hm.get(uid);
	}
	
	//���ص�ǰ���ߵ��˵����
	public static String getAllOnLineUserid()
	{
		//ʹ�õ��������
		Iterator it=hm.keySet().iterator();
		String res="";
		while(it.hasNext())//�ж��������Ƿ���Ԫ��
		{
			res+=it.next().toString()+" ";
		}
		return res;
	}
}
