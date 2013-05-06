package ru.akorolev.formsDataModels;

import ru.akorolev.databaseaccess.DAO;
import ru.akorolev.databaseaccess.DAOImplementation;
import ru.akorolev.models.ListModelImplementation;

import javax.swing.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 06.05.13
 * Time: 12:54
 * To change this template use File | Settings | File Templates.
 */
public class DialogAuditoriesDataModel {
    private DAO dao = DAOImplementation.getInstance();

    private List getAuditories() {
        return dao.getAuditories();
    }

    public ListModel getAuditoriesModel() {
        return new ListModelImplementation(getAuditories());
    }
}
