package com.epayment.DBconnection;

import java.sql.*; 

public class DBConnect {
	public static Connection connect() 
	{
		Connection con = null;
		
		try 
		{
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ecpay", "root", "");
			
		} catch (Exception e) {
		e.printStackTrace();}
		
		return con;
	}

}
