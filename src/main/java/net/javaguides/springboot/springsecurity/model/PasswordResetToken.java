package net.javaguides.springboot.springsecurity.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class PasswordResetToken {
  
    private static final int EXPIRATION = 60 * 24;
  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
  
    private String token;
  
    @OneToOne(targetEntity = User.class)
    @JoinColumn( name = "user_id")
    private User user;
  
    private Date expiryDate;

	public PasswordResetToken(String token2, User user2) {
		this.token=token2;
		this.user=user2;
	}

	public PasswordResetToken() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public static int getExpiration() {
		return EXPIRATION;
	}
}