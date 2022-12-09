package kr2_trial.Visitor;

import kr2_trial.stack.Stack;

public interface Visitor {

    int size = 0;

    void visitStack(Stack stack);

    default int getSize() {
        return size;
    }
}
