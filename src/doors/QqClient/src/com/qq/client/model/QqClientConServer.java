/**
 * 这是客户端连接服务器的后台
 */
package com.qq.client.model;
import com.qq.client.tools.*;
import java.util.*;
import java.net.*;
import java.io.*;
import com.qq.common.*;
public class QqClientConServer {
 
	
	public  Socket s;
	
	//发送第一次请求
	public boolean sendLoginInfoToServer(Object o)//o即是用户名和密码,登录
	{
		boolean b=false;
		try {
			System.out.println("kk");
			s=new Socket("127.0.0.1",9999);
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());//	对象序列化输出，将user序列化之后使用，
			oos.writeObject(o);  //将对象  o 写入oos序列化流
			
			ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
			
			Message ms=(Message)ois.readObject();
			//这里就是验证用户登录的地方
			if(ms.getMesType().equals("1"))//标志
			{
				System.out.println("lj");
				//就创建一个该qq号和服务器端保持通讯连接得线程
				ClientConServerThread ccst=new ClientConServerThread(s);
				//启动该通讯线程
				ccst.start();
				ManageClientConServerThread.addClientConServerThread
				(((User)o).getUserId(), ccst);
			 	b=true;
			}else{
				//关闭Scoket
				s.close();
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			
		}
		return b;
	}
	
	public boolean SendSignInfoToServer(Object o)    //注册
	{
		boolean b=false;
		try {
			System.out.println("注册");
			Socket s=new Socket("127.0.0.1",9999);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());//输出序列化
			oos.writeObject(o);  //序列化输出的流为  数据o
			
            ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
			Message ms=(Message)ois.readObject();   /* 用来接收点击注册后  返回的数据，=并根据返回的ms，得知注册的结果，其实就是序列化写入的流为ms*/
			if (ms.getMesType().equals("6")){
				System.out.println("lj");
				b=true;
				s.close();
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			
		}
		return b;
	}
}
