package kr2_trial.stack;

import java.util.ArrayList;

public class StackIterator implements Iterator{

    ArrayList<Integer> data;
    
    int currentElementIndex = 0;
    
    StackIterator(ArrayList<Integer> data){
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return currentElementIndex < data.size();
    }

    @Override
    public int getNext() {
        return data.get(++currentElementIndex);
    }

    @Override
    public void reset() {
        currentElementIndex = 0;
    }
}
