package net.javaguides.springboot.springsecurity.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.javaguides.springboot.springsecurity.model.Archive;
import net.javaguides.springboot.springsecurity.model.User;
import net.javaguides.springboot.springsecurity.web.dto.ArchiveDto;


public interface ArchiveService {
	List<Archive> get();
	
	Archive get(int id);
	
	void save(Archive employee);
	
	void delete(int id);
	Page<Archive>findPaginated(int pageNo ,int pageSize,String date,User user);
	Page<Archive>findPaginatedAdmin(int pageNo ,int pageSize,String date);
	Page<Archive>findPaginatedAdminAll(int pageNo ,int pageSize);
	Page<Archive>findPaginatedAll(int pageNo ,int pageSize,User user);
}
