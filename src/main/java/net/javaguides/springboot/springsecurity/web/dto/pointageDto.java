package net.javaguides.springboot.springsecurity.web.dto;


public class pointageDto {
	
	private String codeSecret;
	private long cin;
	public long getCin() {
		return cin;
	}

	public void setCin(long cin) {
		this.cin = cin;
	}

	public String getCodeSecret() {
		return codeSecret;
	}

	public void setCodeSecret(String codeSecret) {
		this.codeSecret = codeSecret;
	}
}
