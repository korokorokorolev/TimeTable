package ru.akorolev.controllers;

import ru.akorolev.entities.Auditory;
import ru.akorolev.forms.DialogAddAuditory;
import ru.akorolev.formsDataModels.DialogAddAuditoryDataModel;
import ru.akorolev.informer.Informer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 06.05.13
 * Time: 13:16
 * To change this template use File | Settings | File Templates.
 */
public class DialogAddAuditoryController extends AbstractController{
    private DialogAddAuditory view;
    private DialogAddAuditoryDataModel dataModel;
    private boolean isSuccess = false;
    public DialogAddAuditoryController() {
        super();
        view.setVisible(true);
    }

    @Override
    void initDataModel() {
        dataModel = new DialogAddAuditoryDataModel();
    }

    @Override
    void initView() {
        view = new DialogAddAuditory(null, true);
    }

    @Override
    void regDataListeners() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
    @Override
    void regListeners() {
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
                new Informer(null, true).setVisible(true);
            }
        }
    }
    public boolean isSuccess() {
        return this.isSuccess;
    }
}
