package net.javaguides.springboot.springsecurity.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.javaguides.springboot.springsecurity.model.PasswordResetToken;
import net.javaguides.springboot.springsecurity.model.User;

public interface PasswordTokenRepository extends JpaRepository<PasswordResetToken, Long>{
	Optional<PasswordResetToken> findByToken(String token);
	PasswordResetToken findByUser(User user);
	@Query("select p from PasswordResetToken p where token= :token")
	PasswordResetToken findByTokens(String token);
	@Query("select p from PasswordResetToken p where token= :token")
	List<PasswordResetToken>findByTok(String token);
	@Query("select p from PasswordResetToken p where user= :user")
	List<PasswordResetToken> findByUs(User user);
}
