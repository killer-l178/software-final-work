package com.hnit.view;

import com.hnit.DAO.CarDAO;
import com.hnit.compoent.BGPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ClassName: EnrollUI
 * Description:
 *
 * @Author wzq
 * @Create 2024/6/7 15:44
 * @Version 1.0
 */
public class EnrollUI {
    JFrame jFrame = new JFrame("���޹���ϵͳ");
    JPanel jPanel = new JPanel();
    //����
    JLabel title =new JLabel("��ӭ�������޹���ϵͳ    ");

    public void init(){
        //����tittle����ʹ�С
        title.setFont(new Font("����",Font.PLAIN,13));
        //���ñ����ͼ��
        jFrame.setIconImage(new ImageIcon("./src/image/logo.jpg").getImage());
        //����������С��λ��
        jFrame.setBounds(500,300,500,350);

        //��ӽ����¼Ԫ��
        //����
        BGPanel bgPanel = new BGPanel(new ImageIcon("./src/image/bgEnroll.jpg").getImage());

        //����
        Box vBox = Box.createVerticalBox();
        //�û���
        Box uBox = Box.createHorizontalBox();
        JLabel nameJl = new JLabel("�������û�����");
        JTextField jtf = new JTextField();
        jtf.setOpaque(false);//�ı���͸��
        jtf.setFont(new Font("����",Font.BOLD,15));//�������塢��С
        uBox.add(nameJl);
        uBox.add(Box.createHorizontalStrut(15));
        uBox.add(jtf);
        //����
        Box pBox = Box.createHorizontalBox();
        JLabel pwdJl = new JLabel("���������룺    ");
        JPasswordField jpf = new JPasswordField(15);
        jpf.setOpaque(false);
        pBox.add(pwdJl);
        pBox.add(Box.createHorizontalStrut(15));
        pBox.add(jpf);
        //��ť
        Box bBox = Box.createHorizontalBox();
        Button enroll = new Button("ע��");
        Button back = new Button("����");

        //��ť����¼�
        enroll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //��ȡ�û����������
                String username = jtf.getText().trim();
                String userPwd = new String(jpf.getPassword());
                //ע�ắ��
                if(new CarDAO().Enroll(username,userPwd)!=1&&(username.isEmpty()||userPwd.isEmpty())){
                    //ʧ����ʾ����
                    JOptionPane.showMessageDialog(jFrame,"ע��ʧ��,�û����Ѵ��ڻ��û�������Ϊ��","��ܰ��ʾ",JOptionPane.INFORMATION_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(jFrame,"ע��ɹ�","��ܰ��ʾ",JOptionPane.INFORMATION_MESSAGE);
                    jFrame.dispose();
                    new LogIn().init();

                }
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                new LogIn().init();
            }
        });

        bBox.add(enroll);
        bBox.add(Box.createHorizontalStrut(80));
        bBox.add(back);

        vBox.add(Box.createVerticalStrut(20));
        vBox.add(title);
        vBox.add(Box.createVerticalStrut(30));
        vBox.add(uBox);
        vBox.add(Box.createVerticalStrut(30));
        vBox.add(pBox);
        vBox.add(Box.createVerticalStrut(40));
        vBox.add(bBox);
        bgPanel.add(vBox);

        jFrame.add(bgPanel);

        //�رղ���
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // jFrame.pack();
        //���ڿɼ�
        jFrame.setVisible(true);
        //���ô��ڴ�С���ɸ���
        jFrame.setResizable(false);

    }
}
