package ru.akorolev.controllers;

import ru.akorolev.entities.Auditory;
import ru.akorolev.forms.DialogAuditories;
import ru.akorolev.formsDataModels.DialogAuditoriesDataModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 06.05.13
 * Time: 12:53
 * To change this template use File | Settings | File Templates.
 */
public class DialogAuditoriesController {
    private DialogAuditories view;
    private DialogAuditoriesDataModel dataModel = new DialogAuditoriesDataModel();

    public DialogAuditoriesController() {
        view = new DialogAuditories(null, true);
        initView();
        regListeners();
        regDataListeners();
        view.setVisible(true);
    }

    private void regDataListeners() {
        this.dataModel.setListener(new DialogAuditoriesDataModel.OnChangeListener() {
            @Override
            public void onAuditoryDeleted() {
                view.getjListAuditories().setModel(dataModel.getAuditoriesModel());
                view.getjListAuditories().setSelectedIndex(view.getjListAuditories().getModel().getSize() -1);
            }
        });
    }

    private void regListeners() {
        view.getjButtonBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });
        view.getjButtonAddAuditory().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAddAuditoryButtonClick();
            }
        });
        view.getjButtonRemAuditory().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onRemAuditoryButtonClick();
            }
        });
    }

    private void onRemAuditoryButtonClick() {
        if(view.getjListAuditories().getSelectedValue() != null) {
            try{
                dataModel.removeAuditory((Auditory)view.getjListAuditories().getSelectedValue());
            } catch(Exception e) {

            }
        }
    }

    private void onAddAuditoryButtonClick() {
        DialogAddAuditoryController dialogAddAuditoryController = new DialogAddAuditoryController();
        if(dialogAddAuditoryController.isSuccess()) {
            view.getjListAuditories().setModel(dataModel.getAuditoriesModel());
            view.getjListAuditories().setSelectedIndex(view.getjListAuditories().getModel().getSize() - 1);
        }
    }

    private void initView() {
        view.getjListAuditories().setModel(dataModel.getAuditoriesModel());
        view.getjListAuditories().setSelectedIndex(0);
    }
}
