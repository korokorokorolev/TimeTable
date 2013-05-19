/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.akorolev.models;

import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author alex
 */
public class TextAreaRenderer extends JTextArea implements TableCellRenderer {

    private final DefaultTableCellRenderer adaptee = new DefaultTableCellRenderer();
    private Object couples[];
    private Color color = Color.white;

    public TextAreaRenderer() {
        setLineWrap(true);
        setWrapStyleWord(true);
    }

    public TextAreaRenderer(Object couples[], Color color) {
        setLineWrap(true);
        setWrapStyleWord(true);
        this.couples = couples;
        this.color = color;
    }
    int last_row = -1;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object obj, boolean isSelected, boolean hasFocus, int row, int column) {
        adaptee.getTableCellRendererComponent(table, obj, isSelected, hasFocus, row, column);
        setForeground(adaptee.getForeground());
        setBackground(adaptee.getBackground());
        setBorder(adaptee.getBorder());
        setFont(adaptee.getFont());
        setText(adaptee.getText());

        setText(obj == null ? "" : obj.toString());

        Rectangle rect = table.getCellRect(row, column, true);
        this.setSize(rect.getSize());//для установки ширины компоненты
        int height_wanted = (int) getPreferredSize().getHeight();
        if ((height_wanted > table.getRowHeight(row) | row != last_row) & //если новая строчка и полученная высота больше чем установленна
                height_wanted > table.getRowHeight()) {
            table.setRowHeight(row, height_wanted);
        }

        last_row = row;
        if (couples != null) {
            for (int i = 0; i < couples.length; i++) {
                if (row == ((Couple)couples[i]).getRow() && column == ((Couple)couples[i]).getCol()) {
                    this.setBackground(color);
                    return this;
                } else {
                    this.setBackground(Color.white);
                }
            }
        }

        return this;
    }
}
