package com.zj.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class MainPlatform extends JFrame {

    public JToolBar toolBar=new JToolBar();
    JButton adminBtn=new JButton();
    JButton lendBtn=new JButton();
    JButton recycleBtn=new JButton();
    JButton billBtn=new JButton();

    private JLabel tableName=new JLabel("个人通讯录");

    public JScrollPane tablePane=new JScrollPane();
    public JTable table=new JTable();

    private JLabel idLabel=new JLabel("序号");
    private JLabel nameLabel=new JLabel("姓名");
    private JLabel priceLabel=new JLabel("地址");
    private JLabel numberLabel=new JLabel("电话");

    public JTextField addIdText=new JTextField(6);
    public JTextField addNameText=new JTextField(6);
    public JTextField addSiteText =new JTextField(6);
    public JTextField addPhoneText =new JTextField(6);
    private JButton addBtn=new JButton("新增联系人");

    public JTextField updateIdText=new JTextField(6);
    public JTextField updateNameText=new JTextField(6);
    public JTextField updateSiteText =new JTextField(6);
    public JTextField updatePhoneText =new JTextField(6);
    private JButton updateBtn=new JButton("修改联系人");

    public JTextField delIdText=new JTextField(6);
    private JButton delBtn=new JButton("删除联系人");


    public MainPlatform() {
        this.init();
        this.addComponent();
        this.addListener();
    }

    private void init() {
        this.setTitle("个人通讯录");

        Toolkit toolkit=Toolkit.getDefaultToolkit();
        Image icon = toolkit.getImage("img/titleIcon.jpg");
        this.setIconImage(icon);

        this.setSize(600,450);

        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    // 为窗口添加组件
    private void addComponent() {


        toolBar.add(adminBtn);
        toolBar.add(lendBtn);
        toolBar.add(recycleBtn);
        toolBar.add(billBtn);
        toolBar.setBounds(20,0,560,65);
        this.add(toolBar, BorderLayout.NORTH);

        toolBar.setFloatable(true);

        this.setLayout(null);

        tableName.setBounds(250,80,100,25);

        tableName.setFont(new Font("华文隶书", Font.PLAIN, 23));
        tableName.setForeground(Color.BLACK.brighter());
        this.add(tableName);

        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.setEnabled(false);


        tablePane.setBounds(50,110,500,170);
        tablePane.setViewportView(table);
        this.add(tablePane);

        idLabel.setBounds(50,290,70,25);
        nameLabel.setBounds(150,290,70,25);
        priceLabel.setBounds(250,290,70,25);
        numberLabel.setBounds(350,290,70,25);
        idLabel.setFont(new Font("隶书", Font.PLAIN, 17));
        nameLabel.setFont(new Font("隶书", Font.PLAIN, 17));
        priceLabel.setFont(new Font("隶书", Font.PLAIN, 17));
        numberLabel.setFont(new Font("隶书", Font.PLAIN, 17));
        this.add(idLabel);
        this.add(nameLabel);
        this.add(priceLabel);
        this.add(numberLabel);


        addIdText.setBounds(50,320,80,25);
        addNameText.setBounds(150,320,80,25);
        addSiteText.setBounds(250,320,80,25);
        addPhoneText.setBounds(350,320,80,25);
        addBtn.setBounds(450,320,100,25);
        this.add(addIdText);
        this.add(addNameText);
        this.add(addSiteText);
        this.add(addPhoneText);
        addBtn.setBackground(Color.lightGray);
        addBtn.setFont(new Font("隶书", Font.PLAIN, 16));
        this.add(addBtn);


        updateIdText.setBounds(50,350,80,25);
        updateNameText.setBounds(150,350,80,25);
        updateSiteText.setBounds(250,350,80,25);
        updatePhoneText.setBounds(350,350,80,25);
        updateBtn.setBounds(450,350,100,25);
        this.add(updateIdText);
        this.add(updateNameText);
        this.add(updateSiteText);
        this.add(updatePhoneText);
        updateBtn.setBackground(Color.lightGray);
        updateBtn.setFont(new Font("隶书", Font.PLAIN, 16));
        this.add(updateBtn);


        delIdText.setBounds(50,380,80,25);
        delBtn.setBounds(450,380,100,25);
        this.add(delIdText);
        delBtn.setBackground(Color.lightGray);
        delBtn.setFont(new Font("隶书", Font.PLAIN, 16));
        this.add(delBtn);

    }

    // 为按钮添加监听器
    private void addListener(){
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertPerson();
            }
        });
        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePerson();
            }
        });
        delBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletePerson();
            }
        });
    }

    //查询方法
    public abstract void getList();

    //添加方法
    public abstract void insertPerson();

    //修改方法
    public abstract void updatePerson();

    //删除方法
    public abstract void deletePerson();

}

