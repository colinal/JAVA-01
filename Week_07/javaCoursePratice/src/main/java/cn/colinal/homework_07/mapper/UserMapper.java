package cn.colinal.homework_07.mapper;

import cn.colinal.homework_07.entity.User;
import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User Sel(int id);
    User SelDb2(int id);
}