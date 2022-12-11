package lab10.iterator;

import lab10.Pair;

import java.util.ArrayList;

public class PairMapIterator<K, L> implements MapIterator<K, L>{

    int currentIndex = 0;
    ArrayList<Pair<K, L>> data;

    public PairMapIterator(ArrayList<Pair<K, L>> list){
        data = list;
    }
    @Override
    public boolean hasNext() {
        return currentIndex < data.size();
    }

    @Override
    public Pair<K, L> getNext() {
        return data.get(currentIndex++);
    }

    @Override
    public void reset() {
        currentIndex = 0;
    }
}
