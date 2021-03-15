package net.javaguides.springboot.springsecurity.web.dto;

import org.springframework.lang.NonNull;

public class searchDto {
	@NonNull
	private String cin;

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	
}
