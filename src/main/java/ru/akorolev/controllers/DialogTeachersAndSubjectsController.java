package ru.akorolev.controllers;

import ru.akorolev.entities.Subject;
import ru.akorolev.entities.Teacher;
import ru.akorolev.forms.DialogTeachersAndSubjects;
import ru.akorolev.formsDataModels.DialogTeachersAndSubjectsDataModel;
import ru.akorolev.informer.Informer;

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
public class DialogTeachersAndSubjectsController extends AbstractController{
    private DialogTeachersAndSubjects dialogTeachersAndSubjects;
    private DialogTeachersAndSubjectsDataModel dataModel;

    public DialogTeachersAndSubjectsController() {
        super();
        dialogTeachersAndSubjects.setVisible(true);
    }

    @Override
    void initDataModel() {
        dataModel = new DialogTeachersAndSubjectsDataModel();
    }

    private void regDataModelListener() {
        dataModel.setListener(new DialogTeachersAndSubjectsDataModel.OnModelChangeListener() {
            @Override
            public void onTeacherDeleted() {
                onTeacherDeletedImpl();
            }
            @Override
            public void onSubjectDeleted(Teacher teacher) {
                onSubjectDeletedImpl(teacher);
            }
        });
    }

    private void onSubjectDeletedImpl(Teacher teacher) {
        try {
            dialogTeachersAndSubjects.getjListSubjects().setModel(dataModel.getSubjectsModel(teacher));
        } catch (Exception e) {
            new Informer(null, true).setVisible(true);
        }
    }

    private void onTeacherDeletedImpl() {
        try {
            dialogTeachersAndSubjects.getjListTeachers().setModel(dataModel.getTeachersModel());
            if(dialogTeachersAndSubjects.getjListTeachers().getModel().getSize() -1 > 0) {
                dialogTeachersAndSubjects.getjListTeachers().setSelectedIndex(dialogTeachersAndSubjects.getjListTeachers().getModel().getSize() -1);
            } else {
                dialogTeachersAndSubjects.getjListSubjects().setModel(dataModel.getEmptySubjectsModel());
            }
        } catch (Exception e) {
            new Informer(null, true).setVisible(true);
        }
    }
    @Override
    void initView() {
        dialogTeachersAndSubjects = new DialogTeachersAndSubjects(null,true);
        dialogTeachersAndSubjects.getjListTeachers().setModel(dataModel.getTeachersModel());
    }

    @Override
    void regDataListeners() {
        regDataModelListener();
    }
    @Override
    void regListeners() {
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
        dialogTeachersAndSubjects.getjButtonRemTeacher().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onRemTeacherButtonClick();
            }
        });
        dialogTeachersAndSubjects.getjButtonRemSubject().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onRemSubjectButtonClick();
            }
        });
    }

    private void onRemSubjectButtonClick() {
        try {
            if(dialogTeachersAndSubjects.getjListTeachers().getSelectedValue() != null &&
                    dialogTeachersAndSubjects.getjListSubjects().getSelectedValue() != null) {
                try {
                    dataModel.remSubject((Subject)dialogTeachersAndSubjects.getjListSubjects().getSelectedValue());
                } catch (Exception e) {

                }
            }
        } catch (Exception e) {
            new Informer(null, true).setVisible(true);
        }
    }

    private void onRemTeacherButtonClick() {
        try {
            if(dialogTeachersAndSubjects.getjListTeachers().getSelectedValue() != null) {
                try{
                    dataModel.remTeacher((Teacher) dialogTeachersAndSubjects.getjListTeachers().getSelectedValue());
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } catch (Exception e) {
            new Informer(null, true).setVisible(true);
        }
    }

    private void onAddSubjectButtonClick() {
        try {
            if(dialogTeachersAndSubjects.getjListTeachers().getSelectedValue() != null) {
                Teacher teacher = (Teacher) dialogTeachersAndSubjects.getjListTeachers().getSelectedValue();
                DialogAddSubjectController dialogAddSubjectController = new DialogAddSubjectController(teacher);
                if(dialogAddSubjectController.isSuccess()) {
                    dialogTeachersAndSubjects.getjListSubjects().setModel(dataModel.getSubjectsModel(teacher));
                    dialogTeachersAndSubjects.getjListSubjects().setSelectedIndex(dialogTeachersAndSubjects.getjListSubjects().getModel().getSize() -1);
                }
            }
        } catch (Exception e) {
            new Informer(null, true).setVisible(true);
        }

    }

    private void onAddTeachersButtonClick() {
        try {
            DialogTeacherController dialogTeacherController = new DialogTeacherController();
            if(dialogTeacherController.isSuccess()) {
                dialogTeachersAndSubjects.getjListTeachers().setModel(dataModel.getTeachersModel());
                dialogTeachersAndSubjects.getjListTeachers().setSelectedIndex(dialogTeachersAndSubjects.getjListTeachers().getModel().getSize() -1);
            }
        } catch (Exception e) {
            new Informer(null, true).setVisible(true);
        }
    }
}
