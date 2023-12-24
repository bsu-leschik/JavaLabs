package lab10;

import lab10.iterator.PairMapIterator;
import lab10.visitor.Visitor;

import java.util.ArrayList;
import java.util.function.Consumer;

public class Map<K, L> extends AbstractMap<K, L>{
    public Map(){
        data = new ArrayList<>();
    }

    public boolean put(K key, L value){
        if (value == null){
            throw new NullPointerException("Value cannot be null");
        }

        if (!isThereKey(key)) {
            data.add(new Pair<K, L>(key, value));
            return true;
        }

        return false;
    }

    public L get(K key){
        PairMapIterator<K, L> pairMapIterator = getIterator();
        while (pairMapIterator.hasNext()){
            Pair<K, L> current = pairMapIterator.getNext();
            if (current.key.equals(key)){
                return current.value;
            }
        }
        return null;
    }

    public boolean putAll(ArrayList<Pair<K, L>> pairs){
        boolean isAllAdded = true;
        for (Pair<K, L> pair : pairs) {
            if (!isThereKey(pair.key)){
                data.add(pair);
            }
            else {
                isAllAdded = false;
            }
        }
        return isAllAdded;
    }

    private boolean isThereKey(K key){
        final boolean[] isThere = {false};
        data.stream().forEach(new Consumer<Pair<K, L>>() {
            @Override
            public void accept(Pair<K, L> pair) {
                if (pair.key.equals(key)){
                    isThere[0] = true;
                }
            }
        });
        return isThere[0];
    }

    @Override
    public void accept(Visitor<K, L> v) {
        v.visitMap(this);
    }
}