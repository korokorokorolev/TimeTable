/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.akorolev.databaseaccess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
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
public class DAOImplementation implements DAO {

    private static DAOImplementation instance;
    private SessionFactory sessionFactory = null;
    private Session session = null;

    private DAOImplementation() {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    }

    public static DAO getInstance() {
        if (instance == null) {
            instance = new DAOImplementation();
        }
        return instance;
    }

    private void getNewSession() {
        session = sessionFactory.openSession();
    }

    public void close() {
        sessionFactory.close();
    }

    public void saveTeacher(Teacher teacher) {
        if (teacher.getName().isEmpty()) {
            throw new RuntimeException("Empty teacher name");
        }
        getNewSession();
        try {
            session.beginTransaction();
            session.save(teacher);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    public void removeTeacher(Teacher teacher) {
        getNewSession();
        try {
            session.beginTransaction();
            System.out.println(teacher.getSubjectList());
//            Teacher t = (Teacher) session.load(Teacher.class, teacher.getId());
            for(Subject subject : teacher.getSubjectList()) {
                removeSubjectWithoutSession(subject);
            }
            session.delete(teacher);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    public void editTeacher(Teacher teacher) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void saveSubject(Subject subject) {
        getNewSession();
        try {
            session.beginTransaction();
            session.save(subject);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    private void removeSubjectWithoutSession(Subject subject) {
            session.createQuery("delete from Conflict where cell1 in (from Cell where subject1=:idS)").setParameter("idS", subject).executeUpdate();
            session.createQuery("delete from Conflict where cell2 in (from Cell where subject1=:idS)").setParameter("idS", subject).executeUpdate();
            session.createQuery("delete from Conflict where cell1 in (from Cell where subject2=:idS)").setParameter("idS", subject).executeUpdate();
            session.createQuery("delete from Conflict where cell2 in (from Cell where subject2=:idS)").setParameter("idS", subject).executeUpdate();
            session.createQuery("delete from Conflict where cell1 in (from Cell where subject3=:idS)").setParameter("idS", subject).executeUpdate();
            session.createQuery("delete from Conflict where cell2 in (from Cell where subject3=:idS)").setParameter("idS", subject).executeUpdate();
            session.createQuery("delete from Conflict where cell1 in (from Cell where subject4=:idS)").setParameter("idS", subject).executeUpdate();
            session.createQuery("delete from Conflict where cell2 in (from Cell where subject4=:idS)").setParameter("idS", subject).executeUpdate();
            session.createQuery("delete from Cell where subject1=:idS").setParameter("idS", subject).executeUpdate();
            session.createQuery("delete from Cell where subject2=:idS").setParameter("idS", subject).executeUpdate();
            session.createQuery("delete from Cell where subject3=:idS").setParameter("idS", subject).executeUpdate();
            session.createQuery("delete from Cell where subject4=:idS").setParameter("idS", subject).executeUpdate();
            session.delete(subject);
    }

    public void removeSubject(Subject subject) {
        getNewSession();
        try {
            session.beginTransaction();
            session.createQuery("delete from Conflict where cell1 in (from Cell where subject1=:idS)").setParameter("idS", subject).executeUpdate();
            session.createQuery("delete from Conflict where cell2 in (from Cell where subject1=:idS)").setParameter("idS", subject).executeUpdate();
            session.createQuery("delete from Conflict where cell1 in (from Cell where subject2=:idS)").setParameter("idS", subject).executeUpdate();
            session.createQuery("delete from Conflict where cell2 in (from Cell where subject2=:idS)").setParameter("idS", subject).executeUpdate();
            session.createQuery("delete from Conflict where cell1 in (from Cell where subject3=:idS)").setParameter("idS", subject).executeUpdate();
            session.createQuery("delete from Conflict where cell2 in (from Cell where subject3=:idS)").setParameter("idS", subject).executeUpdate();
            session.createQuery("delete from Conflict where cell1 in (from Cell where subject4=:idS)").setParameter("idS", subject).executeUpdate();
            session.createQuery("delete from Conflict where cell2 in (from Cell where subject4=:idS)").setParameter("idS", subject).executeUpdate();
            session.createQuery("delete from Cell where subject1=:idS").setParameter("idS", subject).executeUpdate();
            session.createQuery("delete from Cell where subject2=:idS").setParameter("idS", subject).executeUpdate();
            session.createQuery("delete from Cell where subject3=:idS").setParameter("idS", subject).executeUpdate();
            session.createQuery("delete from Cell where subject4=:idS").setParameter("idS", subject).executeUpdate();
            session.delete(subject);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    public void editSubject(Subject subject) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Teacher> getTeachers() {
        getNewSession();
        List res = session.createQuery("from Teacher").list();
        session.close();
        return res;
    }

    public List<Auditory> getAuditories() {
        getNewSession();
        List res = session.createQuery("from Auditory").list();
        session.close();
        return res;
    }

    public void saveAuditory(Auditory auditory) {
        getNewSession();
        try {
            session.beginTransaction();
            session.save(auditory);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    public void removeAuditory(Auditory auditory) {
        getNewSession();
        try {
            session.beginTransaction();
            //session.delete(auditory);
            session.beginTransaction();
            session.createQuery("delete from Conflict where cell1 in (from Cell where auditory1=:idS)").setParameter("idS", auditory).executeUpdate();
            session.createQuery("delete from Conflict where cell2 in (from Cell where auditory1=:idS)").setParameter("idS", auditory).executeUpdate();
            session.createQuery("delete from Conflict where cell1 in (from Cell where auditory2=:idS)").setParameter("idS", auditory).executeUpdate();
            session.createQuery("delete from Conflict where cell2 in (from Cell where auditory2=:idS)").setParameter("idS", auditory).executeUpdate();
            session.createQuery("delete from Conflict where cell1 in (from Cell where auditory3=:idS)").setParameter("idS", auditory).executeUpdate();
            session.createQuery("delete from Conflict where cell2 in (from Cell where auditory3=:idS)").setParameter("idS", auditory).executeUpdate();
            session.createQuery("delete from Conflict where cell1 in (from Cell where auditory4=:idS)").setParameter("idS", auditory).executeUpdate();
            session.createQuery("delete from Conflict where cell2 in (from Cell where auditory4=:idS)").setParameter("idS", auditory).executeUpdate();
            session.createQuery("delete from Cell where auditory1=:idS").setParameter("idS", auditory).executeUpdate();
            session.createQuery("delete from Cell where auditory2=:idS").setParameter("idS", auditory).executeUpdate();
            session.createQuery("delete from Cell where auditory3=:idS").setParameter("idS", auditory).executeUpdate();
            session.createQuery("delete from Cell where auditory4=:idS").setParameter("idS", auditory).executeUpdate();
            session.delete(auditory);
           // session.getTransaction().commit();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    public List<TrainingFeed> getTrainingFeeds() {
        getNewSession();
        List res = session.createQuery("from TrainingFeed").list();
        session.close();
        return res;
    }

    public void saveTrainingFeed(TrainingFeed trainingFeed) {
        getNewSession();
        try {
            session.beginTransaction();
            session.save(trainingFeed);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    public void removeTrainingFeed(TrainingFeed trainingFeed) {
        getNewSession();
        try {
            session.beginTransaction();
            TrainingFeed t = (TrainingFeed) session.load(TrainingFeed.class, trainingFeed.getId());
            session.delete(t);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public Groups updateGroup(Groups group) {
        getNewSession();
        Groups g = (Groups) session.get(Groups.class, group.getId());
        session.close();
        return g;
    }

    public boolean haveConflict(Cell cell) {
        getNewSession();
        boolean result = !session.createQuery("from Conflict where (cell1=:curr or cell2=:curr) and status='Y'").setParameter("curr", cell).list().isEmpty();
        session.close();
        return result;
    }

    public Groups getGroupByName(String groupName) {
        getNewSession();
        Groups group = (Groups) session.createQuery("from Groups where Name=:name").setParameter("name", groupName).uniqueResult();
        session.close();
        return group;
    }

    public void saveCell(Cell cell) {
        getNewSession();
        try {
            session.beginTransaction();
            session.save(cell);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
            session.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    public void editCell(Cell cell) {
        getNewSession();
        session.beginTransaction();
        session.update(cell);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteConflictsWithCell(Cell cell) {
        getNewSession();
        session.beginTransaction();
        session.createQuery("delete from Conflict where cell1=:curr or cell2=:curr").setParameter("curr", cell).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public List<Cell> getCellsLine(String day, int trainingNum) {
        getNewSession();
        session.beginTransaction();
        Query query = session.createQuery("from Cell where day_=:day and trainingNum=:trainingNum");
        query.setParameter("day", day);
        query.setParameter("trainingNum", trainingNum);
        List res = query.list();
        session.getTransaction().commit();
        session.close();
        return res;
    }

    public void saveConflict(Conflict conflict) {
        getNewSession();
        session.beginTransaction();
        session.save(conflict);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List getByssyTeachersCh(Cell cell) {
        getNewSession();
        session.beginTransaction();
        SQLQuery sQ = session.createSQLQuery("select Teacher.* from Teacher where ID in  (select s.TEACHER_ID from Subject s join Cell c  on (s.ID = c.SUBJECT_ID_1 or s.ID = c.SUBJECT_ID_2) where c.day_=:day and training_num=:tn and GROUPS_ID<>:gn) ");
        sQ.addEntity(Teacher.class);
        sQ.setParameter("day", cell.getDay());
        sQ.setParameter("tn", cell.getTrainingNum());
        sQ.setParameter("gn", cell.getGroupsId());
        List bussyList = sQ.list();
        session.getTransaction().commit();
        session.close();
        return bussyList;
    }

    public List getFreedomTeachersCh(Cell cell) {
        getNewSession();
        session.beginTransaction();
        List teachers = session.createQuery("from Teacher").list();
        session.getTransaction().commit();
        session.close();
        List bussyTeachers = this.getByssyTeachersCh(cell);
        teachers.removeAll(bussyTeachers);
        return teachers;
    }

    public List getBussyAuditorinesCh(Cell cell) {
        getNewSession();
        session.beginTransaction();
        SQLQuery sQ = session.createSQLQuery("select a.id, a.auditory from Auditory a join Cell c on (a.ID = c.AUDITORY_ID_1 or a.ID = c.AUDITORY_ID_2) where c.day_=:day and training_num=:tn and GROUPS_ID<>:gn");
        sQ.addEntity(Auditory.class);
        sQ.setParameter("day", cell.getDay());
        sQ.setParameter("tn", cell.getTrainingNum());
        sQ.setParameter("gn", cell.getGroupsId());
        List bussyList = sQ.list();
        session.getTransaction().commit();
        session.close();
        return bussyList;
    }

    public List getFreedomAuditoriesCh(Cell cell) {
        getNewSession();
        session.beginTransaction();
        List auditories = session.createQuery("from Auditory").list();
        session.getTransaction().commit();
        session.close();
        List bussyAuditories = this.getBussyAuditorinesCh(cell);
        auditories.removeAll(bussyAuditories);
        return auditories;
    }

    public List getFreedomTeachersZn(Cell cell) {
        getNewSession();
        session.beginTransaction();
        List teachers = session.createQuery("from Teacher").list();
        session.getTransaction().commit();
        session.close();
        List bussyTeachers = this.getByssyTeachersZn(cell);
        teachers.removeAll(bussyTeachers);
        return teachers;
    }

    public List getFreedomAuditoriesZn(Cell cell) {
        getNewSession();
        session.beginTransaction();
        List auditories = session.createQuery("from Auditory").list();
        session.getTransaction().commit();
        session.close();
        List bussyAuditories = this.getBussyAuditorinesZn(cell);
        auditories.removeAll(bussyAuditories);
        return auditories;
    }

    public List getByssyTeachersZn(Cell cell) {
        getNewSession();
        session.beginTransaction();
        SQLQuery sQ = session.createSQLQuery("select Teacher.* from Teacher where ID in  (select s.TEACHER_ID from Subject s join Cell c  on (s.ID = c.SUBJECT_ID_3 or s.ID = c.SUBJECT_ID_4) where c.day_=:day and training_num=:tn and GROUPS_ID<>:gn) ");
        sQ.addEntity(Teacher.class);
        sQ.setParameter("day", cell.getDay());
        sQ.setParameter("tn", cell.getTrainingNum());
        sQ.setParameter("gn", cell.getGroupsId());
        List bussyList = sQ.list();
        session.getTransaction().commit();
        session.close();
        return bussyList;
    }

    public List getBussyAuditorinesZn(Cell cell) {
        getNewSession();
        session.beginTransaction();
        SQLQuery sQ = session.createSQLQuery("select a.id, a.auditory from Auditory a join Cell c on (a.ID = c.AUDITORY_ID_3 or a.ID = c.AUDITORY_ID_4) where c.day_=:day and training_num=:tn and GROUPS_ID<>:gn");
        sQ.addEntity(Auditory.class);
        sQ.setParameter("day", cell.getDay());
        sQ.setParameter("tn", cell.getTrainingNum());
        sQ.setParameter("gn", cell.getGroupsId());
        List bussyList = sQ.list();
        session.getTransaction().commit();
        session.close();
        return bussyList;
    }

    public List<Conflict> getConflicts() {
        getNewSession();
        session.beginTransaction();
        List conflictList = session.createQuery("from Conflict where status = 'Y'").list();
        session.getTransaction().commit();
        session.close();
        return conflictList;
    }

    public List<Conflict> getNoConflicts() {
        getNewSession();
        session.beginTransaction();
        List conflictList = session.createQuery("from Conflict where status = 'N'").list();
        session.getTransaction().commit();
        session.close();
        return conflictList;
    }

    public void setConflictStatusToNo(Conflict conflict) {
        getNewSession();
        session.beginTransaction();
        conflict.setStatus("N");
        session.update(conflict);
        session.getTransaction().commit();
        session.close();
    }

    public void setConflictStatusToYes(Conflict conflict) {
        getNewSession();
        session.beginTransaction();
        conflict.setStatus("Y");
        session.update(conflict);
        session.getTransaction().commit();
        session.close();
    }

    public List<Cell> getCellsFromTF(TrainingFeed trainingFeed) {
        getNewSession();
        List res = new ArrayList();
        for (Groups g : trainingFeed.getGroupsList()) {
            Groups group = (Groups) session.load(Groups.class, g.getId());
            res.addAll(group.getCellList());
        }
        session.close();
        return res;
    }

    public List<Cell> getTeachersEmploymentCh(String day, Teacher teacher) {
        getNewSession();
        Teacher t = (Teacher) session.load(Teacher.class, teacher.getId());
        Query q = session.createQuery("from Cell c where c.subject1.teacherId=:teacher and c.day_=:day");
        q.setParameter("day", day);
        q.setParameter("teacher", t);        
        Query q2 = session.createQuery("from Cell c where c.subject2.teacherId=:teacher and c.day_=:day");
        q2.setParameter("day", day);
        q2.setParameter("teacher", t);
        List<Cell> l1 = q.list();
        List<Cell> l2 = q2.list();
        l1.addAll(l2);
        Collection coll = new HashSet(l1);
        List<Cell> list = new ArrayList(coll);
        session.close();
        return list;
    }

    public List<Cell> getAllCells(Teacher teacher) {
        getNewSession();
        Teacher t = (Teacher) session.load(Teacher.class, teacher.getId());
        Query q1 = session.createQuery("from Cell c where c.subject1.teacherId=:teacher");
        q1.setParameter("teacher", t);
        Query q2 = session.createQuery("from Cell c where c.subject2.teacherId=:teacher");
        q2.setParameter("teacher", t);
        Query q3 = session.createQuery("from Cell c where c.subject3.teacherId=:teacher");
        q3.setParameter("teacher", t);
        Query q4 = session.createQuery("from Cell c where c.subject4.teacherId=:teacher");
        q4.setParameter("teacher", t);
        List<Cell> list1 = q1.list();
        List<Cell> list2 = q2.list();
        List<Cell> list3 = q3.list();
        List<Cell> list4 = q4.list();
        list1.addAll(list2);
        list1.addAll(list3);
        list1.addAll(list4);
        Collection coll = new HashSet(list1);
        List<Cell> list = new ArrayList(coll);
        session.close();
        return list;
    }

    public List<Cell> getTeachersEmploymentZn(String day, Teacher teacher) {
        getNewSession();
        Teacher t = (Teacher) session.load(Teacher.class, teacher.getId());
        Query q = session.createQuery("from Cell c where c.subject3.teacherId=:teacher and c.day_=:day");
        q.setParameter("day", day);
        q.setParameter("teacher", t);        
        Query q2 = session.createQuery("from Cell c where c.subject4.teacherId=:teacher and c.day_=:day");
        q2.setParameter("day", day);
        q2.setParameter("teacher", t);
        List<Cell> l1 = q.list();
        List<Cell> l2 = q2.list();
        l1.addAll(l2);
        Collection coll = new HashSet(l1);
        List<Cell> list = new ArrayList(coll);
        session.close();
        return list;
    }

    public List<Cell> getTeachersEmploymentAll(String day, Teacher teacher) {
        getNewSession();
        Teacher t = (Teacher) session.load(Teacher.class, teacher.getId());
        Query q = session.createQuery("from Cell c where c.subject1.teacherId=:teacher and c.day_=:day");
        q.setParameter("day", day);
        q.setParameter("teacher", t);        
        Query q2 = session.createQuery("from Cell c where c.subject2.teacherId=:teacher and c.day_=:day");
        q2.setParameter("day", day);
        q2.setParameter("teacher", t);
        
        Query q3 = session.createQuery("from Cell c where c.subject3.teacherId=:teacher and c.day_=:day");
        q3.setParameter("day", day);
        q3.setParameter("teacher", t);        
        Query q4 = session.createQuery("from Cell c where c.subject4.teacherId=:teacher and c.day_=:day");
        q4.setParameter("day", day);
        q4.setParameter("teacher", t);
        List<Cell> l1 = q.list();
        List<Cell> l2 = q2.list();
        List<Cell> l3 = q3.list();
        List<Cell> l4 = q4.list();
        l1.addAll(l2);
        l1.addAll(l3);
        l1.addAll(l4);
        Collection coll = new HashSet(l1);
        List<Cell> list = new ArrayList(coll);
        session.close();
        return list;
    }

    public List<Cell> getAuditoriesEmploymentCh(String day, Auditory auditory) {
        getNewSession();
        Auditory a = (Auditory) session.load(Auditory.class, auditory.getId());
        Query q = session.createQuery("from Cell c where c.auditory1=:auditory and c.day_=:day");
        q.setParameter("day", day);
        q.setParameter("auditory", a);        
        Query q2 = session.createQuery("from Cell c where c.auditory2=:auditory and c.day_=:day");
        q2.setParameter("day", day);
        q2.setParameter("auditory", a);
        List<Cell> l1 = q.list();
        List<Cell> l2 = q2.list();
        l1.addAll(l2);
        Collection coll = new HashSet(l1);
        List<Cell> list = new ArrayList(coll);
        session.close();
        return list;
    }

    public List<Cell> getAuditoriesEmploymentZn(String day, Auditory auditory) {
        getNewSession();
        Auditory a = (Auditory) session.load(Auditory.class, auditory.getId());
        Query q = session.createQuery("from Cell c where c.auditory3=:auditory and c.day_=:day");
        q.setParameter("day", day);
        q.setParameter("auditory", a);        
        Query q2 = session.createQuery("from Cell c where c.auditory4=:auditory and c.day_=:day");
        q2.setParameter("day", day);
        q2.setParameter("auditory", a);
        List<Cell> l1 = q.list();
        List<Cell> l2 = q2.list();
        l1.addAll(l2);
        Collection coll = new HashSet(l1);
        List<Cell> list = new ArrayList(coll);
        session.close();
        return list;
    }   
    
}
