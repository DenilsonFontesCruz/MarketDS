package com.markert.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	 
	 public void update (CategoryDTO dto) {
	        
	        connectDB = new ConnectorDAO().ConnectDB();
	        
	        try {
	            String url = "UPDATE CATEGORY SET NAME = ? WHERE ID = ?";
	            
	            pstm = connectDB.prepareStatement(url);
	            
	            pstm.setString(1, dto.getName());
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
	            String url = "DELETE FROM CATEGORY WHERE ID = ?";
	            
	            pstm = connectDB.prepareStatement(url);
	            
	            pstm.setInt(1, id);
	        
	            pstm.execute();
	            pstm.close();
	        }
	        catch(SQLException err) {
	            JOptionPane.showMessageDialog(null, "Error Message: " + err.getMessage());
	        }
	        
		 }
	 
	 public CategoryDTO findById (Integer id) {
	        
	        connectDB = new ConnectorDAO().ConnectDB();
	        
	        try {
	            String url = "SELECT * FROM CATEGORY WHERE ID = ?";
	            
	            pstm = connectDB.prepareStatement(url);
	            
	            pstm.setInt(1, id);
	            
	            ResultSet queryList = pstm.executeQuery();
	       
                CategoryDTO item = new CategoryDTO();
                
                queryList.next();
                item.setId(queryList.getInt("id"));
                item.setName(queryList.getString("name"));
	        
                return item;
                
	        }
	        catch(SQLException err) {
	            JOptionPane.showMessageDialog(null, "Error Message: " + err.getMessage());
	            return new CategoryDTO();
	        }
	        
		 }
	 
	 
	 public List<CategoryDTO> findAll () {
	        
		 	connectDB = new ConnectorDAO().ConnectDB();
	        
	        try {
	            String url = "SELECT * FROM CATEGORY";
	            
	            pstm = connectDB.prepareStatement(url);
	            
	            ResultSet queryList = pstm.executeQuery();
	            
	            List<CategoryDTO> list = new ArrayList<CategoryDTO>();
	          
	            while(queryList.next()) {
	                CategoryDTO item = new CategoryDTO();
	                
	                item.setId(queryList.getInt("id"));
	                item.setName(queryList.getString("name"));
	                
	                list.add(item);
	            }
	            
	            return list;
	            
	        }
	        catch(SQLException err) {
	            JOptionPane.showMessageDialog(null, "Error Message: " + err.getMessage());
	            return new ArrayList<CategoryDTO>();
	        }
	        
	    }
	 
	 public List<CategoryDTO> findFilterList(String filter) {
	        
		 	connectDB = new ConnectorDAO().ConnectDB();
	        
	        try {
	            String url = "SELECT * FROM CATEGORY WHERE NAME LIKE ?";
	            
	            pstm = connectDB.prepareStatement(url);
	            
	            filter = filter.concat("%");
	            
	            pstm.setString(1, filter);
	            
	            ResultSet queryList = pstm.executeQuery();
	            
	            List<CategoryDTO> list = new ArrayList<CategoryDTO>();
	          
	            while(queryList.next()) {
	                CategoryDTO item = new CategoryDTO();
	                
	                item.setId(queryList.getInt("id"));
	                item.setName(queryList.getString("name"));
	                
	                list.add(item);
	            }
	            
	            return list;
	            
	        }
	        catch(SQLException err) {
	            JOptionPane.showMessageDialog(null, "Error Message: " + err.getMessage());
	            return new ArrayList<CategoryDTO>();
	        }
	        
	    }
	    
	
	}
