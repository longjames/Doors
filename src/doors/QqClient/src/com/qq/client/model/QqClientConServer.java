/**
 * ���ǿͻ������ӷ������ĺ�̨
 */
package com.qq.client.model;
import com.qq.client.tools.*;
import java.util.*;
import java.net.*;
import java.io.*;
import com.qq.common.*;
public class QqClientConServer {
 
	
	public  Socket s;
	
	//���͵�һ������
	public boolean sendLoginInfoToServer(Object o)//o�����û���������,��¼
	{
		boolean b=false;
		try {
			System.out.println("kk");
			s=new Socket("127.0.0.1",9999);
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());//	�������л��������user���л�֮��ʹ�ã�
			oos.writeObject(o);  //������  o д��oos���л���
			
			ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
			
			Message ms=(Message)ois.readObject();
			//���������֤�û���¼�ĵط�
			if(ms.getMesType().equals("1"))//��־
			{
				System.out.println("lj");
				//�ʹ���һ����qq�źͷ������˱���ͨѶ���ӵ��߳�
				ClientConServerThread ccst=new ClientConServerThread(s);
				//������ͨѶ�߳�
				ccst.start();
				ManageClientConServerThread.addClientConServerThread
				(((User)o).getUserId(), ccst);
			 	b=true;
			}else{
				//�ر�Scoket
				s.close();
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			
		}
		return b;
	}
	
	public boolean SendSignInfoToServer(Object o)    //ע��
	{
		boolean b=false;
		try {
			System.out.println("ע��");
			Socket s=new Socket("127.0.0.1",9999);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());//������л�
			oos.writeObject(o);  //���л��������Ϊ  ����o
			
            ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
			Message ms=(Message)ois.readObject();   /* �������յ��ע���  ���ص����ݣ�=�����ݷ��ص�ms����֪ע��Ľ������ʵ�������л�д�����Ϊms*/
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
