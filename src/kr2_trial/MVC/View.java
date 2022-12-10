package kr2_trial.MVC;

import kr2_trial.Strategy.CountByIterator;
import kr2_trial.Strategy.CountByVisitorIterator;
import kr2_trial.set.Set;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Objects;

public class View extends JFrame {

    Controller controller;

    JPanel mainPanel;

    JLabel count;

    JLabel set;

    JLabel binarySet;

    public View(Set setModel){
        super("Set");
        controller = new Controller(setModel, this);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        this.add(mainPanel);

        initSizeCalculation();
        initSetControl();
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
                    controller.setSizeCalculatorAndCalculate(new CountByVisitorIterator(controller.setModel));
                } else {
                    controller.setSizeCalculatorAndCalculate(new CountByIterator(controller.setModel));
                }
            }
        });

        sizeCalculatingPanel.add(calculate);

        count = new JLabel(controller.sizeModel);
        sizeCalculatingPanel.add(count);
        mainPanel.add(sizeCalculatingPanel, BorderLayout.CENTER);

    }

    private void initSetControl(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        initSetControlElement("Enter items", 10, panel);
        panel.add(initSave());

        mainPanel.add(panel, BorderLayout.NORTH);
    }

    private void initSetControlElement(String labelText, int columns, JPanel panel){
        JLabel label = new JLabel(labelText);
        panel.add(label);

        JTextField text = new JTextField(columns);
        text.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    try {
                        controller.addElements(text.getText());
                    }
                    catch(IllegalArgumentException exception){
                        JOptionPane.showMessageDialog(View.this, "Enter appropriate items!", exception.getClass().getName(), JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        panel.add(text);
    }

    private JButton initSave(){
        JButton button = new JButton("Save to file");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.showOpenDialog(View.this);
                try {
                    controller.saveToFile(fileChooser.getSelectedFile());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(View.this, "Inappropriate file!", ex.getClass().getName(), JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        return button;
    }

    private void initStackInfo(){
        JPanel setPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel setLabel = new JLabel("Stack items: ");
        set = new JLabel(controller.setModel.toString());
        JScrollPane setScrollPane = new JScrollPane(set);
        setPanel.add(setLabel);
        setPanel.add(setScrollPane);

        JPanel binarySetPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel binarySetLabel = new JLabel("Binary stack items: ");
        binarySet = new JLabel(controller.setModel.binaryToString());
        JScrollPane binarySetScrollPane = new JScrollPane(binarySet);
        binarySetPanel.add(binarySetLabel);
        binarySetPanel.add(binarySetScrollPane);


        mainPanel.add(setPanel);
        mainPanel.add(binarySetPanel);
    }
}
