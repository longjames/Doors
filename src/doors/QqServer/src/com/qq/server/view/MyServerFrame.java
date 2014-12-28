/**
 *  
 * 这是服务器端的控制界面，可以完成启动服务器，关闭服务器
 * 可以管理和监控用户.
 */
package com.qq.server.view;

//待优化，关闭服务器的Button还没有做
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
		jb1=new JButton("启动服务器");
		jb1.addActionListener(this);
		jb2=new JButton("关闭服务器");
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
					MyClose=new MyQqServer();//应该跟这个加上线程，因为这个程序会一直在后台执行，导致根本无法点击jb2，也无法关闭窗口
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
