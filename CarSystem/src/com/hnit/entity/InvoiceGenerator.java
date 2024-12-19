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
 * ClassName: Invoice
 * Description:
 *
 * @Author wzq
 * @Create 2024/6/15 16:58
 * @Version 1.0
 */
//Invoice
public class InvoiceGenerator  extends Box implements MyListener {
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

    public InvoiceGenerator (JFrame jf) {
        super(BoxLayout.Y_AXIS);//垂直布局

        JPanel panelBtn = new JPanel();
        panelBtn.setBackground(new Color(255,255,255));
        panelBtn.setMaximumSize(new Dimension(850, 75));
        //设置右对齐
        panelBtn.setLayout(new FlowLayout(FlowLayout.RIGHT));
        //按钮组件

        Button printAllButton = new Button("打印所有发票");
        Button findButton = new Button("打印指定发票");

        panelBtn.add(printAllButton);
        panelBtn.add(findButton);

        this.add(panelBtn);

        //组装表格
        //-- 发票内容：车牌、车主、车型号、修理项目、工号、完工日期、 价格、车主电话--

        title = new Vector<Object>();
        title.add("车牌");
        title.add("车主");
        title.add("型号");
        title.add("修理项目");
        title.add("工号");
        title.add("完工日期");
        title.add("价格");
        title.add("联系电话");

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

        //打印所有
        printAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "SELECT Car.PlateNumber,Car.OwnerName,Model,RepairProject,RepairRecord.EmployeeID,CompletionDate ,RepairHours * HourlyWage + 300,CarOwnerPhone\n" +
                        "FROM RepairRecord JOIN Car ON RepairRecord.PlateNumber = Car.PlateNumber\n" +
                        "JOIN Employee ON RepairRecord.EmployeeID = Employee.EmployeeID\n" +
                        "JOIN CarOwner ON Car.OwnerName = CarOwner.OwnerName\n";
                Dates = new CarDAO().print(sql);
                tableModel.setDataVector(Dates, title);
                table.setModel(tableModel);
                new setBackground(table,title);
            }
        });
        //打印指定
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "SELECT Car.PlateNumber,Car.OwnerName,Model,RepairProject,RepairRecord.EmployeeID,CompletionDate ,RepairHours * HourlyWage + 300,CarOwnerPhone " +
                        "FROM RepairRecord JOIN Car ON RepairRecord.PlateNumber = Car.PlateNumber " +
                        "JOIN Employee ON RepairRecord.EmployeeID = Employee.EmployeeID " +
                        "JOIN CarOwner ON Car.OwnerName = CarOwner.OwnerName " +
                        "WHERE Car.PlateNumber like ?;";
                String input = JOptionPane.showInputDialog(jf, "请输入要打印的车牌号：", JOptionPane.INFORMATION_MESSAGE);
                Dates = new CarDAO().find(sql,input);
                tableModel.setDataVector(Dates, title);
                table.setModel(tableModel);
                new setBackground(table,title);
            }
        });
    }

}
