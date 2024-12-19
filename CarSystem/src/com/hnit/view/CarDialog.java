package com.hnit.view;

import com.hnit.DAO.CarOperate;
import com.hnit.compoent.MyListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ClassName: Dialog
 * Description:
 *
 * @Author wzq
 * @Create 2024/6/11 17:04
 * @Version 1.0
 */
public class CarDialog extends JDialog {
    private MyListener myListener;

    public void setMyListener(MyListener Listener) {
        this.myListener = Listener;
    }
    public CarDialog(JFrame jf, String title, Boolean is) {
        super(jf,title,is);
        this.setIconImage(new ImageIcon("./src/image/logo.jpg").getImage());
        this.setBounds(750,230,300,300);
        Box vBox = Box.createVerticalBox();

        Box numberBox = Box.createHorizontalBox();
        JLabel numberLabel = new JLabel("车牌号：");
        numberBox.add(numberLabel);
        numberBox.add(Box.createHorizontalStrut(20));
        JTextField numberField = new JTextField(20);
        numberBox.add(numberField);

        Box modelBox = Box.createHorizontalBox();
        JLabel modelLabel = new JLabel("车    型：");
        modelBox.add(modelLabel);
        modelBox.add(Box.createHorizontalStrut(20));
        JTextField modelField = new JTextField(20);
        modelBox.add(modelField);

        Box factoryBox = Box.createHorizontalBox();
        JLabel factoryLabel = new JLabel("生产厂：");
        factoryBox.add(factoryLabel);
        factoryBox.add(Box.createHorizontalStrut(20));
        JTextField factoryField = new JTextField(20);
        factoryBox.add(factoryField);

        Box nameBox = Box.createHorizontalBox();
        JLabel nameLabel = new JLabel("车主名：");
        nameBox.add(nameLabel);
        nameBox.add(Box.createHorizontalStrut(20));
        JTextField nameField = new JTextField(20);
        nameBox.add(nameField);

        Box bBox = Box.createHorizontalBox();
        Button addButton = new Button("添加");
        //点击事件
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String number  = numberField.getText().trim();
                String model = modelField.getText().trim();
                String factory = factoryField.getText().trim();
                String name = nameField.getText().trim();
                if(number.equals("")||model.equals("")||factory.equals("")||name.equals("")){
                    JOptionPane.showMessageDialog(jf,"添加内容不能为空","提示",JOptionPane.WARNING_MESSAGE);
                }else {
                    int car = new CarOperate().addCar(number, model, factory, name);
                    if(car!=0){
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

        vBox.add(Box.createVerticalStrut(35));
        vBox.add(numberBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(modelBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(factoryBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(nameBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(bBox);
        vBox.add(Box.createVerticalStrut(50));

        Box hBox = Box.createHorizontalBox();
        hBox.add(Box.createHorizontalStrut(20));
        hBox.add(vBox);
        hBox.add(Box.createHorizontalStrut(20));

        this.add(hBox);
        //设置可见和关闭操作
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public CarDialog(JFrame jf, String title, Boolean is,String number,String model,String manufacturer,String name) {
        super(jf,title,is);
        this.setIconImage(new ImageIcon("./src/image/logo.jpg").getImage());
        this.setBounds(750,230,300,300);
        Box vBox = Box.createVerticalBox();

        Box numberBox = Box.createHorizontalBox();
        JLabel numberLabel = new JLabel("车牌号：");
        numberBox.add(numberLabel);
        numberBox.add(Box.createHorizontalStrut(20));
        JTextField numberField = new JTextField(20);
        numberField.setText(number);
        numberBox.add(numberField);

        Box modelBox = Box.createHorizontalBox();
        JLabel modelLabel = new JLabel("车    型：");
        modelBox.add(modelLabel);
        modelBox.add(Box.createHorizontalStrut(20));
        JTextField modelField = new JTextField(20);
        modelField.setText(model);
        modelBox.add(modelField);

        Box factoryBox = Box.createHorizontalBox();
        JLabel factoryLabel = new JLabel("生产厂：");
        factoryBox.add(factoryLabel);
        factoryBox.add(Box.createHorizontalStrut(20));
        JTextField factoryField = new JTextField(20);
        factoryField.setText(manufacturer);
        factoryBox.add(factoryField);

        Box nameBox = Box.createHorizontalBox();
        JLabel nameLabel = new JLabel("车主名：");
        nameBox.add(nameLabel);
        nameBox.add(Box.createHorizontalStrut(20));
        JTextField nameField = new JTextField(20);
        nameField.setText(name);
        nameBox.add(nameField);

        Box bBox = Box.createHorizontalBox();
        Button addButton = new Button("修改");
        //点击事件
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String number  = numberField.getText().trim();
                String model = modelField.getText().trim();
                String factory = factoryField.getText().trim();
                String name = nameField.getText().trim();
                if(number.equals("")||model.equals("")||factory.equals("")||name.equals("")){
                    JOptionPane.showMessageDialog(jf,"修改内容不能为空","提示",JOptionPane.WARNING_MESSAGE);
                }else {
                    int car = new CarOperate().updateCar(numberField.getText(), modelField.getText(), factoryField.getText(), nameField.getText());
                    if(car!=0){
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

        vBox.add(Box.createVerticalStrut(35));
        vBox.add(numberBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(modelBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(factoryBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(nameBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(bBox);
        vBox.add(Box.createVerticalStrut(50));

        Box hBox = Box.createHorizontalBox();
        hBox.add(Box.createHorizontalStrut(20));
        hBox.add(vBox);
        hBox.add(Box.createHorizontalStrut(20));

        this.add(hBox);
        //设置可见和关闭操作
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

}
