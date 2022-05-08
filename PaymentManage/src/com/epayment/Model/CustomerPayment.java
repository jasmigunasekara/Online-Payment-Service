package com.epayment.Model;

import com.epayment.DBconnection.*;
import java.sql.*;

public class CustomerPayment {
	public String insertCustomerPayment(String card_type, int card_no, String expiry_date, int cvv,String date, String pay_totalamount,String pay_amount)
	 {
		 String output = "";
		 
		 try
		 {
			 
			 Connection con = DBConnect.connect();
			 
			 if (con == null)
			 {return "Error while connecting to the database for inserting."; }
			 
			 // create a prepared statement
			 String query = " insert into ecpay(`payID`,`payCardType`,`payCardNO`,`payExpiryDate`,`payCVV`,`payDate`,`payTotalAmount`,`payAmount`)"
			 + " values (?, ?, ?, ?, ?,?,?,?)";
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, card_type);
			 preparedStmt.setInt(3, card_no );
			 preparedStmt.setString(4, expiry_date );
			 preparedStmt.setInt(5, cvv);
			 preparedStmt.setString(6, date );
			 preparedStmt.setDouble(7, Double.parseDouble(pay_totalamount));
			 preparedStmt.setDouble(8, Double.parseDouble(pay_amount));
			 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 
			 output = "Inserted successfully";
		 }
		 catch (Exception e)
		 {
			 output = "Error while inserting the customer payment.";
			 System.err.println(e.getMessage());
		 }
		 
		 return output;
	}

	public String updateCustomerPayment(String ID, String card_type, int card_no, String expiry_date, int cvv, String date, String pay_totalamount, String pay_amount)
	{
		 String output = "";
		 
		 try
		 {
			 
			 Connection con = DBConnect.connect();
			 
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
			 
			 // create a prepared statement
			 String query = "UPDATE ecpay SET payCardType=?,payCardNO=?,payExpiryDate=?,payCVV=?,payDate=?,payTotalAmount=?,payAmount=? WHERE payID=?";
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setString(1, card_type);
			 preparedStmt.setInt(2, card_no);
			 preparedStmt.setString(3, expiry_date);
			 preparedStmt.setInt(4, cvv);
			 preparedStmt.setString(5, date);
			 preparedStmt.setDouble(6, Double.parseDouble(pay_totalamount));
			 preparedStmt.setDouble(7, Double.parseDouble(pay_amount));
			 preparedStmt.setInt(8, Integer.parseInt(ID));
			 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 
			 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
			 output = "Error while updating the customer payment.";
			 System.err.println(e.getMessage());
		 }
		 
		 return output;
		 
	}
}
