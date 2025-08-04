package com.microservices.quiz_service.Model;

import lombok.Data;

@Data
public class Parameters
{
    String category;
    int numberOfQuestions;
    String title;
}
