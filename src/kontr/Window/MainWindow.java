package kontr.Window;

import kontr.*;
import lab7.Drawer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.chrono.JapaneseChronology;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.jar.JarEntry;

public class MainWindow extends JFrame {

    JButton openGuardFile;
    JButton openShopAssistantFile;

    JPanel mainPanel;

    ArrayList<? extends Employee> employees;

    ArrayList<String> basicColumnNames = new ArrayList<>(List.of(new String[]{"surname", "organization", "coefficient"}));

    String guardColumnName = "secured_area";
    String shopAssistantColumnName = "money";
    JTable infoOutputTable;
    JTable reworkedTable;

    boolean isGuard = true;

    public MainWindow(){
        super("Kr");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        setMinimumSize(new Dimension(500, 300));
        setVisible(true);
    }

    private void initComponents() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        initButtons();
        initOutput();
        initReworkedOutputData();;
        initDataWork("Sort by salary");
        initDataWork("Sort by salary and coefficient");
        initDataWork("Get companies starting with B");
        init4th();
        this.add(mainPanel);
    }

    private void init4th(){
        JLabel label = new JLabel();
        JLabel title = new JLabel("Average Salary");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JButton button = new JButton("Count average salary");
        button.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                label.setText(String.valueOf(EmployeesList.countAverageSalary(employees, "BelAZ")));
            }
        });
        panel.add(title);
        panel.add(button);
        panel.add(label);
        mainPanel.add(panel);
    }

    private void initButtons(){

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        openGuardFile = new JButton("Open guards");
        openShopAssistantFile = new JButton("Open ShopAssistants");

        openGuardFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                if (fileChooser.showOpenDialog(MainWindow.this) == JFileChooser.APPROVE_OPTION){
                    try {
                        employees =  Parser.parseGuardFile(fileChooser.getSelectedFile());
                        isGuard = true;
                        outputData();
                        openGuardFile.setBackground(Color.GRAY);
                    } catch (Exception ex) {
                        //This is for logging
                        System.err.println(ex.getMessage());
                        openGuardFile.setBackground(Color.RED);
                    }
                    openShopAssistantFile.setBackground(null);
                }
            }
        });

        openShopAssistantFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                if (fileChooser.showOpenDialog(MainWindow.this) == JFileChooser.APPROVE_OPTION){
                    try {
                        employees =  Parser.parseShopAssistantFile(fileChooser.getSelectedFile());
                        isGuard = false;
                        outputData();
                        openShopAssistantFile.setBackground(Color.GRAY);
                    } catch (Exception ex) {
                        //This is for logging
                        System.err.println(ex.getMessage());
                        openShopAssistantFile.setBackground(Color.RED);
                    }
                    openGuardFile.setBackground(null);
                }
            }
        });

        buttons.add(openGuardFile);
        buttons.add(openShopAssistantFile);

        this.add(buttons, BorderLayout.EAST);
    }

    private void initOutput(){
        infoOutputTable = new JTable(new String[][]{{"", "", ""}}, basicColumnNames.toArray());
        mainPanel.add(new JScrollPane(infoOutputTable));
    }

    private void initReworkedOutputData(){
        reworkedTable = new JTable(new String[][]{{"", "", ""}}, basicColumnNames.toArray());
        mainPanel.add(new JScrollPane(reworkedTable));
    }

    private void initDataWork(String buttonName){

        JLabel label = new JLabel();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JButton button = new JButton(buttonName);
        button.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    switch (buttonName){
                        case "Sort by salary": {
                            outputChangedData(EmployeesList.getEmployeesBySalary(employees));
                            break;
                        }
                        case "Sort by salary and coefficient": {
                            outputChangedData(EmployeesList.getEmployeesBySalaryAndCoefficient(employees));
                            break;
                        }
                        case "Get companies starting with B": {
                            outputChangedDataStringArr(EmployeesList.getCompaniesStartingWithB(employees));
                            break;
                        }
                    }
                } catch (IllegalArgumentException e){
                    label.setText("Load data first!");
                }
            }
        });
        panel.add(button);
        panel.add(label);
        mainPanel.add(panel);
    }

    private void outputData(){
        var tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(basicColumnNames.toArray());
        if (isGuard){
            tableModel.addColumn(guardColumnName);
        } else {
            tableModel.addColumn(shopAssistantColumnName);
        }

        for (Employee employee : employees) {
            tableModel.addRow(employee.toStringArray());
        }

        infoOutputTable.setModel(tableModel);
    }

    private void outputChangedData(ArrayList<Employee> employees){
        var tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(basicColumnNames.toArray());
        if (isGuard){
            tableModel.addColumn(guardColumnName);
        } else {
            tableModel.addColumn(shopAssistantColumnName);
        }

        for (Employee employee : employees) {
            tableModel.addRow(employee.toStringArray());
        }

        reworkedTable.setModel(tableModel);
    }

    private void outputChangedDataStringArr(ArrayList<String> companies){
        var tableModel = new DefaultTableModel();
        tableModel.addColumn("company_name", companies.toArray());
        reworkedTable.setModel(tableModel);
    }
}
