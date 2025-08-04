package com.microservices.quiz_service.Service;

import com.microservices.quiz_service.Feign.QuizInterface;
import com.microservices.quiz_service.Model.QuestionWrapper;
import com.microservices.quiz_service.Model.Quiz;
import com.microservices.quiz_service.Model.Response;
import com.microservices.quiz_service.Repository.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServices
{
    @Autowired
    private QuizRepo quizRepo;
    @Autowired
    private QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title)
    {

        List<Integer> questionids = quizInterface.getQuestionsFromQuiz(category,numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questionids);
        quizRepo.save(quiz);
        return new ResponseEntity<>("Successfully created quiz question",HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id)
    {
            Quiz quizList = quizRepo.findById(id).get();
            List<Integer> questionIds = quizList.getQuestionIds();
        return quizInterface.getQuestionsFromId(questionIds);
    }

    public ResponseEntity<Integer> getScore(int id, List<Response> responses)
    {
        Quiz quiz = quizRepo.findById(id).get();
        return quizInterface.getResponseFromQuiz(responses);
    }
}
