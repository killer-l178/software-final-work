package com.hnit.entity;

import com.hnit.DAO.CarDAO;
import com.hnit.compoent.MyListener;
import com.hnit.compoent.setBackground;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * ClassName: Salary
 * Description:
 *
 * @Author wzq
 * @Create 2024/6/16 10:20
 * @Version 1.0
 */
//Salary
public class Salary  extends Box implements MyListener {
    private JTable table;
    private Vector<Object> title;//标题
    private Vector<Vector<Object>> Dates;//数据
    private DefaultTableModel tableModel;

    @Override
    public void onDataAdded() {
        tableModel.setDataVector(Dates, title);
        table.setModel(tableModel);
        new setBackground(table,title);
    }

    public Salary (JFrame jf) {
        super(BoxLayout.Y_AXIS);//垂直布局

        JPanel panelBtn = new JPanel();
        panelBtn.setBackground(new Color(255,255,255));
        panelBtn.setMaximumSize(new Dimension(850, 75));
        //设置右对齐
        panelBtn.setLayout(new FlowLayout(FlowLayout.RIGHT));
        //按钮组件

        Button printAllButton = new Button("查询所有员工工资");
        Button findButton = new Button("查询指定员工工资");

        panelBtn.add(printAllButton);
        panelBtn.add(findButton);

        this.add(panelBtn);


        title = new Vector<Object>();
        title.add("工号");
        title.add("姓名");
        title.add("基本工资");
        title.add("绩效");

        //Dates = null;
        tableModel = new DefaultTableModel(Dates, title);
        table = new JTable(tableModel)
        {
            //不可修改
            @Override
            public boolean isCellEditable(int row ,int column){
                return false;
            }
            @Override
            public void setRowHeight(int rowHeight) {
                super.setRowHeight(25);
            }
            @Override
            public void setFont(Font font) {
                super.setFont(new Font("宋体",Font.BOLD,15));
            }
        };
        //表格标题设置
        JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 35));// 设置表头大小
        head.setFont(new Font("宋体",Font.BOLD,16));//设置表头字体
        // 设置表格间隔色
        new setBackground(table,title);
        //仅能选中一行
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane jScrollPane = new JScrollPane(table);
        this.add(jScrollPane);

        //查询所有
        printAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CarDAO().update();
                Dates = new CarDAO().Fetch("Salary");
                tableModel.setDataVector(Dates, title);
                table.setModel(tableModel);
                new setBackground(table,title);
            }
        });
        //查询指定
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "select *from Salary where EmployeeName like ?";
                String input = JOptionPane.showInputDialog(jf, "请输入查询的姓名：", JOptionPane.INFORMATION_MESSAGE);
                new CarDAO().update();
                Dates = new CarDAO().find(sql, input);
                tableModel.setDataVector(Dates, title);
                table.setModel(tableModel);
                new setBackground(table,title);
            }
        });
    }

}
