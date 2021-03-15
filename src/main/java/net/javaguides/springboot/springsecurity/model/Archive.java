package net.javaguides.springboot.springsecurity.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Archive {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private long id;
@NotEmpty(message = "Il faux séléctionnées un type svp !!")
private String nom;
@NotEmpty(message = "Invalid Numéro !!")
private String uniqueCode;
public String getUniqueCode() {
	return uniqueCode;
}
public void setUniqueCode(String uniqueCode) {
	this.uniqueCode = uniqueCode;
}
private String date;

@NotNull(message = "Montant ne doit étre Null")
private double montant;



@Max(99999999)
@NotNull(message = "CIN invalide !!")
private long cin;


@ManyToOne
@JoinColumn(nullable = true)
private User user;

public void setUser(User user) {
	this.user=user;
}
public User getUser() {
	return user;
}
public long getCin() {
	return cin;
}
public void setCin(long cin) {
	this.cin = cin;
}




public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public double getMontant() {
	return montant;
}
public void setMontant(double montant) {
	this.montant = montant;
}


}
