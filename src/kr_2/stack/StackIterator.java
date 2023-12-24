package kr_2.stack;

import kr_2.Itertor.Iterator;

public class StackIterator implements Iterator {

    Stack stack;

    int currentElementIndex = 0;
    
    StackIterator(Stack stack){
        this.stack = stack;
    }

    @Override
    public boolean hasNext() {
        return currentElementIndex < stack.data.size();
    }

    @Override
    public int getNext() {
        return stack.data.get(currentElementIndex++);
    }

    @Override
    public void reset() {
        currentElementIndex = 0;
    }
}
