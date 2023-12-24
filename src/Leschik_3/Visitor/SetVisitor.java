package Leschik_3.Visitor;

import Leschik_3.Itertor.Iterator;
import Leschik_3.set.Set;

public class SetVisitor implements Visitor {

    int size;

    @Override
    public void visitStack(Set set) {
        Iterator iterator = set.createIterator();
        int i = 0;
        while (iterator.hasNext()){
            i++;
            iterator.getNext();
        }
        this.size = i;
    }

    public int getSize() {
        return size;
    }
}
