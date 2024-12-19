package com.hnit.entity;

import com.hnit.DAO.CarDAO;
import com.hnit.DAO.PartslnventoryOperate;
import com.hnit.compoent.MyListener;
import com.hnit.compoent.setBackground;
import com.hnit.view.PartsInventoryDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * ClassName: PartsInventory
 * Description:
 *
 * @Author wzq
 * @Create 2024/6/9 17:31
 * @Version 1.0
 */

public class PartsInventory extends Box implements MyListener {
    private JTable table;
    private Vector<Object> title;//标题
    private Vector<Vector<Object>> Dates;//数据
    private DefaultTableModel tableModel;

    @Override
    public void onDataAdded() {
        // 数据已添加，重新加载数据并刷新表格
        Dates = new CarDAO().Fetch("PartsInventory");
        tableModel.setDataVector(Dates, title);
        table.setModel(tableModel);
        new setBackground(table,title);
    }

    public PartsInventory(JFrame jf) {
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


        //添加
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PartsInventoryDialog dialog = new PartsInventoryDialog(jf, "添加零件库存信息", false);
                dialog.setMyListener(PartsInventory.this);
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
                    int result = JOptionPane.showConfirmDialog(jf, "确认删除所选数据吗？", "确认删除", JOptionPane.YES_NO_CANCEL_OPTION);
                    if(result !=JOptionPane.YES_OPTION){
                        return;
                    }else{
                        String number = (String) tableModel.getValueAt(row,0);
                        System.out.println(number);
                        int delete = new PartslnventoryOperate().deletePartslnventory(number);
                        if(delete == 1){
                            JOptionPane.showMessageDialog(jf,"删除成功");
                            // 重新加载数据
                            Dates = new CarDAO().Fetch("PartsInventory");
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
                }else {
                    String number = tableModel.getValueAt(row,0)+"";
                    String name = tableModel.getValueAt(row,1)+"";
                    String cost = tableModel.getValueAt(row,2)+"";
                    String price = tableModel.getValueAt(row,3)+"";
                    String stock = tableModel.getValueAt(row,4)+"";
                    String minStock = tableModel.getValueAt(row,5)+"";
                    String order = tableModel.getValueAt(row,6)+"";
                    PartsInventoryDialog dialog = new PartsInventoryDialog(jf, "修改零件库存信息", false, name, Integer.parseInt(cost), Integer.parseInt(price),
                            Integer.parseInt(stock), Integer.parseInt(minStock), Integer.parseInt(order), number);
                    dialog.setMyListener(PartsInventory.this);
                    dialog.setVisible(true);
                }
            }
        });

        //查询事件
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(jf, "请输入要查询的零件名：", JOptionPane.INFORMATION_MESSAGE);
                String sql = "select *from PartsInventory where  PartName like ?";
                Dates = new CarDAO().find(sql,input);
                tableModel.setDataVector(Dates, title);
                table.setModel(tableModel);
                new setBackground(table,title);
            }
        });

        //查询
        panelBtn.add(addButton);
        panelBtn.add(updateButton);
        panelBtn.add(deleteButton);
        panelBtn.add(findButton);
        this.add(panelBtn);

        //组装表格
        title = new Vector<Object>();
        title.add("零件号");
        title.add("零件名");
        title.add("成本");
        title.add("价格");
        title.add("库存量");
        title.add("最低库存");
        title.add("订货量");

        Dates = new CarDAO().Fetch("PartsInventory");
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