package ru.akorolev.formsDataModels;

import ru.akorolev.databaseaccess.DAO;
import ru.akorolev.databaseaccess.DAOImplementation;
import ru.akorolev.entities.Cell;
import ru.akorolev.entities.Groups;
import ru.akorolev.entities.TrainingFeed;
import ru.akorolev.models.Couple;
import ru.akorolev.models.TableModelImplementation;
import ru.akorolev.models.TextAreaRenderer;

import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 07.05.13
 * Time: 11:24
 * To change this template use File | Settings | File Templates.
 */
public class DialogRaspisanieDataModel {
    private DAO dao = DAOImplementation.getInstance();
    private OnModelChangeListener listener;

    public void setListener(OnModelChangeListener listener) {
        this.listener = listener;
    }

    public TableModel getDayTableModel(String day, TrainingFeed trainingFeed) {
        TableModelImplementation tableModel = new TableModelImplementation(trainingFeed.getGroupsNames());
        for(Groups groupTmp : trainingFeed.getGroupsList()) {
            Groups group = dao.updateGroup(groupTmp);
            for(Cell cell : group.getCellList()) {
                if (cell.getDay().equals(day)) {
                    tableModel.setValueAt(cell, cell.getTrainingNum(), groupNum(cell, trainingFeed));
                }
            }
        }
        return tableModel;
    }
    private int groupNum(Cell cell, TrainingFeed trainingFeed) {
        int gn = -1;
        for (int j = 0; j < trainingFeed.getGroupsNames().length; j++) {
            if (cell.getGroupsId().getName().equals(trainingFeed.getGroupsNames()[j])) {
                gn = j;
            }
        }
        return gn;
    }

    public TableCellRenderer getRenderer(String day, TrainingFeed trainingFeed) {
        List<Couple> list = new ArrayList();
        for (Groups groupTmp : trainingFeed.getGroupsList()) {
            Groups group = dao.updateGroup(groupTmp);
            for (Cell cell : group.getCellList()) {
                if (cell.getDay().equals(day)) {
                    if (dao.haveConflict(cell)) {
                        list.add(new Couple(cell.getTrainingNum(), groupNum(cell, trainingFeed)));
                    }
                }
            }
        }
        return new TextAreaRenderer(list.toArray(), Color.red);
    }

    public Groups getGroup(String grName) {
        return dao.getGroupByName(grName);
    }

    public List getCellsFromTF(TrainingFeed trainingFeed) {
        return dao.getCellsFromTF(trainingFeed);
    }

    public interface OnModelChangeListener {
        public void onCellEdit();
        public void onCellAdded();
    }
}
