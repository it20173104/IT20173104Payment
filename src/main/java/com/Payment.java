package com;

import java.sql.*;

public class Payment  

{
	
	//Create database connection
	
private Connection connect() 
	{ 
		Connection con = null; 
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver"); 
			con = 
					DriverManager.getConnection( 
							"jdbc:mysql://127.0.0.1:3306/paymanage", "root", "prasan123"); 
		} 
		catch (Exception e) 
		{ 
			e.printStackTrace(); 
		} 
		return con; 
	} 


  //retrieve data from database and display in page

public String readPayments() 
 	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{ 
				return "Error while connecting to the database for reading."; 
			} 
			
 // Prepare the html table to be displayed
			
			output = "<table border='1'><tr><th>Bill Account</th> <th>Customer Name</th><th>Pay Value</th>"+ "<th>Contact Number</th> <th>Update</th><th>Remove</th></tr>"; 
			String query = "select * from payments"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
			
 // iterate through the rows in the result set
			
			while (rs.next()) 
			{ 
				String paymentID = Integer.toString(rs.getInt("paymentID")); 
				String paymentCode = rs.getString("paymentCode"); 
				String paymentName = rs.getString("paymentName"); 
				String paymentPrice = rs.getString("paymentPrice"); 
				String paymentContact = rs.getString("paymentContact"); 
				
 // Add into the html table
				
				output += "<tr><td><input id='hidPaymentIDUpdate' name='hidPaymentIDUpdate' type='hidden' value='" + paymentID+ "'>" + paymentCode + "</td>"; 
				output += "<td>" + paymentName + "</td>"; 
				output += "<td>" + paymentPrice + "</td>"; 
				output += "<td>" + paymentContact + "</td>"; 
				
 // buttons
				
output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"+ "<td><input name='btnRemove' type='submit' value='Remove' class='btnRemove btn btn-danger' data-paymentid='"+ paymentID + "'>" + "</td></tr>"; 
 } 
			con.close(); 
 
// Complete the html table
 
			output += "</table>"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while reading the payment."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
 	} 

 //Insert data into database

	public String insertPayment(String code, String name, 
			String price, String contact) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{ 
				return "Error while connecting to the database for inserting."; 
			} 
			
 // create a prepared statement
			
			String query = " insert into payments (`paymentID`,`paymentCode`,`paymentName`,`paymentPrice`,`paymentContact`)"+ " values (?, ?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
 // binding values
			
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, code); 
			preparedStmt.setString(3, name); 
			preparedStmt.setString(4, price); 
			preparedStmt.setString(5, contact); 
			
 // execute the statement
			
			preparedStmt.execute(); 
			con.close(); 
			String newPayments = readPayments(); 
			output = "{\"status\":\"success\", \"data\": \"" + 
					newPayments + "\"}"; 
		} 
		catch (Exception e) 
		{ 
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the payment.\"}"; 
			System.err.println(e.getMessage()); 
 } 
		return output; 
	} 
	
// Update payment data
	
	public String updatePayment(String ID, String code, String name, String price, String contact) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{ 
				return "Error while connecting to the database for updating."; 
			} 
			
 // create a prepared statement
			
			String query = "UPDATE payments SET paymentCode=?,paymentName=?,paymentPrice=?,paymentContact=? WHERE paymentID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
 // binding values
			
			preparedStmt.setString(1, code); 
			preparedStmt.setString(2, name); 
			preparedStmt.setString(3, price); 
			preparedStmt.setString(4, contact); 
			preparedStmt.setInt(5, Integer.parseInt(ID)); 
 
 
 // execute the statement
 		
 		preparedStmt.execute(); 
 		con.close(); 
 		String newPayments = readPayments(); 
 		output = "{\"status\":\"success\", \"data\": \"" + 
		 newPayments + "\"}"; 
		} 
		catch (Exception e) 
		{ 
			output = "{\"status\":\"error\", \"data\": \"Error while updating the payments.\"}"; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
		
	} 
	
//delete payment record
	
	public String deletePayment(String paymentID) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{ 
				return "Error while connecting to the database for deleting."; 
			} 
			
 // create a prepared statement
			
			String query = "delete from payments where paymentID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
 // binding values
			
			preparedStmt.setInt(1, Integer.parseInt(paymentID)); 
			
 // execute the statement
			
			preparedStmt.execute(); 
			con.close(); 
			String newPayments = readPayments(); 
			output = "{\"status\":\"success\", \"data\": \"" + newPayments + "\"}"; 
			
		} 
		
		catch (Exception e) 
		{ 
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the payment.\"}"; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
		
	} 
	
}
