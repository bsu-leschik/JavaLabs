package lab10;

import lab10.iterator.Iterable;
import lab10.iterator.PairMapIterator;
import lab10.visitor.Element;

import javax.swing.*;
import java.util.ArrayList;

public abstract class AbstractMap<K, L> implements Iterable<K, L>, Element<K, L> {

    ArrayList<Pair<K, L>> data;

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
        return new PairMapIterator<K, L>(this.data);
    }

    public JList<Pair<K, L>> jList(){
        DefaultListModel<Pair<K, L>> model = new DefaultListModel<>();
        model.addAll(data);
        return new JList<>(model);
    }
}

