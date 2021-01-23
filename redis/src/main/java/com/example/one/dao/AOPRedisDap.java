package com.example.one.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AOPRedisDap {
    List<Map<String, Object>> queryUserRedis(Map<String, Object> params);
}
