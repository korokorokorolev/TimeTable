package ru.akorolev.formsDataModels;

import ru.akorolev.databaseaccess.DAO;
import ru.akorolev.databaseaccess.DAOImplementation;
import ru.akorolev.entities.Conflict;
import ru.akorolev.models.ListModelImplementation;

import javax.swing.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 09.05.13
 * Time: 20:14
 * To change this template use File | Settings | File Templates.
 */
public class DialogConflictsDataModel {
    private DAO dao = DAOImplementation.getInstance();
    private OnChangeListener listener;

    public void setListener(OnChangeListener listener) {
        this.listener = listener;
    }

    public ListModel getConflictsModel() {
        return new ListModelImplementation(getConflicts());
    }

    private List getConflicts() {
        return dao.getConflicts();
    }

    public ListModel getNoConflictsModel() {
        return new ListModelImplementation(getNoConflicts());
    }

    private List getNoConflicts() {
        return dao.getNoConflicts();
    }

    public void setStatusToNo(Conflict conflict) {
        dao.setConflictStatusToNo(conflict);
        if(this.listener != null) {
            listener.onChangeStatus();
        }
    }
    public void setStatusToYes(Conflict conflict) {
        dao.setConflictStatusToYes(conflict);
        if(this.listener != null) {
            listener.onChangeStatus();
        }
    }

    public interface OnChangeListener {
        public void onChangeStatus();
    }
}
