package com.microservices.question_service.Controller;

import com.microservices.question_service.Model.Question;
import com.microservices.question_service.Model.QuestionWrapper;
import com.microservices.question_service.Model.Response;
import com.microservices.question_service.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question/")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("allQuestions")
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{categoryType}")
    public List<Question> getAllQuestionsByCategory(@PathVariable String categoryType) {
        return questionService.getAllQuestionsByCategory(categoryType);
    }

    @PostMapping("add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return new ResponseEntity<>(questionService.addQuestion(question), HttpStatus.OK);
    }

    @DeleteMapping("delete/{Id}")
    public ResponseEntity<?> deleteQuestionById(@PathVariable int Id) {
        questionService.deleteQuestionById(Id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsFromQuiz(@RequestParam String category,@RequestParam int numberOfQuestions){
        return questionService.getQuestionsFromQuiz(category,numberOfQuestions);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionId){
        return questionService.getQuestionsFromId(questionId);
    }

    @PostMapping("score")
    public ResponseEntity<Integer> getResponseFromQuiz(@RequestBody List<Response> responses){
        return questionService.getResponseFromQuiz(responses);
    }
    //generate
    //getquestions id
    //score
}
