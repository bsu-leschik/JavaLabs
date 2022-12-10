package kr2_trial.Strategy;

import kr2_trial.Itertor.Iterator;
import kr2_trial.set.Set;

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
