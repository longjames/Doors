/**
 *  
 * ���Ƿ������˵Ŀ��ƽ��棬��������������������رշ�����
 * ���Թ���ͼ���û�.
 */
package com.qq.server.view;

//���Ż����رշ�������Button��û����
import javax.swing.*;

import com.qq.server.model.MyQqServer;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
public class MyServerFrame extends JFrame implements ActionListener {

	
	JPanel jp1;
	JButton jb1,jb2;
	MyQqServer MyClose;
	MyServerFrame mysf;
	Thread tr;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyServerFrame mysf=new MyServerFrame();
	}
	
	public MyServerFrame()
	{
		jp1=new JPanel();
		jb1=new JButton("����������");
		jb1.addActionListener(this);
		jb2=new JButton("�رշ�����");
		jb2.addActionListener(this);
		jp1.add(jb1);
		jp1.add(jb2);
		
		
		this.add(jp1);
		this.setSize(500, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		if(arg0.getSource()==jb1)
		{
			MyClose=new MyQqServer();
			Thread tr = new Thread(MyClose);
			tr.start();
			/*Runnable doWorkRunnable=new Runnable(){  
				public void run(){
					MyClose=new MyQqServer();//Ӧ�ø���������̣߳���Ϊ��������һֱ�ں�ִ̨�У����¸����޷����jb2��Ҳ�޷��رմ���
				}
    			//MyServerFrame mysf=new MyServerFrame();}  
				};
				SwingUtilities.invokeLater(doWorkRunnable); */
		}
		if (arg0.getSource()==jb2){
			MyClose.CloseServer();
			//MyClose.CloseServer();
			//MyServerFrame mysf=new MyServerFrame();
		}
		
		
	}
	

}
