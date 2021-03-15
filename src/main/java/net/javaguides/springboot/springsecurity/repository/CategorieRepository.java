package net.javaguides.springboot.springsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import net.javaguides.springboot.springsecurity.model.Archive;
import net.javaguides.springboot.springsecurity.model.Categorie;
import net.javaguides.springboot.springsecurity.model.User;

public interface CategorieRepository extends JpaRepository<Categorie, Long>{
	Categorie findByUser(User user);
	@Query("SELECT c from Categorie c where user = :user and date =:date")
	Categorie getCategorie(User user,String date);
	Categorie findByUserAndDate(User user,String date);
	@Query("select c from Categorie c where user= :user and date = :date")
	List<Categorie> findByUserAndDates(User user,String date);
	List<Categorie> findByDate(String date);
	@Query("select c from Categorie c where date= :date and user <> :user")
	List<Categorie>findByDateNotUser(String date,User user);
	List<Categorie>findByDateAndUser(String date,User u);
	@Query("select c from Categorie c where date= :date and user <> :user and catname = :catname")
	List<Categorie>findByDateNotUserAndCatName(String date,User user,String catname);
	@Query("select sum(montantAjouter) From Categorie where date = :date")
	Double getSommeCourant(String date);
	
	List<Categorie>findByDateAndCatname(String date,String Catname);
	
	@Query("select sum(montantAjouter) From Categorie ")
	Double getSomme();
	
	
	  @Query("SELECT c FROM Categorie c where date= :date and (user.nom Like CONCAT('%', :nom,'%') or user.prenom Like CONCAT('%', :nom,'%') or user.cin Like CONCAT('%', :nom,'%') or user.prenom Like CONCAT('%', :nom,'%') or date Like CONCAT('%', :nom,'%'))")
			List<Categorie> getCategorieByNomAndDate(String nom,String date);
	  @Query("SELECT c FROM Categorie c where user.nom Like CONCAT('%', :nom,'%') or user.prenom Like CONCAT('%', :nom,'%')  or user.prenom Like CONCAT('%', :nom,'%') or date Like CONCAT('%', :nom,'%') ")
		List<Categorie> getCategorieByNom(String nom);
	  
	  @Query("SELECT sum(montantAjouter) FROM Categorie c where date= :date and (user.nom Like CONCAT('%', :nom,'%') or user.prenom Like CONCAT('%', :nom,'%')  or user.prenom Like CONCAT('%', :nom,'%') or date Like CONCAT('%', :nom,'%') )")
		Double getCategorieCourantSommeByNom(String nom,String date);
	  
	  @Query("SELECT sum(montantAjouter) FROM Categorie c where user.nom Like CONCAT('%', :nom,'%') or user.prenom Like CONCAT('%', :nom,'%')  or user.prenom Like CONCAT('%', :nom,'%') or date Like CONCAT('%', :nom,'%') ")
		Double getCategorieSommeByNom(String nom);
	  
	
}
