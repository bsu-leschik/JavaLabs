package lab8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListsApp extends JPanel {
    DefaultListModel<String> leftListModel;
    DefaultListModel<String> rightListModel;

    JList<String> leftList;
    JList<String> rightList;

    ListsApp(){
        super();
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents(){
        leftListModel = initListComponents(10);
        rightListModel = initListComponents(3);

        leftList = new JList<>(leftListModel);
        rightList = new JList<>(rightListModel);

        add(leftList, BorderLayout.WEST);
        add(rightList, BorderLayout.EAST);

        initButtons();

    }

    private void initButtons(){
        JPanel panel = new JPanel(new BorderLayout());

        initButton(BorderLayout.NORTH, panel);
        initButton(BorderLayout.SOUTH, panel);

        add(panel, BorderLayout.CENTER);
    }

    private void initButton(String position, JPanel panel){
        final JButton button = new JButton(getBtnTextFromPosition(position));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (position.equals(BorderLayout.NORTH)) {
                    if (!leftList.getSelectedValuesList().isEmpty()) {
                        int[] indices = leftList.getSelectedIndices();
                        for (int i = leftList.getSelectedIndices().length - 1; i >= 0; i--) {
                            rightListModel.addElement(leftListModel.elementAt(indices[i]));
                            leftListModel.remove(indices[i]);
                        }
                    }
                }

                if (position.equals((BorderLayout.SOUTH))) {
                    if (!rightList.getSelectedValuesList().isEmpty()) {
                        int[] indices = rightList.getSelectedIndices();
                        for (int i = rightList.getSelectedIndices().length - 1; i >= 0; i--) {
                            leftListModel.addElement(rightListModel.elementAt(indices[i]));
                            rightListModel.remove(indices[i]);
                        }
                    }
                }
            }
        });

        panel.add(button, position);
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

    private DefaultListModel<String> initListComponents(int amount){
        DefaultListModel<String> arrayList = new DefaultListModel<>();
        for (int i = 0; i < amount; i++) {
            arrayList.addElement(i + "");
        }
        return arrayList;
    }
}