package src.Model.Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class Maze {
    private ArrayList<Room> myRoomList;
    private Room[][] rooms;
    private HashSet<Integer> roomNumbersSet;
    private int[][] layout;
    private HashMap<Integer, int[]> validKeyCoordinates;
    private int keyRow;
    private int keyCol;
    private Random random;

    public Maze() {
        this.random = new Random();
        myRoomList = new ArrayList<>();
        roomNumbersSet = new HashSet<>();
        layout = new int[][] {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 10, 10, 2, 11, 11, 2, 12, 12, 2, 13, 13, 2, 14, 14, 1},
                {1, 10, 10, 1, 11, 11, 1, 12, 12, 1, 13, 13, 1, 14, 14, 1},
                {1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1},
                {1, 15, 15, 1, 16, 16, 1, 17, 17, 1, 18, 18, 1, 19, 19, 1},
                {1, 15, 15, 2, 16, 16, 2, 17, 17, 2, 18, 18, 2, 19, 19, 1},
                {1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1},
                {1, 20, 20, 1, 21, 21, 1, 22, 22, 1, 23, 23, 1, 24, 24, 1},
                {1, 20, 20, 2, 21, 21, 2, 22, 22, 2, 23, 23, 2, 24, 24, 3},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };

        initializeRooms(layout);
        setKeyCoordinates();
    }

    private void initializeRooms(int[][] layout) {
        rooms = new Room[layout.length][layout[0].length];

        int totalRooms = 0; //map and counter for key coordinates logic
        validKeyCoordinates = new HashMap<>(); //map holds every coordinate for valid key position

        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[i].length; j++) {
                int roomNumber = layout[i][j];
                if (roomNumber >= 10) {

                    //valid room for a key so add the coordinates to map
                    int[] coords = new int[2];
                    coords[0] = i; //row
                    coords[1] = j; //col
                    validKeyCoordinates.put(totalRooms, coords);
                    totalRooms++; //total rooms is the key for the map

                    rooms[i][j] = new Room(roomNumber);
                    if (!roomNumbersSet.contains(roomNumber)) {
                        myRoomList.add(rooms[i][j]);
                        roomNumbersSet.add(roomNumber);
                    }
                }
            }
        }
    }

    public void setKeyCoordinates() {
        int keyRoom = random.nextInt(validKeyCoordinates.size());
        int[] keyCoords = validKeyCoordinates.get(keyRoom);
        this.keyRow = keyCoords[0];
        this.keyCol = keyCoords[1];
    }

    public int[] getKeyCoordinates() {
        int[] keyRoom = new int[2];
        keyRoom[0] = this.keyRow;
        keyRoom[1] = this.keyCol;
        return keyRoom;
    }
    public int getCurrentValue(int row, int col) {
        return layout[row][col];
    }
    public void setCurrentValue(int row, int col, int value) {
        layout[row][col] = value;
    }

    public Room[][] getRooms() {
        return rooms;
    }

    public ArrayList<Room> getMyRoomList() {
        return myRoomList;
    }
}
