package Controller;

import View.GameFrame;

import javax.swing.*;

public class Launcher {
    public static void main(String[] agrs) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameFrame game = new GameFrame();
            }
        });
    }
}
