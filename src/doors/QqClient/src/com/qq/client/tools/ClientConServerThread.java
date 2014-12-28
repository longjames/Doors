/**
 * ���ǿͻ��˺ͷ������˱���ͨѶ���߳�.
 */
package com.qq.client.tools;

import java.io.*;
import java.net.*;

import com.qq.client.view.QqChat;//�������
import com.qq.client.view.QqClientLogin;
import com.qq.client.view.QqFriendList;//���ѽ���
import com.qq.common.*;
public class ClientConServerThread extends Thread {

	private Socket s;
	
	//���캯��
	public ClientConServerThread(Socket s)
	{
		this.s=s;
	}
	
	public void run()
	{
		while(true)
		{
			//��ͣ�Ķ�ȡ�ӷ������˷�������Ϣ
			try {
				
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Message m=(Message)ois.readObject();
			/*	System.out.println("��ȡ���ӷ���������Ϣ"+ m.getSender() +" �� "+m.getGetter()+" ����"+
						m.getCon());*/
				
				if(m.getMesType().equals(MessageType.message_comm_mes))//��ͨ��Ϣ��
				{
				
					//�Ѵӷ����������Ϣ����ʾ������ʾ���������
					System.out.println("�յ���������������Ϣ");
					ChattingRecord chat_record =new ChattingRecord();
					chat_record.WriteRecord(m, m.getGetter(), m.getSender());
					try{
					QqChat qqChat=ManageQqChat.getQqChat(m.getGetter()+" "+m.getSender());//�õ��Ǹ�����,�����������̲߳������أ�
					//��ʾ                                                                                                                                                                                                                                    //Ҳ����˵����򲻴��ڵ�ʱ��,��ʱ����ѡ�񵯳�һ���µ������Ҳ���ԣ�
					qqChat.showMessage(m);
					}catch(Exception e){
						QqChat qqChat= new QqChat(m.getGetter(),m.getSender());
						ManageQqChat.addQqChat(m.getGetter()+" "+m.getSender(), qqChat);
					}
				}else if(m.getMesType().equals(MessageType.message_ret_onLineFriend))//�������ߺ��ѵİ������ʱ��õ��Ŀ�������յ�¼�������������ĺ��Ѱ���
				{                                                                    //Ҳ�����Ƿ��������㷢�ĸ����ߵĺ��ѵİ�
					System.out.println("�ͻ��˽��յ�"+m.getCon());
					//String con=m.getCon();//���ʱ��õ����Ѿ������ߺ���
					//String friends[]=con.split(" ");//�Կո�ָ
					String getter=m.getGetter();
					System.out.println("getter="+getter);
					//�޸���Ӧ�ĺ����б�.
					//�޷���������updateFriend,�Ǿ͵õ�getter���߳�
					//QqFriendList qqFriendList=ManageQ qFriendList.getQqFriendList(getter);
					//qqFriendList.updateFriend(m);//???
					(ManageQqFriendList.getQqFriendList(getter)).updateFriend(m);
				//	if(qqFriendList)
					//�������ߺ���.��������б�ǿգ�������ʵ���ۿղ��ն��ø��°�
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
