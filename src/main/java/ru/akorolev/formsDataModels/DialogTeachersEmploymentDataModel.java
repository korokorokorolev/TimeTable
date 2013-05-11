package ru.akorolev.formsDataModels;

import ru.akorolev.databaseaccess.DAO;
import ru.akorolev.databaseaccess.DAOImplementation;
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
 * Date: 10.05.13
 * Time: 11:55
 * To change this template use File | Settings | File Templates.
 */
public class DialogTeachersEmploymentDataModel {
    private DAO dao = DAOImplementation.getInstance();

    public ComboBoxModel getTeachersModel() {
        return new ArrayListComboBoxModel(getTeachers(),false);
    }

    private List getTeachers() {
        return dao.getTeachers();
    }

    public TableModel getTableChModel(String day, Teacher teacher) {
        return new TableModelImplementation(new String[] {"",day}, getTableChData(day, teacher));
    }

    public List<Cell> getAllCells(Teacher teacher) {
        return dao.getAllCells(teacher);
    }

    private Object[][] getTableChData(String day, Teacher teacher) {
        Object[][] res = {
                {"1", ""},
                {"2", ""},
                {"3", ""},
                {"4", ""},
                {"5", ""}
        };
        List<Cell> list = dao.getTeachersEmploymentCh(day, teacher);
        for (int i = 0; i < 5; i++) {
            res[i][0] = String.valueOf(i + 1);
            for (Cell cell : list) {
                if (cell.getTrainingNum() == i) {
                    String subject, auditory;
                    if (cell.getSubject1() != null) {
                        subject = cell.getSubject1().toString();
                        auditory = cell.getAuditory1().toString();
                    } else {
                        subject = cell.getSubject2().toString();
                        auditory = cell.getAuditory2().toString();
                    }
                    if (res[i][1] != null) {
                        res[i][1] = res[i][1] + " " + cell.getGroupsId().getName() + " " + subject + " " + auditory;
                    } else {
                        res[i][1] = cell.getGroupsId().getName() + " " + subject + " " + auditory;
                    }
                }
            }
        }
        return res;
    }

    public TableModel getTableZnModel(String day, Teacher teacher) {
        return new TableModelImplementation(new String[] {"",day}, getTableZnData(day, teacher));
    }

    private Object[][] getTableZnData(String day, Teacher teacher) {
        Object[][] res = {
                {"1", ""},
                {"2", ""},
                {"3", ""},
                {"4", ""},
                {"5", ""}
        };
        List<Cell> list = dao.getTeachersEmploymentZn(day, teacher);
        for (int i = 0; i < 5; i++) {
            res[i][0] = String.valueOf(i + 1);
            for (Cell cell : list) {
                if (cell.getTrainingNum() == i) {
                    String subject, auditory;
                    if (cell.getSubject3() != null) {
                        subject = cell.getSubject3().toString();
                        auditory = cell.getAuditory3().toString();
                    } else {
                        subject = cell.getSubject4().toString();
                        auditory = cell.getAuditory4().toString();
                    }
                    if (res[i][1] != null) {
                        res[i][1] = res[i][1] + " " + cell.getGroupsId().getName() + " " + subject + " " + auditory;
                    } else {
                        res[i][1] = cell.getGroupsId().getName() + " " + subject + " " + auditory;
                    }
                }
            }
        }
        return res;
    }

    public TableModel getTableAllModel(String day, Teacher teacher) {
        return new TableModelImplementation(new String[] {"",day}, getTableAllData(day, teacher));
    }
    private Object[][] getTableAllData(String day, Teacher teacher) {
        Object[][] res = {
                {"1", ""},
                {"2", ""},
                {"3", ""},
                {"4", ""},
                {"5", ""}
        };
        List<Cell> list = dao.getTeachersEmploymentAll(day, teacher);
        for (int i = 0; i < 5; i++) {
            res[i][0] = String.valueOf(i + 1);
            for (Cell cell : list) {
                if (cell.getTrainingNum() == i) {

                    if (res[i][1] != null) {
                        res[i][1] = res[i][1] + " " + cell.getGroupsId().getName() + " " + cell;
                    } else {
                        res[i][1] = cell.getGroupsId().getName() + " " + cell;
                    }
                }
            }
        }
        return res;
    }
}
