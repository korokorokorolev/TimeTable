package ru.akorolev.controllers;

import ru.akorolev.entities.Cell;
import ru.akorolev.entities.Groups;
import ru.akorolev.entities.TrainingFeed;
import ru.akorolev.forms.DialogRaspisanie;
import ru.akorolev.formsDataModels.DialogRaspisanieDataModel;
import ru.akorolev.informer.Informer;
import ru.akorolev.printer.Printer;
import ru.akorolev.staticsVariables.Days;
import ru.akorolev.widgets.DayTable;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 07.05.13
 * Time: 11:23
 * To change this template use File | Settings | File Templates.
 */
public class DialogRaspisanieController extends AbstractController{
    private DialogRaspisanie view;
    private DialogRaspisanieDataModel dataModel;
    private TrainingFeed trainingFeed;

    public DialogRaspisanieController(TrainingFeed trainingFeed) {
        super();
        this.trainingFeed = trainingFeed;
        additionallyInit();
        view.setVisible(true);
    }

    private void additionallyInit() {
        repaintTables();
    }

    private void repaintTables() {
        try {
            view.getDayTable1().setModel(dataModel.getDayTableModel(Days.MONDAY, trainingFeed));
            view.getDayTable2().setModel(dataModel.getDayTableModel(Days.TUESDAY, trainingFeed));
            view.getDayTable3().setModel(dataModel.getDayTableModel(Days.WEDNESDAY, trainingFeed));
            view.getDayTable4().setModel(dataModel.getDayTableModel(Days.THURSDAY, trainingFeed));
            view.getDayTable5().setModel(dataModel.getDayTableModel(Days.FRIDAY, trainingFeed));
            view.getDayTable6().setModel(dataModel.getDayTableModel(Days.SATURDAY, trainingFeed));

            view.getDayTable1().setDefaultRenderer(Object.class, dataModel.getRenderer(Days.MONDAY, trainingFeed));
            view.getDayTable2().setDefaultRenderer(Object.class, dataModel.getRenderer(Days.TUESDAY, trainingFeed));
            view.getDayTable3().setDefaultRenderer(Object.class, dataModel.getRenderer(Days.WEDNESDAY, trainingFeed));
            view.getDayTable4().setDefaultRenderer(Object.class, dataModel.getRenderer(Days.THURSDAY, trainingFeed));
            view.getDayTable5().setDefaultRenderer(Object.class, dataModel.getRenderer(Days.FRIDAY, trainingFeed));
            view.getDayTable6().setDefaultRenderer(Object.class, dataModel.getRenderer(Days.SATURDAY, trainingFeed));
        } catch (Exception e) {
            new Informer(null, true).setVisible(true);
        }
    }

    @Override
    void initDataModel() {
        dataModel = new DialogRaspisanieDataModel();
    }

    @Override
    void initView() {
        view = new DialogRaspisanie(null, true);
    }

    @Override
    void regDataListeners() {

    }

    private class MouseListenerImpl implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            try {
                if (e.getClickCount() == 2) {
                    DialogCellController dialogCellController = null;
                    if(((DayTable) e.getComponent()).getModel().getValueAt(((DayTable) e.getComponent()).getSelectedRow(), ((DayTable) e.getComponent()).getSelectedColumn()) instanceof Cell){
                        Cell currCell = (Cell) ((DayTable) e.getComponent()).getModel().getValueAt(((DayTable) e.getComponent()).getSelectedRow(), ((DayTable) e.getComponent()).getSelectedColumn());
                        System.out.println(currCell.getId());
                        dialogCellController = new DialogCellController(true, currCell);
                    } else {
                        String grName = ((DayTable) e.getComponent()).getColumnName(((DayTable) e.getComponent()).getSelectedColumn());
                        Groups group = dataModel.getGroup(grName);
                        String day = ((DayTable) e.getComponent()).getDay();
                        int trainingNum = ((DayTable) e.getComponent()).getSelectedRow();
                        Cell cell = new Cell();
                        cell.setDay(day);
                        cell.setTrainingNum(trainingNum);
                        cell.setGroupsId(group);
                        dialogCellController = new DialogCellController(false, cell);

                    }
                    if(dialogCellController.isSuccess()) {
                        repaintTables();
                    }
                }
            } catch (Exception e1) {
                new Informer(null, true).setVisible(true);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    @Override
    void regListeners() {
        view.getDayTable1().addMouseListener(new MouseListenerImpl());
        view.getDayTable2().addMouseListener(new MouseListenerImpl());
        view.getDayTable3().addMouseListener(new MouseListenerImpl());
        view.getDayTable4().addMouseListener(new MouseListenerImpl());
        view.getDayTable5().addMouseListener(new MouseListenerImpl());
        view.getDayTable6().addMouseListener(new MouseListenerImpl());

        view.getDayTable1().setDay(Days.MONDAY);
        view.getDayTable2().setDay(Days.TUESDAY);
        view.getDayTable3().setDay(Days.WEDNESDAY);
        view.getDayTable4().setDay(Days.THURSDAY);
        view.getDayTable5().setDay(Days.FRIDAY);
        view.getDayTable6().setDay(Days.SATURDAY);

        view.getjMenuItem1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onShowConflictsClick();
            }
        });
        view.getjMenuItemPrint().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onPrintClick();
            }
        });
    }

    private void onPrintClick() {
        List cells = dataModel.getCellsFromTF(trainingFeed);
        System.out.println(cells);
        Printer printer = new Printer(cells,trainingFeed);
        try {
            printer.print();
        } catch (FileNotFoundException e) {
            new Informer(null, true).setVisible(true);
        }
    }

    private void onShowConflictsClick() {
        try {
            DialogConflictsController dialogConflictsController = new DialogConflictsController();
            repaintTables();
        } catch (Exception e) {
            new Informer(null, true).setVisible(true);
        }
    }
}
