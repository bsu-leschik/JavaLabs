package lab10.visitor;

public interface Element<K, L> {
    void accept(Visitor<K, L> v);
}
