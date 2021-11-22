package com.markert.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
	
	int id;
	String name;
	
	public CategoryDTO(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
