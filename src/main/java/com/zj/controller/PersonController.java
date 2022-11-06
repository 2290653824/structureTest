package com.zj.controller;

import com.zj.domain.Person;
import com.zj.service.PersonService;
import com.zj.view.MainPlatform;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;

public class PersonController extends MainPlatform {

    // 创建BookService实例化对象
    private PersonService personService =new PersonService();

    public PersonController() {
        getList();
    }

    // 将查询的数据转换成二维数组的格式，作为表格中表体的内容
    private String[][] list2Array(ArrayList<Person> list){
        String[][] body=new String[list.size()][4];
        for(int i=0;i<list.size();i++){
            Person person=list.get(i);
            body[i][0]=String.valueOf(person.getId());
            body[i][1]=person.getName();
            // valueOf()方法返回非字符串类型数据的字符串表现形式
            body[i][2]= String.valueOf(person.getSite());
            body[i][3]= String.valueOf(person.getPhone());
        }
        return body;
    }

    @Override
    public void getList() {
        String[] head={"序号","姓名","地址","电话"};
        // 将queryAll()方法返回的数据封装到集合中
        ArrayList<Person> list= (ArrayList<Person>) personService.queryAll();
        // 定义一个String类型的二维数组存储list2Array()方法返回的二维数组，这个二位数组就是表格的表体内容
        String[][] body=list2Array(list);
        TableModel tableModel=new DefaultTableModel(body,head);
        table.setModel(tableModel);
    }

    @Override
    public void insertPerson() {
        int id=Integer.valueOf(addIdText.getText());
        String name=addNameText.getText();
        String site= addSiteText.getText();
        String phone= addPhoneText.getText();
        boolean addSuccess= personService.addBook(id,name,site,phone);
        if(addSuccess){
            getList();
        }else{
            JOptionPane.showMessageDialog(this,"已存在！");
        }
    }

    @Override
    public void updatePerson() {
        int id=Integer.valueOf(updateIdText.getText());
        String name=updateNameText.getText();
        String site= updateSiteText.getText();
        String phone= updatePhoneText.getText();
        boolean updateSuccess= personService.updateBook(id,name,site,phone);
        if(updateSuccess){
            getList();
        }else{
            JOptionPane.showMessageDialog(this,"不存在！");
        }
    }

    @Override
    public void deletePerson() {
        int id=Integer.valueOf(delIdText.getText());
        boolean deleteSuccess= personService.deleteBook(id);
        if(deleteSuccess){
            getList();
        }else{
            JOptionPane.showMessageDialog(this,"不存在！");
        }
    }

}
