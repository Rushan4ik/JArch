package com.company;

import com.company.gui.ArchiverJFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ArchiverJFrame jFrame = new ArchiverJFrame("J-Archiver");
            jFrame.setVisible(true);
        });
    }
}
