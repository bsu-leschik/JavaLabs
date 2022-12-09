package lab11;

import javax.swing.*;

public class JObserverList extends JList<String> implements Observer{

    DefaultListModel<String> model;

    public JObserverList(){
        super();
        model = new DefaultListModel<>();
        setModel(model);
    }

    @Override
    public void update(String character) {
        model.addElement(character);
    }
}
