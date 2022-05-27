package com.example.demo;

import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisClusterNode;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Service
public class RedisTemplateScan {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

            public  Set<String> scanMatch(String matchKey) {
            Set<String> keys = new HashSet();
            RedisConnectionFactory connectionFactory = stringRedisTemplate.getConnectionFactory();
            RedisClusterConnection clusterConnection = connectionFactory.getClusterConnection();
                Iterable<org.springframework.data.redis.connection.RedisClusterNode> redisClusterNodes = clusterConnection.clusterGetNodes();
                Iterator<org.springframework.data.redis.connection.RedisClusterNode> iterator = redisClusterNodes.iterator();
                while (iterator.hasNext()) {
                    RedisClusterNode next = iterator.next();
                    Cursor<byte[]> scan = clusterConnection.scan(next, ScanOptions.scanOptions().match(matchKey).count(10000000).build());
                while (scan.hasNext()) {
                    keys.add(new String(scan.next()));
                }
            }
            return keys;
        }
    }


