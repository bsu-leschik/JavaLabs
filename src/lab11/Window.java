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
        attach(currentKey);
        add(currentKey, BorderLayout.EAST);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!e.isActionKey() && e.getKeyChar() != KeyEvent.VK_BACK_SPACE && e.getKeyChar() != KeyEvent.VK_ENTER
                && e.getKeyChar() != KeyEvent.VK_TAB && e.getKeyChar() != KeyEvent.VK_DELETE){
                    notifyObservers(e.getKeyChar());
                }
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
    public void notifyObservers(Character character) {
        for (Observer observer : observers) {
            observer.update(character);
        }
    }
}
