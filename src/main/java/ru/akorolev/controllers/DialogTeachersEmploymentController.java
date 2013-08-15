package ru.akorolev.controllers;

import ru.akorolev.entities.Teacher;
import ru.akorolev.forms.DialogTeachersEmployment;
import ru.akorolev.formsDataModels.DialogTeachersEmploymentDataModel;
import ru.akorolev.informer.Informer;
import ru.akorolev.printer.PrintTeacher;
import ru.akorolev.staticsVariables.Days;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 10.05.13
 * Time: 11:55
 * To change this template use File | Settings | File Templates.
 */
public class DialogTeachersEmploymentController extends AbstractController{
    private DialogTeachersEmployment view;
    private DialogTeachersEmploymentDataModel dataModel;

    public DialogTeachersEmploymentController() {
        super();

        view.setVisible(true);
    }

    @Override
    void initDataModel() {
        dataModel = new DialogTeachersEmploymentDataModel();

    }

    @Override
    void initView() {
        view = new DialogTeachersEmployment(null, true);
        view.getjComboBoxTeacher().setModel(dataModel.getTeachersModel());
        if(view.getjComboBoxTeacher().getModel().getSize() > 0) {
            view.getjComboBoxTeacher().setSelectedIndex(0);
            onTeacherChange();
        }
    }

    @Override
    void regDataListeners() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void regListeners() {
        view.getjComboBoxTeacher().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onTeacherChange();
            }
        });
        view.getjComboBoxChZn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onTeacherChange();
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
        if(view.getjComboBoxTeacher().getSelectedItem() != null) {
            System.out.println(dataModel.getAllCells((Teacher)view.getjComboBoxTeacher().getSelectedItem()));
            PrintTeacher printer = new PrintTeacher(dataModel.getAllCells((Teacher)view.getjComboBoxTeacher().getSelectedItem()),
                    (Teacher)view.getjComboBoxTeacher().getSelectedItem());
            try {
                printer.print();
            } catch (FileNotFoundException e) {
                new Informer(null, true).setVisible(true);
            }
        }
    }

    private void onTeacherChange() {
        switch (view.getjComboBoxChZn().getSelectedIndex()) {
            case 0: {
                repaintTablesCh();
                break;
            }
            case 1: {
                repaintTablesZn();
                break;
            }
            case 2: {
                repaintTablesAll();
                break;
            }
        }
    }

    private void repaintTablesAll() {
        if(view.getjComboBoxTeacher().getSelectedItem() != null) {
            view.getjTable1().setModel(dataModel.getTableAllModel(Days.MONDAY, (Teacher)view.getjComboBoxTeacher().getSelectedItem()));
            view.getjTable2().setModel(dataModel.getTableAllModel(Days.TUESDAY, (Teacher)view.getjComboBoxTeacher().getSelectedItem()));
            view.getjTable3().setModel(dataModel.getTableAllModel(Days.WEDNESDAY, (Teacher)view.getjComboBoxTeacher().getSelectedItem()));
            view.getjTable4().setModel(dataModel.getTableAllModel(Days.THURSDAY, (Teacher)view.getjComboBoxTeacher().getSelectedItem()));
            view.getjTable5().setModel(dataModel.getTableAllModel(Days.FRIDAY, (Teacher)view.getjComboBoxTeacher().getSelectedItem()));
            view.getjTable6().setModel(dataModel.getTableAllModel(Days.SATURDAY, (Teacher)view.getjComboBoxTeacher().getSelectedItem()));
            setColumnsWidth();
        }
    }

    private void repaintTablesZn() {
        if(view.getjComboBoxTeacher().getSelectedItem() != null) {
            view.getjTable1().setModel(dataModel.getTableZnModel(Days.MONDAY, (Teacher) view.getjComboBoxTeacher().getSelectedItem()));
            view.getjTable2().setModel(dataModel.getTableZnModel(Days.TUESDAY, (Teacher) view.getjComboBoxTeacher().getSelectedItem()));
            view.getjTable3().setModel(dataModel.getTableZnModel(Days.WEDNESDAY, (Teacher) view.getjComboBoxTeacher().getSelectedItem()));
            view.getjTable4().setModel(dataModel.getTableZnModel(Days.THURSDAY, (Teacher) view.getjComboBoxTeacher().getSelectedItem()));
            view.getjTable5().setModel(dataModel.getTableZnModel(Days.FRIDAY, (Teacher) view.getjComboBoxTeacher().getSelectedItem()));
            view.getjTable6().setModel(dataModel.getTableZnModel(Days.SATURDAY, (Teacher) view.getjComboBoxTeacher().getSelectedItem()));
            setColumnsWidth();
        }
    }

    private void repaintTablesCh() {
        if(view.getjComboBoxTeacher().getSelectedItem() != null) {
            view.getjTable1().setModel(dataModel.getTableChModel(Days.MONDAY, (Teacher)view.getjComboBoxTeacher().getSelectedItem()));
            view.getjTable2().setModel(dataModel.getTableChModel(Days.TUESDAY, (Teacher)view.getjComboBoxTeacher().getSelectedItem()));
            view.getjTable3().setModel(dataModel.getTableChModel(Days.WEDNESDAY, (Teacher)view.getjComboBoxTeacher().getSelectedItem()));
            view.getjTable4().setModel(dataModel.getTableChModel(Days.THURSDAY, (Teacher)view.getjComboBoxTeacher().getSelectedItem()));
            view.getjTable5().setModel(dataModel.getTableChModel(Days.FRIDAY, (Teacher)view.getjComboBoxTeacher().getSelectedItem()));
            view.getjTable6().setModel(dataModel.getTableChModel(Days.SATURDAY, (Teacher)view.getjComboBoxTeacher().getSelectedItem()));
            setColumnsWidth();
        }
    }
    private void setColumnsWidth() {
        view.getjTable1().getColumnModel().getColumn(0).setMaxWidth(20);
        view.getjTable2().getColumnModel().getColumn(0).setMaxWidth(20);
        view.getjTable3().getColumnModel().getColumn(0).setMaxWidth(20);
        view.getjTable4().getColumnModel().getColumn(0).setMaxWidth(20);
        view.getjTable5().getColumnModel().getColumn(0).setMaxWidth(20);
        view.getjTable6().getColumnModel().getColumn(0).setMaxWidth(20);

    }
}
