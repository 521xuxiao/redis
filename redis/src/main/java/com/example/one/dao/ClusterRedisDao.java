package com.example.one.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ClusterRedisDao {
    List<Map<String, Object>> queryUsersList(Map<String, Object> params);
}
