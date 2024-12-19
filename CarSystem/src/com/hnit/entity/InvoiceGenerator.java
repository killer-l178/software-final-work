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
    private Vector<Object> title;//����
    private Vector<Vector<Object>> Dates;//����
    private DefaultTableModel tableModel;

    @Override
    public void onDataAdded() {
        tableModel.setDataVector(Dates, title);
        table.setModel(tableModel);
        new setBackground(table,title);
    }

    public InvoiceGenerator (JFrame jf) {
        super(BoxLayout.Y_AXIS);//��ֱ����

        JPanel panelBtn = new JPanel();
        panelBtn.setBackground(new Color(255,255,255));
        panelBtn.setMaximumSize(new Dimension(850, 75));
        //�����Ҷ���
        panelBtn.setLayout(new FlowLayout(FlowLayout.RIGHT));
        //��ť���

        Button printAllButton = new Button("��ӡ���з�Ʊ");
        Button findButton = new Button("��ӡָ����Ʊ");

        panelBtn.add(printAllButton);
        panelBtn.add(findButton);

        this.add(panelBtn);

        //��װ���
        //-- ��Ʊ���ݣ����ơ����������ͺš�������Ŀ�����š��깤���ڡ� �۸񡢳����绰--

        title = new Vector<Object>();
        title.add("����");
        title.add("����");
        title.add("�ͺ�");
        title.add("������Ŀ");
        title.add("����");
        title.add("�깤����");
        title.add("�۸�");
        title.add("��ϵ�绰");

        //Dates = null;
        tableModel = new DefaultTableModel(Dates, title);
        table = new JTable(tableModel)
        {
            //�����޸�
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
                super.setFont(new Font("����",Font.BOLD,15));
            }
        };
        //����������
        JTableHeader head = table.getTableHeader(); // �������������
        head.setPreferredSize(new Dimension(head.getWidth(), 35));// ���ñ�ͷ��С
        head.setFont(new Font("����",Font.BOLD,16));//���ñ�ͷ����
        // ���ñ����ɫ
        new setBackground(table,title);
        //����ѡ��һ��
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane jScrollPane = new JScrollPane(table);
        this.add(jScrollPane);

        //��ӡ����
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
        //��ӡָ��
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "SELECT Car.PlateNumber,Car.OwnerName,Model,RepairProject,RepairRecord.EmployeeID,CompletionDate ,RepairHours * HourlyWage + 300,CarOwnerPhone " +
                        "FROM RepairRecord JOIN Car ON RepairRecord.PlateNumber = Car.PlateNumber " +
                        "JOIN Employee ON RepairRecord.EmployeeID = Employee.EmployeeID " +
                        "JOIN CarOwner ON Car.OwnerName = CarOwner.OwnerName " +
                        "WHERE Car.PlateNumber like ?;";
                String input = JOptionPane.showInputDialog(jf, "������Ҫ��ӡ�ĳ��ƺţ�", JOptionPane.INFORMATION_MESSAGE);
                Dates = new CarDAO().find(sql,input);
                tableModel.setDataVector(Dates, title);
                table.setModel(tableModel);
                new setBackground(table,title);
            }
        });
    }

}
