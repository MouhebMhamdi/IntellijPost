package net.javaguides.springboot.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.springsecurity.model.Categorie;
import net.javaguides.springboot.springsecurity.repository.CategorieRepository;
@Service
public class CategorieServiceImp implements CategorieService {
@Autowired
CategorieRepository catRep;
    @Override
    public Page<Categorie> findPaginatedCategorie(int pageNo, int pageSize) {
        Pageable pageable=PageRequest.of(pageNo-1 , pageSize);
        return this.catRep.findAll(pageable);
    }
    
}