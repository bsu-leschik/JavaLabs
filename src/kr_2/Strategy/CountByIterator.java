package kr_2.Strategy;

import kr_2.Itertor.Iterator;
import kr_2.stack.Stack;

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
