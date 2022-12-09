package kr2_trial.stack.Strategy;

import kr2_trial.stack.Visitor.Stack.Stack;
import kr2_trial.stack.Visitor.StackVisitor;
import kr2_trial.stack.Visitor.Visitor;

public class CountByStrategyIterator implements Strategy {

    Stack stack;


    public CountByStrategyIterator(Stack stack){
        this.stack = stack;
    }

    @Override
    public int execute() {
        Visitor visitor = new StackVisitor();
        visitor.visitStack(stack);
        return visitor.getSize();
    }
}
