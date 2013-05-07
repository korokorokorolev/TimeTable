package ru.akorolev.formsDataModels;

import ru.akorolev.databaseaccess.DAO;
import ru.akorolev.databaseaccess.DAOImplementation;
import ru.akorolev.entities.Teacher;
import ru.akorolev.models.ArrayListComboBoxModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 07.05.13
 * Time: 22:29
 * To change this template use File | Settings | File Templates.
 */
public class Panel4PartDataModel {
    DAO dao = DAOImplementation.getInstance();

    private List getTeachers() {
        return dao.getTeachers();
    }

    public ComboBoxModel getTeachersModel() {
        return new ArrayListComboBoxModel(getTeachers(), true);
    }

    private List getSubjects(Teacher teacher) {
        return teacher.getSubjectList();
    }

    public ComboBoxModel getSubjectModel(Teacher teacher) {
        return new ArrayListComboBoxModel(getSubjects(teacher), false);
    }

    public ComboBoxModel getEmptyModel() {
        return new ArrayListComboBoxModel(new ArrayList(), true);
    }

    private List getAuditories() {
        return dao.getAuditories();
    }

    public ComboBoxModel getAuditoriesModel() {
        return new ArrayListComboBoxModel(getAuditories(), true);
    }
}
