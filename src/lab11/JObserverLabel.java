package lab11;

import javax.swing.*;

public class JObserverLabel extends JLabel implements Observer{

    public JObserverLabel(){super();}

    @Override
    public void update(String character) {
        setText(character.toString());
    }
}
