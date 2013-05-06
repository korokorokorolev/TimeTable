package ru.akorolev.controllers;

import ru.akorolev.entities.Teacher;
import ru.akorolev.forms.DialogTeachersAndSubjects;
import ru.akorolev.formsDataModels.DialogTeachersAndSubjectsDataModel;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 04.05.13
 * Time: 11:59
 * To change this template use File | Settings | File Templates.
 */
public class DialogTeachersAndSubjectsController {
    private DialogTeachersAndSubjects dialogTeachersAndSubjects;
    private DialogTeachersAndSubjectsDataModel dataModel = new DialogTeachersAndSubjectsDataModel();

    public DialogTeachersAndSubjectsController() {
        dialogTeachersAndSubjects = new DialogTeachersAndSubjects(null,true);
        initView();
        regDataModelListener();
        regListeners();

        dialogTeachersAndSubjects.setVisible(true);
    }

    private void regDataModelListener() {
        dataModel.setListener(new DialogTeachersAndSubjectsDataModel.OnModelChangeListener() {
            @Override
            public void onTeacherDeleted() {
                //To change body of implemented methods use File | Settings | File Templates.
            }
            @Override
            public void onSubjectDeleted(Teacher teacher) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    private void initView() {
        dialogTeachersAndSubjects.getjListTeachers().setModel(dataModel.getTeachersModel());
    }

    private void regListeners() {
        dialogTeachersAndSubjects.getjListTeachers().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(dialogTeachersAndSubjects.getjListTeachers().getSelectedValue() != null) {
                    dialogTeachersAndSubjects.getjListSubjects().setModel(dataModel.getSubjectsModel((Teacher)dialogTeachersAndSubjects.getjListTeachers().getSelectedValue()));
                }
            }
        });
        dialogTeachersAndSubjects.getjButtonBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogTeachersAndSubjects.dispose();
            }
        });
        dialogTeachersAndSubjects.getjButtonAddTeacher().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAddTeachersButtonClick();
            }
        });
        dialogTeachersAndSubjects.getjButtonAddSubject().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAddSubjectButtonClick();
            }
        });
    }

    private void onAddSubjectButtonClick() {
        if(dialogTeachersAndSubjects.getjListTeachers().getSelectedValue() != null) {
            Teacher teacher = (Teacher) dialogTeachersAndSubjects.getjListTeachers().getSelectedValue();
            DialogAddSubjectController dialogAddSubjectController = new DialogAddSubjectController(teacher);
            if(dialogAddSubjectController.isSuccess()) {
                dialogTeachersAndSubjects.getjListSubjects().setModel(dataModel.getSubjectsModel(teacher));
            }
        }

    }

    private void onAddTeachersButtonClick() {
        DialogTeacherController dialogTeacherController = new DialogTeacherController();
        if(dialogTeacherController.isSuccess()) {
            dialogTeachersAndSubjects.getjListTeachers().setModel(dataModel.getTeachersModel());
        }
    }
}