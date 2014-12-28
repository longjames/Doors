/**
 * 我的好友列表,(也包括陌生人，黑名单)
 */
package com.qq.client.view;

import com.qq.client.tools.*;
import com.qq.common.Message;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
public class QqFriendList extends JFrame implements ActionListener,MouseListener{

	//处理第一张卡片.
	JPanel js;
	JLabel js_l;
	JTextField js_jtf;
	JButton js_jb;
	
	JPanel jphy1,jphy2,jphy3;
	JButton jphy_jb1,jphy_jb2,jphy_jb3;
	JScrollPane jsp1;
	String owner;
	//处理第二张卡片(陌生人).
	
	JPanel jpmsr1,jpmsr2,jpmsr3;
	JButton jpmsr_jb1,jpmsr_jb2,jpmsr_jb3;
	JScrollPane jsp2;
	JLabel []jbls;
	//把整个JFrame设置成CardLayout
	CardLayout cl;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QqFriendList qqFriendList=new QqFriendList("lj");
	}
	//有好友上线时会自动跟新到FriendList
	/*public void login_call(String FriendId){
		for (int i=0;i<50;i++){
			int in=i+1;
			if (jbls[i].getText()==in+"Friend"){
				jbls[i].setText(FriendId);
				i=50;
			}
		}
	}*/
	
	//更新在线的好友情况，每次在跟新的时候都刷新一遍？还是？,跟新这块还得处理，因为会覆盖原有的在线好友
	public void updateFriend(Message m)
	{
		String onLineFriend[]=m.getCon().split(" ");
		 if (onLineFriend.length==1){//如果值为1时，两种情况，1是只有自己上线时，2是有人上线提醒，但是这两种情况都可以用这种解决
			 for(int j=0;j<50;j++)//找到第一个没有使用的Label
				{
					System.out.println("1");//可以运行到这，只是没有改变Jlabel中的jbls
					int jn=j+1;
					String f=new String(jn+"Friend");
				    if(jbls[j].getText().equals(f)){
					  jbls[j].setText(onLineFriend[0]);//jbls[i]=new JLabel(onLineFriend[i]+"",new ImageIcon("image/mm.jpg"),JLabel.LEFT);//将每个在线的好友的名字发送到jbls上//jbls[Integer.parseInt(onLineFriend[i])-1].setEnabled(true);
					  jbls[j].setEnabled(true);
					  System.out.println("jbls---"+onLineFriend[0]);
					  j=50;
				    }
				}
		 }if(onLineFriend.length>1){//z这种情况就是服务器返回好友列表了，要重新刷新好友列表
			 System.out.println(">1");
			 for(int i=0;i<onLineFriend.length;i++){
				 int in=i+1;
				 jbls[i].setText(onLineFriend[i]);
				 jbls[i].setEnabled(true);
			 }
			 
		 }
		 
		
	}
	
	public QqFriendList(String ownerId)//用户为ID的在线好友
	{
		this.owner=ownerId;
		//搜索栏
		js = new JPanel(new GridLayout(1,4));
		js_l = new JLabel("搜索/添加");
		js_jtf = new JTextField();
		js_jb = new JButton("确定");
		js.add(js_l);
		js.add(js_jtf);
		js.add(js_jb);
		
		
		//处理第一张卡片(显示好友列表)
		jphy_jb1=new JButton("我的好友");
		jphy_jb2=new JButton("陌生人");
		jphy_jb2.addActionListener(this);
		jphy_jb3=new JButton("搜索/添加");
		jphy_jb3.addActionListener(this);
		jphy1=new JPanel(new BorderLayout());
		//假定有50个好友
		jphy2=new JPanel(new GridLayout(50,1,4,4));
		
		//给jphy2，初始化50好友.
		jbls =new JLabel[50];
		
		for(int i=0;i<jbls.length;i++)
		{
			int in=i+1;
			String jbls_friendsname =in+"Friend";//转化成String，为了后面的ID，可以设置成字母数字，字符
			jbls[i]=new JLabel(jbls_friendsname+"",new ImageIcon("image/mm.jpg"),JLabel.LEFT);
			jbls[i].setEnabled(false);
			/*if(jbls[i].getText().equals(ownerId))
			{
				jbls[i].setEnabled(true);
			}*/
			jbls[i].addMouseListener(this);
			jphy2.add(jbls[i]);
			
			
		}
		
		jphy3=new JPanel(new GridLayout(2,1));
		//把两个按钮加入到jphy3
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);
		
		
		jsp1=new JScrollPane(jphy2);
		
		
		//对jphy1,初始化
		jphy1.add(jphy_jb1,"North");
		jphy1.add(jsp1,"Center");
		jphy1.add(jphy3,"South");
		
		
		//处理第二张卡片
		
		
		jpmsr_jb1=new JButton("我的好友");
		jpmsr_jb1.addActionListener(this);
		jpmsr_jb2=new JButton("陌生人");
		jpmsr_jb3=new JButton("搜索/添加");
		jpmsr1=new JPanel(new BorderLayout());
		//假定有20个陌生人
		jpmsr2=new JPanel(new GridLayout(20,1,4,4));
		
		//给jphy2，初始化20陌生人.
		JLabel []jbls2=new JLabel[20];
		
		for(int i=0;i<jbls2.length;i++)
		{
			jbls2[i]=new JLabel(i+1+"",new ImageIcon("image/mm.jpg"),JLabel.LEFT);
			jpmsr2.add(jbls2[i]);
		}
		
		jpmsr3=new JPanel(new GridLayout(2,1));
		//把两个按钮加入到jphy3
		jpmsr3.add(jpmsr_jb1);
		jpmsr3.add(jpmsr_jb2);
		
		
		jsp2=new JScrollPane(jpmsr2);
		
		
		//对jphy1,初始化
		jpmsr1.add(jpmsr3,"North");
		jpmsr1.add(jsp2,"Center");
		jpmsr1.add(jpmsr_jb3,"South");
		
		
		cl=new CardLayout();
		this.setLayout(cl);
		this.add(jphy1,"1"); 
		this.add(jpmsr1,"2");
		this.add(js,"3");
		//在窗口显示自己的编号.
		this.setTitle(ownerId);
		this.setSize(140, 400);
		this.setVisible(true);
		
		
	}

	public void actionPerformed(ActionEvent arg0) {//卡片选项
		// TODO Auto-generated method stub
		//如果点击了陌生人按钮，就显示第二张卡片
		if(arg0.getSource()==jphy_jb2)
		{
			cl.show(this.getContentPane(), "2");
		}
		if(arg0.getSource()==jpmsr_jb1){
			cl.show(this.getContentPane(), "1");
		}
		if(arg0.getSource()==jphy_jb3)
		{
			cl.show(this.getContentPane(), "3");
		}
	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//响应用户双击的事件，并得到好友的编号.
		if(arg0.getClickCount()==2)
		{
			//得到该好友的编号
			String friendNo=((JLabel)arg0.getSource()).getText();//得到Jlabel的text
			//System.out.println("你希望和 "+friendNo+" 聊天");
			QqChat qqChat=new QqChat(this.owner,friendNo);
			
			//把聊天界面加入到管理类
			ManageQqChat.addQqChat(this.owner+" "+friendNo, qqChat);
			
		}
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		JLabel jl=(JLabel)arg0.getSource();
		jl.setForeground(Color.red);
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		JLabel jl=(JLabel)arg0.getSource();
		jl.setForeground(Color.black);
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
