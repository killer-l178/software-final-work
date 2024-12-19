package com.hnit.entity;

import com.hnit.DAO.CarDAO;
import com.hnit.DAO.PartsUsageOperate;
import com.hnit.DAO.RepairRecordOperate;
import com.hnit.compoent.MyListener;
import com.hnit.compoent.setBackground;
import com.hnit.view.PartsUsageDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * ClassName: PartsUsage
 * Description:
 *
 * @Author wzq
 * @Create 2024/6/9 17:30
 * @Version 1.0
 */
//PartsUsage

public class PartsUsage extends Box implements MyListener {
    private JTable table;
    private Vector<Object> title;//标题
    private Vector<Vector<Object>> Dates;//数据
    private DefaultTableModel tableModel;

    @Override
    public void onDataAdded() {
        // 数据已添加，重新加载数据并刷新表格
        Dates = new CarDAO().Fetch("PartsUsage");
        tableModel.setDataVector(Dates, title);
        table.setModel(tableModel);
        new setBackground(table,title);
    }

    public PartsUsage(JFrame jf) {
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
                PartsUsageDialog dialog = new PartsUsageDialog(jf, "添加零件信息", false);
                dialog.setMyListener(PartsUsage.this);
                dialog.setVisible(true);
            }
        });
        //删除
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
                        int delete = new PartsUsageOperate().deletePartsUsage(id);
                        if(delete == 1){
                            JOptionPane.showMessageDialog(jf,"删除成功");
                            // 重新加载数据
                            Dates = new CarDAO().Fetch("PartsUsage");
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
        //修改
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row==-1){
                    JOptionPane.showMessageDialog(jf,"请选择要修改的条目！");
                    return ;
                }else{
                    String id = tableModel.getValueAt(row,0)+"";
                    String number = tableModel.getValueAt(row,1)+"";
                    String quantity = tableModel.getValueAt(row,2)+"";
                    PartsUsageDialog dialog = new PartsUsageDialog(jf, "修改零件数量信息", false, Integer.parseInt(id), number, Integer.parseInt(quantity));
                    dialog.setMyListener(PartsUsage.this);
                    dialog.setVisible(true);
                }
            }
        });
        //查询
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(jf, "请输入要查询的零件号：", JOptionPane.INFORMATION_MESSAGE);
                String sql = "select *from PartsUsage where  PartNumber like ?";
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
        title.add("编号");
        title.add("零件号");
        title.add("数量");

        Dates = new CarDAO().Fetch("PartsUsage");
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