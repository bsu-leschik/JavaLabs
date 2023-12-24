package kr_2.Strategy;

import kr_2.Visitor.StackVisitor;
import kr_2.stack.Stack;

public class CountByVisitorIterator implements Strategy {

    Stack stack;


    public CountByVisitorIterator(Stack stack){
        this.stack = stack;
    }

    @Override
    public int execute() {
        StackVisitor visitor = new StackVisitor();
        visitor.visitStack(stack);
        return visitor.getSize();
    }
}
