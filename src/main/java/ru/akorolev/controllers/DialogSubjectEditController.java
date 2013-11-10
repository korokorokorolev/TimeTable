package ru.akorolev.controllers;

import ru.akorolev.entities.Subject;
import ru.akorolev.entities.Teacher;
import ru.akorolev.forms.DialogAddSubject;
import ru.akorolev.formsDataModels.DialogAddSubjectDataModel;
import ru.akorolev.informer.Informer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: alexey
 * Date: 02.11.13
 * Time: 15:32
 * To change this template use File | Settings | File Templates.
 */
public class DialogSubjectEditController extends AbstractController {
    private DialogAddSubject view;
    private DialogAddSubjectDataModel dataModel;
    private Subject subject;
    private boolean isSuccess = false;

    public DialogSubjectEditController(Subject subject) {
        super();
        this.subject = subject;
        this.view.getjTextFieldFullName().setText(this.subject.getFullName());
        this.view.getjTextFieldShortName().setText(this.subject.getName());
        view.setVisible(true);
    }

    @Override
    void initDataModel() {
        dataModel = new DialogAddSubjectDataModel();
    }

    @Override
    void initView() {
        view = new DialogAddSubject(null, true);
    }

    @Override
    void regDataListeners() {
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
        view.getRootPane().setDefaultButton(view.getjButtonOk());
    }

    private void onOkButtonClick() {
        if(!view.getjTextFieldFullName().getText().isEmpty() && !view.getjTextFieldShortName().getText().isEmpty()) {
            subject.setFullName(view.getjTextFieldFullName().getText());
            subject.setName(view.getjTextFieldShortName().getText());
            try {
                dataModel.editSubject(subject);
                this.isSuccess = true;
                view.dispose();
            } catch(Exception e) {
                new Informer(null, true).setVisible(true);
            }
        }
    }
    public boolean isSuccess() {
        return this.isSuccess;
    }
}
