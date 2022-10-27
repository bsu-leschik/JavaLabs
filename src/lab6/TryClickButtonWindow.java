package lab6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        final JLabel label = new JLabel("Good student!");
        label.setVisible(false);

        final JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        textPanel.add(label);
        goodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setVisible(true);
                pack();
            }
        });

        button.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mouseMoved(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                button.setLocation(getAppropriateX(), getAppropriateY());
                label.setVisible(false);
            }

            private int getAppropriateX(){
                if (button.getX() + 2*button.getWidth() < getWidth()){
                    return button.getX() + 2*button.getWidth();
                }
                if (button.getX() - button.getWidth() > 0) {
                    return button.getX() - button.getWidth();
                }
                return button.getX();
            }

            private int getAppropriateY(){
                if (button.getY() - 2*button.getHeight() > 0) {
                    return button.getY() - button.getHeight();
                }
                if (button.getY() + button.getHeight() < getHeight()){
                    return button.getY() + button.getHeight();
                }
                return button.getY();
            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(goodButton);
        buttonPanel.add(button);

        mainPanel.add(text);
        mainPanel.add(buttonPanel);
        mainPanel.add(textPanel);
    }

    private void initListeners(){

    }
}
