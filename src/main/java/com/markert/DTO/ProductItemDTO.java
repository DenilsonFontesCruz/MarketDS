package com.markert.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductItemDTO {
	
	Integer id;
	int quantity;
	Integer product;
	String order;
}
