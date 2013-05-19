/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.akorolev.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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

/**
 *
 * @author alex
 */
@Entity
@Table(name = "SUBJECT", catalog = "", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subject.findAll", query = "SELECT s FROM Subject s"),
    @NamedQuery(name = "Subject.findById", query = "SELECT s FROM Subject s WHERE s.id = :id"),
    @NamedQuery(name = "Subject.findByName", query = "SELECT s FROM Subject s WHERE s.name = :name"),
    @NamedQuery(name = "Subject.findByFullName", query = "SELECT s FROM Subject s WHERE s.fullName = :fullName")})
public class Subject implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @Column(name = "FULL_NAME")
    private String fullName;
    @JoinColumn(name = "TEACHER_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Teacher teacherId;
    
    @OneToMany(cascade = javax.persistence.CascadeType.ALL,mappedBy = "subject1")
    private List<Cell> cellList1;
    @OneToMany(cascade = javax.persistence.CascadeType.ALL,mappedBy = "subject2")
    private List<Cell> cellList2;
    @OneToMany(cascade = javax.persistence.CascadeType.ALL,mappedBy = "subject3")
    private List<Cell> cellList3;
    @OneToMany(cascade = javax.persistence.CascadeType.ALL,mappedBy = "subject4")
    private List<Cell> cellList4;

    public Subject() {
    }

    public Subject(Integer id) {
        this.id = id;
    }

    public Subject(Integer id, String name, String fullName) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Teacher getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Teacher teacherId) {
        this.teacherId = teacherId;
    }

    public List<Cell> getCellList1() {
        return cellList1;
    }

    public void setCellList1(List<Cell> cellList1) {
        this.cellList1 = cellList1;
    }

    public List<Cell> getCellList2() {
        return cellList2;
    }

    public void setCellList2(List<Cell> cellList2) {
        this.cellList2 = cellList2;
    }

    public List<Cell> getCellList3() {
        return cellList3;
    }

    public void setCellList3(List<Cell> cellList3) {
        this.cellList3 = cellList3;
    }

    public List<Cell> getCellList4() {
        return cellList4;
    }

    public void setCellList4(List<Cell> cellList4) {
        this.cellList4 = cellList4;
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
        if (!(object instanceof Subject)) {
            return false;
        }
        Subject other = (Subject) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
