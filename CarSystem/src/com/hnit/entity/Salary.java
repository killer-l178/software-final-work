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
    private Vector<Object> title;//����
    private Vector<Vector<Object>> Dates;//����
    private DefaultTableModel tableModel;

    @Override
    public void onDataAdded() {
        tableModel.setDataVector(Dates, title);
        table.setModel(tableModel);
        new setBackground(table,title);
    }

    public Salary (JFrame jf) {
        super(BoxLayout.Y_AXIS);//��ֱ����

        JPanel panelBtn = new JPanel();
        panelBtn.setBackground(new Color(255,255,255));
        panelBtn.setMaximumSize(new Dimension(850, 75));
        //�����Ҷ���
        panelBtn.setLayout(new FlowLayout(FlowLayout.RIGHT));
        //��ť���

        Button printAllButton = new Button("��ѯ����Ա������");
        Button findButton = new Button("��ѯָ��Ա������");

        panelBtn.add(printAllButton);
        panelBtn.add(findButton);

        this.add(panelBtn);


        title = new Vector<Object>();
        title.add("����");
        title.add("����");
        title.add("��������");
        title.add("��Ч");

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

        //��ѯ����
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
        //��ѯָ��
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "select *from Salary where EmployeeName like ?";
                String input = JOptionPane.showInputDialog(jf, "�������ѯ��������", JOptionPane.INFORMATION_MESSAGE);
                new CarDAO().update();
                Dates = new CarDAO().find(sql, input);
                tableModel.setDataVector(Dates, title);
                table.setModel(tableModel);
                new setBackground(table,title);
            }
        });
    }

}
