package lab10;

public class Pair<K, L> {

    K key;
    L value;

    Pair(K key, L value){
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return key.toString() + ' ' + value.toString();
    }
}
