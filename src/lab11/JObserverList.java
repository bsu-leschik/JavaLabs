package lab11;

import javax.swing.*;

public class JObserverList extends JList<Character> implements Observer{

    DefaultListModel<Character> model;

    public JObserverList(){
        super();
        model = new DefaultListModel<>();
        setModel(model);
    }

    @Override
    public void update(Character character) {
        model.addElement(character);
    }
}
