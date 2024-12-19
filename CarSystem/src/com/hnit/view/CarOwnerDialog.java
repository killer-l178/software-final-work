package com.hnit.view;

import com.hnit.DAO.CarOperate;
import com.hnit.DAO.CarOwnerOperate;
import com.hnit.compoent.MyListener;
import com.hnit.entity.CarOwner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * ClassName: CarOwnerDialog
 * Description:
 *
 * @Author wzq
 * @Create 2024/6/11 21:22
 * @Version 1.0
 */
public class CarOwnerDialog extends JDialog {

    //监听事件
    private MyListener myListener;

    public void setMyListener(MyListener Listener) {
        this.myListener = Listener;
    }

    public CarOwnerDialog(JFrame jf, String title, Boolean is) {
        super(jf,title,is);
        this.setIconImage(new ImageIcon("./src/image/logo.jpg").getImage());
        this.setBounds(750,230,300,300);
        Box vBox = Box.createVerticalBox();

        Box nameBox = Box.createHorizontalBox();
        JLabel nameLabel = new JLabel("车主名：");
        nameBox.add(nameLabel);
        nameBox.add(Box.createHorizontalStrut(20));
        JTextField nameField = new JTextField(20);
        nameBox.add(nameField);

        Box addressBox = Box.createHorizontalBox();
        JLabel addressLabel = new JLabel("地    址：");
        addressBox.add(addressLabel);
        addressBox.add(Box.createHorizontalStrut(20));
        JTextField addressField = new JTextField(20);
        addressBox.add(addressField);

        Box phoneBox = Box.createHorizontalBox();
        JLabel phoneLabel = new JLabel("电    话：");
        phoneBox.add(phoneLabel);
        phoneBox.add(Box.createHorizontalStrut(20));
        JTextField  phoneField = new JTextField(20);
        phoneBox.add(phoneField);

        Box bBox = Box.createHorizontalBox();
        Button addButton = new Button("添加");
        //点击事件
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name  = nameField.getText().trim();
                String address = addressField.getText().trim();
                String phone = phoneField.getText().trim();
                if(name.equals("")||address.equals("")||phone.equals("")){
                    JOptionPane.showMessageDialog(jf,"添加内容不能为空","提示",JOptionPane.WARNING_MESSAGE);
                }else {
                    int carOwner = new CarOwnerOperate().addCarOwner(name, address, phone);
                    if(carOwner!=0){
                        JOptionPane.showMessageDialog(jf,"添加成功","提示",JOptionPane.INFORMATION_MESSAGE);
                        if (myListener!=null)
                            myListener.onDataAdded();
                        dispose();
                    }else{
                        JOptionPane.showMessageDialog(jf,"添加内容格式错误","提示",JOptionPane.WARNING_MESSAGE);
                        return ;
                    }

                }

            }
        });

        bBox.add(Box.createHorizontalStrut(70));
        bBox.add(addButton);
        bBox.add(Box.createHorizontalStrut(70));

        vBox.add(Box.createVerticalStrut(45));
        vBox.add(nameBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(addressBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(phoneBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(bBox);
        vBox.add(Box.createVerticalStrut(60));

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

    public CarOwnerDialog(JFrame jf, String title, Boolean is, String name,String address,String phone) {
        super(jf,title,is);
        this.setIconImage(new ImageIcon("./src/image/logo.jpg").getImage());
        this.setBounds(750,230,300,300);
        Box vBox = Box.createVerticalBox();

        Box nameBox = Box.createHorizontalBox();
        JLabel nameLabel = new JLabel("车主名：");
        nameBox.add(nameLabel);
        nameBox.add(Box.createHorizontalStrut(20));
        JTextField nameField = new JTextField(20);
        nameField.setText(name);
        nameBox.add(nameField);

        Box addressBox = Box.createHorizontalBox();
        JLabel addressLabel = new JLabel("地    址：");
        addressBox.add(addressLabel);
        addressBox.add(Box.createHorizontalStrut(20));
        JTextField addressField = new JTextField(20);
        addressField.setText(address);
        addressBox.add(addressField);

        Box phoneBox = Box.createHorizontalBox();
        JLabel phoneLabel = new JLabel("电    话：");
        phoneBox.add(phoneLabel);
        phoneBox.add(Box.createHorizontalStrut(20));
        JTextField  phoneField = new JTextField(20);
        phoneField.setText(phone);
        phoneBox.add(phoneField);

        Box bBox = Box.createHorizontalBox();
        Button addButton = new Button("修改");
        //点击事件
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name  = nameField.getText().trim();
                String address = addressField.getText().trim();
                String phone = phoneField.getText().trim();
                if(name.equals("")||address.equals("")||phone.equals("")){
                    JOptionPane.showMessageDialog(jf,"修改内容不能为空","提示",JOptionPane.WARNING_MESSAGE);
                }else {
                    int carOwner = new CarOwnerOperate().updateCarOwner(nameField.getText(), addressField.getText(), phoneField.getText());
                    if(carOwner!=0){
                        JOptionPane.showMessageDialog(jf,"修改成功","提示",JOptionPane.INFORMATION_MESSAGE);
                        if (myListener!=null)
                            myListener.onDataAdded();
                        dispose();
                    }else{
                        JOptionPane.showMessageDialog(jf,"修改内容格式错误","提示",JOptionPane.WARNING_MESSAGE);
                        return ;
                    }

                }

            }
        });
        bBox.add(Box.createHorizontalStrut(70));
        bBox.add(addButton);
        bBox.add(Box.createHorizontalStrut(70));

        vBox.add(Box.createVerticalStrut(45));
        vBox.add(nameBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(addressBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(phoneBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(bBox);
        vBox.add(Box.createVerticalStrut(60));

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
