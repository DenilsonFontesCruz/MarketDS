package com.markert.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.swing.JOptionPane;

import com.markert.DTO.OrderDTO;
import com.markert.DTO.ProductItemDTO;

public class OrderDAO {

	 Connection connectDB;
	 PreparedStatement pstm;
	
	 public String register (OrderDTO dto) {
	        
	        connectDB = new ConnectorDAO().ConnectDB();
	        
	        try {
	            String url = "INSERT INTO ORDER_"
	            		+ "(ID, PAYMETHOD, SELLER, CLIENT)"
	            		+ "VALUES (?, ?, ?, ?)";
	            
	            pstm = connectDB.prepareStatement(url);
	            String uuid = UUID.randomUUID().toString();
	            pstm.setString(1, uuid);
	            pstm.setInt(2, dto.getPayMethod());
	            pstm.setInt(3, dto.getSeller());
	            pstm.setInt(4, dto.getClient());
	        
	            pstm.execute();
	            pstm.close();
	            
	            return uuid;
	        }
	        catch(SQLException err) {
	            JOptionPane.showMessageDialog(null, "Error Message: " + err.getMessage());
	        }
			return null;
	        
		 }
	
	 public void update (OrderDTO dto) {
	        
	        connectDB = new ConnectorDAO().ConnectDB();
	        
	        try {
	            String url = "UPDATE ORDER_ SET PAYMETHOD = ?, SELLER = ?, CLIENT = ? WHERE ID = ?";
	            
	            pstm = connectDB.prepareStatement(url);
	            
	            pstm.setInt(1, dto.getPayMethod());
	            pstm.setInt(2, dto.getSeller());
	            pstm.setInt(3, dto.getClient());
	            
	            pstm.setString(4, dto.getId());
	        
	            pstm.execute();
	            pstm.close();
	        }
	        catch(SQLException err) {
	            JOptionPane.showMessageDialog(null, "Error Message: " + err.getMessage());
	        }
	        
		 }
	 
	 
	 public void delete (String string) {
	        
	        connectDB = new ConnectorDAO().ConnectDB();
	        
	        try {
	            String url = "DELETE FROM ORDER_ WHERE ID = ?";
	            
	            pstm = connectDB.prepareStatement(url);
	            
	            pstm.setString(1, string);
	        
	            pstm.execute();
	            pstm.close();
	        }
	        catch(SQLException err) {
	            JOptionPane.showMessageDialog(null, "Error Message: " + err.getMessage());
	        }
	        
		 }
	 
