package com.codewithsameer.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.codewithsameer.blog.entities.CategoriesEntity;
import com.codewithsameer.blog.entities.Post;
import com.codewithsameer.blog.entities.UserEntity;

public interface PostRepo extends JpaRepository<Post, Integer>{
	
	List<Post> findByUser(UserEntity user);
	List<Post> findByCategory(CategoriesEntity category);
	List<Post> findByTitleContaining(String title);
}
