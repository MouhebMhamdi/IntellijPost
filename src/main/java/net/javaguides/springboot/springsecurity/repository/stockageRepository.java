package net.javaguides.springboot.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.springsecurity.model.User;
import net.javaguides.springboot.springsecurity.model.stockage;

@Repository
public interface stockageRepository extends JpaRepository<stockage, Long>{
	stockage findByNombre(long montant);
	stockage findByUser(User user);
	
	@Query("SELECT s FROM stockage s")
	stockage getStock();
}
