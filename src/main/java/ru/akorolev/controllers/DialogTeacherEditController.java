package ru.akorolev.controllers;

import ru.akorolev.entities.Teacher;
import ru.akorolev.forms.DialogTeacher;
import ru.akorolev.formsDataModels.DialogTeacherDataModel;
import ru.akorolev.informer.Informer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: alexey
 * Date: 02.11.13
 * Time: 13:16
 * To change this template use File | Settings | File Templates.
 */
public class DialogTeacherEditController extends AbstractController {
    private DialogTeacher dialogTeacher;
    private DialogTeacherDataModel dataModel;
    private boolean isSuccess = false;
    private Teacher teacher;

    public DialogTeacherEditController(Teacher teacher) {
        super();
        this.teacher = teacher;
        dialogTeacher.getjTextFieldTeacherName().setText(this.teacher.getName());
        this.dialogTeacher.setVisible(true);
    }

    @Override
    void initDataModel() {
        dataModel = new DialogTeacherDataModel();
    }

    @Override
    void initView() {
        dialogTeacher = new DialogTeacher(null, true);
    }

    @Override
    void regDataListeners() {

    }

    @Override
    void regListeners() {
        dialogTeacher.getjButtonOk().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onOkButtonClick();
            }
        });
        dialogTeacher.getjButtonCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancelButtonClick();
            }
        });
        dialogTeacher.getRootPane().setDefaultButton(dialogTeacher.getjButtonOk());
    }

    private void onCancelButtonClick() {
        dialogTeacher.dispose();
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    private void onOkButtonClick() {
        teacher.setName(dialogTeacher.getjTextFieldTeacherName().getText());
        if(!teacher.getName().isEmpty()) {
            try {
                dataModel.editTeacher(teacher);
                this.isSuccess = true;
                dialogTeacher.dispose();
            } catch(Exception e) {
                new Informer(null, true).setVisible(true);
            }
        }
    }
}
