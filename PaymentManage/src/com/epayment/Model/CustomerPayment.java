package com.epayment.Model;

import com.epayment.DBconnection.*;
import java.sql.*;

public class CustomerPayment {
	public String insertCustomerPayment(String card_type, String card_no, String expiry_date, String cvv,String date, String pay_totalamount,String pay_amount)
	 {
		 String output = "";
		 
		 try
		 {
			 
			 Connection con = DBConnect.connect();
			 
			 if (con == null)
			 {return "Error while connecting to the database for inserting."; }
			 
			 // create a prepared statement
			 String query = " insert into ecpay1(`payID`,`payCardType`,`payCardNO`,`payExpiryDate`,`payCVV`,`payDate`,`payTotalAmount`,`payAmount`)"
			 + " values (?, ?, ?, ?, ?,?,?,?)";
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, card_type);
			 preparedStmt.setString(3, card_no );
			 preparedStmt.setString(4, expiry_date );
			 preparedStmt.setString(5, cvv);
			 preparedStmt.setString(6, date );
			 preparedStmt.setDouble(7, Double.parseDouble(pay_totalamount));
			 preparedStmt.setDouble(8, Double.parseDouble(pay_amount));
			 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 
			 String newPayments = readItems();
			 output = "{\"status\":\"success\", \"data\": \"" +
			 newPayments + "\"}"; 
		 }
		 catch (Exception e)
		 {
			 output = "{\"status\":\"error\", \"data\":\"Error while inserting the customer payment.\"}";
			 System.err.println(e.getMessage());
		 }
		 
		 return output;
	}

	public String readItems()
	{
		String output = "";
		
		try
		{
			 Connection con = DBConnect.connect();
			 
			 if (con == null)
			 {return "Error while connecting to the database for reading."; }
			 
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>Pay CardType</th>" +
					 "<th>Pay CardNO</th>" +
					 "<th>Pay ExpiryDate</th>" +
					 "<th>Pay CVV</th>" +
					 "<th>Pay Date</th>" +
					 "<th>Pay TotalAmount</th>" +
					 "<th>Pay Amount</th>" +
					 "<th>Update</th><th>Remove</th></tr>";

			 String query = "select * from ecpay1";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 
			 // iterate through the rows in the result set
			 while (rs.next())
			 {
				 String payID = Integer.toString(rs.getInt("payID"));
				 String payCardType = rs.getString("payCardType");
				 String payCardNO = Integer.toString(rs.getInt("payCardNO"));
				 String payExpiryDate = rs.getString("payExpiryDate");
				 String payCVV = Integer.toString(rs.getInt("payCVV"));
				 String payDate = rs.getString("payDate");
				 String payTotalAmount = Double.toString(rs.getDouble("payTotalAmount"));
				 String payAmount = Double.toString(rs.getDouble("payAmount"));
				
				  // Add into the html table
				 output += "<tr><td><input id='hidPayIDUpdate'" 
						 + "name='hidPayIDUpdate' "
						 +"type='hidden' value='" + payID
				         + "'>" + payCardType + "</td>";
				 output += "<td>" + payCardNO + "</td>";
				 output += "<td>" + payExpiryDate + "</td>";
				 output += "<td>" + payCVV + "</td>";
				 output += "<td>" + payDate + "</td>";
				 output += "<td>" + payTotalAmount + "</td>";
				 output += "<td>" + payAmount + "</td>";
				 
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update'"
						 + "class='btnUpdate btn btn-secondary'></td>"
						 + "<td><input name='btnRemove' type='button' value='Remove' "
						 + "class='btnRemove btn btn-danger' data-payid='" + payID + "'></td></tr>"; 
			 }
			 
			 con.close();
			 
			 // Complete the html table
			 output += "</table>";
			 
		}
		catch (Exception e)
		{
			 output = "Error while reading the customer payment.";
			 System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	
	public String updateCustomerPayment(String ID, String card_type, String card_no, String expiry_date, String cvv, String date, String pay_totalamount, String pay_amount)
	{
		 String output = "";
		 
		 try
		 {
			 
			 Connection con = DBConnect.connect();
			 
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
			 
			 // create a prepared statement
			 String query = "UPDATE ecpay1 SET payCardType=?,payCardNO=?,payExpiryDate=?,payCVV=?,payDate=?,payTotalAmount=?,payAmount=? WHERE payID=?";
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setString(1, card_type);
			 preparedStmt.setString(2, card_no);
			 preparedStmt.setString(3, expiry_date);
			 preparedStmt.setString(4, cvv);
			 preparedStmt.setString(5, date);
			 preparedStmt.setDouble(6, Double.parseDouble(pay_totalamount));
			 preparedStmt.setDouble(7, Double.parseDouble(pay_amount));
			 preparedStmt.setInt(8, Integer.parseInt(ID));
			 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 
			 String newPayments = readItems();
			 output = "{\"status\":\"success\", \"data\": \"" +
			 newPayments + "\"}"; 
		 }
		 catch (Exception e)
		 {
			 output = "{\"status\":\"error\", \"data\":\"Error while updating the customer payment.\"}";
			 System.err.println(e.getMessage());
		 }
		 
		 return output;
		 
	}
}
