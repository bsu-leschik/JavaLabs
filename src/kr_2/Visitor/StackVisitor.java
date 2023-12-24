package kr_2.Visitor;

import kr_2.Itertor.Iterator;
import kr_2.stack.Stack;

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
