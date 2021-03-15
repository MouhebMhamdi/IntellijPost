package net.javaguides.springboot.springsecurity.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.javaguides.springboot.springsecurity.Dao.ArchiveDAO;
import net.javaguides.springboot.springsecurity.Dao.UserDAO;
import net.javaguides.springboot.springsecurity.model.Archive;
import net.javaguides.springboot.springsecurity.model.Role;
import net.javaguides.springboot.springsecurity.model.User;
import net.javaguides.springboot.springsecurity.repository.UserRepository;
import net.javaguides.springboot.springsecurity.web.dto.UserRegistrationDto;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
     UserRepository userRepository;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User findByUsername(final String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    @Override
    public User get(final long id) {
        return userDAO.get(id);
    }

    @Transactional
    @Override
    public List<User> get() {
        return userDAO.get();
    }

    @Transactional
    @Override
    public void delete(final int id) {
        userDAO.delete(id);
    }

    public User save(final UserRegistrationDto registration) {
        final User user = new User();

        user.setUsername(registration.getUsername());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setAdresse(registration.getAdresse());
        user.setCin(registration.getCin());
        user.setCodeSecret(registration.getCode_secret());
        user.setEmail(registration.getEmail());
        user.setNom(registration.getNom());
        user.setPrenom(registration.getPrenom());
        user.setGuichet(registration.getGuichet());
        user.setTel(registration.getTel());
        user.setTime(registration.getTime());
        user.setVerif(true);
        user.setRoles(Arrays.asList(new Role(registration.getRole())));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        final User user = userRepository.findByUsername(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(final Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public User save(final UserRegistrationDto registration, final User user) {
        user.setUsername(registration.getUsername());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setAdresse(registration.getAdresse());
        user.setCin(registration.getCin());
        user.setCodeSecret(registration.getCode_secret());
        user.setEmail(registration.getEmail());
        user.setNom(registration.getNom());
        user.setPrenom(registration.getPrenom());
        user.setGuichet(registration.getGuichet());
        user.setTel(registration.getTel());
        user.setTime(registration.getTime());
        user.setVerif(true);
        user.setRoles(Arrays.asList(new Role(registration.getRole())));
        return userRepository.save(user);
    }

    @Override
    public User findByCin(final long cin) {
        return userRepository.findByCin(cin);
    }

    @Override
    public User findByGuichet(final int guichet) {

        return userRepository.findByGuichet(guichet);
    }

    
    

    @Override
    public Page<User> findPaginateds(final int pageNo, final int pageSize) {
        final PageRequest pageable = PageRequest.of(pageNo, pageSize);
        return this.userRepository.findAll(pageable);
    }

   
}
