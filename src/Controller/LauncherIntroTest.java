package src.Controller;


import src.View.GameIntro;

import javax.swing.*;

public class LauncherIntroTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameIntro gameIntro = new GameIntro();
            }
        });
    }
}
