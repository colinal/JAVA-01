package cn.colinal.myStarter;

import cn.colinal.myStarter.schoolwork.Student;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(StudentProperties.class)
@ConditionalOnProperty(prefix = "cn.colinal", name = "enable", havingValue = "true")
public class MyStarterAutoConfiguration {

  @Bean
  public Student defaultStudent(StudentProperties studentProperties) {
    Student student = new Student();
    student.setId(studentProperties.getId());
    student.setName(studentProperties.getName());
    student.setClassName(studentProperties.getClassName());
    return student;
  }
}