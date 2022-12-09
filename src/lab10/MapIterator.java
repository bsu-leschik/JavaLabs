package lab10;

public interface MapIterator<K, L> {
    boolean hasNext();

    Pair<K, L> getNext();

    void reset();
}
