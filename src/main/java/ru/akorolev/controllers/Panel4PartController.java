package ru.akorolev.controllers;

import ru.akorolev.entities.Auditory;
import ru.akorolev.entities.Subject;
import ru.akorolev.entities.Teacher;
import ru.akorolev.forms.Panel4Part;
import ru.akorolev.formsDataModels.Panel4PartDataModel;
import ru.akorolev.informer.Informer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 07.05.13
 * Time: 22:28
 * To change this template use File | Settings | File Templates.
 */
public class Panel4PartController {
    private Panel4Part panel4Part;
    private Panel4PartDataModel dataModel = new Panel4PartDataModel();

    public Panel4PartController(Panel4Part panel4Part) {
        this.panel4Part = panel4Part;
        initView();
        initListeners();
    }

    public Panel4PartController(Panel4Part panel4Part, Subject subject, Auditory auditory) {
        this.panel4Part = panel4Part;
        initViewEdit(subject, auditory);
        initListeners();
    }

    private void initViewEdit(Subject subject, Auditory auditory) {
        try {
            this.panel4Part.getjComboBoxTeacher().setModel(dataModel.getTeachersModel());
            this.panel4Part.getjComboBoxAuditory().setModel(dataModel.getAuditoriesModel());

            if (subject != null && subject.getTeacherId() != null) {
                this.panel4Part.getjComboBoxTeacher().setSelectedItem(subject.getTeacherId());
                onComboBoxTeachersChange();
                this.panel4Part.getjComboBoxSubject().setSelectedItem(subject);
                this.panel4Part.getjComboBoxAuditory().setSelectedItem(auditory);
            }
        } catch (Exception e) {
            new Informer(null, true).setVisible(true);
        }
    }

    private void initListeners() {
        this.panel4Part.getjComboBoxTeacher().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onComboBoxTeachersChange();
            }
        });
    }

    private void onComboBoxTeachersChange() {
        try {
            if(panel4Part.getjComboBoxTeacher().getSelectedItem() != null) {
                panel4Part.getjComboBoxSubject().setModel(dataModel.getSubjectModel((Teacher)panel4Part.getjComboBoxTeacher().getSelectedItem()));
                if(panel4Part.getjComboBoxSubject().getModel().getSize() != 0) {
                    panel4Part.getjComboBoxSubject().setSelectedIndex(0);
                }
            } else {
                panel4Part.getjComboBoxSubject().setModel(dataModel.getEmptyModel());
            }
        } catch (Exception e) {
            new Informer(null, true).setVisible(true);
        }
    }

    private void initView() {
        this.panel4Part.getjComboBoxTeacher().setModel(dataModel.getTeachersModel());
        this.panel4Part.getjComboBoxAuditory().setModel(dataModel.getAuditoriesModel());
    }

    public Teacher getTeacher() {
        return (Teacher) this.panel4Part.getjComboBoxTeacher().getSelectedItem();
    }
    public Subject getSubject() {
        return (Subject) this.panel4Part.getjComboBoxSubject().getSelectedItem();
    }
    public Auditory getAuditory() {
        return (Auditory) this.panel4Part.getjComboBoxAuditory().getSelectedItem();
    }

    public boolean isReady() {
        if(this.getTeacher() == null && this.getAuditory() != null ||
                this.getTeacher() != null && this.getAuditory() == null) {
            return false;
        } else {
            return true;
        }
    }

    public void setupTSA(Subject subject, Auditory auditory) {
        try {
            if(subject != null) {
                this.panel4Part.getjComboBoxTeacher().setSelectedItem(subject.getTeacherId());
                onComboBoxTeachersChange();
                this.panel4Part.getjComboBoxSubject().setSelectedItem(subject);
                this.panel4Part.getjComboBoxAuditory().setModel(dataModel.getAuditoriesModel());
                this.panel4Part.getjComboBoxAuditory().setSelectedItem(auditory);
            } else  {
                this.panel4Part.getjComboBoxTeacher().setSelectedItem(null);
                this.panel4Part.getjComboBoxSubject().setSelectedItem(null);
                this.panel4Part.getjComboBoxAuditory().setModel(dataModel.getAuditoriesModel());
                this.panel4Part.getjComboBoxAuditory().setSelectedItem(auditory);
            }
        } catch (Exception e) {
            new Informer(null, true).setVisible(true);
        }
    }

}
