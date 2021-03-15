package net.javaguides.springboot.springsecurity.web.dto;

public class updateParamDto {
private long id;
private String periode;
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getPeriode() {
	return periode;
}
public void setPeriode(String periode) {
	this.periode = periode;
}
public Double getMontant() {
	return montant;
}
public void setMontant(Double montant) {
	this.montant = montant;
}
public int getTime() {
	return time;
}
public void setTime(int time) {
	this.time = time;
}
private Double montant;
private int time;
}
