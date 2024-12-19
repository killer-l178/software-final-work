package com.hnit.view;

import com.hnit.entity.*;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * ClassName: ManagerUI
 * Description:
 *
 * @Author wzq
 * @Create 2024/6/9 10:53
 * @Version 1.0
 */
public class ManagerUI {
    JFrame jFrame = new JFrame("���޹���ϵͳ��ӭ��");

    public void init(){
        //���ñ����ͼ��
        jFrame.setIconImage(new ImageIcon("./src/image/logo.jpg").getImage());
        //���ô�С��λ��
        jFrame.setBounds(400,150,1000,550);

        //���ò˵�
        JMenuBar jmb = new JMenuBar();
        JMenu jm = new JMenu("����");
        JMenuItem Handoff = new JMenuItem("�л��˺�");
        JMenuItem Exit = new JMenuItem("�˳�ϵͳ");
        //����¼�
        Handoff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //��ǰ���ڹرգ��򿪵�¼ҳ��
                new LogIn().init();
                jFrame.dispose();
            }
        });
        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ֱ���˳�ϵͳ
                jFrame.dispose();
            }
        });
        //�˵���װ
        jm.add(Handoff);
        jm.add(Exit);
        jmb.add(jm);
        //�����������
        jFrame.add(jmb, BorderLayout.NORTH);

        //���ò˵����
        JSplitPane jsp = new JSplitPane();
        //֧���϶�����������
        jsp.setContinuousLayout(true);
        //�������������
        jsp.setDividerSize(5);
        //�������ҳ�ʼ���
        jsp.setDividerLocation(150);

        //����������ѡ��
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("ϵͳ����");
        DefaultMutableTreeNode Registration = new DefaultMutableTreeNode("��Ϣ����");
        DefaultMutableTreeNode PartsManagement = new DefaultMutableTreeNode("�������");
        DefaultMutableTreeNode StatisticalPrints = new DefaultMutableTreeNode("ͳ�ƴ�ӡ");

        //��Ϣ�Ǽ��ӹ���
        DefaultMutableTreeNode CarOwnerRegistration = new DefaultMutableTreeNode("������Ϣ����");
        DefaultMutableTreeNode CarRegistration = new DefaultMutableTreeNode("������Ϣ����");
        DefaultMutableTreeNode RepairRegistration = new DefaultMutableTreeNode("�����¼����");
        DefaultMutableTreeNode EmployeeRegistration = new DefaultMutableTreeNode("������Ϣ����");
        //���
        Registration.add(CarOwnerRegistration);
        Registration.add(CarRegistration);
        Registration.add(RepairRegistration);
        Registration.add(EmployeeRegistration);
        //��������ӹ���
        DefaultMutableTreeNode PartsUsage = new DefaultMutableTreeNode("���������Ϣ");
        DefaultMutableTreeNode PartsInventory = new DefaultMutableTreeNode("��������Ϣ");
        //���
        PartsManagement.add(PartsUsage);
        PartsManagement.add(PartsInventory);

        //ͳ�ƴ�ӡ�ӹ���
        DefaultMutableTreeNode InvoicePrints = new DefaultMutableTreeNode("��Ʊ��ӡ");
        DefaultMutableTreeNode WagesPrints = new DefaultMutableTreeNode("���ʴ�ӡ");
        //���
        StatisticalPrints.add(InvoicePrints);
        StatisticalPrints.add(WagesPrints);

        root.add(Registration);
        root.add(PartsManagement);
        root.add(StatisticalPrints);

        //����״�ṹ���������������
        JTree tree = new JTree(root);
        //��������������
        DefaultTreeCellRenderer render=(DefaultTreeCellRenderer)(tree.getCellRenderer());
        render.setLeafIcon(new ImageIcon("./src/image/button.png"));
        render.setFont(new Font("����",Font.BOLD,11));

        //��߲������
        jsp.setLeftComponent(tree);

        //�ұ߲������
        jsp.setRightComponent(new JLabel(new ImageIcon("./src/image/MainBG.png")));
        //���ݵ����ͬ�İ�������ʾ��ͬ��ҳ��
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                Object LastPathComponent = e.getNewLeadSelectionPath().getLastPathComponent();
                if(LastPathComponent.equals(CarOwnerRegistration)){
                    jsp.setRightComponent(new CarOwner(jFrame));
                }else if(LastPathComponent.equals(CarRegistration)){
                    jsp.setRightComponent(new Car(jFrame));
                }else if(LastPathComponent.equals(RepairRegistration)){
                    jsp.setRightComponent(new RepairRecord(jFrame));
                }else if(LastPathComponent.equals(EmployeeRegistration)){
                    jsp.setRightComponent(new Employees(jFrame));
                }else if(LastPathComponent.equals(PartsUsage)){
                    jsp.setRightComponent(new PartsUsage(jFrame));
                }else if(LastPathComponent.equals(PartsInventory)){
                    jsp.setRightComponent(new PartsInventory(jFrame));
                }else if(LastPathComponent.equals(InvoicePrints)){
                    jsp.setRightComponent(new InvoiceGenerator (jFrame));
                }else if(LastPathComponent.equals(WagesPrints)){
                    jsp.setRightComponent(new Salary(jFrame));
                }
            }
        });

        //������������
        jFrame.add(jsp);
        //�رղ���
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // jFrame.pack();
        //���ڿɼ�
        jFrame.setVisible(true);
        //���ô��ڴ�С���ɸ���
        jFrame.setResizable(false);
    }

//    public static void main(String[] args) {
//        new ManagerUI().init();
//    }
}
