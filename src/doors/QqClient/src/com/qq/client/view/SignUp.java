/**
 * ����:qq�ͻ���ע�����   ��ʱֻҪ�˺ź�����
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
	//���山��   ��ӭҳ 
	JLabel jbl1;
          
     //���� �в�
	JPanel jp1;
    JLabel jbl2,jbl3,jbl4,jbl5,jbl6;//�������룬�˺�,�Ա� ���������£�email
    JTextField jtf1,jtf2,jtf3,jtf4;//�����˺����룬Email���룬������������,�Ա�����
    JPasswordField jpass;//������������
    
    //�����ϲ�
    JButton jbut;//����ȷ�����ݣ��ϴ�����ע��
    
    public static void main (String[] args){
    	//System.out.println("1");
    	SignUp signup = new SignUp();
    	//System.out.println("2");
    }
    
    public SignUp()
    {
    	//������   ��ӭҳ
    	jbl1 =new JLabel ("��ӭ����Doors",JLabel.CENTER);
    	
    	//�����в�
    	jp1= new JPanel (new GridLayout(2,2));  //?��ʱֻҪ�˺ź�����
    	
    	jbl2 = new JLabel ("�˺�",JLabel.CENTER);
    	jbl3 = new JLabel ("����",JLabel.CENTER);
   // 	jbl4 = new JLabel ("�Ա�(M/F)",JLabel.CENTER);
    //	jbl5 = new JLabel ("��������(19940722)",JLabel.CENTER);
    //	jbl6 = new JLabel ("E-mail",JLabel.CENTER);//5��JLabel
    	
    	////������������
    	jtf1 = new JTextField();
    //	jtf2 = new JTextField();
   // 	jtf3 = new JTextField();
   // 	jtf4 = new JTextField();
    	jpass = new JPasswordField();
    	
    	//��˳�����ؼ�
    	jp1.add(jbl2);//�˺�
    	jp1.add(jtf1);
    	
    	jp1.add(jbl3);//����
    	jp1.add(jpass);
    	
    //	jp1.add(jbl4);//�Ա�
    //	jp1.add(jtf2);
    	
    //	jp1.add(jbl5);//��������
    //	jp1.add(jtf3);
    	
    //	jp1.add(jbl6);//E-mail
    //	jp1.add(jtf4);
    	//�����ϲ�
    	jbut = new JButton("�ύע��");
    	jbut.addActionListener(this);
         ////���֣��������в����ϲ�
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
			u.setState("3");//״̬��ע��
			
			//System.out.println("signup sucecced?"+ u.getUserId() + u.getPasswd());
    		if (qqClientUser.checkSignup(u)){//
    			System.out.println("signup sucecced");
    			this.dispose();
    			QqClientLogin qqClientLogin=new QqClientLogin();
    		}
    		else{
    			//�˴���ÿ��Ե���һ�����ڣ�����ע��
    			JOptionPane.showMessageDialog(this,"ע��ʧ��,����ע�ᣡ��");
    			this.dispose();
    			SignUp signup = new SignUp();
    		}
    		
    	}
    }
    
}