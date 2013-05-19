/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.akorolev.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "CELL", catalog = "", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cell.findAll", query = "SELECT c FROM Cell c"),
    @NamedQuery(name = "Cell.findById", query = "SELECT c FROM Cell c WHERE c.id = :id"),
    @NamedQuery(name = "Cell.findByDay", query = "SELECT c FROM Cell c WHERE c.day_ = :day_"),
    @NamedQuery(name = "Cell.findByTrainingNum", query = "SELECT c FROM Cell c WHERE c.trainingNum = :trainingNum")})
public class Cell implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "DAY_")
    private String day_;
    @Basic(optional = false)
    @Column(name = "TRAINING_NUM")
    private int trainingNum;
    @JoinColumn(name = "GROUPS_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Groups groupsId;
    
    @JoinColumn(name = "SUBJECT_ID_1", referencedColumnName = "ID")
    @ManyToOne
    @NotFound(action= NotFoundAction.IGNORE)
    private Subject subject1;
    @JoinColumn(name = "SUBJECT_ID_2", referencedColumnName = "ID")
    @ManyToOne
    @NotFound(action= NotFoundAction.IGNORE)
    private Subject subject2;
    @JoinColumn(name = "SUBJECT_ID_3", referencedColumnName = "ID")
    @ManyToOne
    @NotFound(action= NotFoundAction.IGNORE)
    private Subject subject3;
    @JoinColumn(name = "SUBJECT_ID_4", referencedColumnName = "ID")
    @ManyToOne
    @NotFound(action= NotFoundAction.IGNORE)
    private Subject subject4;
    
    @JoinColumn(name = "AUDITORY_ID_1", referencedColumnName = "ID")
    @ManyToOne
    private Auditory auditory1;
    @JoinColumn(name = "AUDITORY_ID_2", referencedColumnName = "ID")
    @ManyToOne
    private Auditory auditory2;
    @JoinColumn(name = "AUDITORY_ID_3", referencedColumnName = "ID")
    @ManyToOne
    private Auditory auditory3;
    @JoinColumn(name = "AUDITORY_ID_4", referencedColumnName = "ID")
    @ManyToOne
    private Auditory auditory4;
    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cell1")
    private List<Conflict> conflictList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cell2")
    private List<Conflict> conflictList2;

    public Cell() {
    }

    public Cell(Integer id) {
        this.id = id;
    }

    public Cell(Integer id, String day, int trainingNum) {
        this.id = id;
        this.day_ = day;
        this.trainingNum = trainingNum;
    }

    public Cell(Cell cell) {
        if(cell.getId() != null) {
            this.setId(cell.getId());
        }
        this.setSubject1(cell.getSubject1());
        this.setSubject2(cell.getSubject2());
        this.setSubject3(cell.getSubject3());
        this.setSubject4(cell.getSubject4());
        this.setAuditory1(cell.getAuditory1());
        this.setAuditory2(cell.getAuditory2());
        this.setAuditory3(cell.getAuditory3());
        this.setAuditory4(cell.getAuditory4());
        this.setDay(cell.getDay());
        this.setTrainingNum(cell.getTrainingNum());
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDay() {
        return day_;
    }

    public void setDay(String day) {
        this.day_ = day;
    }

    public int getTrainingNum() {
        return trainingNum;
    }

    public void setTrainingNum(int trainingNum) {
        this.trainingNum = trainingNum;
    }

    public Groups getGroupsId() {
        return groupsId;
    }

    public void setGroupsId(Groups groupsId) {
        this.groupsId = groupsId;
    }

    public String getDay_() {
        return day_;
    }

    public void setDay_(String day_) {
        this.day_ = day_;
    }

    public Subject getSubject1() {
        return subject1;
    }

    public void setSubject1(Subject subject1) {
        this.subject1 = subject1;
    }

    public Subject getSubject2() {
        return subject2;
    }

    public void setSubject2(Subject subject2) {
        this.subject2 = subject2;
    }

    public Subject getSubject3() {
        return subject3;
    }

    public void setSubject3(Subject subject3) {
        this.subject3 = subject3;
    }

    public Subject getSubject4() {
        return subject4;
    }

    public void setSubject4(Subject subject4) {
        this.subject4 = subject4;
    }

    public Auditory getAuditory1() {
        return auditory1;
    }

    public void setAuditory1(Auditory auditory1) {
        this.auditory1 = auditory1;
    }

    public Auditory getAuditory2() {
        return auditory2;
    }

    public void setAuditory2(Auditory auditory2) {
        this.auditory2 = auditory2;
    }

    public Auditory getAuditory3() {
        return auditory3;
    }

    public void setAuditory3(Auditory auditory3) {
        this.auditory3 = auditory3;
    }

    public Auditory getAuditory4() {
        return auditory4;
    }

    public void setAuditory4(Auditory auditory4) {
        this.auditory4 = auditory4;
    }

    public List<Conflict> getConflictList1() {
        return conflictList1;
    }

    public void setConflictList1(List<Conflict> conflictList1) {
        this.conflictList1 = conflictList1;
    }

    public List<Conflict> getConflictList2() {
        return conflictList2;
    }

    public void setConflictList2(List<Conflict> conflictList2) {
        this.conflictList2 = conflictList2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cell)) {
            return false;
        }
        Cell other = (Cell) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public boolean equalsCell(Cell other) {
        if(this.getId().equals(other.getId())) {
            return false;
        }
        if(this.auditory1 != null) {
            if(this.auditory1.equals(other.getAuditory1())
                || this.auditory1.equals(other.getAuditory2())) {
                return true;
            }
        }
        if(this.auditory2 != null) {
            if(this.auditory2.equals(other.getAuditory1())
                || this.auditory2.equals(other.getAuditory2())) {
                return true;
            }
        }
        
        if(this.auditory3 != null) {
            if(this.auditory3.equals(other.getAuditory3())
                || this.auditory3.equals(other.getAuditory4())) {
                return true;
            }
        }
        
        if(this.auditory4 != null) {
            if(this.auditory4.equals(other.getAuditory3())
                || this.auditory4.equals(other.getAuditory4())) {
                return true;
            }
        }
        
        if(this.subject1 != null) {
            if(this.subject1.equals(other.getSubject1())
                || this.subject1.equals(other.getSubject2())) {
                return true;
            }
        }
        
        if(this.subject2 != null) {
            if(this.subject2.equals(other.getSubject1())
                || this.subject2.equals(other.getSubject2())) {                
                return true;
            }
        }
        
        if(this.subject3 != null) {
            if(this.subject3.equals(other.getSubject3())
                || this.subject3.equals(other.getSubject4())) {
                return true;
            }
        }
        if(this.subject4 != null) {
            if(this.subject4.equals(other.getSubject3())
                || this.subject4.equals(other.getSubject4())) {
                return true;
            }
        }
        return false;
    }
    
    private String stringFormater() {
        //У всей группы одно занятие
        
        if(subject1 != null && subject2 != null && subject3 != null && subject4 != null) {
            if(subject1.equals(subject2) &&
                    subject2.equals(subject3)&&
                    subject3.equals(subject4)) {
                if(auditory1 == auditory2 && auditory2 == auditory3 && auditory3 == auditory4) {
                    return subject1.getTeacherId()+ " "+ subject1  + " " + auditory1;
                }
            }
        }
        //Одно по числителю и другое по знаменателю
        if(subject1 != null && subject2 != null && subject3 != null && subject4 != null) {
            if(subject1.equals(subject2) &&
                    subject3.equals(subject4)) {
                if(auditory1 == auditory2 && auditory3 == auditory4) {
                    return subject1.getTeacherId()+ " "+ subject1  + " " + auditory1 + " // "
                            + subject3.getTeacherId()+ " "+ subject3  + " " + auditory3;
                }
            }
        }
        //Одно у первой подгруппы и другое у второй
        if(subject1 != null && subject3 != null && subject2 != null && subject4 != null) {
            if(subject1.equals(subject3) &&
                    subject2.equals(subject4)) {
                if(auditory1 == auditory3 && auditory2 == auditory4) {
                    return subject1.getTeacherId()+ " "+ subject1  + " " + auditory1 + "|"
                            + subject2.getTeacherId()+ " "+ subject2  + " " + auditory2;
                }
            }
        }
        //только у первой подгруппы занятие
        if(subject1 != null && subject3 != null && subject2 == null && subject4 == null) {
            if(subject1.equals(subject3)) {
                if(auditory1 == auditory3) {
                    return subject1.getTeacherId()+ " "+ subject1  + " " + auditory1 + " |";
                }
            }
        }
        //только у второй подгруппы занятие
        if(subject2 != null && subject4 != null && subject1 == null && subject3 == null) {
            if(subject2.equals(subject4)) {
                if(auditory2 == auditory4) {
                    return " | " +subject2.getTeacherId()+ " "+ subject2  + " " + auditory2;
                }
            }
        }
        //одно занятие только по числителю
        if(subject1 != null && subject2 != null && subject3 == null && subject4 == null) {
            if(subject1.equals(subject2)) {
                if(auditory1 == auditory2) {
                    return subject1.getTeacherId()+ " "+ subject1  + " " + auditory1 + " /";
                }
            }
        } 
        //занятие только по знаменателю
        if(subject3 != null && subject4 != null && subject1 == null && subject2 == null) {
            if(subject3.equals(subject4)) {
                if(auditory3 == auditory4) {
                    return " / "+ subject3.getTeacherId()+ " "+ subject3  + " " + auditory3;
                }
            }
        }
        //всё пусто
        if(subject1 == null && subject2 == null && subject3 == null && subject4 == null) {
            return "";
        }
        String str1 = "";
        String str2 = " | ";
        String str3 = " / ";
        String str4 = " | ";
        if (subject2 != null) {
            str2 = " | " + subject2.getTeacherId() + " " + subject2 + " " + auditory2 + "\n";
        }
        if (subject3 != null) {
            str3 = " / " + subject3.getTeacherId()+ " " + subject3 + " " + auditory3;
        }
        if (subject4 != null) {
            str4 = " | " + subject4.getTeacherId() + " " + subject4 + " " + auditory4;
        }
        if(subject1 != null) {
            str1 = subject1.getTeacherId() + " " + subject1 + " " + auditory1;
        }
        String res = str1+str2+str3+str4;
        //разношерстное занятие
        return res;
    }
    
    @Override
    public String toString() {
        return stringFormater();
    }
    
}
