package lab10;

import javax.swing.*;
import java.awt.*;

public class Window<K, L> extends JFrame {

    Map<K, L> map;
    JList<Pair<K, L>> list;


    public Window(Map<K, L> map){
        super("Map");
        this.map = map;
        list = map.jList();
        add(list, BorderLayout.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
