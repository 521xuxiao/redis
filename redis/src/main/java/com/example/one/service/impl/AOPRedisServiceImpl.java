package com.example.one.service.impl;

import com.example.one.aop.CashFind;
import com.example.one.dao.AOPRedisDap;
import com.example.one.service.AOPRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class AOPRedisServiceImpl implements AOPRedisService {
    @Autowired
    private AOPRedisDap aopRedisDap;

    @Override
    @CashFind(seconds = 1)
    public List<Map<String, Object>> queryUserRedis(Map<String, Object> params, HttpServletRequest request) {
        return aopRedisDap.queryUserRedis(params);
    }
}
