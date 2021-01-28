package com.example.one.aop;

import com.alibaba.fastjson.JSON;
import com.example.one.config.ResisConfig;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisSentinelPool;

import java.util.Map;

@Component
@Aspect
public class CashAOP {
    @Autowired(required = false)
    private JedisCluster jedis;           //  集群
//    private JedisSentinelPool jedisSentinelPool;   // 哨兵
//    private ResisConfig redisConfig;             // 单个redis
    @Around("@annotation(cashFind)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, CashFind cashFind) {
//        Jedis jedis = jedisSentinelPool.getResource();     // 哨兵
//        Jedis jedis = redisConfig.jedis();               // 单个redis
        String key = getKey(proceedingJoinPoint, cashFind);
        Object obj = null;
        if(jedis.get(key) == null) {
            try{
                obj = proceedingJoinPoint.proceed();
                if(cashFind.seconds()>0) {
                    jedis.setex(key, cashFind.seconds(), JSON.toJSONString(obj));
                }else{
                    jedis.set(key, JSON.toJSONString(obj));
                }
                System.err.println("数据库");
//                      // 哨兵
                return obj;
            }catch(Throwable e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }else{
            String value = jedis.get(key);
            Object objs = JSON.parse(value);
            System.err.println("redis");
//              // 哨兵
            return objs;
        }
    }

    private String getKey(ProceedingJoinPoint proceedingJoinPoint, CashFind cashFind) {
        if(!"".equals(cashFind.key())){   // 自定义注解传了key参数
            return cashFind.key();
        }
        //没传key参数，就自己拼key这个参数
        String className = proceedingJoinPoint.getSignature().getDeclaringTypeName(); // 类名
        String methodName = proceedingJoinPoint.getSignature().getName();   // 方法名
        String key = className+"."+methodName;
        Map<String, Object> maps = (Map<String, Object>) proceedingJoinPoint.getArgs()[0];
        for(String itemKey : maps.keySet()){
            key += "::" + maps.get(itemKey);
        }
        System.err.println("56: "+ key);
        return key;
    }
    
    
    /**
     * 使用者传键删除redis里面的数据
     * @param proceedingJoinPoint
     * @param cashDelete
     * @return
     */
    @Around("@annotation(cashDelete)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, CashDelete cashDelete) {
        String key = cashDelete.key();
        Collection<JedisPool> jedisPools = jedis.getClusterNodes().values();
        for (JedisPool pool : jedisPools) {
            Jedis jedis1 =  pool.getResource();
             Set<String> set = jedis1.keys(key+"*");
            for (String item: set) {
                jedis.del(item);
            }
        }
        try{
            Object o = proceedingJoinPoint.proceed();
        }catch(Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return null;
    }
}




//   存的时候用  @CashFind   在查询的时候用
//   删的时候用  @CashDelete("com.example.one.service.impl.AOPRedisServiceImpl.queryUserRedis")   在增删改的时候用




