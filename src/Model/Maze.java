package src.Model;

import java.util.ArrayList;
import java.util.HashSet;

public class Maze {
    private ArrayList<Room> myRoomList;
    private Room[][] rooms;
    private HashSet<Integer> roomNumbersSet;

    public Maze() {
        myRoomList = new ArrayList<>();
        roomNumbersSet = new HashSet<>();
        int[][] layout = {
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
    }

    private void initializeRooms(int[][] layout) {
        rooms = new Room[layout.length][layout[0].length];

        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[i].length; j++) {
                int roomNumber = layout[i][j];
                if (roomNumber >= 10) {
                    rooms[i][j] = new Room(roomNumber);
                    if (!roomNumbersSet.contains(roomNumber)) {
                        myRoomList.add(rooms[i][j]);
                        roomNumbersSet.add(roomNumber);
                    }
                }
            }
        }
    }

    public Room[][] getRooms() {
        return rooms;
    }

    public ArrayList<Room> getMyRoomList() {
        return myRoomList;
    }
}
