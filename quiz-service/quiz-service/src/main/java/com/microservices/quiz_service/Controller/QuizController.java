package com.microservices.quiz_service.Controller;

import com.microservices.quiz_service.Model.Parameters;
import com.microservices.quiz_service.Model.QuestionWrapper;
import com.microservices.quiz_service.Model.Response;
import com.microservices.quiz_service.Service.QuizServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz/")
public class QuizController {
    @Autowired
    private QuizServices quizServices;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody Parameters parameters){
        return quizServices.createQuiz(parameters.getCategory(),parameters.getNumberOfQuestions(),parameters.getTitle());
    }
    @GetMapping("exam/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id){
        return quizServices.getQuizQuestions(id);
    }
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submittedQuestions(@PathVariable int id,@RequestBody List<Response> responses){
        return quizServices.getScore(id,responses);
    }
}
