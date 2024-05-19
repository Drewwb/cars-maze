package src.Model;

public class MazeTest {
    public static void main(String[] args) {
        // Create a new maze
        Maze maze = new Maze();

        // Display the layout of the maze
        System.out.println("Maze Layout:");
        //printMazeLayout(maze.getRooms());

        // Test accessing a room and its doors
        System.out.println("\nAccessing Rooms:");
        for (Room room : maze.getMyRoomList()) {
            System.out.println("Room number: " + room.getRoomNumber());
            System.out.println("Visited: " + room.isVisited());
            System.out.println("Number of Doors: " + room.getDoors().length);
            System.out.println("Doors Directions:");
            for (Door door : room.getDoors()) {
                System.out.println(door.getMyDirection());
            }
            System.out.println();
        }
    }
}

