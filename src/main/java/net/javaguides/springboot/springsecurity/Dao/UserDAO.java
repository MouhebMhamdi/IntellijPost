package net.javaguides.springboot.springsecurity.Dao;

import java.util.List;

import net.javaguides.springboot.springsecurity.model.Archive;
import net.javaguides.springboot.springsecurity.model.User;

public interface UserDAO {
	List<User> get() ;
	User get(long id);
	void delete(int id);
}
