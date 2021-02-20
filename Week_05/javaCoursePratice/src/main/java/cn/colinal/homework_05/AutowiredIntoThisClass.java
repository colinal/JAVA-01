package cn.colinal.homework_05;

import cn.colinal.myStarter.schoolwork.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component
public class AutowiredIntoThisClass {
    @Autowired
    private XmlBeanAutowiredEntity1 xmlBeanAutowiredEntity1;
    @Autowired
    private XmlBeanAutowiredEntity2 xmlBeanAutowiredEntity2;
    @Autowired
    private XmlBeanAutowiredEntity4 xmlBeanAutowiredEntity4;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private Student student;

    @PostConstruct
    public void printAllEntityName() {
        System.out.println("======================Print Start===================");
        // 1. 通过xml配置注入，@Autowired获取
        System.out.println("firstBean name is :" + xmlBeanAutowiredEntity1.getEntityName());
        // 2. 通过@Value和@Autowired获取
        System.out.println("secondBean name is :" + xmlBeanAutowiredEntity2.getEntityName());
        // 3. 使用applicationContext获取
        XmlBeanAutowiredEntity3 thirdBean = (XmlBeanAutowiredEntity3) applicationContext.getBean("thirdBean");
        System.out.println("thirdBean name is :" + thirdBean.getEntityName());
        // 4. 使用BeanPostProcessor
        System.out.println("fourthBean name is :" + xmlBeanAutowiredEntity4.getEntityName());

        // 5. 获取student
        System.out.println("student is :" + student);

        // 6. 数据库
        // 加载MySQL驱动
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String password = "1234567";
            String user = "jangle";
            String url = "jdbc:mysql://mysql.jangle.xyz:3090/demon?characterEncoding=UTF8";
            Connection connection = DriverManager.getConnection(url, user, password);
            String sql ="select * from bs_test where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, 10);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println("id:"+resultSet.getLong("id"));
            }
        }catch (Exception e){
            System.out.println("error ");
        }


        System.out.println("======================Print End===================");
    }
}
