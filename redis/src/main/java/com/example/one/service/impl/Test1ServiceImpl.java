package com.example.one.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.one.config.ResisConfig;
import com.example.one.dao.Test1Dao;
import com.example.one.service.Test1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class Test1ServiceImpl implements Test1Service {
    @Autowired
    private Test1Dao test1Dao;
    @Autowired(required = false)
    private ResisConfig redisConfig;

    /**
     * 单台redis测试   key为包名类名方法名：：参数
     * @param age
     * @return
     */
    @Override
    public List<Map<String, Object>> queryUser(Integer age) {
//        String key = "com.example.one.service.impl.Test1ServiceImpl.queryUser::"+age;
//        Jedis jedis = redisConfig.jedis();
//        String value = jedis.get(key);
//        List<Map<String, Object>> list = new ArrayList<>();
//        if(value==null || "".equals(value)) {
//            list = test1Dao.queryUser(age);
//            jedis.set(key, JSON.toJSONString(list));
//            System.err.println("数据库里面查询的数据");
//        }else{
//            String listString = jedis.get(key);
//            Object obj = JSON.parse(listString);
//            list = (List<Map<String, Object>>) obj;
//            System.err.println("redis缓存");
//        }
//        return list;
        return null;
    }
}
