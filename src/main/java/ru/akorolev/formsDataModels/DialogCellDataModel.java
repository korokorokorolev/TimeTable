package ru.akorolev.formsDataModels;

import ru.akorolev.databaseaccess.DAO;
import ru.akorolev.databaseaccess.DAOImplementation;
import ru.akorolev.entities.Cell;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 07.05.13
 * Time: 20:44
 * To change this template use File | Settings | File Templates.
 */
public class DialogCellDataModel {
    DAO dao = DAOImplementation.getInstance();

    public void saveCell(Cell cell) {
        dao.saveCell(cell);
    }
}
