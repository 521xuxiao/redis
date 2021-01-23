package com.example.one.controller;

import com.example.one.returnData.ReturnData;
import com.example.one.service.AOPRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
public class AOPRedisController {
    @Autowired
    private AOPRedisService aopRedisService;

    @PostMapping("/queryUserRedis")
    public ReturnData queryUserRedis(@RequestBody Map<String, Object> params, HttpServletRequest request) {
        List<Map<String, Object>> list = aopRedisService.queryUserRedis(params, request);
        return new ReturnData(list);
    }
}
