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
	    
		public void  WriteRecord(Message m,String owner,String friendID){//得到消息和消息的得到的主从，
			this.m=m;
			this.owner =owner;
			this.friendID = friendID;
			String url1 = "F:\\Doors\\"+owner;
			String url2 = "F:\\Doors\\"+owner+friendID+".txt";
			File owner_file = new File (url1);
			File record_file = new File (url2);
			try{
				if (!owner_file.exists()){//如果文件不存在就创建一个新文件
					owner_file.createNewFile();
				} 
				if (!record_file.exists()){//如果文件不存在就创建一个新文件
					owner_file.createNewFile();
				}
				//RandomAccessFile raf = null;
				FileOutputStream fos = new FileOutputStream(record_file);
				//写在文件里面的格式sender getter con,以后在调整
				fos.write(m.getCon().getBytes("GBK"));
				fos.close();
			}catch(Exception e){
				
			}//finally{
				//if(raf!=null){  
				  //  raf.close();  
				  // }  
		//	}
			
		}
		
		public String  ReadRecord(String owner,String friendID){ //读聊天记录
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
				if (!record_file.exists()){//没有聊天记录就退出
					Context = "无历史聊天记录";
					return Context;
				}
				fileReader=new FileReader(record_file);  
				bufferedReader=new BufferedReader(fileReader);
				String read = null;
			    while((read=bufferedReader.readLine())!=null){  //读消息
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
