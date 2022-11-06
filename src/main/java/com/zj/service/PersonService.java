package com.zj.service;

import com.zj.config.DBConfig;

import com.zj.domain.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonService {

    // 创建AdminDao实例化对象
    private PersonDao PersonDao =new PersonDao();

    // 实现查询逻辑处理
    public List<Person> queryAll(){
        List data= PersonDao.queryAll();
        return data;
    }

    // 实现添加逻辑处理
    public boolean addBook(int id, String name, String site, String phone){
        // 遍历数据，判断要插入的水果编号是否存在
        List<Person> data=queryAll();
        for(int i=0;i<data.size();i++){
            Person person=data.get(i);
            if(id==person.getId()){
                return false;
            }
        }
        Person person=new Person(id,name,site,phone);
        PersonDao.addPerson(person);
        return true;
    }

    /**
     * BookDao中没有定义修改操作的方法，我们这个小项目中不是通过执行修改SQL语句来实现数据修改的，
     * 而是通过删除要修改的数据后，再添加新的数据的方式来实现数据修改的
     * @return
     */
    public boolean updateBook(int id, String name, String site,String phone) {
        // 遍历数据，判断要插入的水果编号是否存在
        List<Person> data=queryAll();
        for(int i=0;i<data.size();i++){
            Person person=data.get(i);
            if(id==person.getId()){
                // 如果该图书存在则删除
                PersonDao.deletePerson(id);
                // 添加修改内容作为新的图书内容
                Person thisPerson=new Person(id,name,site,phone);
                PersonDao.addPerson(thisPerson);
                /**
                 * 这里不要使用BookService类中的deleteBook()和addBook()方法来删除添加图书，
                 * 因为这两个方法都要遍历一次数据，如果数据量很大的话就会消耗很多时间，
                 * 所以应如上面三行代码这样，直接调用BookDao类中的方法来实现图书的删除添加
                 */
//                deleteBook(id);
//                addBook(id,name,price,number);
                return true;
            }
        }
        return false;
    }

    public boolean deleteBook(int id) {
        // 遍历数据，判断要插入的水果编号是否存在
        List<Person> data=queryAll();
        for(int i=0;i<data.size();i++){
            Person person=data.get(i);
            if(id==person.getId()){
                PersonDao.deletePerson(id);
                return true;
            }
        }
        return false;
    }


}

class PersonDao {

    /**
     * 查询所有联系人信息，返回一个集合
     * @return
     */
    public List<Person> queryAll(){
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        ArrayList<Person> list=new ArrayList<>();
        try{
            connection= DBConfig.getConnection();
            statement=connection.createStatement();
            String sql="select*from person order by id";
            resultSet=statement.executeQuery(sql);
            while(resultSet.next()){
                Person person=new Person();
                // 将获取结果集的信息封装到Book实体类中
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setSite(resultSet.getString("site"));
                person.setPhone(resultSet.getString("phone"));

                // 将实体类中的信息封装到集合中
                list.add(person);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBConfig.close(resultSet,statement,connection);
        }
        return list;
    }

    /**
     * addBook()方法
     * 以实体类Book作为参数类型，实参封装了装备添加到数据库中的图书信息
     *
     * 使用PreparedStatement对象执行插入操作，防止SQL语句注入
     * @return
     */
    public void addPerson(Person person){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try{
            connection=DBConfig.getConnection();
            String sql="insert into person(id,name,site,phone)values(?,?,?,?)";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,person.getId());
            preparedStatement.setString(2,person.getName());
            preparedStatement.setString(3,person.getSite());
            preparedStatement.setString(4,person.getPhone());

            int n=preparedStatement.executeUpdate();
            if(n>0){
                System.out.println("插入通讯人成功！");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBConfig.close(preparedStatement,connection);
        }
    }

    /**
     * deleteBook方法
     * 删除记录deleteBook()方法中的形参类型为String类型的id,也可改为实体类Book作为形参类型
     *
     * 使用PreparedStatement对象执行删除操作，防止SQL语句注入
     * @return
     */
    public void deletePerson(int id){
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try{
            connection=DBConfig.getConnection();
            String sql="delete from person where id=?";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            int num=preparedStatement.executeUpdate();
            if(num>0){
                System.out.println("删除联系人成功！");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBConfig.close(preparedStatement,connection);
        }
    }
}

