package src.Model;

public class Door {
    private Direction myDirection;
    private Question myQuestion;
    private boolean myDoorLock;

    public Door(Direction theDirection, Question theQuestion) {
        this.myDirection = theDirection;
        this.myQuestion = theQuestion;
        this.myDoorLock = false;
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
