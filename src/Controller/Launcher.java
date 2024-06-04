package src.Controller;

import javax.swing.*;

import src.View.GameFrame;

public class  Launcher {
    public static void main(String[] agrs) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //GameFrame game = new GameFrame();

                src.Model.Game.GameLogic gameLogic = new src.Model.Game.GameLogic(); // Create an instance of GameLogic
                GameFrame gameFrame = new GameFrame(gameLogic); // Pass the GameLogic instance to the GameFrame constructor
            }
        });
    }
}

