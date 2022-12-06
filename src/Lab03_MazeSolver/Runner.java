package Lab03_MazeSolver;

public class Runner {
    public static void main(String[] args) {
        Maze maze = new Maze();
        maze.loadMaze("maze-2");
        System.out.println(maze.getNeighbors(new Square(0, 0, 0)));
        System.out.println(maze.getStart());
        System.out.println(maze.getExit());
        System.out.println(maze);
    }
}
