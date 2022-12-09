package kr2_trial.MVC;

import kr2_trial.Strategy.CountByIterator;
import kr2_trial.Strategy.CountByVisitorIterator;
import kr2_trial.Strategy.Strategy;
import kr2_trial.stack.Stack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class View extends JFrame {

    Controller controller;

    JLabel count;

    JLabel stack;

    Strategy calculator;

    public View(Stack stackModel){
        super("Stack");
        controller = new Controller(stackModel, this);
        initSizeCalculation();
        initAddition();
        initStackInfo();
        setMinimumSize(new Dimension(500, 300));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initSizeCalculation() {
        JPanel sizeCalculatingPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        ButtonGroup buttons = new ButtonGroup();

        String[] names = {"with visitor", "without visitor"};

        for (int i = 0; i < 2; i++) {
            JRadioButton radioButton = new JRadioButton(names[i]);

            radioButton.setActionCommand(i + "");
            buttons.add(radioButton);
            sizeCalculatingPanel.add(radioButton);
            buttons.setSelected(radioButton.getModel(), true);
        }

        JButton calculate = new JButton("Calculate");

        calculate.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (Objects.equals(buttons.getSelection().getActionCommand(), "0")) {
                    calculator = new CountByVisitorIterator(controller.stackModel);
                } else {
                    calculator = new CountByIterator(controller.stackModel);
                }

                //controller part
                controller.setSizeModel(String.valueOf(calculator.execute()));
            }
        });

        sizeCalculatingPanel.add(calculate);

        count = new JLabel(controller.sizeModel);
        sizeCalculatingPanel.add(count);
        add(sizeCalculatingPanel, BorderLayout.CENTER);

    }

    private void initAddition(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel label = new JLabel("Enter items");
        panel.add(label);

        JTextField text = new JTextField(10);
        text.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    try {
                        controller.addElements(text.getText());
                    }
                    catch(NumberFormatException exception){
                        JOptionPane.showMessageDialog(View.this, "Enter appropriate items!", exception.getClass().getName(), JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        panel.add(text);

        JPanel deletePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel labelDelete = new JLabel("Enter number of items to delete");
        panel.add(labelDelete);

        JTextField textDelete = new JTextField(3);
        textDelete.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    try {
                        controller.removeElements(Integer.parseInt(textDelete.getText()));
                    }
                    catch(NumberFormatException exception){
                        JOptionPane.showMessageDialog(View.this, "Enter appropriate items!", exception.getClass().getName(), JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        panel.add(textDelete);

        add(panel, BorderLayout.NORTH);
    }

    private void initStackInfo(){
        stack = new JLabel(controller.stackModel.toString());
        add(stack, BorderLayout.SOUTH);
    }
}
