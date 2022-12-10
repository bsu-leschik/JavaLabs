package kr2_trial.set;

import kr2_trial.Itertor.Iterator;

public class SetIterator implements Iterator {

    Set set;

    int currentElementIndex = 0;
    
    SetIterator(Set set){
        this.set = set;
    }

    @Override
    public boolean hasNext() {
        return currentElementIndex < size();
    }

    @Override
    public int getNext() {
        int i;
        for (i = currentElementIndex; i < set.binaryData.size(); i++) {
            if (set.binaryData.get(i)){
                currentElementIndex++;
                return i;
            }
        }
        return -1;
    }

    private int size(){
        int i = 0;
        for (Boolean binaryDatum : set.binaryData) {
            if (binaryDatum){
                i++;
            }
        }
        return i;
    }

    @Override
    public void reset() {
        currentElementIndex = 0;
    }
}
