package lab10;

import lab10.visitor.MapVisitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Window extends JFrame {

    JPanel buttonsPanel;

    Controller controller;

    JList<Pair<Integer, String>> mapList;

    public Window(Map<Integer, String> map){
        super("Map");

        controller = new Controller(map, this);

        setMinimumSize(new Dimension(300, 400));

        add(mapList, BorderLayout.CENTER);
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
                    if(!controller.put(pair.key, pair.value)){
                        JOptionPane.showMessageDialog(Window.this, "Invalid key!",
                                "Key Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
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
                    String value = controller.get(Integer.parseInt(line));
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
                    if(!controller.putAll(list)){
                        JOptionPane.showMessageDialog(Window.this, "Invalid key!",
                                "Key Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch (NumberFormatException | NoSuchElementException | NullPointerException exception){
                    JOptionPane.showMessageDialog(Window.this, "Invalid input!",
                            exception.getClass().getName(), JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonsPanel.add(putAllButton);

        JButton sizeButton = new JButton("Size");

        sizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapVisitor<Integer, String > visitor = new MapVisitor<Integer, String>();
                controller.map.accept(visitor);
                JOptionPane.showMessageDialog(Window.this, visitor.getSize(), "Size", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        buttonsPanel.add(sizeButton);

        add(buttonsPanel, BorderLayout.SOUTH);
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
