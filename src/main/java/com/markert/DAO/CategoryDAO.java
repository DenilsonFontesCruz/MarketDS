package com.markert.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.markert.DTO.CategoryDTO;

public class CategoryDAO {

	 Connection connectDB;
	 PreparedStatement pstm;
	
	 public void register (CategoryDTO dto) {
	        
	        connectDB = new ConnectorDAO().ConnectDB();
	        
	        try {
	            String url = "INSERT INTO CATEGORY"
	            		+ "(NAME)"
	            		+ "VALUES (?)";
	            
	            pstm = connectDB.prepareStatement(url);
	            
	            pstm.setString(1, dto.getName());
	        
	            pstm.execute();
	            pstm.close();
	        }
	        catch(SQLException err) {
	            JOptionPane.showMessageDialog(null, "Error Message: " + err.getMessage());
	        }
	        
		 }
	
	}
