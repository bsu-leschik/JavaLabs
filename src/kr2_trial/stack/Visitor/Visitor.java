package kr2_trial.stack.Visitor;

import kr2_trial.stack.Visitor.Stack.Stack;

public interface Visitor {

    int size = 0;

    void visitStack(Stack stack);

    default int getSize() {
        return size;
    }
}
