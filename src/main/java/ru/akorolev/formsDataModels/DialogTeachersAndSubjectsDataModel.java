package ru.akorolev.formsDataModels;

import ru.akorolev.databaseaccess.DAO;
import ru.akorolev.databaseaccess.DAOImplementation;
import ru.akorolev.entities.Subject;
import ru.akorolev.entities.Teacher;
import ru.akorolev.models.ListModelImplementation;

import javax.swing.*;
import java.util.ArrayList;
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
    private OnModelChangeListener listener;

    public void setListener(OnModelChangeListener listener) {
        this.listener = listener;
    }
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
    public void remTeacher(Teacher teacher) {
        dao.removeTeacher(teacher);
        if(listener != null) {
            listener.onTeacherDeleted();
        }
    }

    public ListModel getEmptySubjectsModel() {
        return new ListModelImplementation(new ArrayList<Subject>());
    }

    public interface OnModelChangeListener {
        public void onTeacherDeleted();
        public void onSubjectDeleted(Teacher teacher);
    }
}
