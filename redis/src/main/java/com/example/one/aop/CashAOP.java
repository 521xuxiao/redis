package com.example.one.aop;

import com.alibaba.fastjson.JSON;
import com.example.one.config.ResisConfig;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CashAOP {
    @Autowired
    private ResisConfig redisConfig;
    @Around("@annotation(cashFind)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, CashFind cashFind) {
        String key = getKey(proceedingJoinPoint, cashFind);
        Object obj = null;
        if(redisConfig.jedis().get(key) == null) {
            try{
                obj = proceedingJoinPoint.proceed();
                if(cashFind.seconds()>0) {
                    redisConfig.jedis().setex(key, cashFind.seconds(), JSON.toJSONString(obj));
                }else{
                    redisConfig.jedis().set(key, JSON.toJSONString(obj));
                }
                System.err.println("数据库");
                return obj;
            }catch(Throwable e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }else{
            String value = redisConfig.jedis().get(key);
            Object objs = JSON.parse(value);
            System.err.println("redis");
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
        String key = className+","+methodName;
        Object[] arr = proceedingJoinPoint.getArgs();
        for (Object item:arr) {
            key += item+"::";
        }
        return key;
    }
}
