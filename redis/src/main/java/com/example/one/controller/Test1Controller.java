package com.example.one.controller;

import com.example.one.returnData.ReturnData;
import com.example.one.service.Test1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping("/test1")
public class Test1Controller {
    @Autowired
    private Test1Service test1Service;


    /**
     * springBoot整合redis,传入年龄查询数据 (缓存)
     */
    @GetMapping("/queryUser")
    public ReturnData queryUser(Integer age) {
        List<Map<String, Object>> list = test1Service.queryUser(age);
        return new ReturnData(list);
    }

}
