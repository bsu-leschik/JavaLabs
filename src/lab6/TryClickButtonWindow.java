package lab6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class TryClickButtonWindow extends JFrame {

    JPanel mainPanel;

    JButton button;

    TryClickButtonWindow(){
        super("Try to click");
        configureWindow();
        initElements();
        initListeners();
    }

    public void showWindow() {
        setVisible(true);
    }

    private void configureWindow() {
        setMinimumSize(new Dimension(480, 300));
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        this.add(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initElements(){
        final JLabel text = new JLabel("Are you happy with your scholarship?");

        button = new JButton("No");
        final JButton goodButton = new JButton("Yes");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(goodButton);
        buttonPanel.add(button);

        mainPanel.add(text);
        mainPanel.add(buttonPanel);
    }

    private void initListeners(){
        button.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mouseMoved(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                button.setLocation(getAppropriateX(), getAppropriateY());
            }

            private int getAppropriateX(){
                int newX;
                if (button.getX() + 2*button.getWidth() < getWidth()){
                    newX = button.getX() + 2*button.getWidth();
                } else if (button.getX() - button.getWidth() > 0) {
                    newX = button.getX() - button.getWidth();
                }
                else {
                    newX = button.getX();
                }
                return newX;
            }

            private int getAppropriateY(){
                int newY;
                if (button.getY() + button.getHeight() < getBounds().height){
                    newY = button.getY() + button.getHeight();
                } else if (button.getLocationOnScreen().y - 2*button.getHeight() > 0) {
                    newY = button.getY() - button.getHeight();
                }
                else {
                    newY = button.getY();
                }
                return newY;
            }
        });
    }
}
