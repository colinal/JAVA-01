package com.baomidou.samples.localtx.controller;

import com.baomidou.samples.localtx.dto.PlaceOrderRequest;
import com.baomidou.samples.localtx.entity.User;
import com.baomidou.samples.localtx.mapper.UserMapper;
import com.baomidou.samples.localtx.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserMapper userMapper;

    @PostMapping("/db1")
    @ApiOperation("读取主库")
    public String testDb1() {
        User sel = userMapper.Sel(1);
        System.out.println("db1:" + sel);
        return "SUCCESS";
    }

    @PostMapping("/db2")
    @ApiOperation("读取从库")
    public String testDb2() {
        User sel = userMapper.SelDb2(1);
        System.out.println("db2:" + sel);
        return "SUCCESS";
    }
}