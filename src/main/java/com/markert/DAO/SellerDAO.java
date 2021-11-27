package com.markert.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.markert.DTO.SellerDTO;
import com.markert.DTO.SellerDTO;

public class SellerDAO {

	 static Connection connectDB;
	 static PreparedStatement pstm;
	
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
	 
	 public void update (SellerDTO dto) {
	        
	        connectDB = new ConnectorDAO().ConnectDB();
	        
	        try {
	            String url = "UPDATE SELLER SET NAME = ?, EMAIL = ?, PASSWORD = ? WHERE ID = ?";
	            
	            pstm = connectDB.prepareStatement(url);
	            
	            pstm.setString(1, dto.getName());
	            pstm.setString(2, dto.getEmail());
	            pstm.setString(3, dto.getPassword());
	            pstm.setInt(4, dto.getId());
	        
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
	            String url = "DELETE FROM SELLER WHERE ID = ?";
	            
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
	            String url = "DELETE FROM SELLER";
	            
	            pstm = connectDB.prepareStatement(url);
	        
	            pstm.execute();
	            pstm.close();
	        }
	        catch(SQLException err) {
	            JOptionPane.showMessageDialog(null, "Error Message: " + err.getMessage());
	        }
	 }
	 
	 public static SellerDTO findById (Integer id) {
	        
		 	connectDB = new ConnectorDAO().ConnectDB();
	        
	        try {
	            String url = "SELECT * FROM SELLER WHERE ID = ?";
	            
	            pstm = connectDB.prepareStatement(url);
	            
	            pstm.setInt(1, id);
	            
	            ResultSet queryList = pstm.executeQuery();
	       
          SellerDTO item = new SellerDTO();
          
          queryList.next();
          item.setId(queryList.getInt("id"));
          item.setName(queryList.getString("name"));
          item.setEmail(queryList.getString("email"));
          item.setPassword(queryList.getString("password"));
	        
          return item;
          
	        }
	        catch(SQLException err) {
	            
	            return new SellerDTO();
	        }
	        
		 }
	 
	 
	 public List<SellerDTO> findAll () {
	        
		 	connectDB = new ConnectorDAO().ConnectDB();
	        
	        try {
	            String url = "SELECT * FROM SELLER";
	            
	            pstm = connectDB.prepareStatement(url);
	            
	            ResultSet queryList = pstm.executeQuery();
	            
	            List<SellerDTO> list = new ArrayList<SellerDTO>();
	          
	            while(queryList.next()) {
	                SellerDTO item = new SellerDTO();
	                
	                item.setId(queryList.getInt("id"));
	                item.setName(queryList.getString("name"));
	                item.setEmail(queryList.getString("email"));
	                item.setPassword(queryList.getString("password"));
	                
	                list.add(item);
	            }
	            
	            return list;
	            
	        }
	        catch(SQLException err) {
	            JOptionPane.showMessageDialog(null, "Error Message: " + err.getMessage());
	            return new ArrayList<SellerDTO>();
	        }
	        
	    }
	 
	 public List<SellerDTO> findFilterList(String filter) {
	        
		 	connectDB = new ConnectorDAO().ConnectDB();
	        
	        try {
	            String url = "SELECT * FROM SELLER WHERE NAME LIKE ?";
	            
	            pstm = connectDB.prepareStatement(url);
	            
	            filter = filter.concat("%");
	            
	            pstm.setString(1, filter);
	            
	            ResultSet queryList = pstm.executeQuery();
	            
	            List<SellerDTO> list = new ArrayList<SellerDTO>();
	          
	            while(queryList.next()) {
	                SellerDTO item = new SellerDTO();
	                
	                item.setId(queryList.getInt("id"));
	                item.setName(queryList.getString("name"));
	                item.setEmail(queryList.getString("email"));
	                item.setPassword(queryList.getString("password"));
	                
	                list.add(item);
	            }
	            
	            return list;
	            
	        }
	        catch(SQLException err) {
	            JOptionPane.showMessageDialog(null, "Error Message: " + err.getMessage());
	            return new ArrayList<SellerDTO>();
	        }
	        
	    }
	    
	
	}
