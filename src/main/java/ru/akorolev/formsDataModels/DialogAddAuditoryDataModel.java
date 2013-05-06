package ru.akorolev.formsDataModels;

import ru.akorolev.databaseaccess.DAO;
import ru.akorolev.databaseaccess.DAOImplementation;
import ru.akorolev.entities.Auditory;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 06.05.13
 * Time: 13:19
 * To change this template use File | Settings | File Templates.
 */
public class DialogAddAuditoryDataModel {
    private DAO dao = DAOImplementation.getInstance();

    public void saveAuditory(Auditory auditory) {
        dao.saveAuditory(auditory);
    }
}
