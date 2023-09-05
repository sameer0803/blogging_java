package com.codewithsameer.blog.services;

import java.util.List;
import com.codewithsameer.blog.payloads.PostDto;
import com.codewithsameer.blog.payloads.PostResponse;

public interface PostService {
	// create Post
	PostDto createPost(PostDto postDto, Integer userId, Integer CategoryID);

	// update
	PostDto updatePost(PostDto postDto, Integer postId);

	// delete
	void deletePost(Integer postId);

	// get all posts
	PostResponse getAllPost(Integer pageNumber,Integer pageSize, String sortBy,String sortDir);

	// get single post
	PostDto getPostById(Integer postId);

	// get all posts by category I
	List<PostDto> getPostsByCategory(Integer categoryld);

	// get all posts by user
	List<PostDto> getPostsByUser(Integer userId);

	// search user
	List<PostDto> searchPosts(String keyword);

}
