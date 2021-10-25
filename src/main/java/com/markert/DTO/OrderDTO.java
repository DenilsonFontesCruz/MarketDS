package com.markert.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
	
	int id;
	int payMethod;
	SellerDTO seller;
	ClientDTO client;
	List<ProductQuantityDTO> products;
}
