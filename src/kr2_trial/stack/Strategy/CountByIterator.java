package kr2_trial.stack.Strategy;

import kr2_trial.stack.Visitor.Stack.Iterator;
import kr2_trial.stack.Visitor.Stack.Stack;

public class CountByIterator implements Strategy {

    Stack stack;


    public CountByIterator(Stack stack){
        this.stack = stack;
    }

    @Override
    public int execute() {
        Iterator iterator = stack.createIterator();
        int i = 0;
        while (iterator.hasNext()){
            i++;
            iterator.getNext();
        }
        return i;
    }
}
