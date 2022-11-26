package lab10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Window extends JFrame {

    Map<Integer, String> map;
    DefaultListModel<Pair<Integer, String>> listModel;
    JPanel buttonsPanel;

    public Window(Map<Integer, String> map){
        super("Map");

        this.map = map;
        listModel = map.listModel();


        setMinimumSize(new Dimension(300, 400));
        add(new JList<>(listModel), BorderLayout.CENTER);
        initButtons();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initButtons(){
        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton putButton = new JButton("Put");
        putButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String line = JOptionPane.showInputDialog("Enter key and value");
                    if (line == null){
                        return;
                    }
                    Pair<Integer, String> pair = parseString(line);
                    if(!map.put(pair.key, pair.value)){
                        JOptionPane.showMessageDialog(Window.this, "Invalid key!",
                                "Key Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    listModel.addElement(pair);
                }
                catch (NumberFormatException | NoSuchElementException | NullPointerException exception){
                    JOptionPane.showMessageDialog(Window.this, "Invalid input!",
                            exception.getClass().getName(), JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonsPanel.add(putButton);

        JButton getButton = new JButton("Get");
        getButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String line = JOptionPane.showInputDialog("Enter key");
                    if (line == null){
                        return;
                    }
                    String value = map.get(Integer.parseInt(line));
                    if(value == null){
                        JOptionPane.showMessageDialog(Window.this, "Invalid key!",
                                "Key Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(Window.this, value, "Your value",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                catch (NumberFormatException exception){
                    JOptionPane.showMessageDialog(Window.this, "Invalid input!",
                            exception.getClass().getName(), JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonsPanel.add(getButton);

        JButton putAllButton = new JButton("Put All");
        putAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String line = JOptionPane.showInputDialog("Enter keys and values");
                    if (line == null){
                        return;
                    }
                    ArrayList<Pair<Integer, String>> list = parseMany(line);
                    if(!map.putAll(list)){
                        JOptionPane.showMessageDialog(Window.this, "Invalid key!",
                                "Key Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        listModel.addAll(list);
                    }
                }
                catch (NumberFormatException | NoSuchElementException | NullPointerException exception){
                    JOptionPane.showMessageDialog(Window.this, "Invalid input!",
                            exception.getClass().getName(), JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonsPanel.add(putAllButton);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void initButton(JButton button){

        buttonsPanel.add(button);
    }

    private Pair<Integer, String> parseString(String input) throws NumberFormatException, NoSuchElementException {
        StringTokenizer tokenizer = new StringTokenizer(input, " ");
        return new Pair<>(Integer.parseInt(tokenizer.nextToken()), tokenizer.nextToken());
    }

    private ArrayList<Pair<Integer, String>> parseMany(String input) throws NumberFormatException, NoSuchElementException {
        StringTokenizer tokenizer = new StringTokenizer(input, ",");
        ArrayList<Pair<Integer, String>> list = new ArrayList<>();

        while (tokenizer.hasMoreTokens()){
            list.add(parseString(tokenizer.nextToken()));
        }
        return list;
    }
}
