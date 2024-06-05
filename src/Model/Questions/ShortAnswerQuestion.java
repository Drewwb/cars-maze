package src.Model.Questions;

import src.Model.Questions.Question;

import java.io.Serializable;

public class ShortAnswerQuestion extends Question implements Serializable {

    public ShortAnswerQuestion(int id) {
        super("ShortAnswer", id);
    }

}
