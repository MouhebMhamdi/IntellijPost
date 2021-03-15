package net.javaguides.springboot.springsecurity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ManyToAny;

@Entity
public class stockage {
@Id
@GeneratedValue
private long id;

private long nombre;

private int nbrGuichet;

public int getNbrGuichet() {
	return nbrGuichet;
}

public void setNbrGuichet(int nbrGuichet) {
	this.nbrGuichet = nbrGuichet;
}

@ManyToOne
private User user;

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public long getNombre() {
	return nombre;
}

public void setNombre(long nombre) {
	this.nombre = nombre;
}

}
