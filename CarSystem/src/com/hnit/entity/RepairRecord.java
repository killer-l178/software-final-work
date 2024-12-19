package com.hnit.entity;

import com.hnit.DAO.CarDAO;
import com.hnit.DAO.EmployeesOperate;
import com.hnit.DAO.RepairRecordOperate;
import com.hnit.compoent.MyListener;
import com.hnit.compoent.setBackground;
import com.hnit.view.RepairRecordDialog;

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
 * ClassName: RepairRecord
 * Description:
 *
 * @Author wzq
 * @Create 2024/6/9 17:30
 * @Version 1.0
 */
public class RepairRecord extends Box implements MyListener {
    private JTable table;
    private Vector<Object> title;//标题
    private Vector<Vector<Object>> Dates;//数据
    private DefaultTableModel tableModel;

    @Override
    public void onDataAdded() {
        // 数据已添加，重新加载数据并刷新表格
        Dates = new CarDAO().Fetch("RepairRecord");
        tableModel.setDataVector(Dates, title);
        table.setModel(tableModel);
        new setBackground(table,title);
    }

    public RepairRecord(JFrame jf) {
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
                RepairRecordDialog dialog = new RepairRecordDialog(jf, "添加修理单信息", false);
                dialog.setMyListener(RepairRecord.this);
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
                        int delete = new RepairRecordOperate().deleteRepairRecord(id);
                        if(delete == 1){
                            JOptionPane.showMessageDialog(jf,"删除成功");
                            // 重新加载数据
                            Dates = new CarDAO().Fetch("RepairRecord");
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
                    String number = tableModel.getValueAt(row,1)+"";
                    String project = tableModel.getValueAt(row,2)+"";
                    String submit = tableModel.getValueAt(row,3)+"";
                    String completion = tableModel.getValueAt(row,4)+"";
                    String EID = tableModel.getValueAt(row,5)+"";
                    String hour = tableModel.getValueAt(row,6)+"";
                    RepairRecordDialog dialog = new RepairRecordDialog(jf, "修改修理单信息", false, Integer.parseInt(id), number, project, submit, completion, Integer.parseInt(EID), Integer.parseInt(hour));
                    dialog.setMyListener(RepairRecord.this);
                    dialog.setVisible(true);
                }
            }
        });

        //查询事件
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(jf, "请输入要查询的牌号：", JOptionPane.INFORMATION_MESSAGE);
                String sql = "select *from RepairRecord where  PlateNumber like ?";
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
        title.add("牌号");
        title.add("修理项目");
        title.add("送修日期");
        title.add("完工日期");
        title.add("工号");
        title.add("修理时长");

        Dates = new CarDAO().Fetch("RepairRecord");
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