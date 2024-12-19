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
    JFrame jFrame = new JFrame("汽修管理系统欢迎您");

    public void init(){
        //设置标题和图标
        jFrame.setIconImage(new ImageIcon("./src/image/logo.jpg").getImage());
        //设置大小和位置
        jFrame.setBounds(400,150,1000,550);

        //设置菜单
        JMenuBar jmb = new JMenuBar();
        JMenu jm = new JMenu("设置");
        JMenuItem Handoff = new JMenuItem("切换账号");
        JMenuItem Exit = new JMenuItem("退出系统");
        //点击事件
        Handoff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //当前窗口关闭，打开登录页面
                new LogIn().init();
                jFrame.dispose();
            }
        });
        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //直接退出系统
                jFrame.dispose();
            }
        });
        //菜单组装
        jm.add(Handoff);
        jm.add(Exit);
        jmb.add(jm);
        //添加至窗口中
        jFrame.add(jmb, BorderLayout.NORTH);

        //设置菜单面板
        JSplitPane jsp = new JSplitPane();
        //支持拖动后连续布局
        jsp.setContinuousLayout(true);
        //设置拉动条宽度
        jsp.setDividerSize(5);
        //设置左右初始宽度
        jsp.setDividerLocation(150);

        //设置面板左侧选项
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("系统功能");
        DefaultMutableTreeNode Registration = new DefaultMutableTreeNode("信息管理");
        DefaultMutableTreeNode PartsManagement = new DefaultMutableTreeNode("零件管理");
        DefaultMutableTreeNode StatisticalPrints = new DefaultMutableTreeNode("统计打印");

        //信息登记子功能
        DefaultMutableTreeNode CarOwnerRegistration = new DefaultMutableTreeNode("车主信息管理");
        DefaultMutableTreeNode CarRegistration = new DefaultMutableTreeNode("车辆信息管理");
        DefaultMutableTreeNode RepairRegistration = new DefaultMutableTreeNode("修理记录管理");
        DefaultMutableTreeNode EmployeeRegistration = new DefaultMutableTreeNode("修理工信息管理");
        //添加
        Registration.add(CarOwnerRegistration);
        Registration.add(CarRegistration);
        Registration.add(RepairRegistration);
        Registration.add(EmployeeRegistration);
        //零件管理子功能
        DefaultMutableTreeNode PartsUsage = new DefaultMutableTreeNode("零件用量信息");
        DefaultMutableTreeNode PartsInventory = new DefaultMutableTreeNode("零件库存信息");
        //添加
        PartsManagement.add(PartsUsage);
        PartsManagement.add(PartsInventory);

        //统计打印子功能
        DefaultMutableTreeNode InvoicePrints = new DefaultMutableTreeNode("发票打印");
        DefaultMutableTreeNode WagesPrints = new DefaultMutableTreeNode("工资打印");
        //添加
        StatisticalPrints.add(InvoicePrints);
        StatisticalPrints.add(WagesPrints);

        root.add(Registration);
        root.add(PartsManagement);
        root.add(StatisticalPrints);

        //将树状结构添加至设置面板左侧
        JTree tree = new JTree(root);
        //结点相关属性设置
        DefaultTreeCellRenderer render=(DefaultTreeCellRenderer)(tree.getCellRenderer());
        render.setLeafIcon(new ImageIcon("./src/image/button.png"));
        render.setFont(new Font("宋体",Font.BOLD,11));

        //左边部分添加
        jsp.setLeftComponent(tree);

        //右边部分添加
        jsp.setRightComponent(new JLabel(new ImageIcon("./src/image/MainBG.png")));
        //根据点击不同的按键，显示不同的页面
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

        //添加面板至窗口
        jFrame.add(jsp);
        //关闭操作
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // jFrame.pack();
        //窗口可见
        jFrame.setVisible(true);
        //设置窗口大小不可更改
        jFrame.setResizable(false);
    }

//    public static void main(String[] args) {
//        new ManagerUI().init();
//    }
}
