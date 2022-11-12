package lab8;

import javax.swing.*;

public class RadioButtonsApp extends JPanel {

    final int AMOUNT_OF_BUTTONS;
    Icon onChooseIcon, onEnterIcon, onEnterAndHoldIcon;

    public RadioButtonsApp(int amountOfButtons, Icon onChooseIcon, Icon onEnterIcon, Icon onEnterAndHoldIcon){
        super();
        AMOUNT_OF_BUTTONS  = amountOfButtons;
        this.onChooseIcon = onChooseIcon;
        this.onEnterIcon = onEnterIcon;
        this.onEnterAndHoldIcon = onEnterAndHoldIcon;
        initButtons();
    }

    private void initButtons(){
        ButtonGroup group = new ButtonGroup();

        for (int i = 0; i < AMOUNT_OF_BUTTONS; i++) {
            JRadioButton radioButton = new JRadioButton(
                    new ImageIcon("/home/skalem/FlappyBird/sprites/OtherTheme/Birds/secondBird0.png"));

            radioButton.setSelectedIcon(onChooseIcon);
            radioButton.setPressedIcon(onEnterAndHoldIcon);
            radioButton.setRolloverIcon(onEnterIcon);

            group.add(radioButton);
            add(radioButton);
        }
    }
}
