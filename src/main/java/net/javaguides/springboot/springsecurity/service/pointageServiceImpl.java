package net.javaguides.springboot.springsecurity.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.springsecurity.model.User;
import net.javaguides.springboot.springsecurity.model.pointage;
import net.javaguides.springboot.springsecurity.repository.PointageService;

@Service
public class pointageServiceImpl implements pointageService {
	@Autowired
	PointageService pointageRepository;

	@Override
	public pointage save(final User user, final pointage point) {
		if (point.getUser() != user) {
			final LocalDate myObj = LocalDate.now();
			final String date = myObj.toString();
			final pointage p = new pointage();
			p.setCas(0);
			p.setUser(user);
			p.setDate(date);
			return pointageRepository.save(p);
		} else {
			return null;
		}

	}

	@Override
	public Page<pointage> findPaginated(final int pageNo, final int pageSize) {
		final Pageable pageable=PageRequest.of(pageNo-1 , pageSize);
		return this.pointageRepository.findAll(pageable);
	}

	@Override
	public Page<pointage> findPaginatedByDate(final int pageNo, final int pageSize, final String date) {
		final Pageable pageable=PageRequest.of(pageNo-1 , pageSize);
		return this.pointageRepository.findByDates(date, pageable);
	}

}
