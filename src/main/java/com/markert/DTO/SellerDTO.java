package com.markert.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerDTO {
	
	int id;
	String name;
	String email;
	String password;
	
	public SellerDTO(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
