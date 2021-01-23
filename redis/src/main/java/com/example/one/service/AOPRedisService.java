package com.example.one.service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface AOPRedisService {
    List<Map<String, Object>> queryUserRedis(Map<String, Object> params, HttpServletRequest request);
}
