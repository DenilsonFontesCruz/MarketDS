package com.markert.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectorDAO {
	
	 public Connection ConnectDB() {
	        
	        Connection conn = null;
	        
	        try {
	            String UrlBD = "jdbc:mysql://localhost:3306/marketbd?user=root&password=1234";
	            conn = DriverManager.getConnection(UrlBD);
	        }
	        catch (SQLException err) {
	            JOptionPane.showMessageDialog(null, "Error Message: " + err.getMessage());
	        }
	        
	        return conn;
	    }
}
