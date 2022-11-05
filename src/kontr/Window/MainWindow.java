package kontr.Window;

import kontr.*;
import lab7.Drawer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class MainWindow extends JFrame {

    JButton openGuardFile;
    JButton openShopAssistantFile;

    JLabel textGuard;

    JLabel textShopAssistant;

    public MainWindow(){
        super("Drawer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        setMinimumSize(new Dimension(500, 300));
        setVisible(true);
    }

    private void initComponents() {
        initButtons();
        initOutput();
    }

    private void initButtons(){
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        openGuardFile = new JButton("Open guards");
        openShopAssistantFile = new JButton("OpenShopAssistants");

        openGuardFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                if (fileChooser.showOpenDialog(MainWindow.this) == JFileChooser.APPROVE_OPTION){
                    try {
                        ArrayList<Guard> lis =  Parser.parseGuardFile(fileChooser.getSelectedFile());
                        openGuardFile.setBackground(Color.GRAY);
                    } catch (Exception ex) {
                        //This is for logging
                        System.err.println(ex.getMessage());

                        openGuardFile.setBackground(Color.RED);
                    }
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
                        ArrayList<ShopAssistant> lis =  Parser.parseShopAssistantFile(fileChooser.getSelectedFile());

                        openShopAssistantFile.setBackground(Color.GRAY);
                    } catch (Exception ex) {
                        //This is for logging
                        System.err.println(ex.getMessage());

                        openGuardFile.setBackground(Color.RED);
                    }
                }
            }
        });

        buttons.add(openGuardFile);
        buttons.add(openShopAssistantFile);
        this.add(buttons, BorderLayout.EAST);
    }

    private void initOutput(){
        JPanel output = new JPanel(new FlowLayout(FlowLayout.CENTER));

        textGuard = new JLabel();
        textShopAssistant = new JLabel();

        output.add(textGuard);
        output.add(textShopAssistant);
        this.add(output, BorderLayout.CENTER);
    }
}
