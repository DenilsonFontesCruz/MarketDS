package com.markert.Enums;

public enum PayMethods {

	DINHEIRO(1, "Dinheiro"),
	BOLETO(2, "Boleto"),
	CREDITO(3, "Credito");
	
	private Integer cod;
	private String description;
	
	private PayMethods(Integer cod, String description) {
		this.cod = cod;
		this.description = description;
	}
	
	public Integer getCod() {
		return cod;
	}
	
	public String getDescription() {
		return description;
	}

	public static String getDescriptionById(Integer id) {
		if(id == null) return null;
		
		for(PayMethods i : PayMethods.values()) {
			if(id.equals(i.getCod())) return i.getDescription();
		}
		
		return null;
	}

	
}
