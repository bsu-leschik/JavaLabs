package kr2_trial.stack.Visitor;

import kr2_trial.stack.Visitor.Visitor;

public interface Element {
    void accept(Visitor v);
}
