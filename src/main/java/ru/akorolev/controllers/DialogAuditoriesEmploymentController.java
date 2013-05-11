package ru.akorolev.controllers;

import ru.akorolev.entities.Auditory;
import ru.akorolev.entities.Teacher;
import ru.akorolev.forms.DialogAuditoryEmployment;
import ru.akorolev.formsDataModels.DialogAuditoriesDataModel;
import ru.akorolev.formsDataModels.DialogAuditoriesEmploymentDataModel;
import ru.akorolev.staticsVariables.Days;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 11.05.13
 * Time: 11:50
 * To change this template use File | Settings | File Templates.
 */
public class DialogAuditoriesEmploymentController extends AbstractController{
    private DialogAuditoryEmployment view;
    private DialogAuditoriesEmploymentDataModel dataModel;

    public DialogAuditoriesEmploymentController() {
        super();
        view.setVisible(true);
    }

    @Override
    void initDataModel() {
        dataModel = new DialogAuditoriesEmploymentDataModel();
    }

    private void onComboChange() {
        switch (view.getjComboBoxChZn().getSelectedIndex()) {
            case 0: {
                repaintTablesCh();
                break;
            }
            case 1: {
                repaintTablesZn();
                break;
            }
        }
    }

    @Override
    void initView() {
        view = new DialogAuditoryEmployment(null ,true);
        view.getjComboBoxAuditory().setModel(dataModel.getAuditoriesModel());
        if(view.getjComboBoxAuditory().getModel().getSize() > 0) {
            view.getjComboBoxAuditory().setSelectedIndex(0);
            onComboChange();
        }
    }

    @Override
    void regDataListeners() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void regListeners() {
        view.getjComboBoxAuditory().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onComboChange();
            }
        });
        view.getjComboBoxChZn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onComboChange();
            }
        });
    }

    private void repaintTablesZn() {
        if(view.getjComboBoxAuditory().getSelectedItem() != null) {
            view.getjTable1().setModel(dataModel.getTableZnModel(Days.MONDAY, (Auditory) view.getjComboBoxAuditory().getSelectedItem()));
            view.getjTable2().setModel(dataModel.getTableZnModel(Days.TUESDAY, (Auditory) view.getjComboBoxAuditory().getSelectedItem()));
            view.getjTable3().setModel(dataModel.getTableZnModel(Days.WEDNESDAY, (Auditory) view.getjComboBoxAuditory().getSelectedItem()));
            view.getjTable4().setModel(dataModel.getTableZnModel(Days.THURSDAY, (Auditory) view.getjComboBoxAuditory().getSelectedItem()));
            view.getjTable5().setModel(dataModel.getTableZnModel(Days.FRIDAY, (Auditory) view.getjComboBoxAuditory().getSelectedItem()));
            view.getjTable6().setModel(dataModel.getTableZnModel(Days.SATURDAY, (Auditory) view.getjComboBoxAuditory().getSelectedItem()));
            setColumnsWidth();
        }
    }

    private void repaintTablesCh() {
        if(view.getjComboBoxAuditory().getSelectedItem() != null) {
            view.getjTable1().setModel(dataModel.getTableChModel(Days.MONDAY, (Auditory)view.getjComboBoxAuditory().getSelectedItem()));
            view.getjTable2().setModel(dataModel.getTableChModel(Days.TUESDAY, (Auditory)view.getjComboBoxAuditory().getSelectedItem()));
            view.getjTable3().setModel(dataModel.getTableChModel(Days.WEDNESDAY, (Auditory)view.getjComboBoxAuditory().getSelectedItem()));
            view.getjTable4().setModel(dataModel.getTableChModel(Days.THURSDAY, (Auditory)view.getjComboBoxAuditory().getSelectedItem()));
            view.getjTable5().setModel(dataModel.getTableChModel(Days.FRIDAY, (Auditory)view.getjComboBoxAuditory().getSelectedItem()));
            view.getjTable6().setModel(dataModel.getTableChModel(Days.SATURDAY, (Auditory)view.getjComboBoxAuditory().getSelectedItem()));
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
