package com.hnit.compoent;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.Vector;

/**
 * ClassName: setBackground
 * Description:
 *
 * @Author wzq
 * @Create 2024/6/14 11:21
 * @Version 1.0
 */
public class setBackground {
    public setBackground(JTable table, Vector<Object>title) {
        DefaultTableCellRenderer ter = new DefaultTableCellRenderer()
        {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (row % 2 == 0)
                    setBackground(new Color(221, 231, 255));
                else if (row % 2 == 1)
                    setBackground(Color.white);
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };
        for (int i = 0; i < title.size(); i++) {
            table.getColumn(title.get(i)).setCellRenderer(ter);
        }
    }
}
