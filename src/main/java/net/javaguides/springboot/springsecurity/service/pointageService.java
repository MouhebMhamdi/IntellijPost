package net.javaguides.springboot.springsecurity.service;

import org.springframework.data.domain.Page;

import net.javaguides.springboot.springsecurity.model.User;
import net.javaguides.springboot.springsecurity.model.pointage;

public interface pointageService {
	pointage save(User user,pointage point);
	Page<pointage>findPaginated(int pageNo ,int pageSize);
	Page<pointage>findPaginatedByDate(int pageNo ,int pageSize,String date);
}
