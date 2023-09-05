package com.codewithsameer.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithsameer.blog.entities.CategoriesEntity;

public interface CategoryRepo extends JpaRepository<CategoriesEntity, Integer> {

	
}
