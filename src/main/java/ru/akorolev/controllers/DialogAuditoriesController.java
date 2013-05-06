package ru.akorolev.controllers;

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
        view.setVisible(true);
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
    }

    private void onAddAuditoryButtonClick() {

    }

    private void initView() {
        view.getjListAuditories().setModel(dataModel.getAuditoriesModel());
        view.getjListAuditories().setSelectedIndex(0);
    }
}
