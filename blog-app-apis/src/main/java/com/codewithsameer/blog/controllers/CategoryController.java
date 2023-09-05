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
import org.springframework.web.bind.annotation.RestController;
import com.codewithsameer.blog.entities.CategoriesEntity;
import com.codewithsameer.blog.payloads.APIResponse;
import com.codewithsameer.blog.payloads.CategoryDto;
import com.codewithsameer.blog.payloads.UserDto;
import com.codewithsameer.blog.services.CategoryService;
import com.codewithsameer.blog.services.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")

public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/version")
	public ResponseEntity version() {
		String metaData = System.getenv("MTA_METADATA");
		System.out.print("MTA_METADATA" + metaData);

		if (metaData != null && metaData.length() > 0) {
			return ResponseEntity.ok().body(metaData);
		} else {
			StringBuilder builder = new StringBuilder();
			builder.append("API is UP");
			return ResponseEntity.ok().body(builder.toString());
		}
	}

	@PostMapping("/create")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto createDto) {
		CategoryDto categoryDto = this.categoryService.createCategory(createDto);
		return new ResponseEntity<>(categoryDto, HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<CategoryDto>> getAllUsers() {
		List<CategoryDto> categoryList = this.categoryService.getAllCategories();
		return ResponseEntity.ok(categoryList);
	}

	@GetMapping("/get/{Id}")
	public ResponseEntity<CategoryDto> getAllUsers(
			@PathVariable("Id") Integer Id) {
	CategoryDto cat=this.categoryService.getCategory(Id);
		return ResponseEntity.ok(cat);
	}
	
	@PutMapping("/update/{Id}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto updateDto,@PathVariable Integer Id) {
		CategoryDto categoryDto = this.categoryService.updateCategory(updateDto,Id);
		return new ResponseEntity<>(categoryDto, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{Id}")
	public ResponseEntity<APIResponse> deleteCategory(@PathVariable Integer Id) {
		 this.categoryService.deleteCategory(Id);
		 return new ResponseEntity<APIResponse>(new APIResponse("User Deleted Successfully",true), HttpStatus.OK);
	 
	}
	
	

}
