package net.javaguides.springboot.springsecurity.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.javaguides.springboot.springsecurity.Dao.ArchiveDAO;
import net.javaguides.springboot.springsecurity.model.Archive;

import net.javaguides.springboot.springsecurity.model.Role;
import net.javaguides.springboot.springsecurity.model.User;
import net.javaguides.springboot.springsecurity.repository.ArchiveRepository;
import net.javaguides.springboot.springsecurity.web.dto.ArchiveDto;



@Service
public class ArchiveServiceImpl implements ArchiveService {

	@Autowired
	private ArchiveDAO archiveDAO;
	@Autowired
	private UserService userSer;
	@Autowired
	private ArchiveRepository arch;
	@Transactional
	@Override
	public List<Archive> get() {
		return archiveDAO.get();
	}

	@Transactional
	@Override
	public Archive get(int id) {
		return archiveDAO.get(id);
	}


	@Transactional
	@Override
	public void save(Archive archive) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		
		if (principal instanceof UserDetails) {
		username = ((UserDetails)principal).getUsername();
		} else {
		 username = principal.toString();
		}
		User user = userSer.findByUsername(username);
		
		archive.setUser(user);
		archiveDAO.save(archive);
	}
	
	@Transactional
	@Override
	public void delete(int id) {
		archiveDAO.delete(id);
	}

	@Override
	public Page<Archive> findPaginated(int pageNo, int pageSize,String date,User user) {
		Pageable pageable=PageRequest.of(pageNo-1 , pageSize);
		
		return this.arch.findByDateAndUser(date,user,pageable);
	}

	@Override
	public Page<Archive> findPaginatedAll(int pageNo, int pageSize, User user) {
		Pageable pageable=PageRequest.of(pageNo-1 , pageSize);
		return this.arch.findUser(user, pageable);
	}

	@Override
	public Page<Archive> findPaginatedAdmin(int pageNo, int pageSize, String date) {
		Pageable pageable=PageRequest.of(pageNo-1 , pageSize);
		return this.arch.findDate(date,pageable);
	}

	@Override
	public Page<Archive> findPaginatedAdminAll(int pageNo, int pageSize) {
		Pageable pageable=PageRequest.of(pageNo-1 , pageSize);
		return this.arch.findAll(pageable);
	}

	

}
