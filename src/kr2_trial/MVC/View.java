package kr2_trial.MVC;

import kr2_trial.stack.Stack;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {

    Stack model;
    Controller controller;

    public View(Stack stackModel){
        super("Stack");
        initSizeCalculation();
        initAddition();


    }

    private void initSizeCalculation(){
        JPanel sizeCalculatingPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        ButtonGroup buttons = new ButtonGroup();

        String[] names = {"with visitor", "without visitor"};

        for (int i = 0; i < 2; i++) {
            JRadioButton radioButton = new JRadioButton(names[i]);

            buttons.add(radioButton);
            sizeCalculatingPanel.add(radioButton);
            buttons.setSelected(radioButton.getModel(), true);
        }


    }

    private void initRadioButtons(){

    }

    private void initCalculateButton(){

    }

    private void initAddition(){

    }
}
