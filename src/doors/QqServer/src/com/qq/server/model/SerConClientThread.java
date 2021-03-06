/**
 * 功能：是服务器和某个客户端的通信线程,让登录成功的用户访问服务器
 */
package com.qq.server.model;

import java.util.*;
import java.net.*;
import java.io.*;
import com.qq.common.*;
import com.qq.server.db.ServerChattingRecord;
public class SerConClientThread  extends Thread{

	Socket s;
	
	public SerConClientThread(Socket s)
	{
		//把服务器和该客户端的连接赋给s
		this.s=s;
	}
	
	//让该线程去通知其它用户
	public void notifyOther(String iam)
	{
		//得到所有在线的人的线程
		HashMap hm=ManageClientThread.hm;//?????????相当于ManageClientThread中的数据调用？
		Iterator it=hm.keySet().iterator();
		
		while(it.hasNext())//检查序列中是否还有元素
		{
			Message m=new Message();
			m.setCon(iam);
			m.setMesType(MessageType.message_ret_onLineFriend);
			//取出在线人的id
			String onLineUserId=it.next().toString();//获得序列中的下一个元素，然后转为String
			try {/////?????????为在线的人跟新在线好友，因为有人上线了，能否在发信息时给一个消息表，让在线的人轮流查表看有没有自己的消息呢
				ObjectOutputStream oos=new ObjectOutputStream(ManageClientThread.getClientThread(onLineUserId).s.getOutputStream());
				m.setGetter(onLineUserId);
				oos.writeObject(m);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
			
		}
	}
	
	public void run()
	{
		
		while(true)
		{
			
			//这里该线程就可以接收客户端的信息.
			try {
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Message m=(Message)ois.readObject();
				
			//	System.out.println(m.getSender()+" 给 "+m.getGetter()+" 说:"+m.getCon());
				
				//对从客户端取得的消息进行类型判断，然后做相应的处理
				if(m.getMesType().equals(MessageType.message_comm_mes))
				{
					//先写进文件中,getter和sendder都要写进去
					ServerChattingRecord scr =new ServerChattingRecord();
					scr.WriteRecord(m, m.getGetter(), m.getSender());
					scr.WriteRecord(m, m.getSender(), m.getGetter());
					//一会完成转发.
					//取得接收人的通信线程
					SerConClientThread sc=ManageClientThread.getClientThread(m.getGetter());
					ObjectOutputStream oos=new ObjectOutputStream(sc.s.getOutputStream());
					oos.writeObject(m);
				}else if(m.getMesType().equals(MessageType.message_get_onLineFriend))
				{
					System.out.println(m.getSender()+" 要他的好友11111");
					//把在服务器的好友给该客户端返回.但是现在这步有问题
					String res=ManageClientThread.getAllOnLineUserid();
					//System.out.println(m.getSender()+" 要他的好友:++++"+res);
					Message m2=new Message();
					m2.setMesType(MessageType.message_ret_onLineFriend);
					m2.setCon(res);
					
					m2.setGetter(m.getSender());
					ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
					oos.writeObject(m2);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
			
			
		}
		
		
	}
}
