package lab10;

import javax.swing.*;
import java.util.ArrayList;

public abstract class AbstractMap<K, L> implements Iterable<K, L>{

    ArrayList<Pair<K, L>> data;


    public int size(){
        return data.size();
    }
    public boolean isEmpty(){
        return data.isEmpty();
    }
    public void clear(){
        data.clear();
    }

    @Override
    public boolean equals(Object obj) {
        return data.equals(obj);
    }

    @Override
    public String toString() {
        return data.toString();
    }

    @Override
    public PairMapIterator<K, L> getIterator(){
        return new PairMapIterator<>(this.data);
    }

    public DefaultListModel<Pair<K, L>> listModel(){
        DefaultListModel<Pair<K, L>> model = new DefaultListModel<>();
        model.addAll(data);
        return model;
    }
}

