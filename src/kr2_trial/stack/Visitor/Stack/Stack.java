package kr2_trial.stack.Visitor.Stack;

import kr2_trial.stack.Visitor.Element;
import kr2_trial.stack.Visitor.Visitor;

import java.util.ArrayList;

public class Stack implements Iterable, Element {
    ArrayList<Integer> data;

    public  Stack(){

    }

    @Override
    public Iterator createIterator() {
        return null;
    }

    @Override
    public void accept(Visitor v) {
        v.visitStack(this);
    }
}
