package com.example.one.service.impl;

import com.example.one.dao.ClusterRedisDao;
import com.example.one.service.ClusterRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClusterRedisServiceImpl implements ClusterRedisService {
    @Autowired
    private ClusterRedisDao clusterRedisDao;

    @Override
    public List<Map<String, Object>> queryUsersList(Map<String, Object> params) {
        return clusterRedisDao.queryUsersList(params);
    }
}
