package com.codewithsameer.blog.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codewithsameer.blog.entities.CategoriesEntity;
import com.codewithsameer.blog.exceptions.ResourceNotFoundException;
import com.codewithsameer.blog.payloads.CategoryDto;
import com.codewithsameer.blog.repositories.CategoryRepo;
import com.codewithsameer.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {

		CategoriesEntity categoryEntity = dtoToEntity(categoryDto);
		CategoriesEntity savedCategoriesEntity = categoryRepo.save(categoryEntity);
		CategoryDto convertedDto = EntityToDto(savedCategoriesEntity);
		return convertedDto;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

		CategoriesEntity categoryEnt = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", "CategoryId", categoryId));

		categoryEnt.setCategoryDescription(categoryDto.getCategoryDescription());
		categoryEnt.setCategoryTitle(categoryDto.getCategoryTitle());
		return this.EntityToDto(categoryEnt);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		

	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		CategoriesEntity categoryEnt = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", "CategoryId", categoryId));
		return this.EntityToDto(categoryEnt);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<CategoriesEntity> list = this.categoryRepo.findAll();
		List<CategoryDto> categoryList = list.stream().map(user -> this.EntityToDto(user)).collect(Collectors.toList());
		return categoryList;
	}

	private CategoriesEntity dtoToEntity(CategoryDto categoryDto) {
		CategoriesEntity categoryEntity = this.modelMapper.map(categoryDto, CategoriesEntity.class);
		return categoryEntity;
	}

	public CategoryDto EntityToDto(CategoriesEntity categoryEntity) {
		CategoryDto categoryDto = this.modelMapper.map(categoryEntity, CategoryDto.class);
		return categoryDto;
	}
}
