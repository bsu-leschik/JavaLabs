package kr_2.stack;

import kr_2.Itertor.Iterable;
import kr_2.Itertor.Iterator;
import kr_2.Visitor.Element;
import kr_2.Visitor.Visitor;

import java.util.ArrayList;

public class Stack implements Iterable, Element {
    ArrayList<Integer> data;

    public  Stack(){
        data = new ArrayList<>();
    }

    @Override
    public Iterator createIterator() {
        return new StackIterator(this);
    }

    @Override
    public void accept(Visitor v) {
        v.visitStack(this);
    }

    public void push(int value){
        data.add(value);
    }

    public int pop(){
        return data.remove(data.size() - 1);
    }

    @Override
    public String toString() {
        return "Items from stack:" + data.toString();
    }
}
