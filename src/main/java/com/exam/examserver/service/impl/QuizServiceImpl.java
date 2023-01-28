package com.exam.examserver.service.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.examserver.entity.exam.Category;
import com.exam.examserver.entity.exam.Quiz;
import com.exam.examserver.repository.QuizRepository;
import com.exam.examserver.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	private QuizRepository quizRepository;
	
	@Override
	public Quiz addQuiz(Quiz quiz) {
		return this.quizRepository.save(quiz);
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		return this.quizRepository.save(quiz);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Quiz getQuiz(Long qid) {
		return this.quizRepository.findById(qid).get();
	}

	@Override
	public Set<Quiz> getQuizzes() {
		return new LinkedHashSet<>(this.quizRepository.findAll());
	}

	@Override
	public void deleteQuiz(Long qid) {
		this.quizRepository.deleteById(qid);
	}

	@Override
	public List<Quiz> getQuizzesByCategory(Long cId) {
		Category category = new Category();
		category.setcId(cId);
		return this.quizRepository.findByCategory(category);
	}

}
