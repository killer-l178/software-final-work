package com.hnit.view;

import com.hnit.DAO.EmployeesOperate;
import com.hnit.DAO.RepairRecordOperate;
import com.hnit.compoent.MyListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ClassName: RepairRecordDialog
 * Description:
 *
 * @Author wzq
 * @Create 2024/6/12 10:59
 * @Version 1.0
 */


public class RepairRecordDialog  extends JDialog {
    private MyListener myListener;

    public void setMyListener(MyListener Listener) {
        this.myListener = Listener;
    }

    public RepairRecordDialog (JFrame jf, String title, Boolean is) {
        super(jf,title,is);
        this.setIconImage(new ImageIcon("./src/image/logo.jpg").getImage());
        this.setBounds(750,230,300,350);
        Box vBox = Box.createVerticalBox();

        Box idBox = Box.createHorizontalBox();
        JLabel idLabel = new JLabel("��    �ţ�");
        idBox.add(idLabel);
        idBox.add(Box.createHorizontalStrut(25));
        JTextField idField = new JTextField(20);
        idBox.add(idField);

        Box numberBox = Box.createHorizontalBox();
        JLabel numberLabel = new JLabel("��    �ţ�");
        numberBox.add(numberLabel);
        numberBox.add(Box.createHorizontalStrut(25));
        JTextField numberField = new JTextField(20);
        numberBox.add(numberField);

        Box projectBox = Box.createHorizontalBox();
        JLabel projectLabel = new JLabel("������Ŀ��");
        projectBox.add(projectLabel);
        projectBox.add(Box.createHorizontalStrut(15));
        JTextField projectField = new JTextField(20);
        projectBox.add(projectField);


        Box EIdBox = Box.createHorizontalBox();
        JLabel EIdLabel = new JLabel("��    �ţ�");
        EIdBox.add(EIdLabel);
        EIdBox.add(Box.createHorizontalStrut(25));
        JTextField  EIdField = new JTextField(20);
        EIdBox.add(EIdField);

        Box SubmitBox = Box.createHorizontalBox();
        JLabel SubmitLabel = new JLabel("�������ڣ�");
        SubmitBox.add(SubmitLabel);
        SubmitBox.add(Box.createHorizontalStrut(15));
        JTextField  SubmitField = new JTextField(20);
        SubmitBox.add(SubmitField);

        Box  CompletionBox = Box.createHorizontalBox();
        JLabel  CompletionLabel = new JLabel("�깤���ڣ�");
        CompletionBox.add(CompletionLabel);
        CompletionBox.add(Box.createHorizontalStrut(15));
        JTextField CompletionField = new JTextField(20);
        CompletionBox.add(CompletionField);

        Box hourBox = Box.createHorizontalBox();
        JLabel hourLabel = new JLabel("����ʱ����");
        hourBox.add(hourLabel);
        hourBox.add(Box.createHorizontalStrut(15));
        JTextField  hourField = new JTextField(20);
        hourBox.add(hourField);

        Box bBox = Box.createHorizontalBox();
        Button addButton = new Button("���");
        //����¼�
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText().trim();
                String number = numberField.getText().trim();
                String project = projectField.getText().trim();
                String Eid = EIdField.getText().trim();
                String submit = SubmitField.getText().trim();
                String  Completion = CompletionField.getText().trim();
                String hour = hourField.getText().trim();
                if(id.isEmpty()||project.isEmpty()||number.isEmpty()||Eid.isEmpty()||submit.isEmpty()||Completion.isEmpty()||hour.isEmpty()){
                    JOptionPane.showMessageDialog(jf,"������ݲ���Ϊ��","��ʾ",JOptionPane.WARNING_MESSAGE);
                }else {
                    int repairRecord = new RepairRecordOperate().addRepairRecord(Integer.parseInt(id), number, project, submit,
                            Completion, Integer.parseInt(Eid), Integer.parseInt(hour));
                    if (repairRecord!=0){
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
        vBox.add(idBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(numberBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(projectBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(SubmitBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(CompletionBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(EIdBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(hourBox);
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

    public RepairRecordDialog (JFrame jf, String title, Boolean is,int id,String number,String project,String submit,String completion,int EID,int hour) {
        super(jf,title,is);
        this.setIconImage(new ImageIcon("./src/image/logo.jpg").getImage());
        this.setBounds(750,230,300,350);
        Box vBox = Box.createVerticalBox();

        Box idBox = Box.createHorizontalBox();
        JLabel idLabel = new JLabel("��    �ţ�");
        idBox.add(idLabel);
        idBox.add(Box.createHorizontalStrut(25));
        JTextField idField = new JTextField(20);
        idField.setText(id+"");
        idBox.add(idField);

        Box numberBox = Box.createHorizontalBox();
        JLabel numberLabel = new JLabel("��    �ţ�");
        numberBox.add(numberLabel);
        numberBox.add(Box.createHorizontalStrut(25));
        JTextField numberField = new JTextField(20);
        numberField.setText(number);
        numberBox.add(numberField);

        Box projectBox = Box.createHorizontalBox();
        JLabel projectLabel = new JLabel("������Ŀ��");
        projectBox.add(projectLabel);
        projectBox.add(Box.createHorizontalStrut(15));
        JTextField projectField = new JTextField(20);
        projectField.setText(project);
        projectBox.add(projectField);


        Box EIdBox = Box.createHorizontalBox();
        JLabel EIdLabel = new JLabel("��    �ţ�");
        EIdBox.add(EIdLabel);
        EIdBox.add(Box.createHorizontalStrut(25));
        JTextField  EIdField = new JTextField(20);
        EIdField.setText(EID+"");
        EIdBox.add(EIdField);

        Box SubmitBox = Box.createHorizontalBox();
        JLabel SubmitLabel = new JLabel("�������ڣ�");
        SubmitBox.add(SubmitLabel);
        SubmitBox.add(Box.createHorizontalStrut(15));
        JTextField  SubmitField = new JTextField(20);
        SubmitField.setText(submit);
        SubmitBox.add(SubmitField);

        Box  CompletionBox = Box.createHorizontalBox();
        JLabel  CompletionLabel = new JLabel("�깤���ڣ�");
        CompletionBox.add(CompletionLabel);
        CompletionBox.add(Box.createHorizontalStrut(15));
        JTextField CompletionField = new JTextField(20);
        CompletionField.setText(completion);
        CompletionBox.add(CompletionField);

        Box hourBox = Box.createHorizontalBox();
        JLabel hourLabel = new JLabel("����ʱ����");
        hourBox.add(hourLabel);
        hourBox.add(Box.createHorizontalStrut(15));
        JTextField  hourField = new JTextField(20);
        hourField.setText(hour+"");
        hourBox.add(hourField);

        Box bBox = Box.createHorizontalBox();
        Button addButton = new Button("�޸�");
        //����¼�
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText().trim();
                String number = numberField.getText().trim();
                String project = projectField.getText().trim();
                String Eid = EIdField.getText().trim();
                String submit = SubmitField.getText().trim();
                String  Completion = CompletionField.getText().trim();
                String hour = hourField.getText().trim();
                if(id.isEmpty()||project.isEmpty()||number.isEmpty()||Eid.isEmpty()||submit.isEmpty()||Completion.isEmpty()||hour.isEmpty()){
                    JOptionPane.showMessageDialog(jf,"�޸����ݲ���Ϊ��","��ʾ",JOptionPane.WARNING_MESSAGE);
                }else {
                    int repairRecord = new RepairRecordOperate().updateRepairRecord(Integer.parseInt(id), number, project, submit,
                            Completion, Integer.parseInt(Eid), Integer.parseInt(hour));
                    if (repairRecord!=0){
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
        vBox.add(idBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(numberBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(projectBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(SubmitBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(CompletionBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(EIdBox);
        vBox.add(Box.createVerticalStrut(15));
        vBox.add(hourBox);
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


