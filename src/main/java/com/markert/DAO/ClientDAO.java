package com.markert.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.markert.DTO.ClientDTO;

public class ClientDAO {

	 Connection connectDB;
	 PreparedStatement pstm;
	
	 public void register (ClientDTO dto) {
        
        connectDB = new ConnectorDAO().ConnectDB();
        
        try {
            String url = "INSERT INTO CLIENT"
            		+ "(CPF)"
            		+ "VALUES (?)";
            
            pstm = connectDB.prepareStatement(url);
            
            pstm.setString(1, dto.getCpf());
        
            pstm.execute();
            pstm.close();
        }
        catch(SQLException err) {
            JOptionPane.showMessageDialog(null, "Error Message: " + err.getMessage());
        }
        
	 }
	
	}
