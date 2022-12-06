package Lab03_MazeSolver;

import java.util.EmptyStackException;

class MyStack implements StackADT {
    private Square[] stack;
    private int size;

    MyStack() {
        this(2);
    }

    MyStack(int initCap) {
        stack = new Square[initCap];
        size = 0;
    }

    @Override
    public void push(Square item) {
        if(stack.length < size + 1) doubleCapacity();
        stack[size] = item;
        size++;
    }

    @Override
    public Square pop() {
        if(isEmpty()) throw new EmptyStackException();

        Square x = peek();
        stack[size - 1] = null;
        size--;
        return x;
    }

    @Override
    public Square peek() {
        if(isEmpty()) throw new EmptyStackException();

        return stack[size - 1];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        while(!isEmpty()) {
            pop();
        }
    }

    private void doubleCapacity() {
        Square[] newStack = new Square[stack.length * 2];
        System.arraycopy(stack, 0, newStack, 0, stack.length);
        stack = newStack;
    }
}
