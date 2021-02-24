package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.desktop.OpenFilesEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class Editor extends JFrame {
    private JToolBar toolBar;
    private JButton newFile;
    private JButton openFile;
    private JButton saveFile;
    private JButton saveAsFile;
    private JTextArea textEditor;
    private JScrollPane scroll;

    private String filename;
    private boolean isSaved;

    public Editor() {
        setTitle("Text Editor");
        setSize(640, 480);
        setLocation(100, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        filename = "";
        isSaved = true;
        toolBar = new JToolBar();
        add(toolBar, BorderLayout.NORTH);
        newFile = new JButton("New");
        toolBar.add(newFile);
        openFile = new JButton("Open");
        toolBar.add(openFile);
        saveFile = new JButton("Save");
        toolBar.add(saveFile);
        saveAsFile = new JButton("Save As");
        toolBar.add(saveAsFile);
        textEditor = new JTextArea();
        scroll = new JScrollPane(textEditor, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scroll, BorderLayout.CENTER);
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newFileItem = new JMenuItem("New");
        JMenuItem openFileItem = new JMenuItem("Open");
        JMenuItem SaveFileItem = new JMenuItem("Save");
        JMenuItem Save_AS_FileItem = new JMenuItem("Save as");
        fileMenu.add(newFileItem);
        fileMenu.add(openFileItem);
        newFile.addActionListener(new ButtonListener(this, "New")); // Привязываем событие к кнопке
        newFileItem.addActionListener(new ButtonListener(this, "New"));
        openFile.addActionListener(new ButtonListener(this, "Open"));
        openFileItem.addActionListener(new ButtonListener(this, "Open"));
        saveFile.addActionListener(new ButtonListener(this, "Save"));
        SaveFileItem.addActionListener(new ButtonListener(this, "Save"));
        saveAsFile.addActionListener(new ButtonListener(this, "Save As"));
        Save_AS_FileItem.addActionListener(new ButtonListener(this, "Save As"));
        fileMenu.add(SaveFileItem);
        fileMenu.add(Save_AS_FileItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
        setVisible(true);
    }

    public void newFileAction() {
        filename = "";
        textEditor.setText("");
    }

    public void openFileAction() {
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(this);
        if (fc.getSelectedFile() != null) {
            try {
                File file = fc.getSelectedFile();
                filename = file.getAbsolutePath();
                FileReader fileReader = new FileReader(file);
                int c;
                String r = "";
                while ((c = fileReader.read()) != -1) {
                    r += ((char) c);
                }
                textEditor.setText(r);
                isSaved = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveFileAction() throws IOException {
        if (filename.equals("")) {
            saveAsFileAction();
        } else {
            FileWriter fileWriter = new FileWriter(filename);
            fileWriter.write(textEditor.getText());
            fileWriter.close();
        }
    }

    public void saveAsFileAction() {
        JFileChooser fc = new JFileChooser();
        fc.showSaveDialog(this);
        if (fc.getSelectedFile() != null) {
            isSaved = true;
            File file = fc.getSelectedFile();
            filename = file.getAbsolutePath();
            try {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(textEditor.getText());
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

public class Main {
    public static void main(String[] args) {
        Editor a = new Editor();
    }
}
