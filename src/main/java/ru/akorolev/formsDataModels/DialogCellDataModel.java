package ru.akorolev.formsDataModels;

import ru.akorolev.databaseaccess.DAO;
import ru.akorolev.databaseaccess.DAOImplementation;
import ru.akorolev.entities.Cell;
import ru.akorolev.entities.Conflict;

import java.util.List;

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
        findAndSetupConflicts(cell);
    }

    private void findAndSetupConflicts(Cell currCell) {
        dao.deleteConflictsWithCell(currCell);
        List<Cell> potentialConflicts = dao.getCellsLine(currCell.getDay(), currCell.getTrainingNum());
        for(Cell cell : potentialConflicts) {
            if(cell.equalsCell(currCell)) {
                Conflict conflict = new Conflict(cell, currCell, "Y");
                dao.saveConflict(conflict);
            }
        }
    }

    public void editCell(Cell cell) {
        dao.editCell(cell);
        findAndSetupConflicts(cell);
    }
}
