package net.javaguides.springboot.springsecurity.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.springsecurity.model.User;
import net.javaguides.springboot.springsecurity.model.pointage;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
	@Query("select u From User u where username = :username")
	List<User>findByUser(String username);

	
	@Query("select u from User u where username <> :username")
	List<User>findByNotUser(String username);
	@Query("select u From User u where cin =:cin")
	List<User>findByCIN(long cin);
	@Query("select u From User u where guichet =:guichet")
	List<User>findByguichet(int guichet);
	
	@Query("select count(*) from User")
	int getNbrUsers();
	
	User findById(long id);
	List<User> findByEmail(String email);
	List<User>findByTel(long tel);
	User findByCin(long cin);
	User findByGuichet(int guichet);
	@Query("SELECT u FROM User u where username <> :username")
	List<User> getAllUsers(String username);
	@Query("SELECT u FROM User u where email <> :email")
	User getWithEmail(String email);
	@Query("SELECT u FROM User u,pointage p where u.username <> :username ")
	List<User> getAllUsersAbsent(String username);
	@Query("SELECT DISTINCT count(*) from Archive where date = :date")
	  int getAllWork(String date);
	@Query("select p from pointage p where cas=0")
	  List<pointage> findAsArray();
	@Query("SELECT u FROM User u where nom Like CONCAT('%', :nom,'%') or prenom Like CONCAT('%', :nom,'%') or tel Like CONCAT('%', :nom,'%') or email Like CONCAT('%', :nom,'%') or username Like CONCAT('%', :nom,'%') or cin Like CONCAT('%', :nom,'%')")
	List<User> getUsersByNom(String nom);
	@Query("SELECT u FROM User u where username <> :username and( nom Like CONCAT('%', :nom,'%') or prenom Like CONCAT('%', :nom,'%') or tel Like CONCAT('%', :nom,'%') or email Like CONCAT('%', :nom,'%') or username Like CONCAT('%', :nom,'%') or cin Like CONCAT('%', :nom,'%'))")
	List<User> getnotUsersByNom(String nom,String username);
	
	
}
