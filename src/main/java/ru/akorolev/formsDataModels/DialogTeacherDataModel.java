package ru.akorolev.formsDataModels;

import ru.akorolev.databaseaccess.DAO;
import ru.akorolev.databaseaccess.DAOImplementation;
import ru.akorolev.entities.Teacher;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 05.05.13
 * Time: 12:35
 * To change this template use File | Settings | File Templates.
 */
public class DialogTeacherDataModel {
    private DAO dao = DAOImplementation.getInstance();

    public void saveTeacher(Teacher teacher) {
        dao.saveTeacher(teacher);
    }

    public void editTeacher(Teacher teacher) {
        dao.editTeacher(teacher);
    }
}
