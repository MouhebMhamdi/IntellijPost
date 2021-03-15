package net.javaguides.springboot.springsecurity.web.dto;

public class userDto {
	private String username;
    private String password;

    private String AncienCodeSecret;
    private String nom;
    private String prenom,adresse,email,codeSecret;
    private long cin,tel;
    private int guichet;
    private String newPassword;
    private String time;
    private String roles;
	
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
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

	public String getAncienCodeSecret() {
		return AncienCodeSecret;
	}
	public void setAncienCodeSecret(String ancienCodeSecret) {
		AncienCodeSecret = ancienCodeSecret;
	}
	public void setPassword(String password) {
		this.password = password;
	}
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
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCodeSecret() {
		return codeSecret;
	}
	public void setCodeSecret(String codeSecret) {
		this.codeSecret = codeSecret;
	}
	public long getCin() {
		return cin;
	}
	public void setCin(long cin) {
		this.cin = cin;
	}
	public long getTel() {
		return tel;
	}
	public void setTel(long tel) {
		this.tel = tel;
	}
	public int getGuichet() {
		return guichet;
	}
	public void setGuichet(int guichet) {
		this.guichet = guichet;
	}
}
