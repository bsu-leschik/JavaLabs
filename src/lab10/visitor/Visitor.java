package lab10.visitor;

import lab10.Map;

public interface Visitor<K, L> {

    int size = 0;

    void visitMap(Map<K, L> set);
}
