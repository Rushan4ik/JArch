package com.company.gui;

import com.company.util.ArchiverCommand;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ArchiverJFrame extends JFrame {
    private GridBagLayout layout;
    private JLabel state;
    private JButton choice, pack, unpack;
    private File file;


    public ArchiverJFrame(String title) throws HeadlessException {
        initWindow(title);
        initComponents();
    }

    private void initWindow(String title) {
        setSize(420, 300);
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        layout = new GridBagLayout();
        setLayout(layout);
    }

    private void initComponents() {
        state = new JLabel("Wait a choice");
        choice = new JButton("Choice file");
        choice.addActionListener(actionEvent -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int returnValue = chooser.showOpenDialog(this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                file = chooser.getSelectedFile();
                state.setText("Select file: " + file);
            }
        });
        pack = new JButton("Pack");
        pack.addActionListener(actionEvent -> {
            if (file == null) {
                state.setText("File not selected");
                return;
            }
            createCommand(true);
        });
        unpack = new JButton("Unpack");
        unpack.addActionListener(actionEvent -> {
            if (file == null) {
                state.setText("File not selected");
                return;
            }
            createCommand(false);
        });
        addComponents();
    }

    private void createCommand(boolean pack) {
        ArchiverCommand command = new ArchiverCommand(file, pack);
        try {
            command.execute();
            state.setText("Done!");
            file = null;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            state.setText("Error!");
        }
    }
    private void addComponents() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.weightx = 1;
        constraints.weighty = 1;
        layout.addLayoutComponent(state, constraints);
        constraints.gridwidth = 1;
        constraints.gridy = 1;
        constraints.gridx = 0;
        layout.addLayoutComponent(choice, constraints);
        constraints.gridx = 1;
        layout.addLayoutComponent(pack, constraints);
        constraints.gridx = 2;
        layout.addLayoutComponent(unpack, constraints);

        add(state);
        add(choice);
        add(pack);
        add(unpack);
    }
}
