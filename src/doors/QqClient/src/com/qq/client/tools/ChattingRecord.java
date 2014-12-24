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
	    
		public void  WriteRecord(Message m,String owner,String friendID){//�õ���Ϣ����Ϣ�ĵõ������ӣ�
			this.m=m;
			this.owner =owner;
			this.friendID = friendID;
			String url1 = "F:\\Doors\\"+owner;
			String url2 = "F:\\Doors\\"+owner+friendID+".txt";
			File owner_file = new File (url1);
			File record_file = new File (url2);
			try{
				if (!owner_file.exists()){//����ļ������ھʹ���һ�����ļ�
					owner_file.createNewFile();
				} 
				if (!record_file.exists()){//����ļ������ھʹ���һ�����ļ�
					owner_file.createNewFile();
				}
				//RandomAccessFile raf = null;
				FileOutputStream fos = new FileOutputStream(record_file);
				//д���ļ�����ĸ�ʽsender getter con,�Ժ��ڵ���
				fos.write(m.getCon().getBytes("GBK"));
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
			String url1 = "F:\\Doors\\"+owner;
			String url2 = "F:\\Doors\\"+owner+friendID+".txt";
			File owner_file = new File (url1);
			File record_file = new File (url2);
			try{
				if (!owner_file.exists()){
					owner_file.createNewFile();
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
