package com.markert.DTO;

import java.util.Date;

import com.markert.DAO.CategoryDAO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	
	int id;
	String name;
	double price;
	Date experitionDate;
	int QRcode;
	CategoryDTO category;
	
}
