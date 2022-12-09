package kr2_trial.Strategy;

import kr2_trial.stack.Stack;
import kr2_trial.Visitor.StackVisitor;
import kr2_trial.Visitor.Visitor;

public class CountByVisitorIterator implements Strategy {

    Stack stack;


    public CountByVisitorIterator(Stack stack){
        this.stack = stack;
    }

    @Override
    public int execute() {
        Visitor visitor = new StackVisitor();
        visitor.visitStack(stack);
        return visitor.getSize();
    }
}
