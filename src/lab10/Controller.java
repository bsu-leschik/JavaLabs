package lab10;

import lab10.iterator.PairMapIterator;

import java.util.ArrayList;

public class Controller {

    Map<Integer, String> map;

    Window window;

    public Controller(Map<Integer, String> map, Window window){
        this.map = map;
        this.window = window;
        window.mapList = map.jList();
    }

    public boolean put(Integer key, String value){
        boolean result = map.put(key, value);
        window.mapList.setModel(map.jList().getModel());
        window.repaint();
        return result;
    }

    public String get(Integer key){
        return map.get(key);
    }

    public boolean putAll(ArrayList<Pair<Integer, String>> pairs){
        boolean result = map.putAll(pairs);
        window.mapList.setModel(map.jList().getModel());
        window.repaint();
        return result;
    }
}
