package lab10.iterator;

public interface Iterable<K, L> {
    public MapIterator<K, L> getIterator();
}