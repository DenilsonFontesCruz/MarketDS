package com.markert.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.markert.DTO.ProductDTO;

public class ProductDAO {

	 Connection connectDB;
	 PreparedStatement pstm;
	
	 public void register (ProductDTO dto) {
	        
	        connectDB = new ConnectorDAO().ConnectDB();
	        
	        try {
	            String url = "INSERT INTO PRODUCT"
	            		+ "(NAME, PRICE, EXPIRATIONDATE, QRCODE, CATEGORY)"
	            		+ "VALUES (?, ?, ?, ?, ?)";
	            
	            pstm = connectDB.prepareStatement(url);
	            
	            pstm.setString(1, dto.getName());
	            pstm.setDouble(2, dto.getPrice());
	            pstm.setDate(3, new java.sql.Date(dto.getExperitionDate().getTime()));
	            pstm.setInt(4, dto.getQRcode());
	            pstm.setInt(5, dto.getCategory().getId());
	        
	            pstm.execute();
	            pstm.close();
	        }
	        catch(SQLException err) {
	            JOptionPane.showMessageDialog(null, "Error Message: " + err.getMessage());
	        }
	        
		 }
	
	}
