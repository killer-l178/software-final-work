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
    private Vector<Object> title;//����
    private Vector<Vector<Object>> Dates;//����
    private DefaultTableModel tableModel;

    @Override
    public void onDataAdded() {
        // ��������ӣ����¼������ݲ�ˢ�±��
        Dates = new CarDAO().Fetch("PartsInventory");
        tableModel.setDataVector(Dates, title);
        table.setModel(tableModel);
        new setBackground(table,title);
    }

    public PartsInventory(JFrame jf) {
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


        //���
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PartsInventoryDialog dialog = new PartsInventoryDialog(jf, "�����������Ϣ", false);
                dialog.setMyListener(PartsInventory.this);
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
                    int result = JOptionPane.showConfirmDialog(jf, "ȷ��ɾ����ѡ������", "ȷ��ɾ��", JOptionPane.YES_NO_CANCEL_OPTION);
                    if(result !=JOptionPane.YES_OPTION){
                        return;
                    }else{
                        String number = (String) tableModel.getValueAt(row,0);
                        System.out.println(number);
                        int delete = new PartslnventoryOperate().deletePartslnventory(number);
                        if(delete == 1){
                            JOptionPane.showMessageDialog(jf,"ɾ���ɹ�");
                            // ���¼�������
                            Dates = new CarDAO().Fetch("PartsInventory");
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
                }else {
                    String number = tableModel.getValueAt(row,0)+"";
                    String name = tableModel.getValueAt(row,1)+"";
                    String cost = tableModel.getValueAt(row,2)+"";
                    String price = tableModel.getValueAt(row,3)+"";
                    String stock = tableModel.getValueAt(row,4)+"";
                    String minStock = tableModel.getValueAt(row,5)+"";
                    String order = tableModel.getValueAt(row,6)+"";
                    PartsInventoryDialog dialog = new PartsInventoryDialog(jf, "�޸���������Ϣ", false, name, Integer.parseInt(cost), Integer.parseInt(price),
                            Integer.parseInt(stock), Integer.parseInt(minStock), Integer.parseInt(order), number);
                    dialog.setMyListener(PartsInventory.this);
                    dialog.setVisible(true);
                }
            }
        });

        //��ѯ�¼�
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(jf, "������Ҫ��ѯ���������", JOptionPane.INFORMATION_MESSAGE);
                String sql = "select *from PartsInventory where  PartName like ?";
                Dates = new CarDAO().find(sql,input);
                tableModel.setDataVector(Dates, title);
                table.setModel(tableModel);
                new setBackground(table,title);
            }
        });

        //��ѯ
        panelBtn.add(addButton);
        panelBtn.add(updateButton);
        panelBtn.add(deleteButton);
        panelBtn.add(findButton);
        this.add(panelBtn);

        //��װ���
        title = new Vector<Object>();
        title.add("�����");
        title.add("�����");
        title.add("�ɱ�");
        title.add("�۸�");
        title.add("�����");
        title.add("��Ϳ��");
        title.add("������");

        Dates = new CarDAO().Fetch("PartsInventory");
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