package com.exam.examserver.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.examserver.entity.exam.Category;
import com.exam.examserver.service.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	//add category
	@PostMapping("/")
	public ResponseEntity<Category> addCategory(@RequestBody Category category) {
		Category category1 = this.categoryService.addCategory(category);
		return ResponseEntity.ok(category1);
	}
	
	//update category
	@PutMapping("/")
	public Category updateCategory(@RequestBody Category category) {
		return this.categoryService.updateCategory(category);
	}
	
	@GetMapping("/{categoryId}")
	public Category getCategory(@PathVariable Long categoryId) {
		return this.categoryService.getCategory(categoryId);
	}
	
	@GetMapping("/")
	public ResponseEntity<Set<Category>> getCategories() {
		@SuppressWarnings("unchecked")
		Set<Category> categories = (Set<Category>) this.categoryService.getCategories();
		return ResponseEntity.ok(categories);
	}
	
	@DeleteMapping("/{categoryId}")
	public void deleteCategory(@PathVariable("categoryId") Long categoryId) {
		this.categoryService.deleteCategory(categoryId);
	}

}
