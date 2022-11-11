package lab8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadioButtonsApp extends JPanel {

    final int AMOUNT_OF_BUTTONS;

    public RadioButtonsApp(int amountOfButtons){
        super();
        AMOUNT_OF_BUTTONS  = amountOfButtons;
        initButtons();
    }

    private void initButtons(){
        ButtonGroup group = new ButtonGroup();

        for (int i = 0; i < AMOUNT_OF_BUTTONS; i++) {
            JRadioButton radioButton = new JRadioButton();

            radioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    radioButton.setIcon(new ImageIcon("/home/skalem/Загрузки/free-icon-matrix-1808972.png"));
                    for (Component component : getComponents()) {
                        JRadioButton button = (JRadioButton) component;
                        if (!button.equals(radioButton)) {
                            button.setIcon(null);
                        }
                    }
                }
            });

            group.add(radioButton);
            add(radioButton);
        }
    }
}
