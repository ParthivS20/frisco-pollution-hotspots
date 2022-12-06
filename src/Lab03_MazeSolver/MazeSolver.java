package Lab03_MazeSolver;

public abstract class MazeSolver {
    private Maze maze;
    private boolean solved;

    MazeSolver(Maze maze) {
        this.maze = maze;
        makeEmpty();
        add(maze.getStart());
    }

    abstract void makeEmpty();
    abstract boolean isEmpty();
    abstract void add(Square s);
    abstract Square next();

    void setSolved(boolean solved) {
        this.solved = solved;
    }

    boolean isSolved() {
        return solved;
    }

    void step() {
        if(next().equals(maze.getExit())) setSolved(true);
    }

    String getPath() {
        return "";
    }

    void solve() {

    }
}
