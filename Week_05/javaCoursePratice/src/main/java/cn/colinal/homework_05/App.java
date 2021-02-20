package cn.colinal.homework_05;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = {"classpath:config/application-bean.xml"})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
