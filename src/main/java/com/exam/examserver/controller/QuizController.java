package com.exam.examserver.controller;

import java.util.List;
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

import com.exam.examserver.entity.exam.Quiz;
import com.exam.examserver.service.QuizService;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {
	
	@Autowired
	private QuizService quizService;
	
	//add quiz
	@PostMapping("/")
	public Quiz addQuiz(@RequestBody Quiz quiz) {
		return this.quizService.addQuiz(quiz);
	}
	
	//update quiz
	@PutMapping("/")
	public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz) {
		Quiz quiz1 = this.quizService.updateQuiz(quiz);
		return ResponseEntity.ok(quiz1);
	}
	
	//Get quizzes
	@GetMapping("/")
	public Set<Quiz> getQuizzes() {
		return this.quizService.getQuizzes();
	}
	
	//get quiz
	@GetMapping("/{quizId}")
	public ResponseEntity<Quiz> getQuiz(@PathVariable Long quizId) {
		Quiz quiz = this.quizService.getQuiz(quizId);
		return ResponseEntity.ok(quiz);
	}
	
	//delete quiz
	@DeleteMapping("/{quizId}")
	public void deleteQuiz(@PathVariable("quizId") Long quizId) {
		this.quizService.deleteQuiz(quizId);
	}
	
	//get quizzes based on Category
	@GetMapping("/category/{cId}")
	public List<Quiz> getQuizzesOfCategory(@PathVariable Long cId) {
		return this.quizService.getQuizzesByCategory(cId);
	}

}
