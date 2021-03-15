package net.javaguides.springboot.springsecurity.web.dto;

public class stockageDto {
private long montant;
private int nbrGuichet;
public int getNbrGuichet() {
	return nbrGuichet;
}

public void setNbrGuichet(int nbrGuichet) {
	this.nbrGuichet = nbrGuichet;
}

public long getMontant() {
	return montant;
}

public void setMontant(long montant) {
	this.montant = montant;
}
}
	