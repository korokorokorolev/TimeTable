package ru.akorolev.controllers;

import ru.akorolev.forms.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 04.05.13
 * Time: 11:43
 * To change this template use File | Settings | File Templates.
 */
public class MainWindowController {
    private MainWindow mainWindow;

    public MainWindowController() {
        mainWindow = new MainWindow();
        mainWindow.setVisible(true);
        mainWindow.setTitle("Расписание факультета ИВТ");
        regListeners();
    }

    private void regListeners() {
        mainWindow.getjMenuItemTeachersAndSubjects().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogTeachersAndSubjectsController dialogTeachersAndSubjectsController = new DialogTeachersAndSubjectsController();
            }
        });
    }
}
