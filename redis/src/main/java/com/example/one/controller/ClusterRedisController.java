package com.example.one.controller;

import com.example.one.returnData.ReturnData;
import com.example.one.service.ClusterRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ClusterRedisController {
    @Autowired
    private ClusterRedisService clusterRedisService;
    @PostMapping("/queryUsersList")
    public ReturnData queryUsersList(@RequestBody Map<String, Object> params) {
        List<Map<String, Object>> list = clusterRedisService.queryUsersList(params);
        return new ReturnData(list);
    }
}
