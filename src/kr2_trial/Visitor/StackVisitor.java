package kr2_trial.Visitor;

import kr2_trial.Itertor.Iterator;
import kr2_trial.stack.Stack;

public class StackVisitor implements Visitor {

    int size;

    @Override
    public void visitStack(Stack stack) {
        Iterator iterator = stack.createIterator();
        int i = 0;
        while (iterator.hasNext()){
            i++;
            iterator.getNext();
        }
        this.size = i;
    }

    public int getSize() {
        return size;
    }
}
