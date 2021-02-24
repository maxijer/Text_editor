package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ButtonListener implements ActionListener {

    public static String NEW_ACTION = "New";
    public static String OPEN_ACTION = "Open";
    public static String SAVE_ACTION = "Save";
    public static String SAVE_AS_ACTION = "Save As";

    Editor wnd;
    public String operation;

    public ButtonListener(Editor wnd, String operation) {
        this.wnd = wnd;
        this.operation = operation;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (operation.equals(NEW_ACTION)) {
            wnd.newFileAction();
        } else if (operation.equals(OPEN_ACTION)) {
            wnd.openFileAction();
        } else if (operation.equals(SAVE_ACTION)) {
            try {
                wnd.saveFileAction();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (operation.equals(SAVE_AS_ACTION)) {
            wnd.saveAsFileAction();
        }
    }
}
