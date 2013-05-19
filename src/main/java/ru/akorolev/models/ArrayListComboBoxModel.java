/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.akorolev.models;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author alex
 */
public class ArrayListComboBoxModel extends AbstractListModel implements
        ComboBoxModel {

    private Object selectedItem;
    private List anArrayList;

 

    public ArrayListComboBoxModel(List subjectList, boolean withNull) {
        this.anArrayList = new ArrayList(subjectList);
        if(withNull) {
            this.anArrayList.add(0, null);
        }
    }

    public void setAnArrayList(ArrayList anArrayList) {
        this.anArrayList = anArrayList;
    }

    @Override
    public Object getSelectedItem() {
        return selectedItem;
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        super.addListDataListener(l);
    }

    @Override
    public void setSelectedItem(Object newValue) {
        selectedItem = newValue;
    }

    @Override
    public int getSize() {
        return anArrayList.size();
    }

    @Override
    public Object getElementAt(int i) {
        return anArrayList.get(i);
    }
}
