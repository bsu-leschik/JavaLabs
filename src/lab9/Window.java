package lab9;

import lab9.lab11_2.DOMReader;
import lab9.lab11_2.DOMWriter;
import lab9.lab11_2.SAXReader;
import lab9.lab11_2.XMLReaderStrategy;
import lab9.sortingStratagy.ConstructorSorter;
import lab9.sortingStratagy.Sorter;
import lab9.sortingStratagy.StreamSorter;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.*;


public class Window extends JFrame {

    private JTable unsortedTable;
    private JTable sortedTable;
    private JTextField filterInput;

    private final Border badFilter = new LineBorder(Color.RED, 3);

    private final String[] tableNames = new String[]{"Identifier", "Surname", "Course", "Group"};

    XMLReaderStrategy xmlParser;

    private DefaultTableModel unsortedTableModel;
    private DefaultTableModel sortedTableModel;

    private Sorter tableSorter;

    Window(){
        super("Students list");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1200, 400));
        initMenu();
        initTables();
        initControlElements();

        tableSorter = new ConstructorSorter();
        xmlParser = new SAXReader();

        this.setVisible(true);
    }

    private void initMenu(){
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menu.add(initMenuItem("Open"));
        menu.add(initMenuSaveItem("Save"));

        menuBar.add(menu);
        this.setJMenuBar(menuBar);
    }


    private JMenuItem initMenuItem(String text){
        JMenuItem menuItem = new JMenuItem(text);
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("xml documents", "xml"));
                if (fileChooser.showOpenDialog(Window.this) == JFileChooser.APPROVE_OPTION){
                    try {
                        loadData(xmlParser.parse(fileChooser.getSelectedFile()));
                    }
                    catch (IOException exception) {
                        JOptionPane.showMessageDialog(Window.this, "Inappropriate path", exception.getClass().getName(), JOptionPane.ERROR_MESSAGE);
                    }
                    catch (ParserConfigurationException exception) {
                        JOptionPane.showMessageDialog(Window.this, "Internal error has happened", exception.getClass().getName(), JOptionPane.ERROR_MESSAGE);
                    }
                    catch (SAXException exception) {
                        JOptionPane.showMessageDialog(Window.this, "Enter appropriate filter item", exception.getClass().getName(), JOptionPane.ERROR_MESSAGE);
                    }
                    catch (NumberFormatException ignored) {
                    }
                }
            }
        });

        return menuItem;
    }

    private JMenuItem initMenuSaveItem(String text){
        JMenuItem menuItem = new JMenuItem(text);
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("xml documents", "xml"));
                if (fileChooser.showSaveDialog(Window.this) == JFileChooser.APPROVE_OPTION){
                    try {
                        DOMWriter.write(sortedTableModel.getDataVector(), fileChooser.getSelectedFile());
                    } catch (ParserConfigurationException e) {
                        JOptionPane.showMessageDialog(Window.this, "Internal error", e.getClass().getName(), JOptionPane.ERROR_MESSAGE);
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(Window.this, "Inappropriate path", e.getClass().getName(), JOptionPane.ERROR_MESSAGE);
                    } catch (TransformerException e) {
                        throw new RuntimeException(e);
                    } catch (SAXException e) {
                        JOptionPane.showMessageDialog(Window.this, "Data is corrupted", e.getClass().getName(), JOptionPane.ERROR_MESSAGE);
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
        initDataTable(unsortedTable, BorderLayout.WEST);
        initDataTable(sortedTable, BorderLayout.EAST);
    }

    private void initControlElements(){
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));

        JButton button = new JButton("Add element");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    addStudent(Utils.parseStudent(JOptionPane.showInputDialog(Window.this, "Enter element string")));
                }
                catch (NumberFormatException exception){
                    if (filterInput.getText().isEmpty()){
                        return;
                    }
                    JOptionPane.showMessageDialog(Window.this, "Enter appropriate filter item!", exception.getClass().getName(), JOptionPane.ERROR_MESSAGE);
                }
                catch (InputMismatchException exception){
                    JOptionPane.showMessageDialog(Window.this, "Data is invalid!", exception.getClass().getName(), JOptionPane.ERROR_MESSAGE);
                }
                catch (NullPointerException ignored){};
            }
        });

        filterInput = new JTextField(5);
        filterInput.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    try {
                        addFilteredData();
                    }
                    catch (NumberFormatException exception){
                        JOptionPane.showMessageDialog(Window.this, "Enter filter item", exception.getClass().getName(), JOptionPane.ERROR_MESSAGE);
                    }
                    catch (IllegalArgumentException exception){
                        JOptionPane.showMessageDialog(Window.this, exception.getMessage(), exception.getClass().getName(), JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        JPanel filterHolder = new JPanel(new FlowLayout(FlowLayout.CENTER));
        filterHolder.add(new JLabel("Filter item:"));
        filterHolder.add(filterInput, BorderLayout.EAST);
        container.add(button);
        container.add(filterHolder);

        ArrayList<Sorter> sorters = new ArrayList<>();
        sorters.add(new ConstructorSorter());
        sorters.add(new StreamSorter());

        ArrayList<XMLReaderStrategy> readers = new ArrayList<>();
        readers.add(new SAXReader());
        readers.add(new DOMReader());

        container.add(initSortingStrategySwitches(new String[]{"constructor sorter", "stream sorter"}, sorters));
        container.add(initXMLStrategySwitches(new String[]{"SAX", "DOM"}, readers));

        this.add(container, BorderLayout.CENTER);
    }

    private JPanel initSortingStrategySwitches(String[] names, ArrayList<Sorter> sorters){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ButtonGroup sortStrategyChooser = new ButtonGroup();

        for (int i = 0; i < names.length; i++) {
            JRadioButton sorterBtn = new JRadioButton(names[i]);
            int finalI = i;
            sorterBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    tableSorter = sorters.get(finalI);
                }
            });

            sorterBtn.setSelected(true);

            sortStrategyChooser.add(sorterBtn);
            panel.add(sorterBtn);
        }

        return panel;
    }

    private JPanel initXMLStrategySwitches(String[] names, ArrayList<XMLReaderStrategy> xmlReaders){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ButtonGroup sortStrategyChooser = new ButtonGroup();

        for (int i = 0; i < names.length; i++) {
            JRadioButton xmlReaderBtn = new JRadioButton(names[i]);
            int finalI = i;
            xmlReaderBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    xmlParser = xmlReaders.get(finalI);
                }
            });

            xmlReaderBtn.setSelected(true);

            sortStrategyChooser.add(xmlReaderBtn);
            panel.add(xmlReaderBtn);
        }

        return panel;
    }

    private void initDataTable(JTable table, String position){
        table.setDefaultEditor(Object.class, null);
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane, position);
    }

    private void loadData(ArrayList<Student> students) throws NumberFormatException{
        unsortedTableModel = new DefaultTableModel(tableNames, 0);
        for (Student student : students) {
            unsortedTableModel.addRow(new String[]{student.identifier + "", student.surname, student.courseNumber + "", student.groupNumber + ""});
        }
        unsortedTable.setModel(unsortedTableModel);

        addFilteredData();
    }

    private void addFilteredData() throws NullPointerException, NumberFormatException{
        sortedTableModel = new DefaultTableModel(tableNames, 0);

        TreeSet<Student> sortedStudents = tableSorter.sortData(getStudentsList(), Integer.parseInt(filterInput.getText()));
        filterInput.setBorder(null);

        for (Student student : sortedStudents) {
            sortedTableModel.addRow(new String[]{student.identifier + "", student.surname, student.courseNumber + "", student.groupNumber + ""});
        }

        sortedTable.setModel(sortedTableModel);
    }


    private ArrayList<Student> getStudentsList() throws NullPointerException{
        ArrayList<Student> students = new ArrayList<>();

        if (unsortedTableModel.getDataVector().isEmpty()){
            throw new IllegalArgumentException("Nothing to sort!");
        }

        for (Vector<String> vector : unsortedTableModel.getDataVector()) {
            students.add(parseVector(vector));
        }
        return students;
    }
    private Student parseVector(Vector<String> studentVector){
        return new Student(Integer.parseInt(studentVector.elementAt(0)),studentVector.elementAt(1),
                Integer.parseInt(studentVector.elementAt(2)), Integer.parseInt(studentVector.elementAt(3)));
    }

    private void addStudent(Student student){
        unsortedTableModel.addRow(new String[]{student.identifier + "", student.surname, student.courseNumber + "", student.groupNumber + ""});
        addFilteredData();
    }
}
