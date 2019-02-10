package by.chmut.composite.interpreter;

import java.util.ArrayDeque;

public class Context {

    private ArrayDeque<Long> contextValue = new ArrayDeque<>();

    public void push(Long number) {
        contextValue.push(number);
    }

    public Long pop() {
        return contextValue.pop();
    }
}
