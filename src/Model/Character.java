package Model;

public class Character { // character class to represent users game character




    private int myX;

    private int myY;

    private int myInitialX;

    private int myInitialY;

    private boolean hasKey;

    Character(final int theX, final int theY){  // passed coordinates for current and initial location
        myX = theX;
        myY = theY;
        myInitialX = theX;
        myInitialY = theY;
    }
    public int getMyX() {
        return myX;
    }

    public int getMyY() {
        return myY;
    }

    public void setMyX(final int theX) {
        myX = theX;
    }

    public void setMyY(final int theY) {
        myY = theY;
    }
    public void moveDown(){ // Moves character down by decrementing Y coordinate
        --myY;
    }
    public void moveUp(){
        ++myY;
    }
    public void moveLeft(){
        --myX;
    }
    public void moveRight(){
        ++myX;
    }
    public void foundKey(){
        hasKey = true;
    }
    public void useKey(){
        if(hasKey = true){
            hasKey = false;
        } else{
            System.out.println("You do not have a key");
        }
    }




}
