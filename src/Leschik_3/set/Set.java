package Leschik_3.set;

import Leschik_3.Itertor.Iterable;
import Leschik_3.Itertor.Iterator;
import Leschik_3.Visitor.Element;
import Leschik_3.Visitor.Visitor;
import Leschik_3.Strategy.CountByVisitorIterator;
import Leschik_3.Strategy.Strategy;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Set implements Iterable, Element {

    ArrayList<Boolean> binaryData;

    Strategy counter = new CountByVisitorIterator(this);

    public Set(){
        binaryData =  new ArrayList<>();
    }

    @Override
    public Iterator createIterator() {
        return new SetIterator(this);
    }

    @Override
    public void accept(Visitor v) {
        v.visitStack(this);
    }

    public boolean add(int value) throws IllegalArgumentException{
        if (value < 0){
            throw new IllegalArgumentException("Value cannot be less than zero");
        }
        if (binaryData.size() < value){
            ensureSize(value);
        }
        if (!binaryData.get(value)){
            binaryData.add(value, true);
            return true;
        }
        return false;
    }

    private void ensureSize(int size){
        for (int i = binaryData.size() - 1; i <= size; i++) {
            binaryData.add(false);
        }
    }

    public void save(File file) throws IOException {
        FileWriter writer = new FileWriter(file);
        writer.write(this.toString());
        writer.close();
    }

    public int cardinality(){
        return counter.execute();
    }

    public void setCounter(Strategy counter){
        this.counter = counter;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append('[');
        for (int i = 0; i < binaryData.size(); i++) {
            if (binaryData.get(i)) {
                stringBuilder.append(i);
                stringBuilder.append(" ");
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public String binaryToString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        for (Boolean binaryDatum : binaryData) {
            stringBuilder.append(binaryDatum ? 1 : 0);
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(']');

        return stringBuilder.toString();
    }
}
