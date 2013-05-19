/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.akorolev.widgets;

import javax.swing.JTable;
import javax.swing.table.TableModel;
import ru.akorolev.models.TableModelImplementation;
import ru.akorolev.models.TextAreaRenderer;

/**
 *
 * @author alex
 */
public class DayTable extends JTable {

    private String day = "";

    public DayTable(TableModelImplementation tModel) {
        super(tModel);
        this.setDefaultRenderer(Object.class, new TextAreaRenderer());
        this.setFillsViewportHeight(true);
        this.setRowSelectionAllowed(false);
        this.setColumnSelectionAllowed(false);
    }

    public DayTable() {
        this.setDefaultRenderer(Object.class, new TextAreaRenderer());
        this.setFillsViewportHeight(true);
        this.setRowSelectionAllowed(false);
        this.setColumnSelectionAllowed(false);
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public void setModel(TableModel dataModel) {
        super.setModel(dataModel);
        if (this.getColumnModel().getColumnCount() > 0) {
            this.getColumnModel().getColumn(0).setMaxWidth(20);
        }
    }
}
