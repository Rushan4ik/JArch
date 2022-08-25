package com.company;

import com.company.algorithm.ArchiverCommand;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public class ArchiverFrame extends Frame {
    public ArchiverFrame() {
        initWindow();
        initComponents();
    }

    private void initWindow() {
        setTitle("J-Archiver");
        setSize(300, 300);
        setLayout(new FlowLayout());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private void initComponents() {
        Button b = (Button) add(new Button("select file"));
        b.addActionListener(ae -> {
            FileDialog dialog = new FileDialog(this, "Select file", FileDialog.LOAD);
            dialog.setSize(100, 100);
            dialog.setVisible(true);
            String fileName = dialog.getFile();
            ArchiverCommand command = new ArchiverCommand(new File(fileName));
            try {
                command.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
