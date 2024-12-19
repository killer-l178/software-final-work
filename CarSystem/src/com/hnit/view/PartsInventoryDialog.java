package com.hnit.view;

import com.hnit.DAO.PartslnventoryOperate;
import com.hnit.DAO.RepairRecordOperate;
import com.hnit.compoent.MyListener;

import javax.annotation.processing.Completion;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ClassName: PartsInventoryDialog
 * Description:
 *
 * @Author wzq
 * @Create 2024/6/12 16:16
 * @Version 1.0
 */
//PartsInventoryDialog

public class PartsInventoryDialog  extends JDialog {
    private MyListener myListener;

    public void setMyListener(MyListener Listener) {
        this.myListener = Listener;
    }

    public PartsInventoryDialog (JFrame jf, String title, Boolean is) {
        super(jf,title,is);
        this.setIconImage(new ImageIcon("./src/image/logo.jpg").getImage());
        this.setBounds(750,230,300,350);
        Box vBox = Box.createVerticalBox();

        Box numberBox = Box.createHorizontalBox();
        JLabel numberLabel = new JLabel("����ţ�");
        numberBox.add(numberLabel);
        numberBox.add(Box.createHorizontalStrut(20));
        JTextField numberField = new JTextField(20);
        numberBox.add(numberField);

        Box nameBox = Box.createHorizontalBox();
        JLabel nameLabel = new JLabel("�������");
        nameBox.add(nameLabel);
        nameBox.add(Box.createHorizontalStrut(20));
        JTextField nameField = new JTextField(20);
        nameBox.add(nameField);

        Box costBox = Box.createHorizontalBox();
        JLabel costLabel = new JLabel("��    ����");
        costBox.add(costLabel);
        costBox.add(Box.createHorizontalStrut(25));
        JTextField costField = new JTextField(20);
        costBox.add(costField);


        Box priceBox = Box.createHorizontalBox();
        JLabel priceLabel = new JLabel("��    ��");
        priceBox.add(priceLabel);
        priceBox.add(Box.createHorizontalStrut(25));
        JTextField  priceField = new JTextField(20);
        priceBox.add(priceField);

        Box stockBox = Box.createHorizontalBox();
        JLabel stockLabel = new JLabel("��    �棺");
        stockBox.add(stockLabel);
        stockBox.add(Box.createHorizontalStrut(25));
        JTextField  stockField = new JTextField(20);
        stockBox.add(stockField);

        Box  mixStockBox = Box.createHorizontalBox();
        JLabel  mixStockLabel = new JLabel("��Ϳ�棺");
        mixStockBox.add(mixStockLabel);
        mixStockBox.add(Box.createHorizontalStrut(10));
        JTextField mixStockField = new JTextField(20);
        mixStockBox.add(mixStockField);

        Box orderBox = Box.createHorizontalBox();
        JLabel orderLabel = new JLabel("��������");
        orderBox.add(orderLabel);
        orderBox.add(Box.createHorizontalStrut(20));
        JTextField  orderField = new JTextField(20);
        orderBox.add(orderField);

        Box bBox = Box.createHorizontalBox();
        Button addButton = new Button("���");
        //����¼�
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String number = numberField.getText().trim();
                String name = nameField.getText().trim();
                String cost = costField.getText().trim();
                String price = priceField.getText().trim();
                String stock = stockField.getText().trim();
                String  mixStock = mixStockField.getText().trim();
                String order = orderField.getText().trim();
                if(name.isEmpty()||cost.isEmpty()||number.isEmpty()||price.isEmpty()||stock.isEmpty()||mixStock.isEmpty()||order.isEmpty()){
                    JOptionPane.showMessageDialog(jf,"������ݲ���Ϊ��","��ʾ",JOptionPane.WARNING_MESSAGE);
                }else {

                    int partsInventory = new PartslnventoryOperate().addPartslnventory(number, name, Integer.parseInt(cost),
                            Integer.parseInt(price), Integer.parseInt(stock), Integer.parseInt(mixStock), Integer.parseInt(order));
                    if(partsInventory!=0){
                        JOptionPane.showMessageDialog(jf,"��ӳɹ�","��ʾ",JOptionPane.INFORMATION_MESSAGE);
                        if (myListener!=null)
                            myListener.onDataAdded();
                        dispose();
                    }else {
                        JOptionPane.showMessageDialog(jf,"������ݸ�ʽ����","��ʾ",JOptionPane.WARNING_MESSAGE);
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
        vBox.add(costBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(priceBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(stockBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(mixStockBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(orderBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(bBox);
        vBox.add(Box.createVerticalStrut(25));

        Box hBox = Box.createHorizontalBox();
        hBox.add(Box.createHorizontalStrut(25));
        hBox.add(vBox);
        hBox.add(Box.createHorizontalStrut(25));

        this.add(hBox);
        //���ÿɼ��͹رղ���
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    }

    public PartsInventoryDialog (JFrame jf, String title, Boolean is,String name,int cost,int price,int stock,int minStock,int order,String number) {
        super(jf,title,is);
        this.setIconImage(new ImageIcon("./src/image/logo.jpg").getImage());
        this.setBounds(750,230,300,350);
        Box vBox = Box.createVerticalBox();

        Box numberBox = Box.createHorizontalBox();
        JLabel numberLabel = new JLabel("����ţ�");
        numberBox.add(numberLabel);
        numberBox.add(Box.createHorizontalStrut(20));
        JTextField numberField = new JTextField(20);
        numberField.setText(number);
        numberBox.add(numberField);

        Box nameBox = Box.createHorizontalBox();
        JLabel nameLabel = new JLabel("�������");
        nameBox.add(nameLabel);
        nameBox.add(Box.createHorizontalStrut(20));
        JTextField nameField = new JTextField(20);
        nameField.setText(name);
        nameBox.add(nameField);

        Box costBox = Box.createHorizontalBox();
        JLabel costLabel = new JLabel("��    ����");
        costBox.add(costLabel);
        costBox.add(Box.createHorizontalStrut(25));
        JTextField costField = new JTextField(20);
        costField.setText(cost+"");
        costBox.add(costField);


        Box priceBox = Box.createHorizontalBox();
        JLabel priceLabel = new JLabel("��    ��");
        priceBox.add(priceLabel);
        priceBox.add(Box.createHorizontalStrut(25));
        JTextField  priceField = new JTextField(20);
        priceField.setText(price+"");
        priceBox.add(priceField);

        Box stockBox = Box.createHorizontalBox();
        JLabel stockLabel = new JLabel("��    �棺");
        stockBox.add(stockLabel);
        stockBox.add(Box.createHorizontalStrut(25));
        JTextField  stockField = new JTextField(20);
        stockField.setText(stock+"");
        stockBox.add(stockField);

        Box  mixStockBox = Box.createHorizontalBox();
        JLabel  mixStockLabel = new JLabel("��Ϳ�棺");
        mixStockBox.add(mixStockLabel);
        mixStockBox.add(Box.createHorizontalStrut(10));
        JTextField mixStockField = new JTextField(20);
        mixStockField.setText(minStock+"");
        mixStockBox.add(mixStockField);

        Box orderBox = Box.createHorizontalBox();
        JLabel orderLabel = new JLabel("��������");
        orderBox.add(orderLabel);
        orderBox.add(Box.createHorizontalStrut(20));
        JTextField  orderField = new JTextField(20);
        orderField.setText(order+"");
        orderBox.add(orderField);

        Box bBox = Box.createHorizontalBox();
        Button addButton = new Button("�޸�");
        //����¼�
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String number = numberField.getText().trim();
                String name = nameField.getText().trim();
                String cost = costField.getText().trim();
                String price = priceField.getText().trim();
                String stock = stockField.getText().trim();
                String  mixStock = mixStockField.getText().trim();
                String order = orderField.getText().trim();
                if(name.isEmpty()||cost.isEmpty()||number.isEmpty()||price.isEmpty()||stock.isEmpty()||mixStock.isEmpty()||order.isEmpty()){
                    JOptionPane.showMessageDialog(jf,"�޸����ݲ���Ϊ��","��ʾ",JOptionPane.WARNING_MESSAGE);
                }else {

                    int partsInventory = new PartslnventoryOperate().updatePartInventory(name,Integer.parseInt(cost),
                            Integer.parseInt(price), Integer.parseInt(stock), Integer.parseInt(mixStock), Integer.parseInt(order),number);
                    if(partsInventory!=0){
                        JOptionPane.showMessageDialog(jf,"�޸ĳɹ�","��ʾ",JOptionPane.INFORMATION_MESSAGE);
                        if (myListener!=null)
                            myListener.onDataAdded();
                        dispose();
                    }else {
                        JOptionPane.showMessageDialog(jf,"�޸����ݸ�ʽ����","��ʾ",JOptionPane.WARNING_MESSAGE);
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
        vBox.add(costBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(priceBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(stockBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(mixStockBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(orderBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(bBox);
        vBox.add(Box.createVerticalStrut(25));

        Box hBox = Box.createHorizontalBox();
        hBox.add(Box.createHorizontalStrut(25));
        hBox.add(vBox);
        hBox.add(Box.createHorizontalStrut(25));

        this.add(hBox);
        //���ÿɼ��͹رղ���
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    }
}
