package lab10.iterator;

import lab10.Pair;

public interface MapIterator<K, L> {
    boolean hasNext();

    Pair<K, L> getNext();

    void reset();
}
