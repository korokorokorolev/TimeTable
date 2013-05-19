/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.akorolev.models;

import java.util.List;
import javax.swing.event.ListDataListener;

/**
 *
 * @author alex
 */
public class ListModelImplementation implements javax.swing.ListModel {

    private List list;

    public ListModelImplementation(List list) {
        this.list = list;
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public Object getElementAt(int index) {
        return list.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
    }    
}
