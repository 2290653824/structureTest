package com.zj.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class loginPlatform extends JFrame {

    ImageIcon img=new ImageIcon("img/CD4356.jpg");
    private JLabel imglabel=new JLabel(img);

    JPanel btnPanel=new JPanel();
    private JLabel usernamelabel=new JLabel("账号:");
    private JTextField username=new JTextField(8);
    private JLabel userpasslabel=new JLabel("密码:");
    private JTextField userpass=new JTextField(8);
    private JButton btn=new JButton("登陆");
    private JLabel report=new JLabel();

    public loginPlatform(){
        this.init();
        this.addComponent();
        this.addListener();
    }

    private void init(){
        this.setTitle("个人通讯录");
        this.setSize(600,410);

        Toolkit toolkit=Toolkit.getDefaultToolkit();
        Image icon = toolkit.getImage("img/titleIcon.jpg");
        this.setIconImage(icon);

        Dimension screenSize =toolkit.getScreenSize();
        int x=(screenSize.width-this.getWidth())/2;
        int y=(screenSize.height-this.getHeight())/2;
        this.setLocation(x,y);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addComponent() {
        imglabel.setBounds(0,0,img.getIconWidth(),img.getIconHeight());
        this.add(imglabel,BorderLayout.NORTH);

        btnPanel.setLayout(null);
        usernamelabel.setBounds(40,17,50,28);
        usernamelabel.setFont(new Font("隶书", Font.PLAIN, 20));
        username.setBounds(100,17,150,28);
        userpasslabel.setBounds(265,17,50,28);
        userpasslabel.setFont(new Font("隶书", Font.PLAIN, 20));
        userpass.setBounds(325,17,150,28);
        btn.setBackground(Color.lightGray);
        btn.setBounds(500,16,60,30);
        btnPanel.add(usernamelabel);
        btnPanel.add(username);
        btnPanel.add(userpasslabel);
        btnPanel.add(userpass);
        btnPanel.add(btn);

        report.setBounds(200,50,200,20);

        report.setFont(new Font("隶书", Font.PLAIN, 15));
        report.setText("账号密码请联系管理员获取");
        report.setForeground(Color.red);
        btnPanel.add(report);
        this.add(btnPanel);
    }

    // 为按钮设置监听器
    private void addListener() {
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(username.getText().equals("root") && userpass.getText().equals("123456")){
                    showPersonAdmin();
                    // 进入JDialog管理界面后，关闭JFrame窗口
                    dispose();
                }else{
                    report.setText("账号或密码错误！");
                    username.setText("");
                    userpass.setText("");
                }
            }
        });
    }

    public abstract void showPersonAdmin();

}
