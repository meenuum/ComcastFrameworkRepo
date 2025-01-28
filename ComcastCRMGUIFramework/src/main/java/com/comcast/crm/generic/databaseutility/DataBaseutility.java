package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DataBaseutility {
	
	 Connection conn;
 public void getDbconnection(String url,String username, String password)
 {
	 try {
	Driver driver=new Driver();
	 DriverManager.registerDriver(driver);
	 
     conn= DriverManager.getConnection(url, username, password);
	 }catch(Exception e) {
	 
 }

 }
 public void getDbconnection1()
 {
	 try {
	Driver driver=new Driver();
	 DriverManager.registerDriver(driver);
	 
     conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
	 }catch(Exception e) {
	 }
 }
 public void closeDbconnection() throws SQLException {
	 try {
	 conn.close();
	 }catch (Exception e) {
		
	}
	 
 }

	
 public ResultSet executeSelectQuery(String query) throws SQLException {
	 ResultSet result = null;
	 try {
	 Statement stat=conn.createStatement();
    result =	 stat.executeQuery(query);
	 }catch (Exception e) {

	}
   return result;
 }
 public int executeNonselectQuery(String query) {
	 int result =0;
	 try {
		 Statement stat = conn.createStatement();
		 result = stat.executeUpdate(query);
	 }catch (Exception e) {
	}
	 return result;
	 }
 }
 
