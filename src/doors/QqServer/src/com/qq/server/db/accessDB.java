package com.qq.server.db;
import java.sql.*;
import java.util.*;
import java.io.*;

public class accessDB { 
	String DBDriver = "com.mysql.jdbc.Driver";
	String ConnStr = "jdbc:MySQL://localhost:3306/qquser";
	Connection conn = null;
	ResultSet rs = null;
public  accessDB(){
		try{
			Class.forName(DBDriver);
			conn = DriverManager.getConnection(ConnStr,"root","");
		}catch(Exception e){
			System.err.println(e.toString());
		}
}
	
public  ResultSet executeQuery(String sql) {
	 rs = null;
	try{
		Statement stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
	}catch(SQLException e){
		System.err.println(e.toString());
	}
	return rs;
}
public String executeUpdate(String sql){
	try{
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sql);
		rs = stmt.executeQuery("select last_insert_id() as rowID");
		int rowID =0;
		while(rs.next()){
			rowID = rs.getInt("rowID");
		}
		return String.valueOf(rowID);
	}catch (SQLException e){
		System.err.println(e.toString());
		return e.toString();
	}
}
public void close(){
	rs = null;
	try{
		conn.commit();
		conn.close();
	}catch(Exception e){
		System.err.println(e.toString());
	}
}
}


