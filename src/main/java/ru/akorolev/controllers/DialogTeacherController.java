package ru.akorolev.controllers;

import ru.akorolev.entities.Teacher;
import ru.akorolev.forms.DialogTeacher;
import ru.akorolev.formsDataModels.DialogTeacherDataModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 04.05.13
 * Time: 11:56
 * To change this template use File | Settings | File Templates.
 */
public class DialogTeacherController {
    private DialogTeacher dialogTeacher;
    private DialogTeacherDataModel dataModel = new DialogTeacherDataModel();
    private boolean isSuccess = false;

    public DialogTeacherController() {
        dialogTeacher = new DialogTeacher(null, true);
        regListeners();
        dialogTeacher.setVisible(true);
    }

    private void regListeners() {
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
    }

    private void onCancelButtonClick() {
        dialogTeacher.dispose();
    }

    private void onOkButtonClick() {
        Teacher teacher = new Teacher();
        teacher.setName(dialogTeacher.getjTextFieldTeacherName().getText());
        if(!teacher.getName().isEmpty()) {
            try {
                dataModel.saveTeacher(teacher);
                this.isSuccess = true;
                dialogTeacher.dispose();
            } catch(Exception e) {
                System.out.println(e);
            }
        }
    }
    public boolean isSuccess() {
        return this.isSuccess;
    }
}
