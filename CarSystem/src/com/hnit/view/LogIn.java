package com.hnit.view;

import com.hnit.DAO.CarDAO;
import com.hnit.compoent.BGPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ClassName: LogIn
 * Description:
 *
 * @Author wzq
 * @Create 2024/6/6 21:57
 * @Version 1.0
 */
public class LogIn extends JFrame{
    CarDAO dao = new CarDAO();
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
        BGPanel bgPanel = new BGPanel(new ImageIcon("./src/image/bg.png").getImage());
        //��¼Ԫ��
        Box vBox = Box.createVerticalBox();
        //�û���
        Box uBox = Box.createHorizontalBox();
        JLabel nameJl = new JLabel("�û�����");
        JTextField jtf = new JTextField();
        jtf.setOpaque(false);//�ı���͸��
        jtf.setFont(new Font("����",Font.BOLD,15));//�������塢��С
        uBox.add(nameJl);
        uBox.add(Box.createHorizontalStrut(15));
        uBox.add(jtf);
        //����
        Box pBox = Box.createHorizontalBox();
        JLabel pwdJl = new JLabel("��    �룺");
        JPasswordField jpf = new JPasswordField(15);
        jpf.setOpaque(false);
        pBox.add(pwdJl);
        pBox.add(Box.createHorizontalStrut(15));
        pBox.add(jpf);
        //��ť
        Box bBox = Box.createHorizontalBox();
        Button log = new Button("��¼");
        Button Enroll = new Button("ע��");
        //��¼�¼�����
        log.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //��ȡ�û����������
                String username = jtf.getText().trim();
                String userPwd = new String(jpf.getPassword());
                //���ʵ�¼�ӿ�
                if(dao.VerifyLogIn(username,userPwd)==1){
                    //�ɹ���ʾ����
                    JOptionPane.showMessageDialog(jFrame,"��¼�ɹ�","��ܰ��ʾ",JOptionPane.INFORMATION_MESSAGE);
                    new ManagerUI().init();
                    jFrame.dispose();
                    System.out.println(username);
                }
                else {
                    JOptionPane.showMessageDialog(jFrame,"��¼ʧ��,�û������������","��ܰ��ʾ",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        //ע�����¼�����
        Enroll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EnrollUI().init();
                jFrame.dispose();
            }
        });


        bBox.add(log);
        bBox.add(Box.createHorizontalStrut(80));
        bBox.add(Enroll);

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
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //���ڿɼ�
        jFrame.setVisible(true);
        //���ô��ڴ�С���ɸ���
        jFrame.setResizable(false);
    }

    public static void main(String[] args) {
        new LogIn().init();
    }
}
