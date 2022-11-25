package lab10;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

public abstract class AbstractMap<K, L> extends ArrayList<Pair<K, L>> {

    @Override
    @Deprecated
    public Iterator<Pair<K, L>> iterator(){
        return super.iterator();
    }

    @Override
    @Deprecated
    public boolean add(Pair<K, L> pair){
        return super.add(pair);
    }

    @Override
    @Deprecated
    public void add(int element , Pair<K, L> pair){
        super.add(element, pair);
    }

    public PairMapIterator<K, L> getIterator(){
        return new PairMapIterator<>(this);
    }

    public JList<Pair<K, L>> jList(){
        Vector<Pair<K, L>> vector = new Vector<>(this);
        return new JList<Pair<K, L>>(vector);
    }

    public static void main(String[] args) {
        Map<Integer, Integer> map = new Map<Integer, Integer>();
    }
}

