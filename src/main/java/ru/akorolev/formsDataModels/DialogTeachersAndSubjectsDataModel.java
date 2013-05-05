package ru.akorolev.formsDataModels;

import ru.akorolev.databaseaccess.DAO;
import ru.akorolev.databaseaccess.DAOImplementation;
import ru.akorolev.entities.Teacher;
import ru.akorolev.models.ListModelImplementation;

import javax.swing.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 04.05.13
 * Time: 12:38
 * To change this template use File | Settings | File Templates.
 */
public class DialogTeachersAndSubjectsDataModel {
    private DAO dao = DAOImplementation.getInstance();

    private List getTeachers() {
        return dao.getTeachers();
    }
    private List getSubjects(Teacher teacher) {
        return teacher.getSubjectList();
    }
    public ListModel getTeachersModel() {
        return new ListModelImplementation(this.getTeachers());
    }
    public ListModel getSubjectsModel(Teacher teacher) {
        return new ListModelImplementation(this.getSubjects(teacher));
    }
}
