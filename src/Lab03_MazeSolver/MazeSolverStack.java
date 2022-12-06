package Lab03_MazeSolver;

public class MazeSolverStack extends MazeSolver {
    private MyStack worklist;

    MazeSolverStack(Maze maze) {
        super(maze);
    }

    @Override
    void makeEmpty() {
        worklist = new MyStack();
    }

    @Override
    boolean isEmpty() {
        return worklist.isEmpty();
    }

    @Override
    void add(Square s) {
        worklist.push(s);
        s.setStatus(Square.EXPLORED);
    }

    @Override
    Square next() {
        return worklist.peek();
    }
}
