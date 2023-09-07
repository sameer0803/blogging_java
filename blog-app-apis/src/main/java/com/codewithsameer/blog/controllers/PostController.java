package com.codewithsameer.blog.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codewithsameer.blog.config.AppConstant;
import com.codewithsameer.blog.payloads.APIResponse;
import com.codewithsameer.blog.payloads.PostDto;
import com.codewithsameer.blog.payloads.PostResponse;
import com.codewithsameer.blog.services.PostService;

@RestController
@RequestMapping("/api/posts")

public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/userId/{userId}/category/{categoyId}/posts")
	public ResponseEntity<PostDto> createCategory(@RequestBody PostDto PostDto, @PathVariable("userId") Integer userId,
			@PathVariable("categoyId") Integer categoyId) {
		PostDto postDto = this.postService.createPost(PostDto, userId, categoyId);
		return new ResponseEntity<>(postDto, HttpStatus.CREATED);
	}

	@GetMapping("/category/{categoyId}/posts")
	public ResponseEntity<List<PostDto>> getPostsCategory(@PathVariable("categoyId") Integer categoyId) {
		List<PostDto> postList = this.postService.getPostsByCategory(categoyId);
		return new ResponseEntity<List<PostDto>>(postList, HttpStatus.OK);
	}

	@GetMapping("/User/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable("userId") Integer userId) {
		List<PostDto> postList = this.postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(postList, HttpStatus.OK);

	}

	@GetMapping("/getAll")
	public ResponseEntity<PostResponse> getAll(
			@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstant.SORTDIR, required = false) String sortDir) {
		PostResponse allPost = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(allPost, HttpStatus.OK);
	}
	

	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
		PostDto postDto = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
	}

	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> getPostById(@RequestBody PostDto PostDto, @PathVariable Integer postId) {
		PostDto postDto = this.postService.updatePost(PostDto, postId);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
	}

	@DeleteMapping("/post/{postId}")
	public APIResponse deletePostById(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new APIResponse("Post is sucessfully deleted", true);
	}
	
	//search 
	
	@GetMapping("/get/{search}")
	public ResponseEntity<List<PostDto>> searchByTitle(@PathVariable("search") String search) {
		List<PostDto> searchPosts = this.postService.searchPosts(search);
		return new ResponseEntity<List<PostDto>> (searchPosts, HttpStatus.OK);
	}
	
	
	

}
