package Lab02_MyStack;

import java.util.EmptyStackException;
import java.util.Stack;

class MyStack {
    private Integer[] stack;
    private int size;
    private Stack<Integer> mins;

    public MyStack() {
        this(2);
    }

    public MyStack(int initCap) {
        stack = new Integer[initCap];
        size = 0;
        mins =  new Stack<>();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Integer peek() {
        if(isEmpty()) throw new EmptyStackException();

        return stack[size - 1];
    }

    public Integer pop() {
        if(isEmpty()) throw new EmptyStackException();

        Integer x = peek();
        stack[size - 1] = null;
        size--;

        if(x.equals(mins.peek())) {
            mins.pop();
        }

        return x;
    }

    public void push(Integer item) {
        if(stack.length < size + 1) {
            doubleCapacity();
        }

        if(mins.isEmpty() || item < mins.peek()) {
            mins.push(item);
        }

        stack[size] = item;
        size++;
    }

    public Integer getMin() {
        return mins.peek();
    }

    private void doubleCapacity() {
        Integer[] newStack = new Integer[stack.length * 2];
        for(int i = 0; i < stack.length; i++) {
            newStack[i] = stack[i];
        }
        stack = newStack;
    }

    @Override
    public String toString() {
        String x = "";
        for(int i = size - 1; i >= 0; i--) {
            x += stack[i];
            if(i == size - 1) {
                x += "\t<----- TOP";
            }
            x += "\n";
        }
        return x + "¯¯¯¯¯¯¯";
    }
}
