package lab5;

import javax.naming.InvalidNameException;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Window extends JFrame {
    JPanel pathFieldPanel;
    ButtonGroup seriesTypeButton;

    JPanel seriesTypeButtonPanel;
    JPanel mainInfoPanel;
    JPanel submitButtonPanel;
    final LineBorder good = new LineBorder(Color.GREEN, 2);
    final LineBorder bad = new LineBorder(Color.RED, 3);

    double firstElement;
    double amountOfElements;
    double delta;

    String path;

    JPanel outputPanel;

    Series series;

    public Window() {
        super("Series creator");
        setLayout(new GridLayout(0, 1, 0, this.getHeight() / 20));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(480, 300));

        fieldsInit();
        setVisible(true);
    }

    private void fieldsInit() {
        initPathField(this.getWidth() / 20);
        initRadioButtons();
        initMainTextFields();
        initButton();
        initOutput();
    }

    private void initPathField(int width) {
        final JTextField pathField = new JTextField(width);
        final JLabel pathLabel = new JLabel("Path to the output file:");

        pathField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validate();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validate();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validate();
            }

            private void validate() {
                String text = pathField.getText();
                if (!text.equals("")) {
                    path = text;
                    setFieldValid(pathField);
                    tryUnblockButton();
                } else {
                    setFieldInvalid(pathField);
                    blockButton();
                }
            }
        });
        pathFieldPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        pathFieldPanel.add(pathLabel);
        pathFieldPanel.add(pathField);


        this.add(pathFieldPanel);
    }

    private void initRadioButtons() {
        seriesTypeButton = new ButtonGroup();
        seriesTypeButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addRadioButton("Linear");
        addRadioButton("Exponential");
        this.add(seriesTypeButtonPanel);
    }

    private void addRadioButton(String name) {
        final JRadioButton button = new JRadioButton(name);
        seriesTypeButton.add(button);
        seriesTypeButton.setSelected(button.getModel(), true);
        seriesTypeButtonPanel.add(button);
    }

    private void initMainTextFields() {
        mainInfoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        addMainInfoTextField("Number of elements:");
        addMainInfoTextField("First number:");
        addMainInfoTextField("Delta:");

        this.add(mainInfoPanel);
    }

    private void addMainInfoTextField(String name) {
        final JTextField textField = new JTextField(this.getWidth() / 100);
        final JLabel label = new JLabel(name);

        textField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                validate();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validate();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validate();
            }

            private void validate() {
                try {
                    saveDataTo(Double.parseDouble(textField.getText()), name);
                    setFieldValid(textField);
                    tryUnblockButton();
                } catch (InvalidNameException | NumberFormatException | NullPointerException exception) {
                    System.out.println(exception.getMessage());
                    setFieldInvalid(textField);
                    blockButton();
                }
            }
        });

        mainInfoPanel.add(label);
        mainInfoPanel.add(textField);
    }

    private void initButton() {
        final JButton submitButton = new JButton("Submit and calculate");
        submitButton.setEnabled(false);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                series = getSeriesType();
                try {
                    series.saveToFile(new File(path));
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                    setFieldInvalid((JTextField) pathFieldPanel.getComponent(1));
                    blockButton();
                }
                JScrollPane elementsScroller = (JScrollPane) outputPanel.getComponent(1);
                JTextArea elements = (JTextArea) elementsScroller.getViewport().getView();
                JScrollPane sumScroller = (JScrollPane) outputPanel.getComponent(3);
                JTextArea sum = (JTextArea) sumScroller.getViewport().getView();

                elements.setText(series.toString());
                sum.setText(String.valueOf(series.calculateSum()));
                for (Component component : outputPanel.getComponents()) {
                    component.setVisible(true);
                }
            }
        });

        submitButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        submitButtonPanel.add(submitButton);
        this.add(submitButtonPanel);
    }

    private void initOutput() {
        outputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        initOutputElement("Elements:", this.getHeight() / 30);
        initOutputElement("Sum:", this.getHeight() / 50);

        this.add(outputPanel);
    }

    private void initOutputElement(String name, int columns){
        final JLabel elementsLabel = new JLabel(name);
        elementsLabel.setVisible(false);
        final JTextArea elementsOutput = new JTextArea(1, columns);
        elementsOutput.setEditable(false);
        JScrollPane scroller = new JScrollPane(elementsOutput);
        scroller.createVerticalScrollBar();
        scroller.setVisible(false);

        outputPanel.add(elementsLabel);
        outputPanel.add(scroller);
    }

    private void saveDataTo(double data, String name) throws InvalidNameException {
        switch (name) {
            //case "Number of elements:" -> this.amountOfElements = data;
            //case "First number:" -> this.firstElement = data;
            //case "Delta:" -> this.delta = data;
            //default -> throw new InvalidNameException("Internal: no such field");
        }
    }

    private void tryUnblockButton() {
        for (Component component : mainInfoPanel.getComponents()) {
            if (!component.getClass().equals(JTextField.class)) continue;
            if (!isDataValid((JTextField) component)) return;
        }

        if (isDataValid((JTextField) pathFieldPanel.getComponent(1))) {
            unblockButton();
        }
    }

    private boolean isDataValid(JTextField field) {
        if (!field.getBorder().getClass().equals(LineBorder.class)) return false;
        LineBorder border = (LineBorder) field.getBorder();
        return border.getLineColor().equals(Color.GREEN);
    }

    private void setFieldInvalid(JTextField field) {
        field.setBorder(bad);
    }

    private void setFieldValid(JTextField field) {
        field.setBorder(good);
    }

    private void unblockButton() {
        JButton button = (JButton) submitButtonPanel.getComponent(0);
        button.setEnabled(true);
    }

    private void blockButton() {
        JButton button = (JButton) submitButtonPanel.getComponent(0);
        button.setEnabled(false);
    }

    private Series getSeriesType() {
        ButtonModel pressedModel = seriesTypeButton.getSelection();
        for (Component component : seriesTypeButtonPanel.getComponents()) {
            JRadioButton button = (JRadioButton) component;
            if (pressedModel.equals(button.getModel())) {
                if (button.getText().equals("Linear")) {
                    return new Linear((int) this.amountOfElements, this.firstElement, this.delta);
                }
            }
        }
        return new Exponential((int) this.amountOfElements, this.firstElement, this.delta);
    }
}
