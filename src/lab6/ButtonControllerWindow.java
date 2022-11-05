package lab6;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class ButtonControllerWindow extends JFrame {
    JPanel mainPanel;
    JButton button;
    JTextField xOutput;
    JTextField yOutput;

    public ButtonControllerWindow() {
        super("Button controller");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        configureWindow();

        initCoordinates();
        initButton();

        initListeners();
    }

    public void showWindow() {
        setVisible(true);
    }

    private void configureWindow() {
        setMinimumSize(new Dimension(480, 300));
        mainPanel = new JPanel(null);
        this.add(mainPanel);
    }

    private void initCoordinates() {
        final JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel panel = initOutputElement("X: ");
        xOutput = (JTextField) panel.getComponent(1);
        infoPanel.add(panel);

        panel = initOutputElement("Y: ");
        yOutput = (JTextField) panel.getComponent(1);
        infoPanel.add(panel);

        infoPanel.setBorder(new LineBorder(Color.BLUE, 2));
        add(infoPanel, BorderLayout.SOUTH);
    }

    private JPanel initOutputElement(String name) {
        final JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final JLabel label = new JLabel(name);
        final JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(this.getWidth() / 10, this.getHeight() / 10));
        textField.setEditable(false);
        panel.add(label);
        panel.add(textField);
        return panel;
    }

    private void initListeners() {
        mainPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button.setLocation(e.getPoint());
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });

        MouseMotionListener motionListener = new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mouseMoved(e);
                if (e.isControlDown()) {
                    button.setLocation(new Point(e.getXOnScreen() - mainPanel.getLocationOnScreen().x, e.getYOnScreen() - mainPanel.getLocationOnScreen().y));
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                xOutput.setText(Integer.toString(e.getLocationOnScreen().x));
                yOutput.setText(Integer.toString(e.getLocationOnScreen().y));
            }
        };

        mainPanel.addMouseMotionListener(motionListener);
        button.addMouseMotionListener(motionListener);

        button.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE){
                    if (button.getText().isBlank()) return;
                    button.setText(button.getText().substring(0, button.getText().length() - 1));
                }
                else {
                    button.setText(button.getText() + e.getKeyChar());
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    private void initButton() {
        button = new JButton();
        button.setSize(new Dimension(this.getWidth() / 5, this.getHeight() / 10));
        mainPanel.add(button);
    }

}
