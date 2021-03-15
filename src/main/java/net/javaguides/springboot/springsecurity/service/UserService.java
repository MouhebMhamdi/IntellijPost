package net.javaguides.springboot.springsecurity.service;

import java.util.List;

import org.springframework.data.domain.*;
import org.springframework.security.core.userdetails.UserDetailsService;

import net.javaguides.springboot.springsecurity.model.User;

import net.javaguides.springboot.springsecurity.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {

    User findByUsername(String username);
    Page<User>findPaginateds(int pageNo ,int pageSize);
    User findByCin(long cin);
    User findByGuichet(int guichet);
    User save(UserRegistrationDto registration);
    User save(UserRegistrationDto registration,User user);
    
    
    List<User> get();
	
	User get(long id);
	
	void delete(int id);

}
