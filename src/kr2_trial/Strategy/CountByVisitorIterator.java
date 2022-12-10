package kr2_trial.Strategy;

import kr2_trial.set.Set;
import kr2_trial.Visitor.SetVisitor;

public class CountByVisitorIterator implements Strategy {

    Set set;


    public CountByVisitorIterator(Set set){
        this.set = set;
    }

    @Override
    public int execute() {
        SetVisitor visitor = new SetVisitor();
        visitor.visitStack(set);
        return visitor.getSize();
    }
}
