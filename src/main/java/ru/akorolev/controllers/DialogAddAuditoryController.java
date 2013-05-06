package ru.akorolev.controllers;

import ru.akorolev.entities.Auditory;
import ru.akorolev.forms.DialogAddAuditory;
import ru.akorolev.formsDataModels.DialogAddAuditoryDataModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 06.05.13
 * Time: 13:16
 * To change this template use File | Settings | File Templates.
 */
public class DialogAddAuditoryController {
    private DialogAddAuditory view;
    private DialogAddAuditoryDataModel dataModel = new DialogAddAuditoryDataModel();
    private boolean isSuccess = false;
    public DialogAddAuditoryController() {
        view = new DialogAddAuditory(null, true);
        regListeners();

        view.setVisible(true);
    }

    private void regListeners() {
        view.getjButtonOk().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onOkButtonClick();
            }
        });
        view.getjButtonCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });
    }

    private void onOkButtonClick() {
        if(!view.getjTextFieldAuditory().getText().isEmpty()){
            Auditory auditory = new Auditory();
            auditory.setAuditory(view.getjTextFieldAuditory().getText());
            try{
                dataModel.saveAuditory(auditory);
                this.isSuccess = true;
                view.dispose();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    public boolean isSuccess() {
        return this.isSuccess;
    }
}
