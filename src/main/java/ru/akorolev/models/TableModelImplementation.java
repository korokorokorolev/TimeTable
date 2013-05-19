/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.akorolev.models;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alex
 */
public class TableModelImplementation extends AbstractTableModel{

    private String[] columnNames = {"", "ИВТ", "ИВТ", "ИВТ"};
    private Object[][] data = {
        {"1", "", "", ""},
        {"2", "", "", ""},
        {"3", "", "", ""},
        {"4", "", "", ""},
        {"5", "", "", ""}
    };
    
    public TableModelImplementation() {
        
    }
    public TableModelImplementation(String[] columnNames, Object[][] data) {
        this.columnNames = columnNames;
        this.data = data;
        fireTableDataChanged();
    }
    
    public TableModelImplementation(String[] columnNames) {
        this.columnNames = columnNames;
        fireTableDataChanged();
    }
    
    public String[] getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }

    public Object[][] getData() {
        return data;
    }

    public void setData(Object[][] data) {
        this.data = data;
    }
    
    
    
    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }
    
    @Override
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
    
     @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }
    
}
