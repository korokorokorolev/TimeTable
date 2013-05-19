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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "AUDITORY", catalog = "", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Auditory.findAll", query = "SELECT a FROM Auditory a"),
    @NamedQuery(name = "Auditory.findById", query = "SELECT a FROM Auditory a WHERE a.id = :id"),
    @NamedQuery(name = "Auditory.findByAuditory", query = "SELECT a FROM Auditory a WHERE a.auditory = :auditory")})
public class Auditory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "AUDITORY",unique=true)
    private String auditory;
    @OneToMany(mappedBy = "auditory1")
    private List<Cell> cellList1;
    @OneToMany(mappedBy = "auditory2")
    private List<Cell> cellList2;
    @OneToMany(mappedBy = "auditory3")
    private List<Cell> cellList3;
    @OneToMany(mappedBy = "auditory4")
    private List<Cell> cellList4;

    public Auditory() {
    }

    public Auditory(Integer id) {
        this.id = id;
    }

    public Auditory(Integer id, String auditory) {
        this.id = id;
        this.auditory = auditory;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuditory() {
        return auditory;
    }

    public void setAuditory(String auditory) {
        this.auditory = auditory;
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
        if (!(object instanceof Auditory)) {
            return false;
        }
        Auditory other = (Auditory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
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
    public String toString() {
        return this.auditory;
    }
    
}
