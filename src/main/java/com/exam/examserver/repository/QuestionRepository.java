package com.exam.examserver.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.examserver.entity.exam.Question;
import com.exam.examserver.entity.exam.Quiz;

public interface QuestionRepository extends JpaRepository<Question, Long>{
	
	public Set<Question> findByQuiz(Quiz quiz);

}
