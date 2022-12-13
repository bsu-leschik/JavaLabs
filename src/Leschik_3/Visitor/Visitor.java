package Leschik_3.Visitor;

import Leschik_3.set.Set;

public interface Visitor {

    int size = 0;

    void visitStack(Set set);
}
