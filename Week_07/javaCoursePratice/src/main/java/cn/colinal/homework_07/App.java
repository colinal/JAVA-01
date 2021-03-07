package cn.colinal.homework_07;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@MapperScan("cn.colinal.homework_07.mapper")
public class App {
    public static void main(String[] args) {
        String addClassPath = "spring.config.additional-location:classpath:/";
        addClassPath += "config/homework_07/application.yml";
        new SpringApplicationBuilder(App.class).properties("spring.config.name:application", addClassPath).build().run(args);
    }
}
