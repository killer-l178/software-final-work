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
    private Vector<Object> title;//����
    private Vector<Vector<Object>> Dates;//����
    private DefaultTableModel tableModel;

    @Override
    public void onDataAdded() {
        // ��������ӣ����¼������ݲ�ˢ�±��
        Dates = new CarDAO().Fetch("Employee");
        tableModel.setDataVector(Dates, title);
        table.setModel(tableModel);
        new setBackground(table,title);
    }
    public Employees(JFrame jf) {
        super(BoxLayout.Y_AXIS);//��ֱ����

        JPanel panelBtn = new JPanel();
        panelBtn.setBackground(new Color(255,255,255));
        panelBtn.setMaximumSize(new Dimension(850, 75));
        //�����Ҷ���
        panelBtn.setLayout(new FlowLayout(FlowLayout.RIGHT));
        //��ť���
        Button addButton = new Button("���");
        Button updateButton = new Button("�޸�");
        Button deleteButton = new Button("ɾ��");
        Button findButton = new Button("��ѯ");

        //��� ����¼�
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmployeesDialog dialog = new EmployeesDialog(jf, "���������Ϣ", false);
                dialog.setMyListener(Employees.this);
                dialog.setVisible(true);
            }
        });
        //ɾ�� ����¼�
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row==-1){
                    JOptionPane.showMessageDialog(jf,"��ѡ��Ҫɾ������Ŀ��");
                    return ;
                }else{
                    //��ֹ��
                    int result = JOptionPane.showConfirmDialog(jf, "ȷ��ɾ����ѡ������", "ȷ�����", JOptionPane.YES_NO_CANCEL_OPTION);
                    if(result !=JOptionPane.YES_OPTION){
                        return;
                    }else{
                        int id = (int) tableModel.getValueAt(row,0);
                        System.out.println(id);
                        int delete = new EmployeesOperate().deleteEmployees(id);
                        if(delete == 1){
                            JOptionPane.showMessageDialog(jf,"ɾ���ɹ�");
                            // ���¼�������
                            Dates = new CarDAO().Fetch("Employee");
                            // ���±��ģ��
                            tableModel.setDataVector(Dates, title);
                            // ˢ��ҳ��
                            table.setModel(tableModel);
                            new setBackground(table,title);
                        }
                        else JOptionPane.showMessageDialog(jf,"ɾ��ʧ��");
                    }
                }
            }
        });
        //�޸��¼�
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row==-1){
                    JOptionPane.showMessageDialog(jf,"��ѡ��Ҫ�޸ĵ���Ŀ��");
                    return ;
                }else{
                    String id = tableModel.getValueAt(row,0)+"";
                    String name = (String) tableModel.getValueAt(row,1);
                    String address = (String) tableModel.getValueAt(row,2);
                    String phone = (String)tableModel.getValueAt(row,3);
                    String birth = tableModel.getValueAt(row,4)+"";
                    String entry = tableModel.getValueAt(row,5)+"";
                    String wage = tableModel.getValueAt(row,6)+"";

                    EmployeesDialog dialog = new EmployeesDialog(jf, "�޸�������Ϣ", false,Integer.parseInt(id.trim()),name,address,phone,birth,entry,Integer.parseInt(wage.trim()));
                    dialog.setMyListener(Employees.this);
                    dialog.setVisible(true);
                }
            }
        });
        //��ѯ
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(jf, "������Ҫ��ѯ��������", JOptionPane.INFORMATION_MESSAGE);
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

        //��װ���
        title = new Vector<Object>();
        title.add("����");
        title.add("����");
        title.add("��ַ");
        title.add("�绰");
        title.add("��������");
        title.add("��ְ����");
        title.add("Сʱ����");

        Dates = new CarDAO().Fetch("Employee");
        tableModel = new DefaultTableModel(Dates,title);
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
    }
}