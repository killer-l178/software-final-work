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
        BGPanel bgPanel = new BGPanel(new ImageIcon("./src/image/bgEnroll.jpg").getImage());

        //内容
        Box vBox = Box.createVerticalBox();
        //用户名
        Box uBox = Box.createHorizontalBox();
        JLabel nameJl = new JLabel("请输入用户名：");
        JTextField jtf = new JTextField();
        jtf.setOpaque(false);//文本框透明
        jtf.setFont(new Font("黑体",Font.BOLD,15));//设置字体、大小
        uBox.add(nameJl);
        uBox.add(Box.createHorizontalStrut(15));
        uBox.add(jtf);
        //密码
        Box pBox = Box.createHorizontalBox();
        JLabel pwdJl = new JLabel("请输入密码：    ");
        JPasswordField jpf = new JPasswordField(15);
        jpf.setOpaque(false);
        pBox.add(pwdJl);
        pBox.add(Box.createHorizontalStrut(15));
        pBox.add(jpf);
        //按钮
        Box bBox = Box.createHorizontalBox();
        Button enroll = new Button("注册");
        Button back = new Button("返回");

        //按钮点击事件
        enroll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取用户输入的数据
                String username = jtf.getText().trim();
                String userPwd = new String(jpf.getPassword());
                //注册函数
                if(new CarDAO().Enroll(username,userPwd)!=1&&(username.isEmpty()||userPwd.isEmpty())){
                    //失败提示窗口
                    JOptionPane.showMessageDialog(jFrame,"注册失败,用户名已存在或用户名不能为空","温馨提示",JOptionPane.INFORMATION_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(jFrame,"注册成功","温馨提示",JOptionPane.INFORMATION_MESSAGE);
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

        //关闭操作
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // jFrame.pack();
        //窗口可见
        jFrame.setVisible(true);
        //设置窗口大小不可更改
        jFrame.setResizable(false);

    }
}
