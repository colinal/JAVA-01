package cn.colinal.homework_07;

import cn.colinal.homework_07.entity.User;
import cn.colinal.homework_07.mapper.UserMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
public class TestController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/hello")
    public String hello() {
        User sel = userMapper.Sel(1);
        User sel2 = userMapper.SelDb2(1);
        System.out.println("Db1:"+sel);
        System.out.println("Db2:"+sel2);
        return "Hello Spring Boot!";
    }
}
