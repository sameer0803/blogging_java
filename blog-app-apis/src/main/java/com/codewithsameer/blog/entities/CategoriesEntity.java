package com.codewithsameer.blog.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="category")

public class CategoriesEntity {


	@Id
	@GeneratedValue(strategy = GenerationType. IDENTITY)
	private Integer categoryId;

	@Column(name="title”,length = 100,nullable = false")
	private String categoryTitle;

	@Column(name="description")
	private String categoryDescription;
	
	
	@OneToMany(mappedBy ="category",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	//cascade:-  parent hataaye to child bhi hat jaaye,parent add kre to child se alg save na krna pde
	//fetch :- parent nikale to child na nikle
	private List<Post> posts=new ArrayList<>();


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


	public List<Post> getPosts() {
		return posts;
	}


	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}


	public CategoriesEntity() {
		super();
	}


	public CategoriesEntity(Integer categoryId, String categoryTitle, String categoryDescription, List<Post> posts) {
		super();
		this.categoryId = categoryId;
		this.categoryTitle = categoryTitle;
		this.categoryDescription = categoryDescription;
		this.posts = posts;
	}
	
	

}
