/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.akorolev.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "CONFLICT", catalog = "", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conflict.findAll", query = "SELECT c FROM Conflict c"),
    @NamedQuery(name = "Conflict.findById", query = "SELECT c FROM Conflict c WHERE c.id = :id"),
    @NamedQuery(name = "Conflict.findByStatus", query = "SELECT c FROM Conflict c WHERE c.status = :status")})
public class Conflict implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "STATUS")
    private String status;

    @JoinColumn(name = "CELL_ID_1", referencedColumnName="ID")
    @ManyToOne
    private Cell cell1;
    @JoinColumn(name = "CELL_ID_2", referencedColumnName="ID")
    @ManyToOne
    private Cell cell2;

    public Conflict() {
    }

    public Conflict(Integer id) {
        this.id = id;
    }

    public Conflict(Integer id, String status) {
        this.id = id;
        this.status = status;
    }

    public Conflict(Cell cell1, Cell cell2, String status) {
        this.cell1 = cell1;
        this.cell2 = cell2;
        this.status = status;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Cell getCell1() {
        return cell1;
    }

    public void setCell1(Cell cell1) {
        this.cell1 = cell1;
    }

    public Cell getCell2() {
        return cell2;
    }

    public void setCell2(Cell cell2) {
        this.cell2 = cell2;
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
        if (!(object instanceof Conflict)) {
            return false;
        }
        Conflict other = (Conflict) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return cell1.getDay() + " пара № "+  String.valueOf(cell2.getTrainingNum() +1) + " "
                + "Группа " + cell1.getGroupsId() + " и группа " + cell2.getGroupsId();
    }
    
}
