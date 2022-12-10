package kr_2.Visitor;

import kr_2.stack.Stack;

public interface Visitor {

    int size = 0;

    void visitStack(Stack stack);
}
