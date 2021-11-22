package com.markert.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.markert.DTO.ClientDTO;

public class ClientDAO {

	 static Connection connectDB;
	 static PreparedStatement pstm;
	
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
	 
	 public void update (ClientDTO dto) {
	        
	        connectDB = new ConnectorDAO().ConnectDB();
	        
	        try {
	            String url = "UPDATE CLIENT SET CPF = ? WHERE ID = ?";
	            
	            pstm = connectDB.prepareStatement(url);
	            
	            pstm.setString(1, dto.getCpf());
	            pstm.setInt(2, dto.getId());
	        
	            pstm.execute();
	            pstm.close();
	        }
	        catch(SQLException err) {
	            JOptionPane.showMessageDialog(null, "Error Message: " + err.getMessage());
	        }
	        
		 }
	 
	 
	 public void delete (Integer id) {
	        
	        connectDB = new ConnectorDAO().ConnectDB();
	        
	        try {
	            String url = "DELETE FROM CLIENT WHERE ID = ?";
	            
	            pstm = connectDB.prepareStatement(url);
	            
	            pstm.setInt(1, id);
	        
	            pstm.execute();
	            pstm.close();
	        }
	        catch(SQLException err) {
	            JOptionPane.showMessageDialog(null, "Error Message: " + err.getMessage());
	        }
	        
		 }
	 
	 public void deleteAll() {
		 connectDB = new ConnectorDAO().ConnectDB();
	        
	        try {
	            String url = "DELETE FROM CATEGORY";
	            
	            pstm = connectDB.prepareStatement(url);
	        
	            pstm.execute();
	            pstm.close();
	        }
	        catch(SQLException err) {
	            JOptionPane.showMessageDialog(null, "Error Message: " + err.getMessage());
	        }
	 }
	 
	 public static ClientDTO findById (Integer id) {
	        
	        connectDB = new ConnectorDAO().ConnectDB();
	        
	        try {
	            String url = "SELECT * FROM CLIENT WHERE ID = ?";
	            
	            pstm = connectDB.prepareStatement(url);
	            
	            pstm.setInt(1, id);
	            
	            ResultSet queryList = pstm.executeQuery();
	       
             ClientDTO item = new ClientDTO();
             
             queryList.next();
             item.setId(queryList.getInt("id"));
             item.setCpf(queryList.getString("cpf"));
	        
             return item;
             
	        }
	        catch(SQLException err) {
	            
	            return new ClientDTO();
	        }
	        
		 }
	 
	 
	 public List<ClientDTO> findAll () {
	        
		 	connectDB = new ConnectorDAO().ConnectDB();
	        
	        try {
	            String url = "SELECT * FROM CLIENT";
	            
	            pstm = connectDB.prepareStatement(url);
	            
	            ResultSet queryList = pstm.executeQuery();
	            
	            List<ClientDTO> list = new ArrayList<ClientDTO>();
	          
	            while(queryList.next()) {
	                ClientDTO item = new ClientDTO();
	                
	                item.setId(queryList.getInt("id"));
	                item.setCpf(queryList.getString("cpf"));
	                
	                list.add(item);
	            }
	            
	            return list;
	            
	        }
	        catch(SQLException err) {
	            JOptionPane.showMessageDialog(null, "Error Message: " + err.getMessage());
	            return new ArrayList<ClientDTO>();
	        }
	        
	    }
	 
	 public List<ClientDTO> findFilterList(String filter) {
	        
		 	connectDB = new ConnectorDAO().ConnectDB();
	        
	        try {
	            String url = "SELECT * FROM CLIENT WHERE CPF LIKE ?";
	            
	            pstm = connectDB.prepareStatement(url);
	            
	            filter = filter.concat("%");
	            
	            pstm.setString(1, filter);
	            
	            ResultSet queryList = pstm.executeQuery();
	            
	            List<ClientDTO> list = new ArrayList<ClientDTO>();
	          
	            while(queryList.next()) {
	                ClientDTO item = new ClientDTO();
	                
	                item.setId(queryList.getInt("id"));
	                item.setCpf(queryList.getString("cpf"));
	                
	                list.add(item);
	            }
	            
	            return list;
	            
	        }
	        catch(SQLException err) {
	            JOptionPane.showMessageDialog(null, "Error Message: " + err.getMessage());
	            return new ArrayList<ClientDTO>();
	        }
	        
	    }
	    
	
	}
