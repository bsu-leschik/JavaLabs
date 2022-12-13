package Leschik_3.Strategy;

import Leschik_3.set.Set;
import Leschik_3.Visitor.SetVisitor;

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
