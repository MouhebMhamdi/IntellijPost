package net.javaguides.springboot.springsecurity.web.dto;

import javax.validation.constraints.NotEmpty;

import org.springframework.lang.NonNull;

public class ArchiveDto {
	@NotEmpty 
	private String nom;

	@NonNull
	private double montant;
	@NonNull
	private long cin;
	@NotEmpty
	private String uniqueCode;
	public String getUniqueCode() {
		return uniqueCode;
	}
	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public long getCin() {
		return cin;
	}
	public void setCin(long cin) {
		this.cin = cin;
	}
}
