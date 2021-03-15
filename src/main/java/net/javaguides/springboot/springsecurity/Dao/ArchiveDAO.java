package net.javaguides.springboot.springsecurity.Dao;

import java.util.List;

import net.javaguides.springboot.springsecurity.model.Archive;

public interface ArchiveDAO {
	
	 List<Archive> get() ;
	
	Archive get(long l);
	Archive empGet(int id);
	void save(Archive archive);
	
	void delete(int id);
	void insertEmp(long id);
}
