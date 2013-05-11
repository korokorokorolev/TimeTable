package ru.akorolev.formsDataModels;

import ru.akorolev.databaseaccess.DAO;
import ru.akorolev.databaseaccess.DAOImplementation;
import ru.akorolev.entities.Auditory;
import ru.akorolev.entities.Cell;
import ru.akorolev.entities.Teacher;
import ru.akorolev.models.ArrayListComboBoxModel;
import ru.akorolev.models.TableModelImplementation;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 11.05.13
 * Time: 11:50
 * To change this template use File | Settings | File Templates.
 */
public class DialogAuditoriesEmploymentDataModel {
    private DAO dao = DAOImplementation.getInstance();

    private List getAuditories() {
        return dao.getAuditories();
    }

    public ComboBoxModel getAuditoriesModel() {
        return new ArrayListComboBoxModel(getAuditories(), false);
    }

    public TableModel getTableZnModel(String day, Auditory auditory) {
        return new TableModelImplementation(new String[] {"",day},getTableZnData(day, auditory));
    }

    public TableModel getTableChModel(String day, Auditory auditory) {
        return new TableModelImplementation(new String[] {"",day},getTableChData(day, auditory));
    }
    private Object[][] getTableChData(String day, Auditory auditory) {
        Object[][] res = {
                {"1", ""},
                {"2", ""},
                {"3", ""},
                {"4", ""},
                {"5", ""}
        };
        List<Cell> list = dao.getAuditoriesEmploymentCh(day, auditory);
        for (int i = 0; i < 5; i++) {
            res[i][0] = String.valueOf(i + 1);
            for (Cell cell : list) {
                if (cell.getTrainingNum() == i) {
                    String teacher, group;
                    if (cell.getSubject1() != null) {
                        teacher = cell.getSubject1().getTeacherId().toString();
                        group = cell.getGroupsId().toString();
                    } else {
                        teacher = cell.getSubject2().getTeacherId().toString();
                        group = cell.getGroupsId().toString();
                    }
                    if (res[i][1] != null) {
                        res[i][1] = res[i][1] + " " + cell.getGroupsId().getName() + " " + teacher + " " + auditory;
                    } else {
                        res[i][1] = cell.getGroupsId().getName() + " " + teacher + " " + auditory;
                    }
                }
            }
        }
        return res;
    }
    private Object[][] getTableZnData(String day, Auditory auditory) {
        Object[][] res = {
                {"1", ""},
                {"2", ""},
                {"3", ""},
                {"4", ""},
                {"5", ""}
        };
        List<Cell> list = dao.getAuditoriesEmploymentZn(day, auditory);
        for (int i = 0; i < 5; i++) {
            res[i][0] = String.valueOf(i + 1);
            for (Cell cell : list) {
                if (cell.getTrainingNum() == i) {
                    String teacher, group;
                    if (cell.getSubject3() != null) {
                        teacher = cell.getSubject3().getTeacherId().toString();
                        group = cell.getGroupsId().toString();
                    } else {
                        teacher = cell.getSubject4().getTeacherId().toString();
                        group = cell.getGroupsId().toString();
                    }
                    if (res[i][1] != null) {
                        res[i][1] = res[i][1] + " " + cell.getGroupsId().getName() + " " + teacher + " " + auditory;
                    } else {
                        res[i][1] = cell.getGroupsId().getName() + " " + teacher + " " + auditory;
                    }
                }
            }
        }
        return res;
    }
}
