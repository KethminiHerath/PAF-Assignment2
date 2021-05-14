package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class item {

	
	public Connection connect()
	{
	 Connection con = null;

	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");
	 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/shop","root", "");
	 //For testing
	 System.out.print("Successfully connected");
	 }
	 catch(Exception e)
	 {
	 e.printStackTrace();
	 }

	 return con;
	}
	
	

	//read
	public String readsupplier()
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
	 output = "<table border='1'><tr><th>Suplier_code</th>" 
	 + "<th>Name</th><th>Phone</th>"
	 
	 + "<th>Update</th><th>Remove</th></tr>"; 
	 String query = "select * from supplier"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String supplierID = Integer.toString(rs.getInt("supplierID")); 
	 String Supplier_code = rs.getString("Supplier_code"); 
	 String Name = rs.getString("Name"); 
	
	 String Phone = rs.getString("Phone"); 
	// Add into the html table
	 output += "<tr><td>" + Supplier_code + "</td>"; 
	 output += "<td>" + Name + "</td>"; 
	 output += "<td>" + Phone + "</td>"; 
	
	// buttons
	output += "<td><input name='btnUpdate' type='button' value='Update' "
	+ "class='btnUpdate btn btn-secondary' data-supplierid='" + supplierID + "'></td>"
	+ "<td><input name='btnRemove' type='button' value='Remove' "
	+ "class='btnRemove btn btn-danger' data-supplierid='" + supplierID + "'></td></tr>"; 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	catch (Exception e) 
	 { 
	 output = "Error while reading the supplier."; 
	 System.err.println(e.getMessage()); 
	 } 
	return output; 
	}
	
	
	
	
	public String insertsupplier(String Supplier_code, String Name, String Phone)
	{
	 String output = "";
	try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database";
	 }
	 // create a prepared statement
	 String query = " insert into supplier(`Supplier_code`,`Name`,`Phone`) values (?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, Supplier_code);
	 preparedStmt.setString(3, Name);
	 preparedStmt.setString(4, Phone); 
	
	//execute the statement
	 preparedStmt.execute();
	 con.close();
	 String newItem = readsupplier(); 
	 output = "{\"status\":\"success\", \"data\": \"" + 
			 newItem + "\"}";
	 }
	catch (Exception e)
	 {
		output = "{\"status\":\"error\", \"data\": \"Error while inserting the supplier.\"}"; 
				 System.err.println(e.getMessage()); 
	 }
	return output;
	}
	
	
	
	
	
	
	public String updatesupplier(String supplierID,String Supplier_code, String Name, String Phone)
	
		
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 // create a prepared statement
		 String query = "UPDATE supplier SET Supplier_code=?,Name=?,Phone=? WHERE supplierID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, Supplier_code); 
		 preparedStmt.setString(2, Name); 
		  
		 preparedStmt.setString(3, Phone); 
		 preparedStmt.setInt(4, Integer.parseInt(supplierID)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newItem = readsupplier(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newItem + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "{\"status\":\"error\", \"data\": \"Error while updating the supplier.\"}"; 
					 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 }
	
	
	public String deletesupplier(String supplierID)
	{
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
			 String query = "delete from supplier where supplierID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(supplierID));

			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 String newItem = readsupplier(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
					 newItem + "\"}";
			 }
			catch (Exception e)
			 {
				output = "{\"status\":\"error\", \"data\": \"Error while deleting the supplier.\"}"; 
						 System.err.println(e.getMessage()); 
			 }
			return output;
			}
}
}