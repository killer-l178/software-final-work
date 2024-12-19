package com.hnit.view;

import com.hnit.DAO.CarOwnerOperate;
import com.hnit.DAO.EmployeesOperate;
import com.hnit.compoent.MyListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ClassName: EmployeesDialog
 * Description:
 *
 * @Author wzq
 * @Create 2024/6/11 21:41
 * @Version 1.0
 */
//EmployeesDialog
public class EmployeesDialog  extends JDialog {
    private MyListener myListener;

    public void setMyListener(MyListener Listener) {
        this.myListener = Listener;
    }

    public EmployeesDialog (JFrame jf, String title, Boolean is) {
        super(jf,title,is);
        this.setIconImage(new ImageIcon("./src/image/logo.jpg").getImage());
        this.setBounds(750,230,300,350);
        Box vBox = Box.createVerticalBox();

        Box numberBox = Box.createHorizontalBox();
        JLabel numberLabel = new JLabel("工    号：");
        numberBox.add(numberLabel);
        numberBox.add(Box.createHorizontalStrut(25));
        JTextField numberField = new JTextField(20);
        numberBox.add(numberField);

        Box nameBox = Box.createHorizontalBox();
        JLabel nameLabel = new JLabel("姓    名：");
        nameBox.add(nameLabel);
        nameBox.add(Box.createHorizontalStrut(25));
        JTextField nameField = new JTextField(20);
        nameBox.add(nameField);

        Box phoneBox = Box.createHorizontalBox();
        JLabel phoneLabel = new JLabel("电    话：");
        phoneBox.add(phoneLabel);
        phoneBox.add(Box.createHorizontalStrut(25));
        JTextField  phoneField = new JTextField(20);
        phoneBox.add(phoneField);


        Box addressBox = Box.createHorizontalBox();
        JLabel addressLabel = new JLabel("地    址：");
        addressBox.add(addressLabel);
        addressBox.add(Box.createHorizontalStrut(25));
        JTextField  addressField = new JTextField(20);
        addressBox.add(addressField);

        Box birthBox = Box.createHorizontalBox();
        JLabel birthLabel = new JLabel("出生日期：");
        birthBox.add(birthLabel);
        birthBox.add(Box.createHorizontalStrut(15));
        JTextField  birthField = new JTextField(20);
        birthBox.add(birthField);

        Box entryBox = Box.createHorizontalBox();
        JLabel entryLabel = new JLabel("入职日期：");
        entryBox.add( entryLabel);
        entryBox.add(Box.createHorizontalStrut(15));
        JTextField  entryField = new JTextField(20);
        entryBox.add( entryField);

        Box wageBox = Box.createHorizontalBox();
        JLabel wageLabel = new JLabel("小时工资：");
        wageBox.add(wageLabel);
        wageBox.add(Box.createHorizontalStrut(15));
        JTextField  wageField = new JTextField(20);
        wageBox.add(wageField);

        Box bBox = Box.createHorizontalBox();
        Button addButton = new Button("添加");
        //点击事件
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = numberField.getText().trim();
                String name  = nameField.getText().trim();
                String address = addressField.getText().trim();
                String phone = phoneField.getText().trim();
                String birth = birthField.getText().trim();
                String entry = entryField.getText().trim();
                String wage = wageField.getText().trim();
                if(name.isEmpty()||address.isEmpty()||phone.isEmpty()||birth.isEmpty()||entry.isEmpty()||id.isEmpty()||wage.isEmpty()){
                    JOptionPane.showMessageDialog(jf,"添加内容不能为空","提示",JOptionPane.WARNING_MESSAGE);
                }else {
                    int employees = new EmployeesOperate().addEmployees(Integer.parseInt(id), name, address, phone, birth, entry, Integer.parseInt(wage));
                    if(employees!=0){
                        JOptionPane.showMessageDialog(jf,"添加成功","提示",JOptionPane.INFORMATION_MESSAGE);
                        if (myListener!=null)
                            myListener.onDataAdded();
                        dispose();
                    }else {
                        JOptionPane.showMessageDialog(jf,"添加内容格式错误","提示",JOptionPane.WARNING_MESSAGE);
                        return ;
                    }

                }

            }
        });

        bBox.add(Box.createHorizontalStrut(70));
        bBox.add(addButton);
        bBox.add(Box.createHorizontalStrut(70));

        vBox.add(Box.createVerticalStrut(15));
        vBox.add(numberBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(nameBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(addressBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(phoneBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(birthBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(entryBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(wageBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(bBox);
        vBox.add(Box.createVerticalStrut(25));

        Box hBox = Box.createHorizontalBox();
        hBox.add(Box.createHorizontalStrut(25));
        hBox.add(vBox);
        hBox.add(Box.createHorizontalStrut(25));

        this.add(hBox);
        //设置可见和关闭操作
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    //修改
    public EmployeesDialog (JFrame jf, String title, Boolean is,int id,String name,String address,String phone,String birth,String entry,int wage) {
        super(jf,title,is);
        this.setIconImage(new ImageIcon("./src/image/logo.jpg").getImage());
        this.setBounds(750,230,300,350);
        Box vBox = Box.createVerticalBox();

        Box numberBox = Box.createHorizontalBox();
        JLabel numberLabel = new JLabel("工    号：");
        numberBox.add(numberLabel);
        numberBox.add(Box.createHorizontalStrut(25));
        JTextField numberField = new JTextField(20);
        numberField.setText(id+"");
        numberBox.add(numberField);

        Box nameBox = Box.createHorizontalBox();
        JLabel nameLabel = new JLabel("姓    名：");
        nameBox.add(nameLabel);
        nameBox.add(Box.createHorizontalStrut(25));
        JTextField nameField = new JTextField(20);
        nameField.setText(name);
        nameBox.add(nameField);

        Box phoneBox = Box.createHorizontalBox();
        JLabel phoneLabel = new JLabel("电    话：");
        phoneBox.add(phoneLabel);
        phoneBox.add(Box.createHorizontalStrut(25));
        JTextField  phoneField = new JTextField(20);
        phoneField.setText(phone);
        phoneBox.add(phoneField);


        Box addressBox = Box.createHorizontalBox();
        JLabel addressLabel = new JLabel("地    址：");
        addressBox.add(addressLabel);
        addressBox.add(Box.createHorizontalStrut(25));
        JTextField  addressField = new JTextField(20);
        addressField.setText(address);
        addressBox.add(addressField);

        Box birthBox = Box.createHorizontalBox();
        JLabel birthLabel = new JLabel("出生日期：");
        birthBox.add(birthLabel);
        birthBox.add(Box.createHorizontalStrut(15));
        JTextField  birthField = new JTextField(20);
        birthField.setText(birth);
        birthBox.add(birthField);

        Box entryBox = Box.createHorizontalBox();
        JLabel entryLabel = new JLabel("入职日期：");
        entryBox.add( entryLabel);
        entryBox.add(Box.createHorizontalStrut(15));
        JTextField  entryField = new JTextField(20);
        entryField.setText(entry);
        entryBox.add( entryField);

        Box wageBox = Box.createHorizontalBox();
        JLabel wageLabel = new JLabel("小时工资：");
        wageBox.add(wageLabel);
        wageBox.add(Box.createHorizontalStrut(15));
        JTextField  wageField = new JTextField(20);
        wageField.setText(wage+"");
        wageBox.add(wageField);

        Box bBox = Box.createHorizontalBox();
        Button addButton = new Button("修改");
        //点击事件
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = numberField.getText().trim();
                String name  = nameField.getText().trim();
                String address = addressField.getText().trim();
                String phone = phoneField.getText().trim();
                String birth = birthField.getText().trim();
                String entry = entryField.getText().trim();
                String wage = wageField.getText().trim();
                if(name.isEmpty()||address.isEmpty()||phone.isEmpty()||birth.isEmpty()||entry.isEmpty()||id.isEmpty()||wage.isEmpty()){
                    JOptionPane.showMessageDialog(jf,"修改内容不能为空","提示",JOptionPane.WARNING_MESSAGE);
                }else {
                    int employees = new EmployeesOperate().updateEmployees(Integer.parseInt(id), name, address, phone, birth, entry, Integer.parseInt(wage));
                    if(employees!=0){
                        JOptionPane.showMessageDialog(jf,"修改成功","提示",JOptionPane.INFORMATION_MESSAGE);
                        if (myListener!=null)
                            myListener.onDataAdded();
                        dispose();
                    }else {
                        JOptionPane.showMessageDialog(jf,"修改内容格式错误","提示",JOptionPane.WARNING_MESSAGE);
                        return ;
                    }

                }

            }
        });

        bBox.add(Box.createHorizontalStrut(70));
        bBox.add(addButton);
        bBox.add(Box.createHorizontalStrut(70));

        vBox.add(Box.createVerticalStrut(15));
        vBox.add(numberBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(nameBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(addressBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(phoneBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(birthBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(entryBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(wageBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(bBox);
        vBox.add(Box.createVerticalStrut(25));

        Box hBox = Box.createHorizontalBox();
        hBox.add(Box.createHorizontalStrut(25));
        hBox.add(vBox);
        hBox.add(Box.createHorizontalStrut(25));

        this.add(hBox);
        //设置可见和关闭操作
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    }

}


