package lab10;

public class Lab10 {
    public static void main(String[] args) {
        Map<Integer, String> map = new Map<>();
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
        map.put(5, "E");
        map.put(3, "qw");
        Window window = new Window(map);
    }
}
