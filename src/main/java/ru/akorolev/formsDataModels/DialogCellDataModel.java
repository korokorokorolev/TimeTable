package ru.akorolev.formsDataModels;

import ru.akorolev.databaseaccess.DAO;
import ru.akorolev.databaseaccess.DAOImplementation;
import ru.akorolev.entities.Cell;
import ru.akorolev.entities.Conflict;
import ru.akorolev.models.ListModelImplementation;

import javax.swing.*;
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
        System.out.println(cell.getId());
        dao.saveCell(cell);
        System.out.println(cell.getId());
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

    public ListModel getTeachersChFreedomModel(Cell cell) {
        return new ListModelImplementation(getTeachersChFreedom(cell));
    }

    private List getTeachersChFreedom(Cell cell) {
        return dao.getFreedomTeachersCh(cell);
    }

    public ListModel getTeachersChEmplModel(Cell cell) {
        return new ListModelImplementation(getTeachersChEmpl(cell));
    }

    private List getTeachersChEmpl(Cell cell) {
        return dao.getByssyTeachersCh(cell);
    }

    public ListModel getAudChFreedomModel(Cell cell) {
        return new ListModelImplementation(getAudChFreedom(cell));
    }

    private List getAudChFreedom(Cell cell) {
        return dao.getFreedomAuditoriesCh(cell);
    }

    public ListModel getAudChEmplModel(Cell cell) {
        return new ListModelImplementation(getAudChEmpl(cell));
    }

    private List getAudChEmpl(Cell cell) {
        return dao.getBussyAuditorinesCh(cell);
    }

    public ListModel getTeachersZnEmplModel(Cell cell) {
        return new ListModelImplementation(getTeachersZnEmpl(cell));
    }

    private List getTeachersZnEmpl(Cell cell) {
        return dao.getByssyTeachersZn(cell);
    }

    public ListModel getTeachersZnFreedomModel(Cell cell) {
        return new ListModelImplementation(getTeachersZnFreedom(cell));
    }

    private List getTeachersZnFreedom(Cell cell) {
        return dao.getFreedomTeachersZn(cell);
    }

    public ListModel getAudZnEmplModel(Cell cell) {
        return new ListModelImplementation(getAudZnEmpl(cell));
    }

    private List getAudZnEmpl(Cell cell) {
        return dao.getBussyAuditorinesZn(cell);
    }

    public ListModel getAudZnFreedomModel(Cell cell) {
        return new ListModelImplementation(getAudZnFreedom(cell));
    }

    private List getAudZnFreedom(Cell cell) {
        return dao.getFreedomAuditoriesZn(cell);
    }

    public String getStatusCurrentSituation(Cell cellCopy) {
        List<Cell> potentialConflicts = dao.getCellsLine(cellCopy.getDay(), cellCopy.getTrainingNum());
        String res = "";
        for (Cell cell : potentialConflicts) {
            if (cell.equalsCell(cellCopy)) {
                res += cell.getGroupsId().toString() + "; ";
                System.out.println(cell);
            }
        }
        if(res.equals("")) {
            return "Конфликтных ситуаций не выявлено";
        }
            return "Существует конфликт с группами: " +  res;
        }
    }
