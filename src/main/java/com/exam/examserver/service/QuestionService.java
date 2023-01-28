package com.exam.examserver.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.exam.examserver.entity.exam.Question;
import com.exam.examserver.entity.exam.Quiz;

@Service
public interface QuestionService {
	
	public Question addQuestion(Question question);
	
	public Question updateQuestion(Question question);
	
	public Question getQuestion(Long qusId);
	
	public Set<Question> getQuestions();
	
	public Set<Question> getQuestionsOfQuiz(Quiz quiz);
	
	public void deleteQuestion(Long qusId);

}
