/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.akorolev.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "TRAINING_FEED", catalog = "", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TrainingFeed.findAll", query = "SELECT t FROM TrainingFeed t"),
    @NamedQuery(name = "TrainingFeed.findById", query = "SELECT t FROM TrainingFeed t WHERE t.id = :id"),
    @NamedQuery(name = "TrainingFeed.findByName", query = "SELECT t FROM TrainingFeed t WHERE t.name = :name")})
public class TrainingFeed implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "NAME",unique=true)
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainingFeedId", fetch = FetchType.EAGER)
    private List<Groups> groupsList;

    public TrainingFeed() {
        groupsList = new ArrayList<Groups>();
    }

    public TrainingFeed(Integer id) {
        this.id = id;
    }

    public TrainingFeed(Integer id, String name) {
        this.id = id;
        this.name = name;
        groupsList = new ArrayList<Groups>();
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

    @XmlTransient
    public List<Groups> getGroupsList() {
        return groupsList;
    }

    public void setGroupsList(List<Groups> groupsList) {
        this.groupsList = groupsList;
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
        if (!(object instanceof TrainingFeed)) {
            return false;
        }
        TrainingFeed other = (TrainingFeed) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    private void remove(Groups group) {
        for(int i = 0; i < groupsList.size(); i++) {
            if(groupsList.get(i).getName().equals(group.getName())) {
                groupsList.get(i).setTrainingFeedId(null);
                groupsList.remove(i);
                return;
            }
        }
    }

    public void deleteGroup(Groups group) {       
        remove(group);
    }
    

    public void addGroup(Groups group) {
        if(group.getTrainingFeedId() != null) {
            group.getTrainingFeedId().groupsList.remove(group);
        }
        groupsList.add(group);
        group.setTrainingFeedId(this);
    }
    
    public String[] getGroupsNames() {
        String grNames[] = new String[groupsList.size() + 1];
        grNames[0] = "";
        for(int i = 0; i < groupsList.size(); i++) {
            grNames[i+1] = groupsList.get(i).getName();
        }
        return grNames;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
}
