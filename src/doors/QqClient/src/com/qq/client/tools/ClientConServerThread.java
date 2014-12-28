/**
 * 这是客户端和服务器端保持通讯的线程.
 */
package com.qq.client.tools;

import java.io.*;
import java.net.*;

import com.qq.client.view.QqChat;//聊天界面
import com.qq.client.view.QqClientLogin;
import com.qq.client.view.QqFriendList;//好友界面
import com.qq.common.*;
public class ClientConServerThread extends Thread {

	private Socket s;
	
	//构造函数
	public ClientConServerThread(Socket s)
	{
		this.s=s;
	}
	
	public void run()
	{
		while(true)
		{
			//不停的读取从服务器端发来的消息
			try {
				
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Message m=(Message)ois.readObject();
			/*	System.out.println("读取到从服务发来的消息"+ m.getSender() +" 给 "+m.getGetter()+" 内容"+
						m.getCon());*/
				
				if(m.getMesType().equals(MessageType.message_comm_mes))//普通信息包
				{
				
					//把从服务器获得消息，显示到该显示的聊天界面
					System.out.println("收到服务器发来的消息");
					ChattingRecord chat_record =new ChattingRecord();
					chat_record.WriteRecord(m, m.getGetter(), m.getSender());
					try{
					QqChat qqChat=ManageQqChat.getQqChat(m.getGetter()+" "+m.getSender());//得到那个进程,但是如果这个线程不存在呢？
					//显示                                                                                                                                                                                                                                    //也就是说聊天框不存在的时候,这时可以选择弹出一个新的聊天框，也可以？
					qqChat.showMessage(m);
					}catch(Exception e){
						QqChat qqChat= new QqChat(m.getGetter(),m.getSender());
						ManageQqChat.addQqChat(m.getGetter()+" "+m.getSender(), qqChat);
					}
				}else if(m.getMesType().equals(MessageType.message_ret_onLineFriend))//返回在线好友的包，这个时候得到的可能是你刚登录是向服务器请求的好友包，
				{                                                                    //也可能是服务器向你发的刚上线的好友的包
					System.out.println("客户端接收到"+m.getCon());
					//String con=m.getCon();//这个时候得到的已经是在线好友
					//String friends[]=con.split(" ");//以空格分割，
					String getter=m.getGetter();
					System.out.println("getter="+getter);
					//修改相应的好友列表.
					//无法正常调用updateFriend,那就得到getter的线程
					//QqFriendList qqFriendList=ManageQ qFriendList.getQqFriendList(getter);
					//qqFriendList.updateFriend(m);//???
					(ManageQqFriendList.getQqFriendList(getter)).updateFriend(m);
				//	if(qqFriendList)
					//更新在线好友.如果好友列表非空？？？其实无论空不空都得跟新吧
					//if(qqFriendList!=null)
					//{
						//qqFriendList.upateFriend(m);
					//}
				}
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
	}

	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}
	
}
