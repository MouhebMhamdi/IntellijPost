package net.javaguides.springboot.springsecurity.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.springsecurity.model.Archive;
import net.javaguides.springboot.springsecurity.model.User;


@Repository
public interface ArchiveRepository extends JpaRepository<Archive, Long> {
	 String JDBC_MAX_ROWS = null;
	@Query("SELECT SUM(montant) FROM Archive where nom not in :list and date = :date")
	    Double MontantSum(List<String> list,String date);
	 @Query("SELECT SUM(montant) FROM Archive where nom not in :list ")
	    Double MontantAllsum(List<String> list);
	 @Query("SELECT SUM(montant) FROM Archive where nom not in :list and date= :date")
	    Double MontantAllsumDate(List<String> list,String date);
	  @Query("SELECT SUM(montant) FROM Archive where nom in :list ")
	    Double MontantAllSumRetrait(List<String> list);
	 
    List<Archive> findByNom(String nom);
    List<Archive>  findByUser(User user);
    @Query("SELECT SUM(montant) FROM Archive where nom in :list and date= :date")
    Double MontantSumRetrait(List<String> list,String date);
    List<Archive> findByDateAndCin(String date,long cin);
    List<Archive> findByCin(long cin);
	List<Archive> findByDate(String date);
	List<Archive> findByUserAndDate(User user,String date);
	Page<Archive>findByDateAndUser(String date,User user,Pageable pageable);
	List<Archive> findByUserAndDateAndCin(User user,String date,long cin);
	List<Archive> findByUniqueCode(String uniqueCode);
	@Query("select a from Archive a where date= :date")
	Page<Archive>findDate(String date,Pageable pageable);
	@Query("select a from Archive a where user= :user")
	Page<Archive>findUser(User user,Pageable pageable);
	@Query("select a from Archive a where uniqueCode = :uniqueCode and user <> :user")
	List<Archive>findByUnqueCodeAndNotUser(String uniqueCode,User user);
	
		@Query("select a from Archive a where id= :id")
		Archive findByIdd(long id);
		
		
		
	 @Query("SELECT SUM(montant) FROM Archive where nom not in :list and date = :date and user= :user")
	    Double MontantSumUser(List<String> list,String date,User user);
	 
	 @Query("SELECT a FROM Archive a where date =:date and user<> :user")
	 List<Archive>getArchive(String date,User user);
	 
	 @Query("SELECT SUM(montant) FROM Archive where nom not in :list and user= :user and date= :date")
	    Double somme(List<String> list,User user,String date);
	 
	 @Query("SELECT count(*) FROM Archive where  user= :user")
	    int travail(User user);
	 @Query("SELECT count(*) FROM Archive where date= :date")
	    int travails(String date);
	 @Query("SELECT count(*) FROM Archive ")
	    int travailsTot();
	  @Query("SELECT SUM(montant) FROM Archive where nom in :list and date= :date and user= :user")
	    Double MontantSumRetraitUser(List<String> list,String date,User user);
	  
	  @Query("SELECT SUM(montant) FROM Archive where nom not in :list and user= :user")
	    Double MontantAllsumUser(List<String> list,User user);
	  @Query("SELECT SUM(montant) FROM Archive where nom in :list and user= :user")
	    Double MontantAllSumRetraitUser(List<String> list,User user);
	  @Query("SELECT a FROM Archive a where cin Like CONCAT('%', :nom,'%') or nom Like CONCAT('%', :nom,'%') or date Like CONCAT('%', :nom,'%') or user.nom Like CONCAT('%', :nom,'%') or user.prenom Like CONCAT('%', :nom,'%') or uniqueCode Like CONCAT('%', :nom,'%')")
		List<Archive> getArchiveByNom(String nom);
	  @Query("SELECT a FROM Archive a where date =:date and (cin Like CONCAT('%', :nom,'%') or nom Like CONCAT('%', :nom,'%')  or user.nom Like CONCAT('%', :nom,'%') or user.prenom Like CONCAT('%', :nom,'%') or uniqueCode Like CONCAT('%', :nom,'%')) ")
		List<Archive> getArchiveByNomAndDate(String nom,String date);
	  @Query("SELECT a FROM Archive a where date =:date and user= :user and (cin Like CONCAT('%', :nom,'%') or nom Like CONCAT('%', :nom,'%')  or user.nom Like CONCAT('%', :nom,'%') or user.prenom Like CONCAT('%', :nom,'%') or uniqueCode Like CONCAT('%', :nom,'%')) ")
		List<Archive> getArchiveByNomAndDateAndUser(String nom,String date,User user);
	  @Query("SELECT a FROM Archive a where  user= :user and (cin Like CONCAT('%', :nom,'%') or nom Like CONCAT('%', :nom,'%')  or user.nom Like CONCAT('%', :nom,'%') or user.prenom Like CONCAT('%', :nom,'%') or uniqueCode Like CONCAT('%', :nom,'%')) ")
			List<Archive> getArchiveByNomAndUser(String nom,User user);
	  
	  
   
}
