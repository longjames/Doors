/**
 * �ҵĺ����б�,(Ҳ����İ���ˣ�������)
 */
package com.qq.client.view;

import com.qq.client.tools.*;
import com.qq.common.Message;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
public class QqFriendList extends JFrame implements ActionListener,MouseListener{

	//�����һ�ſ�Ƭ.
	JPanel js;
	JLabel js_l;
	JTextField js_jtf;
	JButton js_jb;
	
	JPanel jphy1,jphy2,jphy3;
	JButton jphy_jb1,jphy_jb2,jphy_jb3;
	JScrollPane jsp1;
	String owner;
	//����ڶ��ſ�Ƭ(İ����).
	
	JPanel jpmsr1,jpmsr2,jpmsr3;
	JButton jpmsr_jb1,jpmsr_jb2,jpmsr_jb3;
	JScrollPane jsp2;
	JLabel []jbls;
	//������JFrame���ó�CardLayout
	CardLayout cl;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QqFriendList qqFriendList=new QqFriendList("lj");
	}
	//�к�������ʱ���Զ����µ�FriendList
	/*public void login_call(String FriendId){
		for (int i=0;i<50;i++){
			int in=i+1;
			if (jbls[i].getText()==in+"Friend"){
				jbls[i].setText(FriendId);
				i=50;
			}
		}
	}*/
	
	//�������ߵĺ��������ÿ���ڸ��µ�ʱ��ˢ��һ�飿���ǣ�,������黹�ô�����Ϊ�Ḳ��ԭ�е����ߺ���
	public void updateFriend(Message m)
	{
		String onLineFriend[]=m.getCon().split(" ");
		 if (onLineFriend.length==1){//���ֵΪ1ʱ�����������1��ֻ���Լ�����ʱ��2�������������ѣ�������������������������ֽ��
			 for(int j=0;j<50;j++)//�ҵ���һ��û��ʹ�õ�Label
				{
					System.out.println("1");//�������е��⣬ֻ��û�иı�Jlabel�е�jbls
					int jn=j+1;
					String f=new String(jn+"Friend");
				    if(jbls[j].getText().equals(f)){
					  jbls[j].setText(onLineFriend[0]);//jbls[i]=new JLabel(onLineFriend[i]+"",new ImageIcon("image/mm.jpg"),JLabel.LEFT);//��ÿ�����ߵĺ��ѵ����ַ��͵�jbls��//jbls[Integer.parseInt(onLineFriend[i])-1].setEnabled(true);
					  jbls[j].setEnabled(true);
					  System.out.println("jbls---"+onLineFriend[0]);
					  j=50;
				    }
				}
		 }if(onLineFriend.length>1){//z����������Ƿ��������غ����б��ˣ�Ҫ����ˢ�º����б�
			 System.out.println(">1");
			 for(int i=0;i<onLineFriend.length;i++){
				 int in=i+1;
				 jbls[i].setText(onLineFriend[i]);
				 jbls[i].setEnabled(true);
			 }
			 
		 }
		 
		
	}
	
	public QqFriendList(String ownerId)//�û�ΪID�����ߺ���
	{
		this.owner=ownerId;
		//������
		js = new JPanel(new GridLayout(1,4));
		js_l = new JLabel("����/���");
		js_jtf = new JTextField();
		js_jb = new JButton("ȷ��");
		js.add(js_l);
		js.add(js_jtf);
		js.add(js_jb);
		
		
		//�����һ�ſ�Ƭ(��ʾ�����б�)
		jphy_jb1=new JButton("�ҵĺ���");
		jphy_jb2=new JButton("İ����");
		jphy_jb2.addActionListener(this);
		jphy_jb3=new JButton("����/���");
		jphy_jb3.addActionListener(this);
		jphy1=new JPanel(new BorderLayout());
		//�ٶ���50������
		jphy2=new JPanel(new GridLayout(50,1,4,4));
		
		//��jphy2����ʼ��50����.
		jbls =new JLabel[50];
		
		for(int i=0;i<jbls.length;i++)
		{
			int in=i+1;
			String jbls_friendsname =in+"Friend";//ת����String��Ϊ�˺����ID���������ó���ĸ���֣��ַ�
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
		//��������ť���뵽jphy3
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);
		
		
		jsp1=new JScrollPane(jphy2);
		
		
		//��jphy1,��ʼ��
		jphy1.add(jphy_jb1,"North");
		jphy1.add(jsp1,"Center");
		jphy1.add(jphy3,"South");
		
		
		//����ڶ��ſ�Ƭ
		
		
		jpmsr_jb1=new JButton("�ҵĺ���");
		jpmsr_jb1.addActionListener(this);
		jpmsr_jb2=new JButton("İ����");
		jpmsr_jb3=new JButton("����/���");
		jpmsr1=new JPanel(new BorderLayout());
		//�ٶ���20��İ����
		jpmsr2=new JPanel(new GridLayout(20,1,4,4));
		
		//��jphy2����ʼ��20İ����.
		JLabel []jbls2=new JLabel[20];
		
		for(int i=0;i<jbls2.length;i++)
		{
			jbls2[i]=new JLabel(i+1+"",new ImageIcon("image/mm.jpg"),JLabel.LEFT);
			jpmsr2.add(jbls2[i]);
		}
		
		jpmsr3=new JPanel(new GridLayout(2,1));
		//��������ť���뵽jphy3
		jpmsr3.add(jpmsr_jb1);
		jpmsr3.add(jpmsr_jb2);
		
		
		jsp2=new JScrollPane(jpmsr2);
		
		
		//��jphy1,��ʼ��
		jpmsr1.add(jpmsr3,"North");
		jpmsr1.add(jsp2,"Center");
		jpmsr1.add(jpmsr_jb3,"South");
		
		
		cl=new CardLayout();
		this.setLayout(cl);
		this.add(jphy1,"1"); 
		this.add(jpmsr1,"2");
		this.add(js,"3");
		//�ڴ�����ʾ�Լ��ı��.
		this.setTitle(ownerId);
		this.setSize(140, 400);
		this.setVisible(true);
		
		
	}

	public void actionPerformed(ActionEvent arg0) {//��Ƭѡ��
		// TODO Auto-generated method stub
		//��������İ���˰�ť������ʾ�ڶ��ſ�Ƭ
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
		//��Ӧ�û�˫�����¼������õ����ѵı��.
		if(arg0.getClickCount()==2)
		{
			//�õ��ú��ѵı��
			String friendNo=((JLabel)arg0.getSource()).getText();//�õ�Jlabel��text
			//System.out.println("��ϣ���� "+friendNo+" ����");
			QqChat qqChat=new QqChat(this.owner,friendNo);
			
			//�����������뵽������
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
