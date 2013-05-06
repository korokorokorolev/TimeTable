package ru.akorolev.controllers;

import ru.akorolev.entities.Subject;
import ru.akorolev.entities.Teacher;
import ru.akorolev.forms.DialogAddSubject;
import ru.akorolev.formsDataModels.DialogAddSubjectDataModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 05.05.13
 * Time: 13:56
 * To change this template use File | Settings | File Templates.
 */
public class DialogAddSubjectController {
    private DialogAddSubject view;
    private DialogAddSubjectDataModel dataModel = new DialogAddSubjectDataModel();
    private Teacher teacher;
    private boolean isSuccess = false;
    public DialogAddSubjectController(Teacher teacher) {
        view = new DialogAddSubject(null, true);
        this.teacher = teacher;
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
    }

    private void onOkButtonClick() {
        if(!view.getjTextFieldFullName().getText().isEmpty() && !view.getjTextFieldShortName().getText().isEmpty()) {
            Subject subject = new Subject();
            subject.setFullName(view.getjTextFieldFullName().getText());
            subject.setName(view.getjTextFieldShortName().getText());
            teacher.addSubject(subject);
            try {
                dataModel.saveSubject(subject);
                this.isSuccess = true;
                view.dispose();
            } catch(Exception e) {
                System.out.println(e);
            }
        }
    }
    public boolean isSuccess() {
        return this.isSuccess;
    }
}
