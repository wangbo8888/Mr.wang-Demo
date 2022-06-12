package com.example.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * @package: com.shuyu.blog.util
 * @className: RedisUtil
 * @description:
 * @author: Shuyu.Wang
 * @date: 2019-07-14 14:42
 * @since: 0.1
 **/
@Component
@Slf4j
public class RedisUtil {
 
private static final Long SUCCESS = 1L;
 
@Resource
private RedisTemplate<String, Object> redisTemplate;
// =============================common============================
 
  
 
/**
 * 获取锁
 * @param lockKey
 * @param value
 * @param expireTime：单位-秒
 * @return
 */
public boolean getLock(String lockKey, Object value, int expireTime) {
try {
log.info("添加分布式锁key={},expireTime={}",lockKey,expireTime);
String script = "if redis.call('setNx',KEYS[1],ARGV[1]) then if redis.call('get',KEYS[1])==ARGV[1] then return redis.call('expire',KEYS[1],ARGV[2]) else return 0 end end";
RedisScript<String> redisScript = new DefaultRedisScript<>(script, String.class);
Object result = redisTemplate.execute(redisScript, Collections.singletonList(lockKey), value, expireTime);
if (SUCCESS.equals(result)) {
return true;
}
} catch (Exception e) {
e.printStackTrace();
}
return false;
}
 
/**
 * 释放锁
 * @param lockKey
 * @param value
 * @return
 */
public boolean releaseLock(String lockKey, String value) {
String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
RedisScript<String> redisScript = new DefaultRedisScript<>(script, String.class);
Object result = redisTemplate.execute(redisScript, Collections.singletonList(lockKey), value);
if (SUCCESS.equals(result)) {
return true;
}
return false;
}
 
}