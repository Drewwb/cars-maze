package src.Model;

public class Door {
    private Direction myDirection;
    private Question myQuestion;
    private boolean myDoorLock;

    public Door(Direction theDirection) {
        this.myDirection = theDirection;
        QuestionFactory singleQuestion = new QuestionFactory();
        this.myQuestion = singleQuestion.createQuestion();

        //default value for the door -  True = locked, False = opened
        this.myDoorLock = true;
    }

    public boolean isDoorLocked() {
        return myDoorLock;
    }
    public void setDirection(Direction theDirection) {
        this.myDirection = theDirection;
    }
    public Direction getMyDirection() {
        return myDirection;
    }
    public void setQuestion(Question theQuestion) {
        this.myQuestion = theQuestion;
    }
    public Question getMyQuestion() {
        return myQuestion;
    }
    public void setDoorLock(boolean theDoorLock) {
        this.myDoorLock = theDoorLock;
    }
}
