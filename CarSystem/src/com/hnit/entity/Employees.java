package com.hnit.entity;

import com.hnit.DAO.CarDAO;
import com.hnit.DAO.EmployeesOperate;
import com.hnit.compoent.MyListener;
import com.hnit.compoent.setBackground;
import com.hnit.view.EmployeesDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * ClassName: Employees
 * Description:
 *
 * @Author wzq
 * @Create 2024/6/9 17:30
 * @Version 1.0
 */
public class Employees extends Box implements MyListener {
    private JTable table;
    private Vector<Object> title;//标题
    private Vector<Vector<Object>> Dates;//数据
    private DefaultTableModel tableModel;

    @Override
    public void onDataAdded() {
        // 数据已添加，重新加载数据并刷新表格
        Dates = new CarDAO().Fetch("Employee");
        tableModel.setDataVector(Dates, title);
        table.setModel(tableModel);
        new setBackground(table,title);
    }
    public Employees(JFrame jf) {
        super(BoxLayout.Y_AXIS);//垂直布局

        JPanel panelBtn = new JPanel();
        panelBtn.setBackground(new Color(255,255,255));
        panelBtn.setMaximumSize(new Dimension(850, 75));
        //设置右对齐
        panelBtn.setLayout(new FlowLayout(FlowLayout.RIGHT));
        //按钮组件
        Button addButton = new Button("添加");
        Button updateButton = new Button("修改");
        Button deleteButton = new Button("删除");
        Button findButton = new Button("查询");

        //添加 点击事件
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeesDialog dialog = new EmployeesDialog(jf, "添加修理工信息", false);
                dialog.setMyListener(Employees.this);
                dialog.setVisible(true);
            }
        });
        //删除 点击事件
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row==-1){
                    JOptionPane.showMessageDialog(jf,"请选择要删除的条目！");
                    return ;
                }else{
                    //防止误触
                    int result = JOptionPane.showConfirmDialog(jf, "确认删除所选数据吗？", "确认输出", JOptionPane.YES_NO_CANCEL_OPTION);
                    if(result !=JOptionPane.YES_OPTION){
                        return;
                    }else{
                        int id = (int) tableModel.getValueAt(row,0);
                        System.out.println(id);
                        int delete = new EmployeesOperate().deleteEmployees(id);
                        if(delete == 1){
                            JOptionPane.showMessageDialog(jf,"删除成功");
                            // 重新加载数据
                            Dates = new CarDAO().Fetch("Employee");
                            // 更新表格模型
                            tableModel.setDataVector(Dates, title);
                            // 刷新页面
                            table.setModel(tableModel);
                            new setBackground(table,title);
                        }
                        else JOptionPane.showMessageDialog(jf,"删除失败");
                    }
                }
            }
        });
        //修改事件
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row==-1){
                    JOptionPane.showMessageDialog(jf,"请选择要修改的条目！");
                    return ;
                }else{
                    String id = tableModel.getValueAt(row,0)+"";
                    String name = (String) tableModel.getValueAt(row,1);
                    String address = (String) tableModel.getValueAt(row,2);
                    String phone = (String)tableModel.getValueAt(row,3);
                    String birth = tableModel.getValueAt(row,4)+"";
                    String entry = tableModel.getValueAt(row,5)+"";
                    String wage = tableModel.getValueAt(row,6)+"";

                    EmployeesDialog dialog = new EmployeesDialog(jf, "修改修理工信息", false,Integer.parseInt(id.trim()),name,address,phone,birth,entry,Integer.parseInt(wage.trim()));
                    dialog.setMyListener(Employees.this);
                    dialog.setVisible(true);
                }
            }
        });
        //查询
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(jf, "请输入要查询的姓名：", JOptionPane.INFORMATION_MESSAGE);
                String sql = "select *from Employee where  EmployeeName like ?";
                Dates = new CarDAO().find(sql,input);
                tableModel.setDataVector(Dates, title);
                table.setModel(tableModel);
                new setBackground(table,title);
            }
        });

        panelBtn.add(addButton);
        panelBtn.add(updateButton);
        panelBtn.add(deleteButton);
        panelBtn.add(findButton);
        this.add(panelBtn);

        //组装表格
        title = new Vector<Object>();
        title.add("工号");
        title.add("姓名");
        title.add("地址");
        title.add("电话");
        title.add("出生日期");
        title.add("入职日期");
        title.add("小时工资");

        Dates = new CarDAO().Fetch("Employee");
        tableModel = new DefaultTableModel(Dates,title);
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
    }
}