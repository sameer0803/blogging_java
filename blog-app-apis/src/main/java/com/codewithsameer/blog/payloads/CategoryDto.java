package com.codewithsameer.blog.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CategoryDto {

	private Integer categoryId;
	@NotEmpty
	@Size(min = 3, max = 40, message = "Username must be of 3 characters and 40 characters")

	private String categoryTitle;

	@NotEmpty
	@Size(min = 3, max = 40, message = "Username must be of 5 characters and 100 characters")
	private String categoryDescription;

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public CategoryDto() {
		super();
	}

}
