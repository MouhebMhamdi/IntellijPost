package net.javaguides.springboot.springsecurity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Categorie {
@Id
@GeneratedValue
private int id;
private String time;
private String date;
public String getDate() {
	return date;
}

public void setDate(String date) {
	this.date = date;
}

public String getTime() {
	return time;
}

public void setTime(String time) {
	this.time = time;
}

private String catname;
private Double montant;
private Double montantAjouter;
public Double getMontantAjouter() {
	return montantAjouter;
}

public void setMontantAjouter(Double montantAjouter) {
	this.montantAjouter = montantAjouter;
}

public Double getMontant() {
	return montant;
}

public void setMontant(Double montant) {
	this.montant = montant;
}

@ManyToOne
private User user;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getCatname() {
	return catname;
}



public void setCatname(String catName) {
	catname = catName;
}

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}
}
