package com.markert.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.markert.DTO.CategoryDTO;
import com.markert.DTO.ProductDTO;
import com.markert.DTO.ProductItemDTO;

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
	            
	            Integer qrCode = (int)(Math.random() * (10000000 - 9000000+1)+10000000);
	            
	            pstm.setInt(4, qrCode);
	            pstm.setInt(5, dto.getCategory());
	        
	            pstm.execute();
	            pstm.close();
	        }
	        catch(SQLException err) {
	            JOptionPane.showMessageDialog(null, "Error Message: " + err.getMessage());
	        }
	        
		 }
	
	 public void update (ProductDTO dto) {
	        
	        connectDB = new ConnectorDAO().ConnectDB();
	        
	        try {
	            String url = "UPDATE PRODUCT SET NAME = ?, PRICE = ?, EXPIRATIONDATE = ?, CATEGORY = ? WHERE ID = ?";
	            
	            pstm = connectDB.prepareStatement(url);
	            
	            pstm.setString(1, dto.getName());
	            pstm.setDouble(2, dto.getPrice());
	            pstm.setDate(3, new java.sql.Date(dto.getExperitionDate().getTime()));
	            pstm.setInt(4, dto.getCategory());
	            pstm.setInt(5, dto.getId());
	        
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
	            String url = "DELETE FROM PRODUCT WHERE ID = ?";
	            
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
	            String url = "DELETE FROM PRODUCT";
	            
	            pstm = connectDB.prepareStatement(url);
	        
	            pstm.execute();
	            pstm.close();
	        }
	        catch(SQLException err) {
	            JOptionPane.showMessageDialog(null, "Error Message: " + err.getMessage());
	        }
	 }
	 
	 public ProductDTO findById (Integer id) {
	        
	        connectDB = new ConnectorDAO().ConnectDB();
	        
	        try {
	            String url = "SELECT * FROM PRODUCT WHERE ID = ?";
	            
	            pstm = connectDB.prepareStatement(url);
	            
	            pstm.setInt(1, id);
	            
	            ResultSet queryList = pstm.executeQuery();
	       
       ProductDTO item = new ProductDTO();
       
       queryList.next();
       item.setId(queryList.getInt("id"));
       item.setName(queryList.getString("name"));
       item.setPrice(queryList.getDouble("price"));
       item.setExperitionDate(queryList.getDate("expirationDate"));
       item.setQRcode(queryList.getInt("qrcode"));
       item.setCategory(queryList.getInt("category"));
	        
       return item;
       
	        }
	        catch(SQLException err) {
	            JOptionPane.showMessageDialog(null, "Error Message: " + err.getMessage());
	            return new ProductDTO();
	        }
	        
		 }
	 
	 
	 public List<ProductDTO> findAll () {
	        
		 	connectDB = new ConnectorDAO().ConnectDB();
	        
	        try {
	            String url = "SELECT * FROM PRODUCT";
	            
	            pstm = connectDB.prepareStatement(url);
	            
	            ResultSet queryList = pstm.executeQuery();
	            
	            List<ProductDTO> list = new ArrayList<ProductDTO>();
	          
	            while(queryList.next()) {
	                ProductDTO item = new ProductDTO();
	                
	                item.setId(queryList.getInt("id"));
	                item.setName(queryList.getString("name"));
	                item.setPrice(queryList.getDouble("price"));
	                item.setExperitionDate(queryList.getDate("expirationDate"));
	                item.setQRcode(queryList.getInt("qrcode"));
	                item.setCategory(queryList.getInt("category"));
	    	        
	                
	                list.add(item);
	            }
	            
	            return list;
	            
	        }
	        catch(SQLException err) {
	            JOptionPane.showMessageDialog(null, "Error Message: " + err.getMessage());
	            return new ArrayList<ProductDTO>();
	        }
	        
	    }
	 
	 
	 
	 public List<ProductDTO> findFilterList(String filter) {
	        
		 	connectDB = new ConnectorDAO().ConnectDB();
	        
	        try {
	            String url = "SELECT * FROM PRODUCT WHERE NAME LIKE ?";
	            
	            pstm = connectDB.prepareStatement(url);
	            
	            filter = filter.concat("%");
	            
	            pstm.setString(1, filter);
	            
	            ResultSet queryList = pstm.executeQuery();
	            
	            List<ProductDTO> list = new ArrayList<ProductDTO>();
	          
	            while(queryList.next()) {
	                ProductDTO item = new ProductDTO();
	               
	                item.setId(queryList.getInt("id"));
	                item.setName(queryList.getString("name"));
	                item.setPrice(queryList.getDouble("price"));
	                item.setExperitionDate(queryList.getDate("expirationDate"));
	                item.setQRcode(queryList.getInt("qrcode"));
	                item.setCategory(queryList.getInt("category"));
	    	        
	                
	                list.add(item);
	            }
	            
	            return list;
	            
	        }
	        catch(SQLException err) {
	            JOptionPane.showMessageDialog(null, "Error Message: " + err.getMessage());
	            return new ArrayList<ProductDTO>();
	        }
	        
	    }
	}
