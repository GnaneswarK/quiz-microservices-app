package com.microservices.question_service.Repository;

import com.microservices.question_service.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Integer>
{
       List<Question> findByCategory(String Category);

       @Query(value = "Select q.id from Question q where q.category = :category ORDER BY Random() LIMIT :numQ ",nativeQuery = true)
       List<Integer> findRandomQuestionByCategory(String category, int numQ);
}
