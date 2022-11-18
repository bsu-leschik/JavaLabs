package lab9;

import jdk.jshell.execution.Util;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.TreeSet;


public class Window extends JFrame {

    private JTable unsortedTable;
    private JTable sortedTable;

    private String[] tableNames = new String[]{"Identifier", "Surname", "Course", "Group"};

    private DefaultTableModel unsortedTableModel;
    private DefaultTableModel sortedTableModel;

    Window(){
        super("Students list");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1200, 400));
        initMenu();
        initTables();
        initButton();
        this.setVisible(true);
    }

    private void initMenu(){
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menu.add(initMenuItem("Open"));

        menuBar.add(menu);
        this.setJMenuBar(menuBar);
    }


    private JMenuItem initMenuItem(String text){
        JMenuItem menuItem = new JMenuItem(text);
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                if (fileChooser.showOpenDialog(Window.this) == JFileChooser.APPROVE_OPTION){
                    try {
                        loadData(Utils.readFromFile(fileChooser.getSelectedFile()));
                    } catch (FileNotFoundException ex) {
                        JOptionPane.showMessageDialog(Window.this, "Invalid path to the file!", "IOException", JOptionPane.ERROR_MESSAGE);
                    }
                    catch (NoSuchElementException e){
                        JOptionPane.showMessageDialog(Window.this, "Data is corrupted!", "NoSuchElementException", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        return menuItem;
    }

    private void initTables(){
        unsortedTableModel = new DefaultTableModel(tableNames, 0);
        unsortedTable = new JTable(unsortedTableModel);
        sortedTableModel = new DefaultTableModel(tableNames, 0);
        sortedTable = new JTable(sortedTableModel);
        initUnsortedData(unsortedTable, BorderLayout.WEST);
        initUnsortedData(sortedTable, BorderLayout.EAST);
    }

    private void initButton(){
        JPanel container = new JPanel(new FlowLayout());
        JButton button = new JButton("Add element");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        container.add(button);
        this.add(container, BorderLayout.CENTER);
    }

    private void initUnsortedData(JTable table, String position){
        table.setDefaultEditor(Object.class, null);
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane, position);
    }

    private void loadData(ArrayList<Student> students){
        unsortedTableModel = new DefaultTableModel(tableNames, 0);
        for (Student student : students) {
            unsortedTableModel.addRow(new String[]{student.identifier + "", student.surname, student.courseNumber + "", student.groupNumber + ""});
        }
        unsortedTable.setModel(unsortedTableModel);

        sortedTableModel = new DefaultTableModel(tableNames, 0);
        TreeSet<Student> sortedStudents = Utils.sortData(students, 3);

        for (Student student : sortedStudents) {
            sortedTableModel.addRow(new String[]{student.identifier + "", student.surname, student.courseNumber + "", student.groupNumber + ""});
        }

        sortedTable.setModel(sortedTableModel);
    }

}
