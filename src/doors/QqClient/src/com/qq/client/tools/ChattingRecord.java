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
				FileOutputStream fos = new FileOutputStream(record_file,true);//����ƺ�����
				
				//д���ļ�����ĸ�ʽsender getter con,�Ժ��ڵ������Ի��з�������ÿ����Ϣ
				fos.write((m.getCon()+"\r\n").getBytes("GBK"));
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
