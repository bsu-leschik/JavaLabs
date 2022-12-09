package lab11;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Window extends JFrame implements Subject{

    ArrayList<Observer> observers;

    JObserverList keyHistory;
    JObserverLabel currentKey;

    public Window(){
        super();
        observers = new ArrayList<>();
        setMinimumSize(new Dimension(300, 400));


        keyHistory  = new JObserverList();
        keyHistory.setFocusable(false);
        attach(keyHistory);
        JScrollPane scrollPane = new JScrollPane(keyHistory);
        add(scrollPane, BorderLayout.WEST);

        currentKey = new JObserverLabel();
        currentKey.setFocusable(false);
        currentKey.setFont(new Font(null , Font.PLAIN, 20));
        attach(currentKey);
        add(currentKey, BorderLayout.EAST);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                notifyObservers(KeyEvent.getKeyText(e.getKeyCode()));
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }


    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String character) {
        for (Observer observer : observers) {
            observer.update(character);
        }
    }
}
