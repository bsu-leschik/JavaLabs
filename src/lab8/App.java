package lab8;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {

    JTabbedPane pane;

    public App(){
        super("Lab8");
        setLayout(new CardLayout());

        setMinimumSize(new Dimension(400, 300));
        pane = new JTabbedPane();
        addComponents();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void addComponents(){
        pane.addTab("Lists", new ListsApp());
        pane.addTab("Grid", new GridApp(3));
        pane.addTab("RadioButtons", new RadioButtonsApp(3));
        this.add(pane);
    }
}
