package net.javaguides.springboot.springsecurity.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String time;
    
    public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	private String nom;
    private String prenom,adresse,email,codeSecret;
    private long cin,tel;
    private int guichet;
    private boolean verif;
    
    public boolean isVerif() {
		return verif;
	}

	public void setVerif(boolean verif) {
		this.verif = verif;
	}

	public int getGuichet() {
		return guichet;
	}

	public void setGuichet(int guichet) {
		this.guichet = guichet;
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

	public User() {
    	
    }
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;
    
    

	public User( String username, String password) {

        this.username = username;
        this.password = password;
    }

    public User(String username, String password, Collection<Role> roles) {
        
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
              
                ", username='" + username + '\'' +
                ", password='" + "*********" + '\'' +
                ", roles=" + roles +
                '}';
    }
}
