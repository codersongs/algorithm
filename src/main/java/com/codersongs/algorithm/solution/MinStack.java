package com.codersongs.algorithm.solution;

import java.util.Deque;
import java.util.LinkedList;

public class MinStack {
    private Deque<Integer> minStack;
    private Deque<Integer> stack;
    public MinStack() {
        this.minStack = new LinkedList<>();
        this.stack = new LinkedList<>();
        this.minStack.push(Integer.MAX_VALUE);
    }

    public void push(int val) {
        this.stack.push(val);
        this.minStack.push(Math.min(this.minStack.peek(), val));
    }

    public void pop() {
        this.stack.pop();
        this.minStack.pop();
    }

    public int top() {
        return this.stack.peek();
    }

    public int getMin() {
        return this.minStack.peek();
    }
}
