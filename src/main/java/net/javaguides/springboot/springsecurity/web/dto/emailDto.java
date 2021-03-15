package net.javaguides.springboot.springsecurity.web.dto;

import java.io.File;

public class emailDto {
private String email,sujet,message;


public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getSujet() {
	return sujet;
}

public void setSujet(String sujet) {
	this.sujet = sujet;
}

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}


}
