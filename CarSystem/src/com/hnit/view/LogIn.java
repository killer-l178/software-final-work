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
    JFrame jFrame = new JFrame("汽修管理系统");
    JPanel jPanel = new JPanel();
    //标题
    JLabel title =new JLabel("欢迎来到汽修管理系统    ");


    public void init(){
        //设置tittle字体和大小
        title.setFont(new Font("黑体",Font.PLAIN,13));
        //设置标题和图标
        jFrame.setIconImage(new ImageIcon("./src/image/logo.jpg").getImage());
        //设置容器大小、位置
        jFrame.setBounds(500,300,500,350);

        //添加界面登录元素
        //背景
        BGPanel bgPanel = new BGPanel(new ImageIcon("./src/image/bg.png").getImage());
        //登录元素
        Box vBox = Box.createVerticalBox();
        //用户名
        Box uBox = Box.createHorizontalBox();
        JLabel nameJl = new JLabel("用户名：");
        JTextField jtf = new JTextField();
        jtf.setOpaque(false);//文本框透明
        jtf.setFont(new Font("黑体",Font.BOLD,15));//设置字体、大小
        uBox.add(nameJl);
        uBox.add(Box.createHorizontalStrut(15));
        uBox.add(jtf);
        //密码
        Box pBox = Box.createHorizontalBox();
        JLabel pwdJl = new JLabel("密    码：");
        JPasswordField jpf = new JPasswordField(15);
        jpf.setOpaque(false);
        pBox.add(pwdJl);
        pBox.add(Box.createHorizontalStrut(15));
        pBox.add(jpf);
        //按钮
        Box bBox = Box.createHorizontalBox();
        Button log = new Button("登录");
        Button Enroll = new Button("注册");
        //登录事件监听
        log.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取用户输入的数据
                String username = jtf.getText().trim();
                String userPwd = new String(jpf.getPassword());
                //访问登录接口
                if(dao.VerifyLogIn(username,userPwd)==1){
                    //成功提示窗口
                    JOptionPane.showMessageDialog(jFrame,"登录成功","温馨提示",JOptionPane.INFORMATION_MESSAGE);
                    new ManagerUI().init();
                    jFrame.dispose();
                    System.out.println(username);
                }
                else {
                    JOptionPane.showMessageDialog(jFrame,"登录失败,用户名或密码错误","温馨提示",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        //注册点击事件监听
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

        //关闭操作
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //窗口可见
        jFrame.setVisible(true);
        //设置窗口大小不可更改
        jFrame.setResizable(false);
    }

    public static void main(String[] args) {
        new LogIn().init();
    }
}
