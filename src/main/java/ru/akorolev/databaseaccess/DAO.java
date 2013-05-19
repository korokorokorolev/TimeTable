/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.akorolev.databaseaccess;

import java.util.List;
import ru.akorolev.entities.Auditory;
import ru.akorolev.entities.Cell;
import ru.akorolev.entities.Conflict;
import ru.akorolev.entities.Groups;
import ru.akorolev.entities.Subject;
import ru.akorolev.entities.Teacher;
import ru.akorolev.entities.TrainingFeed;

/**
 *
 * @author alex
 */
public interface DAO {
    public void saveTeacher(Teacher teacher);
    public void removeTeacher(Teacher teacher);
    public void editTeacher(Teacher teacher);
    public List<Teacher> getTeachers();
    public void saveSubject(Subject subject);
    public void removeSubject(Subject subject);
    public void editSubject(Subject subject);
    public List<Auditory> getAuditories();
    public void saveAuditory(Auditory auditory);
    public void removeAuditory(Auditory auditory);
    public List<TrainingFeed> getTrainingFeeds();
    public void saveTrainingFeed(TrainingFeed trainingFeed);
    public void removeTrainingFeed(TrainingFeed trainingFeed);
    public Groups updateGroup(Groups group);
    public boolean haveConflict(Cell cell);
    public Groups getGroupByName(String groupName);
    public void saveCell(Cell cell);
    public void editCell(Cell cell);
    public void deleteConflictsWithCell(Cell cell);
    public List<Cell> getCellsLine(String day, int trainingNum);
    public void saveConflict(Conflict conflict);
    public List getByssyTeachersCh(Cell cell);
    public List getFreedomTeachersCh(Cell cell);
    public List getBussyAuditorinesCh(Cell cell);
    public List getFreedomAuditoriesCh(Cell cell);
    public List getFreedomTeachersZn(Cell cell);
    public List getFreedomAuditoriesZn(Cell cell);
    public List getByssyTeachersZn(Cell cell);
    public List getBussyAuditorinesZn(Cell cell);
    public List<Conflict> getConflicts();
    public List<Conflict> getNoConflicts();
    public void setConflictStatusToNo(Conflict conflict);
    public void setConflictStatusToYes(Conflict conflict);
    public List<Cell> getCellsFromTF(TrainingFeed trainingFeed);
    public List<Cell> getTeachersEmploymentCh(String day, Teacher teacher);
    public List<Cell> getTeachersEmploymentZn(String day, Teacher teacher);
    public List<Cell> getTeachersEmploymentAll(String day, Teacher teacher);
    public List<Cell> getAllCells(Teacher teacher);
    public List<Cell> getAuditoriesEmploymentCh(String day, Auditory auditory);
    public List<Cell> getAuditoriesEmploymentZn(String day, Auditory auditory);
}
