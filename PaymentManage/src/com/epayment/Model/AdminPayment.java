package com.epayment.Model;

import com.epayment.DBconnection.*;
import java.sql.*;

public class AdminPayment {
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

			 String query = "select * from ecpay";
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
				 output += "<tr><td><input id='hidItemIDUpdate'"
						 + "name='hidItemIDUpdate' " 
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
						 + "class='btnUpdate btn btn-secondary' data-itemid='\" + itemID + \"' ></td>"
						 + "<td><input name='btnRemove' type='button' value='Remove' "
						 + "class='btnRemove btn btn-danger' data-itemid='" + payID + "'></td></tr>"; 
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
	
	public String deleteAdminPayment(String payID)
	 {
		 String output = "";
		 
		 try
		 {
			 Connection con = DBConnect.connect();
			 
			 if (con == null)
			 {return "Error while connecting to the database for deleting."; }
			 
			 // create a prepared statement
			 String query = "delete from ecpay where payID=?";
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(payID));
			 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 
			 String newPayments = readItems();
			 output = "{\"status\":\"success\", \"data\": \"" +
			 newPayments + "\"}"; 
		 }
		 catch (Exception e)
		 {
			 output = "{\"status\":\"error\", \"data\": \"Error while deleting the customer payment.\"}";
			 System.err.println(e.getMessage());
		 }
		 
		 return output;
	 }

}

