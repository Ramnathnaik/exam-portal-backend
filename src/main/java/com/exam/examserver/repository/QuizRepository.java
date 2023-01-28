package com.exam.examserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.examserver.entity.exam.Category;
import com.exam.examserver.entity.exam.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long>{
	
	public List<Quiz> findByCategory(Category category);

}
