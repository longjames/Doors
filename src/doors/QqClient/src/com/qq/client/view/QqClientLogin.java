/**
 * ����:qq�ͻ��˵�¼����
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

	//���山����Ҫ�����
	
	JLabel jbl1;
	//�����в���Ҫ�����
	
	JTabbedPane jtp;//.�в�������JPanel,��һ����ѡ����ڹ���
	JPanel jp2,jp3,jp4;
	JLabel jp2_jbl1,jp2_jbl2;//,jp2_jbl4,jp2_jbl3
	//JButton jp2_jb1;
	JComboBox jcbox=null;//JTextField jp2_jtf;//qq haoma
	JPasswordField jp2_jpf;//mima
	JCheckBox jp2_jcb2;//jp2_jcb1,
	//String[] id={"1","2","3","4","5"};//������¼������id����
	//String [] historyId=null;//���ļ��л�ȡ��ʷ����
	String defaultId="luojun";//Ĭ��id����������idΪĬ��id

	//�����ϲ���Ҫ�����
	JPanel jp1;
	JButton jp1_jb1,jp1_jb2,jp1_jb3;//�������3��
	String userid =null;//������id������
	String password=null;
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		QqClientLogin qqClientLogin=new QqClientLogin();
	
	}
	
	
	public QqClientLogin()
	{
		//������
		jbl1=new JLabel(new ImageIcon("image/tou.jpg"));
		
	
		//�����в�
		jp2=new JPanel(new GridLayout(3,2));//3X2
		
		jp2_jbl1=new JLabel("Doors����",JLabel.CENTER);
		//jp2_jbl1.addFocusListener(this);//�����ý��㣬��������������˺ţ��뿪�����ѯ�Ƿ�������ס����
		jp2_jbl2=new JLabel("Doors����",JLabel.CENTER);
		//jp2_jbl3=new JLabel("��������",JLabel.CENTER);
		//jp2_jbl3.setForeground(Color.blue);//������ɫ
		//jp2_jbl4=new JLabel("�������뱣��",JLabel.CENTER);
		//jp2_jb1=new JButton(new ImageIcon("image/clear.gif"));
		jcbox=new JComboBox(ChattingRecord.ReadHistroyId());
		jcbox.setEditable(true);//�ɱ༭
		ComboBoxEditor editor=jcbox.getEditor();//���룬����TextFiled
		jcbox.configureEditor(editor,defaultId);//Ĭ��id
		jcbox.addItemListener(this);
		//jcbox.addActionListener(this);
		jp2_jpf=new JPasswordField(password);
		//jp2_jcb1=new JCheckBox("�����¼");
		jp2_jcb2=new JCheckBox("��ס����");
		
		//�ѿؼ�����˳����뵽jp2
		jp2.add(jp2_jbl1);
		jp2.add(jcbox);//qq���� 
		//jp2.add(jp2_jb1);
		////
		jp2.add(jp2_jbl2);//qq ����
		jp2.add(jp2_jpf);
		//jp2.add(jp2_jbl3);
		////
		//jp2.add(jp2_jcb1);//ѡ��
		jp2.add(jp2_jcb2);
		//jp2.add(jp2_jbl4);
		//����ѡ�����
		jtp=new JTabbedPane();//��3��ѡ�����
		jtp.add("Doors����",jp2);
		jp3= new JPanel();
		jtp.add("�ֻ�����",jp3);
		jp4=new JPanel();
		jtp.add("�����ʼ�",jp4);
		
		//�����ϲ�
		jp1=new JPanel();
		jp1_jb1=new JButton(new ImageIcon("image/denglu.gif"));
		//��Ӧ�û������¼
		jp1_jb1.addActionListener(this);
		jp1_jb2=new JButton(new ImageIcon("image/quxiao.gif"));
		//-----------
		jp1_jb3=new JButton(new ImageIcon("image/xiangdao.gif"));
		jp1_jb3.addActionListener(this);                         //�������������ѡ��
		//-------------
		//��������ť���뵽jp1
		jp1.add(jp1_jb1);
		jp1.add(jp1_jb2);
		jp1.add(jp1_jb3);
		////���֣��������в����ϲ�
		this.add(jbl1,"North");
		this.add(jtp,"Center");
		this.add(jp1,"South");
		this.setSize(350, 240);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//����û������¼
		if(arg0.getSource()==jp1_jb1)
		{
			QqClientUser qqClientUser=new QqClientUser();
			User u=new User();
			u.setUserId(userid);
			u.setPasswd(new String(jp2_jpf.getPassword()));
			u.setState("1");//�����¼�Ļ�����2
			
			if(qqClientUser.checkUser(u))
			{
				//�������˼�ס���룬д��ĳ���ļ���,���������д,�˺�Ҫ��ס��
				ChattingRecord.WriteHistoryId(u.getUserId());
				if(jp2_jcb2.isSelected()){
					ChattingRecord.WritePassword(u.getUserId(),u.getPasswd());
				}
				JOptionPane.showMessageDialog(this,"��¼�ɹ�");//��¼�ɹ��ᵯ��
				try {
					//�Ѵ��������б�������ǰ.qqlistΪ��ǰ���õĺ����б���������ڼ�����һ������
					QqFriendList qqList=new QqFriendList(u.getUserId());
					//setFriendlistname(u.getUserId(), qqList);
					ManageQqFriendList.addQqFriendList(u.getUserId(), qqList);

					//����һ��Ҫ�󷵻����ߺ��ѵ������.��������ͨ��U��ID�õ��߳��������õ���IO��
					ObjectOutputStream oos=new ObjectOutputStream
					(ManageClientConServerThread.getClientConServerThread(u.getUserId()).getS().getOutputStream());
					//����IO����U
					//��һ��Message���������ߺ���
					Message m=new Message();
					m.setMesType(MessageType.message_get_onLineFriend);
                  // ָ����Ҫ�������qq�ŵĺ������.���������Message m��˭���յ���???????
					m.setSender(u.getUserId());
					oos.writeObject(m);
				} catch (Exception e) {
					e.printStackTrace();
				
					// TODO: handle exception
				}
				
				//�رյ���¼����
				this.dispose();
			}else{
				JOptionPane.showMessageDialog(this,"�û����������");
			}
		}
		//����û����ע��
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
	public void itemStateChanged(ItemEvent e){//ItemListener����ֻ��itemStateChanged()һ���������ڴ�ʵ������
		   if (e.getStateChange()==ItemEvent.SELECTED){//����ѡ�е������Ϊ״̬�ı������֣�һ��û��ѡ�У�һ��ѡ����
		  try{
		   userid=(String) e.getItem();//���õ���id���ļ��в�ѯһ�Σ����Ƿ񱣳��ļ�,û�л᷵�ؿ�
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