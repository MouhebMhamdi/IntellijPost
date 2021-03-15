package net.javaguides.springboot.springsecurity.repository;

import java.util.List;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.springsecurity.model.User;
import net.javaguides.springboot.springsecurity.model.pointage;
@Repository
public interface PointageService extends JpaRepository<pointage, Long>{
pointage findById(int id);
pointage findBydateAndUser(String date,User user);
pointage findByUser(User user);
@Query("select p from pointage p where date = :date")
Page<pointage>findByDates(String date,Pageable pageable);
List<pointage> findBydateAndUserAndCas(String date,User user,int cas);

@Query("SELECT p FROM pointage p where date= :date and user= :user and cas = :cas")
pointage dateAndUserAndCas(String date,User user,int cas);


List<pointage> findBydate(String date);
@Query("select u From User u where u NOT IN (select user  from pointage p where p.user= u)")
List<User> getUser();

@Query("select DISTINCT p From pointage p where date <> :date")
List<pointage> getp(String date);

@Query("select count(*) FROM pointage where cas=0 and user= :user")
int getAbsence(User user);

@Query("select count(*) FROM pointage where cas=1 and user= :user")
int getPresence(User user);

@Query("select count(*) FROM pointage where cas=0 and date= :date")
int getAllAbsence(String date);

@Query("select count(*) FROM pointage where cas=1 and date= :date")
int getAllPresence(String date);

@Query("select count(*) from User")
int allPointage();
@Query("select count(*) from pointage where cas=1 and user = :user")
int nbrDeJoursTravalliant(User user);

@Query("select count(*) from pointage where cas=:cas and user = :user")
int nbrJours(int cas,User user );
List<pointage>findByUserAndDate(User user,String date);
@Query("select count(*) from pointage where cas=:cas ")
int nbrJoursTravail(int cas );

@Query("select count(*) from pointage")
int nbrJourss(int cas);
@Query("SELECT count(*) From pointage where date =:date")
int getNbrUserByDate(String date);
@Query("SELECT p FROM pointage p where date =:date and (user.cin Like CONCAT('%', :nom,'%')   or user.nom Like CONCAT('%', :nom,'%') or user.prenom Like CONCAT('%', :nom,'%')or user.email Like CONCAT('%', :nom,'%')or user.tel Like CONCAT('%', :nom,'%')) ")
List<pointage> getPointagByNomAndDate(String nom,String date);
@Query("SELECT p FROM pointage p where  user.cin Like CONCAT('%', :nom,'%')   or user.nom Like CONCAT('%', :nom,'%') or user.prenom Like CONCAT('%', :nom,'%')or user.email Like CONCAT('%', :nom,'%')or user.tel Like CONCAT('%', :nom,'%') ")
List<pointage> getPointagByNom(String nom);
}

