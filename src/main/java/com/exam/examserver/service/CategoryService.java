package com.exam.examserver.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.exam.examserver.entity.exam.Category;

@Service
public interface CategoryService {

	public Category addCategory(Category category);
	
	public Category updateCategory(Category category);
	
	public Category getCategory(Long cid);
	
	public Set<Category> getCategories();
	
	public void deleteCategory(Long cid);
	
}
