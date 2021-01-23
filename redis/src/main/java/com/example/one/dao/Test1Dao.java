package com.example.one.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface Test1Dao {

    List<Map<String, Object>> queryUser(Integer age);
}
