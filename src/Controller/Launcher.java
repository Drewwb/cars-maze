package src.Controller;

import javax.swing.*;

import src.View.GameFrame;

public class  Launcher {
    public static void main(String[] agrs) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameFrame game = new GameFrame();
            }
        });
    }
}

