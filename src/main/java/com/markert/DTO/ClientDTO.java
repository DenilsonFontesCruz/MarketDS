package com.markert.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
	
	int id;
	String cpf;
	
	public ClientDTO(String cpf) {
		this.cpf = cpf;
	}
	
	@Override
	public String toString() {
		return cpf;
	}
}
