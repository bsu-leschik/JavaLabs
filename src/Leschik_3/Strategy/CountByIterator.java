package Leschik_3.Strategy;

import Leschik_3.Itertor.Iterator;
import Leschik_3.set.Set;

public class CountByIterator implements Strategy {

    Set set;


    public CountByIterator(Set set){
        this.set = set;
    }

    @Override
    public int execute() {
        Iterator iterator = set.createIterator();
        int i = 0;
        while (iterator.hasNext()){
            i++;
            iterator.getNext();
        }
        return i;
    }
}
