package net.javaguides.springboot.springsecurity.web.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import net.javaguides.springboot.springsecurity.constraint.FieldMatch;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmPassword", message = "Il faut respecter les champs de mot de passe "),
})
public class UserRegistrationDto {

	
	@NotEmpty(message = "Nom ne doit étre vide !")
	private String nom;
	@NotEmpty(message = "Prénom ne doit étre vide !")
	private String prenom;
	@Email(message = "Email invalide !!")
	@NotEmpty(message = "Email ne doit étre vide !") 
	private String email;
	@NotEmpty(message = "Adresse ne doit étre vide !")
	private String Adresse;
	private String time;
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@NotNull
	private long cin;

	@NotNull
	private long tel;
	@NotEmpty(message = "Code secret ne doit étre vide !")
	private String code_secret;
	@NotNull(message = "Guichet ne doit étre vide !")
	private int guichet;
	
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

	public String getAdresse() {
		return Adresse;
	}

	public void setAdresse(String adresse) {
		Adresse = adresse;
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

	public String getCode_secret() {
		return code_secret;
	}

	public void setCode_secret(String code_secret) {
		this.code_secret = code_secret;
	}

	public int getGuichet() {
		return guichet;
	}

	public void setGuichet(int guichet) {
		this.guichet = guichet;
	}

	@NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;

    @NotEmpty
    private String username;

    

    
    @NotEmpty
    private String role;

    public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}





    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }



    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	

}