	 public void deleteItem(Integer id) {
		 connectDB = new ConnectorDAO().ConnectDB();
	        
	        try {
	            String url = "DELETE FROM PRODUCT_ORDER WHERE ID = ?";
	            
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
	 
	 public OrderDTO findById (Integer id) {
	        
	        connectDB = new ConnectorDAO().ConnectDB();
	        
	        try {
	            String url = "SELECT * FROM ORDER_ WHERE ID = ?";
	            
	            pstm = connectDB.prepareStatement(url);
	            
	            pstm.setInt(1, id);
	            
	            ResultSet queryList = pstm.executeQuery();
	       
       OrderDTO item = new OrderDTO();
       
       queryList.next();
       item.setId(queryList.getString("id"));
       item.setPayMethod(queryList.getInt("payMethod"));
       item.setSeller(queryList.getInt("seller"));
       item.setClient(queryList.getInt("client"));
	        
       return item;
       
	        }
	        catch(SQLException err) {
	            JOptionPane.showMessageDialog(null, "Error Message: " + err.getMessage());
	            return new OrderDTO();
	        }
	        
		 }
	 
	 
	 public List<OrderDTO> findAll () {
	        
		 	connectDB = new ConnectorDAO().ConnectDB();
	        
	        try {
	            String url = "SELECT * FROM ORDER_";
	            
	            pstm = connectDB.prepareStatement(url);
	            
	            ResultSet queryList = pstm.executeQuery();
	            
	            List<OrderDTO> list = new ArrayList<OrderDTO>();
	          
	            while(queryList.next()) {
	                OrderDTO item = new OrderDTO();
	                
	                item.setId(queryList.getString("id"));
	                item.setPayMethod(queryList.getInt("payMethod"));
	                item.setSeller(queryList.getInt("seller"));
	                item.setClient(queryList.getInt("client"));
	                
	               
	                list.add(item);
	            }
	            
	            return list;
	            
	        }
	        catch(SQLException err) {
	            JOptionPane.showMessageDialog(null, "Error Message: " + err.getMessage());
	            return new ArrayList<OrderDTO>();
	        }
	        
	    }
	 
	 public List<ProductItemDTO> findAllItems (String id) {
	        
		 	connectDB = new ConnectorDAO().ConnectDB();
	        
	        try {
	            String url = "SELECT p.* FROM PRODUCT_ORDER p WHERE p.ORDER_ = ?";
	            
	            pstm = connectDB.prepareStatement(url);
	            
	            pstm.setString(1, id);
	            
	            ResultSet queryList = pstm.executeQuery();
	            
	            List<ProductItemDTO> list = new ArrayList<ProductItemDTO>();
	          
	            while(queryList.next()) {
	                ProductItemDTO item = new ProductItemDTO();
	                
	                item.setId(queryList.getInt("id"));
	                item.setQuantity(queryList.getInt("quantity"));
	                item.setProduct(queryList.getInt("product"));
	    	        item.setOrder(queryList.getString("order_"));
	                
	                list.add(item);
	            }
	            
	            return list;
	            
	        }
	        catch(SQLException err) {
	            return new ArrayList<ProductItemDTO>();
	        }
	        
	    }
	 
	 public List<OrderDTO> findFilterList(String filter) {
	        
		 	connectDB = new ConnectorDAO().ConnectDB();
	        
	        try {
	            String url = "SELECT * FROM ORDER_ WHERE ID LIKE ?";
	            
	            pstm = connectDB.prepareStatement(url);
	            
	            filter = filter.concat("%");
	            
	            pstm.setString(1, filter);
	            
	            ResultSet queryList = pstm.executeQuery();
	            
	            List<OrderDTO> list = new ArrayList<OrderDTO>();
	          
	            while(queryList.next()) {
	                OrderDTO item = new OrderDTO();
	               
	                item.setId(queryList.getString("id"));
	                item.setPayMethod(queryList.getInt("payMethod"));
	                item.setSeller(queryList.getInt("seller"));
	                item.setClient(queryList.getInt("client"));
	             
	                
	                list.add(item);
	            }
	            
	            return list;
	            
	        }
	        catch(SQLException err) {
	            JOptionPane.showMessageDialog(null, "Error Message: " + err.getMessage());
	            return new ArrayList<OrderDTO>();
	        }
	        
	    }
	 
	 public void addProductItem(ProductItemDTO dto) {
		 
		 connectDB = new ConnectorDAO().ConnectDB();
	        
	        try {
	            String url = "INSERT INTO PRODUCT_ORDER"
	            		+ "(PRODUCT, ORDER_, QUANTITY)"
	            		+ "VALUES (?, ?, ?)";
	            
	            pstm = connectDB.prepareStatement(url);
	            
	            pstm.setInt(1, dto.getProduct());
	            pstm.setString(2, dto.getOrder());
	            pstm.setInt(3, dto.getQuantity());
	        
	            pstm.execute();
	            pstm.close();
	        }
	        catch(SQLException err) {
	            JOptionPane.showMessageDialog(null, "Error Message: " + err.getMessage());
	        }
	 }
	 
	 public void removeProductItem(ProductItemDTO dto) {
		 connectDB = new ConnectorDAO().ConnectDB();
	        
	        try {
	            String url = "DELETE FROM ORDER_ WHERE PRODUCT = ? and ORDER_ = ?";
	            
	            pstm = connectDB.prepareStatement(url);
	            
	            pstm.setInt(1, dto.getProduct());
	            pstm.setString(2, dto.getOrder());
	            
	            pstm.execute();
	            pstm.close();
	        }
	        catch(SQLException err) {
	            JOptionPane.showMessageDialog(null, "Error Message: " + err.getMessage());
	        }
	 }

	 
	}
