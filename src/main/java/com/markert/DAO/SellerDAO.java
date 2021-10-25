package com.markert.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.markert.DTO.SellerDTO;

public class SellerDAO {

	 Connection connectDB;
	 PreparedStatement pstm;
	
	 public void register (SellerDTO dto) {
        
        connectDB = new ConnectorDAO().ConnectDB();
        
        try {
            String url = "INSERT INTO SELLER"
            		+ "(NAME, EMAIL, PASSWORD)"
            		+ "VALUES (?, ?, ?)";
            
            pstm = connectDB.prepareStatement(url);
            
            pstm.setString(1, dto.getName());
            pstm.setString(2, dto.getEmail());
            pstm.setString(3, dto.getPassword());
        
            pstm.execute();
            pstm.close();
        }
        catch(SQLException err) {
            JOptionPane.showMessageDialog(null, "Error Message: " + err.getMessage());
        }
        
	 }
	
	}
