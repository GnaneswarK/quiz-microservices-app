package com.microservices.question_service.Service;


import com.microservices.question_service.Model.Question;
import com.microservices.question_service.Model.QuestionWrapper;
import com.microservices.question_service.Model.Response;
import com.microservices.question_service.Repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionService
{
    @Autowired
    private QuestionRepo questionRepo;
    public List<Question> getAllQuestions() {
        return questionRepo.findAll();
    }

    public List<Question> getAllQuestionsByCategory(String categoryType)
    {
        return questionRepo.findByCategory(categoryType);
    }

    public Question addQuestion(Question question)
    {
        questionRepo.save(question);
        return question;
    }

    public void deleteQuestionById(int id)
    {
        questionRepo.deleteById(id);
    }

    public ResponseEntity<List<Integer>> getQuestionsFromQuiz(String category, int numberOfQuestions)
    {
        List<Integer> questions = questionRepo.findRandomQuestionByCategory(category,numberOfQuestions);

        return new ResponseEntity<>(questions, HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionId)
    {
        List<Question> questions = new ArrayList<>();
        List<QuestionWrapper> wrapper = new ArrayList<>();
        for(Integer id : questionId) {
            questions.add(questionRepo.findById(id).get());
        }
        for(Question q : questions){
            QuestionWrapper questionWrapper = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            wrapper.add(questionWrapper);
        }
        return new ResponseEntity<>(wrapper,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getResponseFromQuiz(List<Response> responses)
    {
        int right = 0;
        for(Response r : responses) {
            Question question = questionRepo.findById(r.getId()).get();
            if (r.getResponse().equals(question.getRightAnswer()))
                right++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
