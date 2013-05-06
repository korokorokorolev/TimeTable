package ru.akorolev.formsDataModels;

import ru.akorolev.databaseaccess.DAO;
import ru.akorolev.databaseaccess.DAOImplementation;
import ru.akorolev.entities.Subject;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 05.05.13
 * Time: 13:57
 * To change this template use File | Settings | File Templates.
 */
public class DialogAddSubjectDataModel {
    private DAO dao = DAOImplementation.getInstance();

    public void saveSubject(Subject subject) {
        dao.saveSubject(subject);
    }
}
