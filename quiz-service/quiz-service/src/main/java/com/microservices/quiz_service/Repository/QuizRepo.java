package com.microservices.quiz_service.Repository;

import com.microservices.quiz_service.Model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepo extends JpaRepository<Quiz,Integer>
{

}
