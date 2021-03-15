package net.javaguides.springboot.springsecurity.web.dto;

public class compteParametreDto {
private String nom,prenom,email,username,password,code,newpassword,confpassword,codeActuel;
public String getCodeActuel() {
	return codeActuel;
}
public void setCodeActuel(String codeActuel) {
	this.codeActuel = codeActuel;
}
public String getNewpassword() {
	return newpassword;
}
public void setNewpassword(String newpassword) {
	this.newpassword = newpassword;
}
public String getConfpassword() {
	return confpassword;
}
public void setConfpassword(String confpassword) {
	this.confpassword = confpassword;
}
private long tel;
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getPrenom() {
	return prenom;
}
public void setPrenom(String prenom) {
	this.prenom = prenom;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public long getTel() {
	return tel;
}
public void setTel(long tel) {
	this.tel = tel;
}
}
