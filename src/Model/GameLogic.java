package src.Model;

public class GameLogic {
    private int points;
    private boolean answerCorrect;
    private boolean gameOver;
    private Maze myMaze;

    public GameLogic() {
        myMaze = new Maze();


        this.points = 0;
        this.answerCorrect = false;
        this.gameOver = false;
    }
    // spawn character
    private void characterSpawn() {

    }

    public int getPoints() {
        return this.points;
    }
    public void setPoints(int points) {
        this.points = points;
    }
    public boolean getAnswerStatement() {
        return this.answerCorrect;
    }
    public void setAnswerStatement(boolean answerCorrect) {
        this.answerCorrect = answerCorrect;
    }
    public void incrementPoints(int points) {
        this.points += points;
    }
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
