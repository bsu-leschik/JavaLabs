package kr2_trial.Visitor;

import kr2_trial.set.Set;

public interface Visitor {

    int size = 0;

    void visitStack(Set set);
}
