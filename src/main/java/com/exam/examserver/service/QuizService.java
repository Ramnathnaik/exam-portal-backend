package com.exam.examserver.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.exam.examserver.entity.exam.Quiz;

@Service
public interface QuizService {

	public Quiz addQuiz(Quiz quiz);

	public Quiz updateQuiz(Quiz quiz);

	public Quiz getQuiz(Long qid);

	public Set<Quiz> getQuizzes();

	public void deleteQuiz(Long qid);
	
	public List<Quiz> getQuizzesByCategory(Long cId);

}
