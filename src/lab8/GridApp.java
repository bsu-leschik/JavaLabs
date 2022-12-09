package lab8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GridApp extends JPanel {
    final int AMOUNT_OF_BUTTONS_IN_ROW;
    GridApp(int amountOfButtonsInRow){
        super();
        AMOUNT_OF_BUTTONS_IN_ROW = amountOfButtonsInRow;
        setLayout(new GridLayout(AMOUNT_OF_BUTTONS_IN_ROW, AMOUNT_OF_BUTTONS_IN_ROW));

        initComponents();
    }

    private void initComponents(){

        MouseAdapter enterListener = new MouseAdapter() {
            final String TEXT = "Clicked!";
            final Color COLOR = Color.BLUE;

            String realText;
            Color realColor;

            @Override
            public void mousePressed(MouseEvent e){
                if (e.getButton() == MouseEvent.BUTTON1){
                    JButton button = (JButton) e.getComponent();
                    realText = button.getText();
                    button.setText(TEXT);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e){
                if (e.getButton() == MouseEvent.BUTTON1){
                    JButton button = (JButton) e.getComponent();
                    button.setText(realText);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                JButton button = (JButton) e.getComponent();
                realColor = button.getBackground();
                button.setBackground(COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JButton button = (JButton) e.getComponent();
                button.setBackground(realColor);
            }
        };

        for (int i = 0; i < AMOUNT_OF_BUTTONS_IN_ROW * AMOUNT_OF_BUTTONS_IN_ROW; i++) {
            JButton button = new JButton(i + "");
            button.addMouseListener(enterListener);
            add(button);
        }
    }
}
