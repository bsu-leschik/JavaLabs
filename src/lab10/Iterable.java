package lab10;

public interface Iterable<K, L> {
    public MapIterator<K, L> getIterator();
}