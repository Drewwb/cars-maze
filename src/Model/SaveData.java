package src.Model;

import java.io.*;

public class SaveData { // this class will have a method to saveData and method to loadData using serializable
    // Method to save player data to a file
    public static void saveGame(Object object, String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(object); // Write the object to the file
            out.close();
            fileOut.close();
            System.out.println("Game data saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to load game data from a file
    public static Object loadGame(String fileName) {
        Object object = null;
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            object = in.readObject(); // Read the object from the file
            in.close();
            fileIn.close();
            System.out.println("Game data loaded.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }



}
