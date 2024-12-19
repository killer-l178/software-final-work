package com.hnit.view;

import com.hnit.DAO.CarOwnerOperate;
import com.hnit.DAO.PartsUsageOperate;
import com.hnit.compoent.MyListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ClassName: PartsUsageDialog
 * Description:
 *
 * @Author wzq
 * @Create 2024/6/12 16:08
 * @Version 1.0
 */
//PartsUsageDialog
public class PartsUsageDialog extends JDialog {
    private MyListener myListener;

    public void setMyListener(MyListener Listener) {
        this.myListener = Listener;
    }

    public PartsUsageDialog(JFrame jf, String title, Boolean is) {
        super(jf,title,is);
        this.setIconImage(new ImageIcon("./src/image/logo.jpg").getImage());
        this.setBounds(750,230,300,300);
        Box vBox = Box.createVerticalBox();

        Box numberBox = Box.createHorizontalBox();
        JLabel numberLabel = new JLabel("编    号：");
        numberBox.add(numberLabel);
        numberBox.add(Box.createHorizontalStrut(20));
        JTextField numberField = new JTextField(20);
        numberBox.add(numberField);

        Box nameBox = Box.createHorizontalBox();
        JLabel nameLabel = new JLabel("零件名：");
        nameBox.add(nameLabel);
        nameBox.add(Box.createHorizontalStrut(20));
        JTextField nameField = new JTextField(20);
        nameBox.add(nameField);

        Box quantityBox = Box.createHorizontalBox();
        JLabel quantityLabel = new JLabel("数    量：");
        quantityBox.add(quantityLabel);
        quantityBox.add(Box.createHorizontalStrut(20));
        JTextField quantityField = new JTextField(20);
        quantityBox.add(quantityField);

        Box bBox = Box.createHorizontalBox();
        Button addButton = new Button("添加");
        //点击事件
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String number  = numberField.getText().trim();
                String name = nameField.getText().trim();
                String quantity = quantityField.getText().trim();
                if(number.equals("")||name.equals("")||quantity.equals("")){
                    JOptionPane.showMessageDialog(jf,"添加内容不能为空","提示",JOptionPane.WARNING_MESSAGE);
                }else {
                    int partsUsage = new PartsUsageOperate().addPartsUsage(Integer.parseInt(number), name, Integer.parseInt(quantity));
                    if (partsUsage!=0){
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

        vBox.add(Box.createVerticalStrut(45));
        vBox.add(numberBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(nameBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(quantityBox);
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

    public PartsUsageDialog(JFrame jf, String title, Boolean is,int id,String number,int quantity) {
        super(jf,title,is);
        this.setIconImage(new ImageIcon("./src/image/logo.jpg").getImage());
        this.setBounds(750,230,300,300);
        Box vBox = Box.createVerticalBox();

        Box numberBox = Box.createHorizontalBox();
        JLabel numberLabel = new JLabel("编    号：");
        numberBox.add(numberLabel);
        numberBox.add(Box.createHorizontalStrut(20));
        JTextField numberField = new JTextField(20);
        numberField.setText(id+"");
        numberBox.add(numberField);

        Box nameBox = Box.createHorizontalBox();
        JLabel nameLabel = new JLabel("零件名：");
        nameBox.add(nameLabel);
        nameBox.add(Box.createHorizontalStrut(20));
        JTextField nameField = new JTextField(20);
        nameField.setText(number);
        nameBox.add(nameField);

        Box quantityBox = Box.createHorizontalBox();
        JLabel quantityLabel = new JLabel("数    量：");
        quantityBox.add(quantityLabel);
        quantityBox.add(Box.createHorizontalStrut(20));
        JTextField quantityField = new JTextField(20);
        quantityField.setText(quantity+"");
        quantityBox.add(quantityField);

        Box bBox = Box.createHorizontalBox();
        Button addButton = new Button("修改");
        //点击事件
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String number  = numberField.getText().trim();
                String name = nameField.getText().trim();
                String quantity = quantityField.getText().trim();
                if(number.equals("")||name.equals("")||quantity.equals("")){
                    JOptionPane.showMessageDialog(jf,"修改内容不能为空","提示",JOptionPane.WARNING_MESSAGE);
                }else {
                    int partsUsage = new PartsUsageOperate().updatePartsUsage(Integer.parseInt(number), name, Integer.parseInt(quantity));
                    if (partsUsage!=0){
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

        vBox.add(Box.createVerticalStrut(45));
        vBox.add(numberBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(nameBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(quantityBox);
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
