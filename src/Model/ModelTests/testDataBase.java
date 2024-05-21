package src.Model.ModelTests;

import src.Model.Questions.MultipleChoiceQuestion;
import src.Model.Questions.Question;
import src.Model.Questions.QuestionFactory;
import src.Model.Questions.ShortAnswerQuestion;
import src.Model.Questions.TrueFalseQuestion;

public class testDataBase {
    // test huyhuynh

    public static void main(String[] args) {
        // Instantiate QuestionFactory
        QuestionFactory questionFactory = new QuestionFactory();

        // Create a question
        Question question = questionFactory.createQuestion();

        // Check the type of the question and interact accordingly
        if (question instanceof MultipleChoiceQuestion) {
            MultipleChoiceQuestion mcQuestion = (MultipleChoiceQuestion) question;
            System.out.println("Multiple Choice Question:" + mcQuestion.getQuestion());
            System.out.println("Question ID: " + mcQuestion.getId());
            System.out.println("Question type: " + mcQuestion.getType());
            System.out.println("Question answer: " + mcQuestion.getAnswer());

            System.out.println("Options: ");
            for (String option : mcQuestion.getOptions()) {
                System.out.println(option);
            }
        } else if (question instanceof TrueFalseQuestion) {
            TrueFalseQuestion tfQuestion = (TrueFalseQuestion) question;
            System.out.println("True/False Question:" + tfQuestion.getQuestion());
            System.out.println("Question ID: " + tfQuestion.getId());
            System.out.println("Question type: " + tfQuestion.getType());
            System.out.println("Question answer: " + tfQuestion.getAnswer());
        } else if (question instanceof ShortAnswerQuestion) {
            ShortAnswerQuestion saQuestion = (ShortAnswerQuestion) question;
            System.out.println("Short Answer Question:" + saQuestion.getQuestion());
            System.out.println("Question ID: " + saQuestion.getId());
            System.out.println("Question type: " + saQuestion.getType());
            System.out.println("Question answer: " + saQuestion.getAnswer());
        }
    }
}
