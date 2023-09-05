package com.codewithsameer.blog.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.codewithsameer.blog.entities.CategoriesEntity;
import com.codewithsameer.blog.entities.Post;
import com.codewithsameer.blog.entities.UserEntity;
import com.codewithsameer.blog.exceptions.ResourceNotFoundException;
import com.codewithsameer.blog.payloads.PostDto;
import com.codewithsameer.blog.payloads.PostResponse;
import com.codewithsameer.blog.repositories.CategoryRepo;
import com.codewithsameer.blog.repositories.PostRepo;
import com.codewithsameer.blog.repositories.UserRepo;
import com.codewithsameer.blog.services.PostService;
import org.springframework.data.domain.Sort;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelmapper;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer CategoryID) {

		UserEntity user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "UserID", userId));
		CategoriesEntity category = this.categoryRepo.findById(CategoryID)
				.orElseThrow(() -> new ResourceNotFoundException("User", "UserID", userId));

		Post post = this.modelmapper.map(postDto, Post.class);

		post.setImageName("defaultImage.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post posts = postRepo.save(post);
		return this.modelmapper.map(posts, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
		Post post_to_change = this.modelmapper.map(postDto, Post.class);
		post.setTitle(post_to_change.getTitle());
		post.setImageName(post_to_change.getImageName());
		post.setContent(post_to_change.getContent());
		post.setAddedDate(new Date());
		postRepo.save(post);
		PostDto postDtoo = this.modelmapper.map(post, PostDto.class);
		return postDtoo;
	}

	@Override
	public void deletePost(Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
		postRepo.delete(post);

	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		System.out.println("SortDir" + sortDir);

		Sort sort = (sortDir.equalsIgnoreCase("asc")) ? (Sort.by(sortBy).ascending()) : Sort.by(sortBy).descending();

//		Sort sort =null;
//		
//		if (sortDir.equalsIgnoreCase("asc")) {
//			sort = Sort.by(sortBy).ascending();
//		} else if (sortDir.equalsIgnoreCase("desc"))
//		{
//
//			sort = Sort.by(sortBy).descending();
//			
//		}
		Pageable page = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePost = this.postRepo.findAll(page);
		List<Post> posts = pagePost.getContent();

		List<PostDto> postDtoList = posts.stream().map((post) -> this.modelmapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();

		postResponse.setContent(postDtoList);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setLastPage(pagePost.isLast());
		postResponse.setTotalPages(pagePost.getTotalPages());

		return postResponse;
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryld) {

		CategoriesEntity category = this.categoryRepo.findById(categoryld)
				.orElseThrow(() -> new ResourceNotFoundException("Categories", "categoryld", categoryld));

		List<Post> posts = this.postRepo.findByCategory(category);
		List<PostDto> postsDTOs = posts.stream().map((post) -> this.modelmapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postsDTOs;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {

		UserEntity user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));

		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> postsList = posts.stream().map((post) -> this.modelmapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postsList;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
		return this.modelmapper.map(post, PostDto.class);

	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> post_title_containing = this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postDTO = post_title_containing.stream().map((post) -> this.modelmapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postDTO;

	}

}
