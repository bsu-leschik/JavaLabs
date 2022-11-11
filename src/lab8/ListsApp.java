package lab8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListsApp extends JPanel {
    JList<String> leftList;
    JList<String> rightList;

    ListsApp(){
        super();
        initComponents();
    }

    private void initComponents(){
        add(new JList<String>(), BorderLayout.EAST);
        add(new JList<String>(), BorderLayout.WEST);


    }

    private void initButtons(){
        initButton(BorderLayout.NORTH);
        initButton(BorderLayout.SOUTH);
    }

    private void initButton(String position){
        final JButton button = new JButton(getBtnTextFromPosition(position));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (leftList.getComponentCount() == 0){

                }
            }
        });
    }

    private static String getBtnTextFromPosition(String position){
        if (position.equals(BorderLayout.NORTH)){
            return ">";
        }

        if (position.equals(BorderLayout.SOUTH)){
            return "<";
        }

        return null;
    }

    private void transferLists(){

    }
}
