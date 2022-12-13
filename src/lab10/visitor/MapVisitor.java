package lab10.visitor;

import lab10.Map;
import lab10.iterator.MapIterator;

public class MapVisitor<K, L> implements Visitor<K, L> {

    int size;

    @Override
    public void visitMap(Map<K, L> map) {
        MapIterator<K, L> iterator = map.getIterator();
        int i = 0;
        while (iterator.hasNext()){
            i++;
            iterator.getNext();
        }
        this.size = i;
    }

    public int getSize() {
        return size;
    }
}
