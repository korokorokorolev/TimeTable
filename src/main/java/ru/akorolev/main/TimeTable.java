package ru.akorolev.main;

import ru.akorolev.controllers.MainWindowController;
import ru.akorolev.databaseaccess.DAO;
import ru.akorolev.databaseaccess.DAOImplementation;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 04.05.13
 * Time: 11:35
 * To change this template use File | Settings | File Templates.
 */
public class TimeTable {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                MainWindowController mainWindowController = new MainWindowController();
            }
        });
    }
}
