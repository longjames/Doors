/**
 * 功能:qq客户端登录界面
 */
package com.qq.client.view;
import com.qq.common.*;
import com.qq.client.tools.*;

import java.io.*;

import javax.swing.*;

import com.qq.client.model.QqClientUser;
import com.qq.common.User;

import java.awt.*;
import java.awt.event.*;
public class QqClientLogin extends JFrame implements ItemListener, ActionListener,FocusListener{

	//定义北部需要的组件
	
	JLabel jbl1;
	//定义中部需要的组件
	
	JTabbedPane jtp;//.中部有三个JPanel,有一个叫选项卡窗口管理
	JPanel jp2,jp3,jp4;
	JLabel jp2_jbl1,jp2_jbl2;//,jp2_jbl4,jp2_jbl3
	//JButton jp2_jb1;
	JComboBox jcbox=null;//JTextField jp2_jtf;//qq haoma
	JPasswordField jp2_jpf;//mima
	JCheckBox jp2_jcb2;//jp2_jcb1,
	//String[] id={"1","2","3","4","5"};//用来记录以往的id输入
	//String [] historyId=null;//从文件中获取历史输入
	String defaultId="luojun";//默认id，最近输入的id为默认id

	//定义南部需要的组件
	JPanel jp1;
	JButton jp1_jb1,jp1_jb2,jp1_jb3;//最下面的3个
	String userid =null;//真正的id和密码
	String password=null;
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		QqClientLogin qqClientLogin=new QqClientLogin();
	
	}
	
	
	public QqClientLogin()
	{
		//处理北部
		jbl1=new JLabel(new ImageIcon("image/tou.jpg"));
		
	
		//处理中部
		jp2=new JPanel(new GridLayout(3,2));//3X2
		
		jp2_jbl1=new JLabel("Doors号码",JLabel.CENTER);
		//jp2_jbl1.addFocusListener(this);//如果获得焦点，下拉出最近几个账号，离开焦点查询是否曾经记住密码
		jp2_jbl2=new JLabel("Doors密码",JLabel.CENTER);
		//jp2_jbl3=new JLabel("忘记密码",JLabel.CENTER);
		//jp2_jbl3.setForeground(Color.blue);//设置颜色
		//jp2_jbl4=new JLabel("申请密码保护",JLabel.CENTER);
		//jp2_jb1=new JButton(new ImageIcon("image/clear.gif"));
		jcbox=new JComboBox(ChattingRecord.ReadHistroyId());
		jcbox.setEditable(true);//可编辑
		ComboBoxEditor editor=jcbox.getEditor();//输入，就像TextFiled
		jcbox.configureEditor(editor,defaultId);//默认id
		jcbox.addItemListener(this);
		//jcbox.addActionListener(this);
		jp2_jpf=new JPasswordField(password);
		//jp2_jcb1=new JCheckBox("隐身登录");
		jp2_jcb2=new JCheckBox("记住密码");
		
		//把控件按照顺序加入到jp2
		jp2.add(jp2_jbl1);
		jp2.add(jcbox);//qq号码 
		//jp2.add(jp2_jb1);
		////
		jp2.add(jp2_jbl2);//qq 密码
		jp2.add(jp2_jpf);
		//jp2.add(jp2_jbl3);
		////
		//jp2.add(jp2_jcb1);//选项
		jp2.add(jp2_jcb2);
		//jp2.add(jp2_jbl4);
		//创建选项卡窗口
		jtp=new JTabbedPane();//将3个选项卡加入
		jtp.add("Doors号码",jp2);
		jp3= new JPanel();
		jtp.add("手机号码",jp3);
		jp4=new JPanel();
		jtp.add("电子邮件",jp4);
		
		//处理南部
		jp1=new JPanel();
		jp1_jb1=new JButton(new ImageIcon("image/denglu.gif"));
		//响应用户点击登录
		jp1_jb1.addActionListener(this);
		jp1_jb2=new JButton(new ImageIcon("image/quxiao.gif"));
		//-----------
		jp1_jb3=new JButton(new ImageIcon("image/xiangdao.gif"));
		jp1_jb3.addActionListener(this);                         //监听下面的三个选项
		//-------------
		//把三个按钮放入到jp1
		jp1.add(jp1_jb1);
		jp1.add(jp1_jb2);
		jp1.add(jp1_jb3);
		////布局，北部，中部，南部
		this.add(jbl1,"North");
		this.add(jtp,"Center");
		this.add(jp1,"South");
		this.setSize(350, 240);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//如果用户点击登录
		if(arg0.getSource()==jp1_jb1)
		{
			QqClientUser qqClientUser=new QqClientUser();
			User u=new User();
			u.setUserId(userid);
			u.setPasswd(new String(jp2_jpf.getPassword()));
			u.setState("1");//隐身登录的话就是2
			
			if(qqClientUser.checkUser(u))
			{
				//如果点击了记住密码，写入某个文件夹,这里待会再写,账号要记住吧
				ChattingRecord.WriteHistoryId(u.getUserId());
				if(jp2_jcb2.isSelected()){
					ChattingRecord.WritePassword(u.getUserId(),u.getPasswd());
				}
				JOptionPane.showMessageDialog(this,"登录成功");//登录成功会弹出
				try {
					//把创建好友列表的语句提前.qqlist为提前做好的好友列表，将这个窗口加入了一个管理
					QqFriendList qqList=new QqFriendList(u.getUserId());
					//setFriendlistname(u.getUserId(), qqList);
					ManageQqFriendList.addQqFriendList(u.getUserId(), qqList);

					//发送一个要求返回在线好友的请求包.序列流，通过U的ID得到线程名，并得到其IO流
					ObjectOutputStream oos=new ObjectOutputStream
					(ManageClientConServerThread.getClientConServerThread(u.getUserId()).getS().getOutputStream());
					//现在IO流归U
					//做一个Message，请求在线好友
					Message m=new Message();
					m.setMesType(MessageType.message_get_onLineFriend);
                  // 指明我要的是这个qq号的好友情况.，但是这个Message m是谁接收到呢???????
					m.setSender(u.getUserId());
					oos.writeObject(m);
				} catch (Exception e) {
					e.printStackTrace();
				
					// TODO: handle exception
				}
				
				//关闭掉登录界面
				this.dispose();
			}else{
				JOptionPane.showMessageDialog(this,"用户名密码错误");
			}
		}
		//如果用户点击注册
		if (arg0.getSource()==jp1_jb3)
		{
			this.dispose();
			SignUp signup = new SignUp();
			
		}
	}


	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void itemStateChanged(ItemEvent e){//ItemListener界面只有itemStateChanged()一个方法，在此实作它。
		   if (e.getStateChange()==ItemEvent.SELECTED){//这是选中的那项，因为状态改变有两种，一个没有选中，一个选中了
		  try{
		   userid=(String) e.getItem();//将得到的id到文件中查询一次，看是否保持文件,没有会返回空
		  // password= "luojunniubi";
		  password= ChattingRecord.ReadPassword(userid);
		  System.out.println("password"+password);
		  System.out.println("userid"+userid);
		  System.out.println(e.getItem());
		  jp2_jpf.setText(password);
		   }finally{//
		   
		   }
		   }
		   }
	
}