package net.javaguides.springboot.springsecurity.service;

import org.springframework.data.domain.Page;

import net.javaguides.springboot.springsecurity.model.Categorie;

public interface CategorieService {
    Page<Categorie> findPaginatedCategorie(int pageNo, int pageSize);
}