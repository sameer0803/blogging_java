package com.codewithsameer.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithsameer.blog.entities.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {
	
	

}
