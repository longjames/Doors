/**
 * 这是qq服务器，它在监听，等待某个qq客户端，来连接
 */
package com.qq.server.model;

import com.qq.common.*;

import java.net.*;
import java.io.*;
import java.sql.ResultSet;
import java.util.*;

import com.qq.server.db.accessDB;

public class MyQqServer implements Runnable {
	
				ServerSocket ss ;
				Socket s;
				boolean flag =true;
	public void CloseServer() {
		    System.out.println("我是服务器，拜拜");
			 flag =false;
		    
		
	}

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//public MyQqServer() {

			try {
				// 在9999监听
				System.out.println("我是服务器，在9999监听");
				ServerSocket ss = new ServerSocket(9999);
				// 阻塞,等待连接
				while (flag) {
					Socket s = ss.accept();

					// 接收客户端发来的信息.

					ObjectInputStream ois = new ObjectInputStream(
							s.getInputStream());
					User u = (User) ois.readObject();
					String ID = u.getUserId();
					String psw = u.getPasswd();
					String sta = u.getState();
					System.out.println("服务器接收到用户id:" + u.getUserId() + "  密码:"
							+ u.getPasswd() + "状态:" + u.getState());
					if (sta.equals ("1")) {// 登录信号

						accessDB db = new accessDB();//
						// 将ID和password与数据库里面的数据进行比对
						Message m = new Message();// 用来设置各种信号
						ObjectOutputStream oos = new ObjectOutputStream(
								s.getOutputStream());
						String sql = "Select * from user where username='" + ID
								+ "'and userpassword='" + psw + "'";
						ResultSet rs = db.executeQuery(sql);
						if (rs.next())// 找到了与之匹配的ID和密码
						{
							// 返回一个成功登陆的信息报
							m.setMesType("1");
							oos.writeObject(m);

							// 这里就单开一个线程，让该线程与该客户端保持通讯.多线程！！！！！！！！！！！！！！
							SerConClientThread scct = new SerConClientThread(s);
							ManageClientThread.addClientThread(u.getUserId(), scct);///UserID作为KEY值，线程名作为Value
							// 启动与该客户端通信的线程.
							scct.start();

							// 并通知其它在线用户.?????
							scct.notifyOther(u.getUserId());
						} else {
							m.setMesType("2");// 验证失败
							oos.writeObject(m);
							// 关闭Socket
							s.close();

						}
					}
					if (sta.equals ("3")) {// 注册信号
						accessDB db = new accessDB();//
						// 将ID和password与数据库里面的数据进行比对
						Message m = new Message();// 用来设置各种信号
						ObjectOutputStream oos = new ObjectOutputStream(
								s.getOutputStream());

						String sql1 = "Select * from user where username='" + ID
								+ "'";
						ResultSet rs = db.executeQuery(sql1);
						if (rs.next())// 失败
						{
							m.setMesType("9");
							oos.writeObject(m);

						} else {// 成功注册
							System.out.println("我是服务器，在在插入数据之前");
							String sql2 = "Insert into user(username,userpassword) values('"
									+ ID + "','" + psw + "')";
							db.executeUpdate(sql2);
							m.setMesType("6");
							oos.writeObject(m);
						}

					}

				}

			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			} finally {

			}

		//}

		
	}

}
