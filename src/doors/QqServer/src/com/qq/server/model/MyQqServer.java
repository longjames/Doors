/**
 * ����qq�����������ڼ������ȴ�ĳ��qq�ͻ��ˣ�������
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
		    System.out.println("���Ƿ��������ݰ�");
			 flag =false;
		    
		
	}

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//public MyQqServer() {

			try {
				// ��9999����
				System.out.println("���Ƿ���������9999����");
				ServerSocket ss = new ServerSocket(9999);
				// ����,�ȴ�����
				while (flag) {
					Socket s = ss.accept();

					// ���տͻ��˷�������Ϣ.

					ObjectInputStream ois = new ObjectInputStream(
							s.getInputStream());
					User u = (User) ois.readObject();
					String ID = u.getUserId();
					String psw = u.getPasswd();
					String sta = u.getState();
					System.out.println("���������յ��û�id:" + u.getUserId() + "  ����:"
							+ u.getPasswd() + "״̬:" + u.getState());
					if (sta.equals ("1")) {// ��¼�ź�

						accessDB db = new accessDB();//
						// ��ID��password�����ݿ���������ݽ��бȶ�
						Message m = new Message();// �������ø����ź�
						ObjectOutputStream oos = new ObjectOutputStream(
								s.getOutputStream());
						String sql = "Select * from user where username='" + ID
								+ "'and userpassword='" + psw + "'";
						ResultSet rs = db.executeQuery(sql);
						if (rs.next())// �ҵ�����֮ƥ���ID������
						{
							// ����һ���ɹ���½����Ϣ��
							m.setMesType("1");
							oos.writeObject(m);

							// ����͵���һ���̣߳��ø��߳���ÿͻ��˱���ͨѶ.���̣߳���������������������������
							SerConClientThread scct = new SerConClientThread(s);
							ManageClientThread.addClientThread(u.getUserId(), scct);///UserID��ΪKEYֵ���߳�����ΪValue
							// ������ÿͻ���ͨ�ŵ��߳�.
							scct.start();

							// ��֪ͨ���������û�.?????
							scct.notifyOther(u.getUserId());
						} else {
							m.setMesType("2");// ��֤ʧ��
							oos.writeObject(m);
							// �ر�Socket
							s.close();

						}
					}
					if (sta.equals ("3")) {// ע���ź�
						accessDB db = new accessDB();//
						// ��ID��password�����ݿ���������ݽ��бȶ�
						Message m = new Message();// �������ø����ź�
						ObjectOutputStream oos = new ObjectOutputStream(
								s.getOutputStream());

						String sql1 = "Select * from user where username='" + ID
								+ "'";
						ResultSet rs = db.executeQuery(sql1);
						if (rs.next())// ʧ��
						{
							m.setMesType("9");
							oos.writeObject(m);

						} else {// �ɹ�ע��
							System.out.println("���Ƿ����������ڲ�������֮ǰ");
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
