package kr2_trial.Visitor;

import kr2_trial.Itertor.Iterator;
import kr2_trial.set.Set;

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
