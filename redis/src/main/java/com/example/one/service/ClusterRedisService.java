package com.example.one.service;

import java.util.List;
import java.util.Map;

public interface ClusterRedisService {
    List<Map<String, Object>> queryUsersList(Map<String, Object> params);
}
