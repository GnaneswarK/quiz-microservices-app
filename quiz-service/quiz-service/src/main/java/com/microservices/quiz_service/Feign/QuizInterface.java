package com.microservices.quiz_service.Feign;

import com.microservices.quiz_service.Model.QuestionWrapper;
import com.microservices.quiz_service.Model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

//@FeignClient("QUESTION-SERVICE")
@FeignClient(name = "question-service", url = "http://localhost:8080/")
public interface QuizInterface
{
    @GetMapping("question/generate")
    ResponseEntity<List<Integer>> getQuestionsFromQuiz(@RequestParam String category, @RequestParam int numberOfQuestions);

    @PostMapping("question/getQuestions")
    ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionId);

    @PostMapping("question/score")
    ResponseEntity<Integer> getResponseFromQuiz(@RequestBody List<Response> responses);
}
