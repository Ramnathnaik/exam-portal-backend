package com.exam.examserver.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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

import com.exam.examserver.entity.exam.Question;
import com.exam.examserver.entity.exam.Quiz;
import com.exam.examserver.service.QuestionService;
import com.exam.examserver.service.QuizService;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private QuizService quizService;
	
	//add question
	@PostMapping("/")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
		Question question1 = this.questionService.addQuestion(question);
		return ResponseEntity.ok(question1);
	}
	
	//update question
	@PutMapping("/")
	public Question updateQuestion(@RequestBody Question question) {
		return this.questionService.updateQuestion(question);
	}
	
//	get question
	@GetMapping("/{qusId}")
	public Question getQuestion(@PathVariable Long qusId) {
		return this.questionService.getQuestion(qusId);
	}
	
	//get all questions
	@GetMapping("/")
	public Set<Question> getQuestions() {
		return this.questionService.getQuestions();
	}
	
	//get questions of a quiz
	@GetMapping("/quiz/{quizId}")
	public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable Long quizId) {
//		Quiz quiz = new Quiz();
//		quiz.setqId(quizId);
//		return this.questionService.getQuestionsOfQuiz(quiz);
		
		Quiz quiz = this.quizService.getQuiz(quizId);
		Set<Question> questions = quiz.getQuestions();
		questions.forEach(q -> {
			q.setAnswer("");
		});
		List list = new ArrayList<>(questions);
		
		if (list.size() > Integer.parseInt(quiz.getNumberOfQuestions())) {
			list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions() + 1));
		}
		
		Collections.shuffle(list);
		return ResponseEntity.ok(list);
	}
	
	//get all questions of a quiz
	@GetMapping("/quiz/all/{quizId}")
	public Set<Question> getQuestionsOfQuizAdmin(@PathVariable Long quizId) {
		Quiz quiz = new Quiz();
		quiz.setqId(quizId);
		return this.questionService.getQuestionsOfQuiz(quiz);
	}
	
	//delete a question
	@DeleteMapping("/{qusId}")
	public void deleteQuestion(@PathVariable Long qusId) {
		this.questionService.deleteQuestion(qusId);
	}
	
	//Evaluate quiz
	@PostMapping("/evalQuiz")
	public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions) {
		int marksGot = 0;
		int attempted = 0;
		int correctAnswers = 0;
		double singleQuestionMarks = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks()) / questions.size();
		
		for (Question q : questions)
		{
			Question question = this.questionService.getQuestion(q.getQusId());
			if (question.getAnswer().trim().equals(q.getGivenAnswer().trim()))
			{
				marksGot += singleQuestionMarks;
				correctAnswers++;
			}
			if (!q.getGivenAnswer().equals("")) {
				attempted++;
			}
		}
		
		Map<Object, Object> of = Map.of("marksGot", marksGot, "attempted", attempted, "correctAnswers", correctAnswers);
		
		return ResponseEntity.ok(of);
	}

}
