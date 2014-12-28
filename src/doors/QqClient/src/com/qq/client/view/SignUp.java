/**
 * 功能:qq客户端注册界面   暂时只要账号和密码
 */
package com.qq.client.view;

import com.qq.common.*;
import com.qq.client.tools.*;

import java.io.*;

import javax.swing.*;

import com.qq.client.model.QqClientUser;
import com.qq.client.model.QqClientConServer;
import com.qq.common.User;

import java.awt.*;
import java.awt.event.*;
public class SignUp  extends JFrame implements ActionListener{
	//定义北部   欢迎页 
	JLabel jbl1;
          
     //定义 中部
	JPanel jp1;
    JLabel jbl2,jbl3,jbl4,jbl5,jbl6;//用于密码，账号,性别 ，出生年月，email
    JTextField jtf1,jtf2,jtf3,jtf4;//用于账号输入，Email输入，出生年月输入,性别输入
    JPasswordField jpass;//用于密码输入
    
    //定义南部
    JButton jbut;//用于确定数据，上传数据注册
    
    public static void main (String[] args){
    	//System.out.println("1");
    	SignUp signup = new SignUp();
    	//System.out.println("2");
    }
    
    public SignUp()
    {
    	//处理北部   欢迎页
    	jbl1 =new JLabel ("欢迎来到Doors",JLabel.CENTER);
    	
    	//处理中部
    	jp1= new JPanel (new GridLayout(2,2));  //?暂时只要账号和密码
    	
    	jbl2 = new JLabel ("账号",JLabel.CENTER);
    	jbl3 = new JLabel ("密码",JLabel.CENTER);
   // 	jbl4 = new JLabel ("性别(M/F)",JLabel.CENTER);
    //	jbl5 = new JLabel ("出生年月(19940722)",JLabel.CENTER);
    //	jbl6 = new JLabel ("E-mail",JLabel.CENTER);//5个JLabel
    	
    	////处理输入区域
    	jtf1 = new JTextField();
    //	jtf2 = new JTextField();
   // 	jtf3 = new JTextField();
   // 	jtf4 = new JTextField();
    	jpass = new JPasswordField();
    	
    	//按顺序加入控件
    	jp1.add(jbl2);//账号
    	jp1.add(jtf1);
    	
    	jp1.add(jbl3);//密码
    	jp1.add(jpass);
    	
    //	jp1.add(jbl4);//性别
    //	jp1.add(jtf2);
    	
    //	jp1.add(jbl5);//出生年月
    //	jp1.add(jtf3);
    	
    //	jp1.add(jbl6);//E-mail
    //	jp1.add(jtf4);
    	//处理南部
    	jbut = new JButton("提交注册");
    	jbut.addActionListener(this);
         ////布局，北部，中部，南部
    	this.add(jbl1,"North");
    	this.add(jp1,"Center");
    	this.add(jbut,"South");
    	this.setSize(350, 150);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setVisible(true);
    
    }
    public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
    	if (arg0.getSource()==jbut){
    		QqClientUser qqClientUser=new QqClientUser();
    		
			User u=new User();
			u.setUserId(jtf1.getText().trim());
			u.setPasswd(new String(jpass.getPassword()));
			u.setState("3");//状态是注册
			
			//System.out.println("signup sucecced?"+ u.getUserId() + u.getPasswd());
    		if (qqClientUser.checkSignup(u)){//
    			System.out.println("signup sucecced");
    			this.dispose();
    			QqClientLogin qqClientLogin=new QqClientLogin();
    		}
    		else{
    			//此处最好可以弹出一个窗口，重新注册
    			JOptionPane.showMessageDialog(this,"注册失败,重新注册！！");
    			this.dispose();
    			SignUp signup = new SignUp();
    		}
    		
    	}
    }
    
}