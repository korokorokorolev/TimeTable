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
 * User: alex
 * Date: 05.05.13
 * Time: 13:56
 * To change this template use File | Settings | File Templates.
 */
public class DialogAddSubjectController extends AbstractController{
    private DialogAddSubject view;
    private DialogAddSubjectDataModel dataModel;
    private Teacher teacher;
    private boolean isSuccess = false;
    public DialogAddSubjectController(Teacher teacher) {
        super();
        this.teacher = teacher;
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
                new Informer(null, true).setVisible(true);
            }
        }
    }
    public boolean isSuccess() {
        return this.isSuccess;
    }
}
