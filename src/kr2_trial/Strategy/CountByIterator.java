package kr2_trial.Strategy;

import kr2_trial.Itertor.Iterator;
import kr2_trial.stack.Stack;

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
