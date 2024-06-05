package src.Model.Questions;

import src.Model.Questions.Question;

import java.io.Serializable;

public class TrueFalseQuestion extends Question implements Serializable {

    public TrueFalseQuestion(int id) {
        super("TrueFalse", id);

    }
}
