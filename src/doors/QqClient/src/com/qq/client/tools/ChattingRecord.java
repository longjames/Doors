package com.qq.client.tools;
import com.qq.common.Message;

import java.io.BufferedReader;  
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.FileReader;  
import java.io.RandomAccessFile;
public class ChattingRecord {
	    private Message m;
	    private String owner;
	    private String friendID;
	    public static void  WriteHistoryId(String idname){//��id����,����ID����ʱ�͸��£��������ֻ����5��,
	    	String idstr=idname;
	        String []idn= idstr.split(" ",5);//���ո�ָ�
	        String id="";
	        for(int i=0;i<idn.length;i++){
	        	  id = id +idn[i]+"\n";
	        }
	        File owner_file = new File ("F://Doors");//�ļ�Ҫһ��һ���Ľ���
			File history_file= new File("F://Doors//"+"DoorsHistoryId.txt");
			try{
				if (!owner_file.exists()){//����ļ������ھʹ���һ�����ļ�
					owner_file.mkdir();
				} 
	
				if (!history_file.exists()){//����ļ������ھʹ���һ�����ļ�
					history_file.createNewFile();
				}
				FileOutputStream fos = new FileOutputStream(history_file);
				fos.write((id).getBytes("GBK"));
			}catch(Exception e){
				e.printStackTrace();
			}
	        
	    }
	    
	    
	    //////
	    public static String[] ReadHistroyId(){
	    	String [] id={"1","2","3","4","5"};
	    	File owner_file = new File ("F://Doors");//�ļ�Ҫһ��һ���Ľ���
			File history_file= new File("F://Doors//"+"DoorsHistoryId.txt");
			try{
				if (!owner_file.exists()){//����ļ������ھʹ���һ�����ļ�
					owner_file.mkdir();
				} 
	
				if (!history_file.exists()){//����ļ������ھʹ���һ�����ļ�
					history_file.createNewFile();
					return id;
				}
			FileReader fileReader= new FileReader(history_file);
			BufferedReader bufferedReader=new  BufferedReader(fileReader);
			String read = null;
			String ids =null;
		    while((read=bufferedReader.readLine())!=null){  //����Ϣ,�ÿո�ֿ�
		     ids=ids + " "+read;
		    }
		    if(ids!=null){
		    id = ids.split(" ");
		    }
		    if(bufferedReader!=null){  
			    bufferedReader.close();  
			   }  
			  if(fileReader!=null){  
			    fileReader.close();
			  }
			}catch(Exception e){
				e.printStackTrace();
			}
	    	return id;
	    }
	    ///////�ڶ���ʱ����������ھ�Ҫ�жϣ���Ȼ���
	    public static String ReadPassword(String userid){//�˺� ����\n   �ĸ�ʽ�������ļ��У������������˺��������һ��String���棬Ȼ���ж��Ƿ����
	    	String []passwordAndId=null;
	    	String pai="";
	    	String password="";
	    	String id=userid;
	    	File owner_file = new File ("F://Doors");//�ļ�Ҫһ��һ���Ľ���
			File history_file= new File("F://Doors//"+"DoorsPassword.txt");
			try{
				if (!owner_file.exists()){//����ļ������ھʹ���һ�����ļ�
					owner_file.mkdir();
				} 
	
				if (!history_file.exists()){//����ļ������ھʹ���һ�����ļ�
					history_file.createNewFile();
					return password;
				}
			FileReader fileReader= new FileReader(history_file);
			BufferedReader bufferedReader=new  BufferedReader(fileReader);
			String read = null;
			
		    while((read=bufferedReader.readLine())!=null){  //����Ϣ,�ÿո�ֿ�
		    	pai=pai+read+" ";//�˺�+����
		    }
		    System.out.println("pai+"+pai);
		    if (pai!=null){
		    	passwordAndId = pai.split(" ");//��������������˺ţ��˺���ż��λ 
		    
		    //�ж��Ƿ�������ס���룬
		    for (int i=0;i<passwordAndId.length;i=i+2){
		    	//System.out.println(i);
		    	if(id.equals(passwordAndId[i])){
		    		//���ҵ���������������룬��������
		    		password=passwordAndId[i+1];
		    		i=passwordAndId.length;
		    	}
		    }
		    }
		    if(bufferedReader!=null){  
			    bufferedReader.close();  
			   }  
			  if(fileReader!=null){  
			    fileReader.close();
			  }
			}catch(Exception e){
				e.printStackTrace();
			}
	    	return password;
	    }
	    
	    
	    
	    
	    public static void WritePassword(String id,String password){//��ס������
	    	File owner_file = new File ("F://Doors");//�ļ�Ҫһ��һ���Ľ���
			File record_file= new File("F://Doors//"+"DoorsPassword.txt");
			try{
				if (!owner_file.exists()){//����ļ������ھʹ���һ�����ļ�
					owner_file.mkdir();
				} 
	
				if (!record_file.exists()){//����ļ������ھʹ���һ�����ļ�
					record_file.createNewFile();
				}
				//������������޸���������ô�죬��Ҳû���ˣ��������//֮ǰ�������������֮����û�е���Ļ���û�е����ס����ļ���������ļ�����ɾ��������
				//������������Ѿ���������ݣ��ֱ���ò�Ʋ��ã���øģ�����������������������
				FileOutputStream fos = new FileOutputStream(record_file,true);
				fos.write((id+" "+password).getBytes("GBK"));
				fos.close();
			}catch(Exception e){                                              
				e.printStackTrace();
			}
	    }
		public  void  WriteRecord(Message m,String owner,String friendID){//�õ���Ϣ����Ϣ�ĵõ������ӣ�
			this.m=m;
			this.owner =owner;
			this.friendID = friendID;
			//String url1 = "F://Doors//"+owner;//������ʹurl1��ֻ��һ��б��
			//System.out.println(url1);
			//String url2 = "F://Doors//"+owner+"//"+friendID+".txt";
			File owner_file = new File ("F://Doors");//�ļ�Ҫһ��һ���Ľ���
			File owner_file_file= new File("F://Doors//"+owner);
			File record_file = new File ("F://Doors//"+owner+"/"+friendID+".txt");
			try{
				if (!owner_file.exists()){//����ļ������ھʹ���һ�����ļ�
					owner_file.mkdir();
				} 
				if(!owner_file_file.exists()){
					owner_file_file.mkdir();
				}
				if (!record_file.exists()){//����ļ������ھʹ���һ�����ļ�
					record_file.createNewFile();
				}
				//RandomAccessFile raf = null;
				FileOutputStream fos = new FileOutputStream(record_file,true);//true �Ǳ���ԭ��������
				
				//д���ļ�����ĸ�ʽsender getter con,�Ժ��ڵ������Ի��з�������ÿ����Ϣ
				fos.write((m.getSender()+"->"+m.getGetter()+":"+m.getCon()+"\r\n").getBytes("GBK"));
				System.out.println("д�������¼�ͷ�������Ϣ");
				fos.close();
			}catch(Exception e){
				
			}//finally{
				//if(raf!=null){  
				  //  raf.close();  
				  // }  
		//	}
			
		}
		
		public String  ReadRecord(String owner,String friendID){ //�������¼
			this.m=m;
			this.owner =owner;
			this.friendID = friendID;
			String Context = null;
			FileReader fileReader=null;  
		    BufferedReader bufferedReader=null; 
			//String url1 = "F:\\Doors\\"+owner;
			//String url2 = "F:\\Doors\\"+owner+"/"friendID+".txt";
			File owner_file = new File ("F:\\Doors\\"+owner);
			File record_file = new File ("F:\\Doors\\"+owner+"/"+friendID+".txt");
			try{
				if (!owner_file.exists()){
					owner_file.mkdir();
				}
				if (!record_file.exists()){//û�������¼���˳�
					Context = "����ʷ�����¼";
					return Context;
				}
				fileReader=new FileReader(record_file);  
				bufferedReader=new BufferedReader(fileReader);
				String read = null;
			    while((read=bufferedReader.readLine())!=null){  //����Ϣ
			     Context=Context+read+"\r\n"; 
			    }
			    if(bufferedReader!=null){  
				    bufferedReader.close();  
				   }  
				  if(fileReader!=null){  
				    fileReader.close();
				  }
			}catch(Exception e){
				e.printStackTrace(); 
			}
		   
			return Context;
		}
		
		
}
