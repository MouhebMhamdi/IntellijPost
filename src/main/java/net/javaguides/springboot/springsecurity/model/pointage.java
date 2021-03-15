package net.javaguides.springboot.springsecurity.model;


import javax.persistence.*;

@Entity
public class pointage {
@Id
@GeneratedValue
private int id;
@Column(unique = true)
private String date;
private int cas;

public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
@ManyToOne
private User user;

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public int getCas() {
	return cas;
}
public void setCas(int cas) {
	this.cas = cas;
}

}
