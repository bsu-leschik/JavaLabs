package lab10;

public class Lab10 {
    public static void main(String[] args) {
        Map<String, Integer> map = new Map<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        map.put("D", 4);
        map.put("E", 5);
        Window<String, Integer> window = new Window<>(map);
    }
}
