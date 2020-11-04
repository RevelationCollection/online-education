package com.study.edu.controller;

import com.study.edu.common.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.PublicKey;

@CrossOrigin
@RestController
@RequestMapping("user")
@Api("用户登录")
public class LoginController {

    @ApiOperation("登录")
    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }

    @ApiOperation("获取用户信息")
    @RequestMapping("info")
    public R info(){
        return R.ok()
                .data("roles","[admin]")
                .data("name","admin")
                .data("avatar","https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
    }

    @ApiOperation("退出")
    @PostMapping("logout")
    public R logout(){
        return R.ok();
    }
}
